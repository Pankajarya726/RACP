package com.tekzee.racp.ui.Form.bakari_vitran;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.constant.GlideModuleConstant;
import com.tekzee.racp.databinding.FormBakriVitranBinding;
import com.tekzee.racp.sqlite.SqliteDB;
import com.tekzee.racp.ui.Form.bakari_vitran.model.BakriData;
import com.tekzee.racp.ui.Form.bakari_vitran.model.RetrivedDataResponse;
import com.tekzee.racp.ui.Form.bakari_vitran.model.TeekaData;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;
import com.tekzee.racp.ui.ImagePickerActivity;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpMapActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.CalenderUtils;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BakriVitranActivity extends MvpMapActivity <BakriVitranPresenter> implements BakariVitranView, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private static String tag = BakriVitranActivity.class.getCanonicalName();
    private static String TAG = BakriVitranActivity.class.getCanonicalName();
    byte[] imageBytes;
    JsonArray marray = new JsonArray();
    List <TeekaData> dataList = new ArrayList <>();
    List <Immunization> immunizations = new ArrayList <>();
    List <BakriData> bakriDataList = new ArrayList <>();
    boolean isOkayClicked = true;
    com.tekzee.racp.ui.Form.bakari_vitran.TeekaAdapter teekaAdapter;
    private InputStream imageInputStream = null;
    private String image_path = "";
    private int benifeciery_id = 0;
    private FormBakriVitranBinding binding;
    private SqliteDB sqliteDB = new SqliteDB(this);
    private int recordNo = 1;
    private int record_count = 1;
    private int table_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_bakri_vitran);
        getSupportActionBar().setTitle(R.string.form_3);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // sqliteDB.clearBakariDetail();


        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowDialog();
        }


        SpinnerData();
        recordNo = sqliteDB.countBakriDetail() + 1;
        record_count = recordNo;

        dataList.clear();
        immunizations.clear();
        bakriDataList.clear();

        BakriData.deleteAll(BakriData.class);
        TeekaData.deleteAll(TeekaData.class);

        binding.todayDate.setText(mDatePickerDialog.showDate());
        binding.txtno.setText(String.valueOf(recordNo));
        binding.edtProofdate.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.teekaDate.setOnClickListener(this);
        binding.spBenifecieryType.setOnClickListener(this);
        binding.checkNo.setOnClickListener(this);
        binding.checkYes.setOnClickListener(this);
        binding.edtDateCrimNashak.setOnClickListener(this);
        binding.ivBakari.setOnClickListener(this);

    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 3);

        mvpPresenter.getFormRecordData(jsonObject);
    }

    @Override
    protected BakriVitranPresenter createPresenter() {
        return new BakriVitranPresenter(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //startActivity(new Intent(ForSale.this, HomeActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        this.finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.check_yes:
                if (binding.checkYes.isChecked()) {
                    if (binding.checkNo.isChecked()) {
                        binding.checkNo.setChecked(false);
                    }
                    binding.checkYes.setChecked(true);
                    binding.layoutCriminashak.setVisibility(View.VISIBLE);

                } else {
                    binding.checkYes.setChecked(false);
                    binding.layoutCriminashak.setVisibility(View.GONE);
                }
                break;


            case R.id.check_no:
                if (binding.checkNo.isChecked()) {
                    if (binding.checkYes.isChecked()) {
                        binding.checkYes.setChecked(false);
                    }
                    binding.checkNo.setChecked(true);
                    binding.layoutCriminashak.setVisibility(View.GONE);
                } else {
                    binding.checkNo.setChecked(false);
                    binding.layoutCriminashak.setVisibility(View.GONE);
                }
                break;


            case R.id.sp_benifeciery_type:
                mvpPresenter.getBenifeciery_type();
                break;

            case R.id.edt_proofdate:
                mDatePickerDialog.getdate(BakriVitranActivity.this, binding.edtProofdate);
                break;

            case R.id.edt_date_crim_nashak:
                mDatePickerDialog.getdate(BakriVitranActivity.this, binding.edtDateCrimNashak);
                break;

            case R.id.tv_addRecord:
                addRecordinSqlite();
                break;
            case R.id.privious:
                try {
                    privious();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.next:
                try {
                    next();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.teeka_date:
                DialogFragment datePicker = new mDatePickerDialog();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;

            case R.id.tv_save:
                try {
                    submitRecord();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.iv_bakari:
                Dexter.withActivity(getContext())
                        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {

                                    Intent intent = new Intent(getContext(), ImagePickerActivity.class);
                                    intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);
                                    // setting aspect ratio
                                    intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
                                    intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
                                    intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
                                    // setting maximum bitmap width and height
                                    intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
                                    intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
                                    intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);
                                    startActivityForResult(intent, Constant.IMAGE_REQUEST);
                                }

                                if (report.isAnyPermissionPermanentlyDenied()) {
                                    showSettingsDialog();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List <PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();

                break;
        }

    }


    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.IMAGE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    if (uri != null) {

                        image_path = String.valueOf(uri);
//                        String fileLocation = Utility.getFileName(getAppContext(), uri);
//                        Log.view(TAG,"fileLocation: "+fileLocation);
//                        geoTag(uri.toString(), mLocation.getLatitude(), mLocation.getLongitude());

                        Glide.with(getContext()).load(image_path).into(binding.ivBakari);
                        try {
                            imageInputStream = getContentResolver().openInputStream(uri);

                            com.tekzee.racp.utils.Log.view(TAG, "uri = = " + uri);
                            com.tekzee.racp.utils.Log.view(TAG, "imageInputStream = = " + imageInputStream);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        imageBytes = Utility.getBytes(imageInputStream);
                        // mvpPresenter.uploadDocument(imageBytes);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void loadList(String format, String name) {
        immunizations.add(new Immunization(name, format));
        Immunization immunization = new Immunization(name, format);
        immunization.save();

        setupRecyclerView();

    }

    private void setupRecyclerView() {

        binding.recyclerImmunization.setLayoutManager(new LinearLayoutManager(this));

        teekaAdapter = new TeekaAdapter(getContext(), immunizations, this);

        binding.recyclerImmunization.setAdapter(teekaAdapter);
        teekaAdapter.notifyDataSetChanged();


    }

    private boolean addRecordinSqlite() {
        if (binding.edtTagNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno));
            return false;
        } else if (binding.edtAveragProduction.getText().toString().isEmpty()) {

            Dialogs.showColorDialog(getContext(), getString(R.string.enter_average_milk_production));
            return false;
        } else if (binding.edtProofdate.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_physical_proof_date));
            return false;

        } else if (binding.spBenifecieryType.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_labharthee_shreni));
            return false;
        } else if (binding.checkYes.isChecked() && binding.edtDateCrimNashak.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_criminashak_date));
            return false;
        }else if (imageBytes==null){
            Dialogs.showColorDialog(getContext(),getString(R.string.select_bakari_image));
            return false;
        }
        else {

            String day;
            if (binding.day.getText().toString().isEmpty()) {
                day = "01";

            } else {
                day = binding.day.getText().toString();
                if (!mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),
                        binding.spMonth.getSelectedItemPosition() + 1,
                        Integer.valueOf(binding.spYear.getSelectedItem().toString()))) {

                    //mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),binding.spMonth.getSelectedItemPosition()+1,Integer.valueOf(binding.spYear.getSelectedItem().toString()));
                    Dialogs.showColorDialog(getContext(), getString(R.string.invalid_Date));
                    // Toast.makeText(this, getString(R.string.invalid_Date), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < immunizations.size(); i++) {
                Immunization im = immunizations.get(i);

                JsonObject object = new JsonObject();
                object.addProperty("teeka_type", im.getTeekaname());
                object.addProperty("teeka_date", im.getTeekadate());
                jsonArray.add(object);


            }

            TeekaData teekaData = new TeekaData(jsonArray.toString());
            teekaData.save();

          /*  BakriData data = new BakriData(day,
                    String.valueOf(binding.spMonth.getSelectedItemPosition()),
                    binding.spYear.getSelectedItem().toString(),
                    binding.edtTagNo.getText().toString(),
                    binding.spBenifecieryType.getText().toString(),
                    binding.edtPolicyNo.getText().toString(),
                    binding.edtAveragProduction.getText().toString(),
                    binding.edtProofdate.getText().toString(),
                    binding.edtNote.getText().toString());
            data.save();*/

            BakriData data = new BakriData(day,
                    binding.spMonth.getSelectedItem().toString(),
                    binding.spYear.getSelectedItem().toString(),
                    binding.spMonth.getSelectedItemPosition(),
                    binding.spYear.getSelectedItemPosition(),
                    binding.edtTagNo.getText().toString(),
                    binding.spBenifecieryType.getText().toString(),
                    binding.edtAveragProduction.getText().toString(),
                    binding.edtProofdate.getText().toString(),
                    image_path,
                    binding.checkYes.isChecked(),
                    binding.edtDateCrimNashak.getText().toString(),
                    binding.edtNote.getText().toString()
                    );
            data.save();

            Log.e(tag, "record count = " + BakriData.count(BakriData.class));
            Log.e(tag, "json array" + marray.toString());

            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));
            clearallField();
            binding.privious.setVisibility(View.VISIBLE);
            immunizations.clear();
            setupRecyclerView();
            record_count = record_count + 1;
            return true;
        }
    }

    private void next() throws JSONException {
        binding.privious.setVisibility(View.VISIBLE);
        record_count = record_count + 1;
        if (record_count == recordNo) {
            binding.next.setVisibility(View.GONE);
            binding.tvAddRecord.setVisibility(View.VISIBLE);
            binding.tvSave.setVisibility(View.VISIBLE);
        }
        binding.txtno.setText(String.valueOf(record_count));


        dataList.clear();
        immunizations.clear();
        bakriDataList.clear();
        bakriDataList = BakriData.listAll(BakriData.class);
        dataList = TeekaData.listAll(TeekaData.class);

        if (record_count <= BakriData.count(BakriData.class)) {

            BakriData bakriData = bakriDataList.get(record_count - 1);
            TeekaData teekaData = dataList.get(record_count - 1);

            binding.day.setText(bakriData.getDd());
            binding.spMonth.setSelection(bakriData.getMm_id());
            binding.spYear.setSelection(bakriData.getYy_id());
            binding.edtTagNo.setText(bakriData.getTag_no());
            binding.spBenifecieryType.setText(bakriData.getCategory());
           // binding.edtPolicyNo.setText(bakriData.getPolicy_no());
            binding.edtAveragProduction.setText(bakriData.getAverage());
            binding.edtProofdate.setText(bakriData.getPhucal_proof_date());
            binding.edtNote.setText(bakriData.getNote());
            if (bakriData.isCriminashak()){
                binding.checkYes.setChecked(true);
                binding.layoutCriminashak.setVisibility(View.VISIBLE);
                binding.edtDateCrimNashak.setText(bakriData.getCriminashak_date());

            }
            GlideModuleConstant.setImage(binding.ivBakari,getContext(),bakriData.getImage_path());






            String data = teekaData.getData();
            JSONArray jsonArray = new JSONArray(String.valueOf(data));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                immunizations.add(new Immunization(obj.getString("teeka_type"), obj.getString("teeka_date")));
            }

            setupRecyclerView();
            DisableField();

        } else {
            clearallField();
            setupRecyclerView();

            binding.immunization.setVisibility(View.VISIBLE);
        }
    }

    private void privious() throws JSONException {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }

        binding.txtno.setText(String.valueOf(record_count));

        dataList.clear();
        immunizations.clear();
        bakriDataList.clear();
        bakriDataList = BakriData.listAll(BakriData.class);
        dataList = TeekaData.listAll(TeekaData.class);

        BakriData bakriData = bakriDataList.get(record_count - 1);
        TeekaData teekaData = dataList.get(record_count - 1);

      /*  binding.day.setText(bakriData.getDd());
        binding.spMonth.setSelection(Integer.valueOf(bakriData.getMm()) - 1);
        binding.edtTagNo.setText(bakriData.getTag_no());
        binding.spBenifecieryType.setText(bakriData.getBeema_detail());
        binding.edtPolicyNo.setText(bakriData.getPolicy_no());
        binding.edtAveragProduction.setText(bakriData.getAverage());
        binding.edtProofdate.setText(bakriData.getPhucal_proof_date());
        binding.edtNote.setText(bakriData.getNote());
        String data = teekaData.getData();
        JSONArray jsonArray = new JSONArray(String.valueOf(data));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            immunizations.add(new Immunization(obj.getString("teeka_type"), obj.getString("teeka_date")));
        }

        setupRecyclerView();*/
        binding.day.setText(bakriData.getDd());
        binding.spMonth.setSelection(bakriData.getMm_id());
        binding.spYear.setSelection(bakriData.getYy_id());
        binding.edtTagNo.setText(bakriData.getTag_no());
        binding.spBenifecieryType.setText(bakriData.getCategory());
        // binding.edtPolicyNo.setText(bakriData.getPolicy_no());
        binding.edtAveragProduction.setText(bakriData.getAverage());
        binding.edtProofdate.setText(bakriData.getPhucal_proof_date());
        binding.edtNote.setText(bakriData.getNote());
        if (bakriData.isCriminashak()){
            binding.checkYes.setChecked(true);
            binding.layoutCriminashak.setVisibility(View.VISIBLE);
            binding.edtDateCrimNashak.setText(bakriData.getCriminashak_date());

        }
        GlideModuleConstant.setImage(binding.ivBakari,getContext(),bakriData.getImage_path());






        String data = teekaData.getData();
        JSONArray jsonArray = new JSONArray(String.valueOf(data));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            immunizations.add(new Immunization(obj.getString("teeka_type"), obj.getString("teeka_date")));
        }

        setupRecyclerView();
        DisableField();
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);
        binding.immunization.setVisibility(View.GONE);

    }

    private void submitRecord() throws JSONException {

        if (addRecordinSqlite()) {

            dataList.clear();
            bakriDataList.clear();
            JsonArray jsonArray = new JsonArray();
            bakriDataList = BakriData.listAll(BakriData.class);
            dataList = TeekaData.listAll(TeekaData.class);

            for (int i = 0; i < bakriDataList.size(); i++) {
                BakriData data = bakriDataList.get(i);
                TeekaData data1 = dataList.get(i);

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("tag_no", data.getTag_no());
//                jsonObject.addProperty("beema_detail", data.getBeema_detail());
//                jsonObject.addProperty("policy_no", data.getPolicy_no());
//                jsonObject.addProperty("physical_proof_date", mDatePickerDialog.changeFormate(data.getPhucal_proof_date()));
                jsonObject.addProperty("average_milk_production", data.getAverage());
                jsonObject.addProperty("note", data.getNote());
                jsonObject.addProperty("dd", data.getDd());
                jsonObject.addProperty("mm", data.getMm());
                jsonObject.addProperty("yy", data.getYy());

                JsonArray marray = new JsonArray();

                String mdata = data1.getData();
                JSONArray array = new JSONArray(String.valueOf(mdata));
                for (int j = 0; j < array.length(); j++) {
                    JSONObject obj = array.getJSONObject(j);
                    JsonObject jsonObject1 = new JsonObject();
                    jsonObject1.addProperty("teeka_type", obj.getString("teeka_type"));
                    jsonObject1.addProperty("teeka_date", mDatePickerDialog.changeFormate(obj.getString("teeka_date")));
                    marray.add(jsonObject1);

                }
                jsonObject.add("teekakaran", marray);
                jsonArray.add(jsonObject);
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("user_id", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
            jsonObject.addProperty("form_id", 3);
            jsonObject.addProperty("status_receipt", 1);
            jsonObject.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_member_id));
            jsonObject.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_group_id));
            jsonObject.add("data", jsonArray);
            Log.e(tag, "final json = " + jsonObject.toString());
            mvpPresenter.saveForm(jsonObject);
        }

    }

    private void ShowDialog() {

        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.bakari_availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {
                finish();
            }
        });

        /*ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);

        dialog.setTitle(getResources().getString(R.string.form_3));
        dialog.setColor("#FF6500");
        dialog.setContentText(R.string.availornot);
        dialog.setPositiveListener(getText(R.string.yes), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {

                dialog.dismiss();
            }
        })
                .setNegativeListener(getText(R.string.no), new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {

                        dialog.dismiss();

                        finish();
                    }
                }).show();*/
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = dateFormatter.format(c.getTime());

        loadList(currentDateString, binding.spTeeka.getSelectedItem().toString());
        binding.teekaDate.setText("");
    }

    @Override
    public Activity getContext() {
        return this;
    }


    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.showColorDialog(getContext(), commonResult.getMessage());

    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), this, "  ");

        BakriData.deleteAll(BakriData.class);
        TeekaData.deleteAll(TeekaData.class);
        record_count = 1;
        recordNo = 1;
        binding.txtno.setText(String.valueOf(recordNo));


        clearallField();
        binding.privious.setVisibility(View.VISIBLE);


        immunizations.clear();

        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);
        setupRecyclerView();
    }

    @Override
    public void onDateSelected(int adapterPosition, List <Immunization> immunizations, TextView tv_date) {

        Immunization im = immunizations.get(adapterPosition);

        mDatePickerDialog.getdate(getContext(), tv_date);

        im.setTeekadate(tv_date.getText().toString());

    }

    @Override
    public void onSuccessfullyRetrived(RetrivedDataResponse successResult) {

        binding.receiptDate.setVisibility(View.VISIBLE);

        binding.edtTagNo.setEnabled(false);
        binding.edtNote.setEnabled(false);
        binding.spBenifecieryType.setEnabled(false);
        binding.edtPolicyNo.setEnabled(false);
        binding.edtProofdate.setClickable(false);
        binding.edtAveragProduction.setEnabled(false);

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);
        binding.immunization.setVisibility(View.GONE);
        binding.recyclerImmunization.setClickable(false);


        if (!String.valueOf(successResult.getData().getNote()).equalsIgnoreCase("null")) {
            binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        }
        binding.edtTagNo.setText(String.valueOf(successResult.getData().getTagNo()));
        binding.receiptDate.setText(mDatePickerDialog.changeFormate(String.valueOf(successResult.getData().getDateReceipt())));


        binding.spBenifecieryType.setText(String.valueOf(successResult.getData().getBeemaDetail()));


        binding.edtPolicyNo.setText(String.valueOf(successResult.getData().getPolicyNo()));

        binding.edtProofdate.setText(mDatePickerDialog.changeFormate(String.valueOf(successResult.getData().getDatePhysicalProof())));


        binding.edtAveragProduction.setText(String.valueOf(successResult.getData().getAverageMilkProduction()));


        String ppr = successResult.getData().getDatePpr();
        String et = successResult.getData().getDateEt();
        String fmd = successResult.getData().getDateFmd();
        String hs = successResult.getData().getDateHs();

        String[] date = ppr.split(",");
        for (int i = 0; i < date.length; i++) {
            if (!date[i].isEmpty() && date[i] != null && !date[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.ppr), mDatePickerDialog.changeFormate(date[i])));
            }

        }
        String[] date1 = et.split(",");
        for (int i = 0; i < date1.length; i++) {
            if (!date1[i].isEmpty() && date1[i] != null && !date1[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.et), mDatePickerDialog.changeFormate(date1[i])));
            }
        }

        String[] date2 = fmd.split(",");
        for (int i = 0; i < date2.length; i++) {
            if (!date2[i].isEmpty() && date2[i] != null && !date2[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.fmd), mDatePickerDialog.changeFormate(date2[i])));
            }
        }

        String[] date3 = hs.split(",");
        for (int i = 0; i < date3.length; i++) {
            if (!date3[i].isEmpty() && date3[i] != null && !date3[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.hs), mDatePickerDialog.changeFormate(date3[i])));
            }
        }
        setupRecyclerView();

    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {

        if (type.equalsIgnoreCase("type")) {
            binding.spBenifecieryType.setText(model.getGrampanchayatName());
            benifeciery_id = model.getGrampanchayatId();
        }

    }

    private void SpinnerData() {
        List <String> teeka_type = new ArrayList <>();
        teeka_type.add("PPR");
        teeka_type.add("ET");
        teeka_type.add("FMD");
        teeka_type.add("HS");

        ArrayAdapter <String> adapter = new ArrayAdapter <String>(this,
                R.layout.spinner_item, teeka_type);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spTeeka.setAdapter(adapter);

        CalenderUtils.loadMonths(getContext(), binding.spMonth, binding.spYear);

    }

    private void clearallField() {
        binding.edtTagNo.setText("");
        binding.day.setText("");
        binding.spBenifecieryType.setText("");
        binding.edtPolicyNo.setText("");
        binding.ivBakari.setImageResource(R.drawable.ic_camera);
        binding.edtProofdate.setText("");
        binding.edtAveragProduction.setText("");
        binding.edtNote.setText("");
        binding.checkYes.setChecked(false);
        binding.checkNo.setChecked(false);
        binding.edtDateCrimNashak.setText("");
        binding.layoutCriminashak.setVisibility(View.GONE);
        binding.spMonth.setSelection(0);
        binding.spYear.setSelection(0);
        binding.teekaDate.setText("");
        EnableView();

    }

    private void DisableField() {
        binding.edtTagNo.setEnabled(false);
        binding.edtNote.setEnabled(false);
        binding.spBenifecieryType.setEnabled(false);
        binding.edtPolicyNo.setEnabled(false);
        binding.checkYes.setEnabled(false);
        binding.checkNo.setEnabled(false);
        binding.edtDateCrimNashak.setEnabled(false);
        binding.edtProofdate.setClickable(false);
        binding.edtAveragProduction.setEnabled(false);
        binding.spYear.setEnabled(false);
        binding.spMonth.setEnabled(false);
        binding.day.setEnabled(false);
    }

    private void EnableView() {
        binding.edtTagNo.setEnabled(true);
        binding.edtNote.setEnabled(true);
        binding.spBenifecieryType.setEnabled(true);
        binding.edtPolicyNo.setEnabled(true);
        binding.edtProofdate.setClickable(true);
        binding.edtAveragProduction.setEnabled(true);
        binding.spYear.setEnabled(true);
        binding.spMonth.setEnabled(true);
        binding.day.setEnabled(true);
        binding.checkYes.setEnabled(true);
        binding.checkNo.setEnabled(true);
        binding.edtDateCrimNashak.setEnabled(true);
    }

    @Override
    public void onOkClickListner() {
        this.finish();
    }

}
