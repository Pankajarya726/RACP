package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
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
import com.tekzee.racp.constant.GlideApp;
import com.tekzee.racp.databinding.FormVitritbakrokavivranBinding;
import com.tekzee.racp.sqlite.SqliteDB;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.BakariDataModel;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.BakriDatum;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Data;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormRecordDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Record;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.ResponseDataRetrived;
import com.tekzee.racp.ui.ImagePickerActivity;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpMapActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.CalenderUtils;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.KeyboardUtils;
import com.tekzee.racp.utils.PhotoFullPopupWindow;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FormActivity extends MvpMapActivity <FormPresenter> implements FormView, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private final static String TAG = FormActivity.class.getSimpleName();
    SqliteDB sqliteDB = new SqliteDB(this);
    List <Immunization> immunizations = new ArrayList <>();
    List <Record> mRecord = new ArrayList <>();
    JsonArray Myobject = new JsonArray();
    TeekaAdapter teekaAdapter;
    int bakari_count = 0;
    List <BakariDataModel> bakariDataModelList = new ArrayList <>();
    private int criminashak = 0;
    private FormVitritbakrokavivranBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private int table_id;
    private InputStream imageInputStream = null;
    private byte[] imageBytes;
    private String image_path = "";
    private int bakari_nasl_id = 0;
    private int bakara_nasl_id = 0;
    private String[] tagno;

    private Location mLocation;
    private int count = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_vitritbakrokavivran);
        KeyboardUtils.hideSoftInput(this);

        Log.e(TAG, "panding record to submit online " + Data.count(Data.class));

        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {

            getFormRecordData();
        } else {

            ShowDialog();
            SpinnerData();

        }

        getSupportActionBar().setTitle(R.string.form_1);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Immunization.deleteAll(Immunization.class);
        BakariDataModel.deleteAll(BakariDataModel.class);
        Record.deleteAll(Record.class);


        binding.txtno.setText(String.valueOf(recordNo));
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvPhyProof.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.teekaDate.setOnClickListener(this);
        binding.addmore.setOnClickListener(this);
        binding.spNaslBakara.setOnClickListener(this);
        binding.addmore.setVisibility(View.GONE);
        binding.checkNo.setOnClickListener(this);
        binding.checkYes.setOnClickListener(this);
        binding.edtDateCrimNashak.setOnClickListener(this);
        binding.ivGo.setOnClickListener(this);
        binding.ivBakra.setOnClickListener(this);
        binding.spnaslbakari.setOnClickListener(this);
        binding.addBakari.setOnClickListener(this);
        binding.count.setText(String.valueOf(count));
    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();


        jsonObject.addProperty("user_id",Utility.getIngerSharedPreferences(getContext(),Constant.USER_ID));
        jsonObject.addProperty("form_id",1);
        jsonObject.addProperty("mtg_member_id",Utility.getIngerSharedPreferences(getContext(),Constant.mtg_member_id));
        jsonObject.addProperty("mtg_group_id",Utility.getIngerSharedPreferences(getContext(),Constant.mtg_group_id));
//        jsonObject.addProperty("table_id", table_id);
//        jsonObject.addProperty("form_id", 1);

        mvpPresenter.getFormRecordData(jsonObject);

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
    protected FormPresenter createPresenter() {
        return new FormPresenter(this);
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
                    criminashak = 1;
                    binding.layoutCriminashak.setVisibility(View.VISIBLE);

                } else {
                    binding.checkYes.setChecked(false);
                    criminashak = 0;
                    binding.layoutCriminashak.setVisibility(View.GONE);
                }
                break;


            case R.id.check_no:
                if (binding.checkNo.isChecked()) {
                    if (binding.checkYes.isChecked()) {
                        binding.checkYes.setChecked(false);
                    }
                    binding.checkNo.setChecked(true);
                    criminashak = 0;
                    binding.layoutCriminashak.setVisibility(View.GONE);
                } else {
                    binding.checkNo.setChecked(false);
                    criminashak = 0;
                    binding.layoutCriminashak.setVisibility(View.GONE);
                }
                break;


            case R.id.add_bakari:
                if (recordNo <= bakari_count) {
                    addBakariDetail();
                } else {

                }

                break;


            case R.id.edt_date_crim_nashak:
                mDatePickerDialog.getdate(getContext(), binding.edtDateCrimNashak);
                break;

            case R.id.tv_phy_proof:
                mDatePickerDialog.getdate(this, binding.tvPhyProof);
                break;

            case R.id.tv_addRecord:
                save();
                break;

            case R.id.privious:
                privious();
                break;

            case R.id.next:
                next();
                break;

            case R.id.tv_save:
                try {
                    submitRecord();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;

            case R.id.teeka_date:
                DialogFragment datePicker = new mDatePickerDialog();
                datePicker.show(getSupportFragmentManager(), "date picker");
                //mDatePickerDialog.getdate(this, binding.teekaDate);
                break;

            case R.id.sp_nasl_bakara:
                mvpPresenter.getNasl("bakara_nasl");
                break;


            case R.id.spnaslbakari:
                mvpPresenter.getNasl("bakari_nasl");
                break;


            case R.id.iv_bakra:
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


            case R.id.iv_go:


                bakari_count = Integer.valueOf(binding.edtBakariCount.getText().toString().trim());
                if (bakari_count < 10) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.should_have_atleast_ten_goats));
                } else {

                    binding.addBakari.setVisibility(View.VISIBLE);
                    binding.linearLayoutBakariDetail.setVisibility(View.VISIBLE);

                    /*for (int i = 0; i < bakari_count; i++) {
                        bakariDataModelList.add(new BakariDataModel("", "", "", "", "", 0));
                    }*/

                    /* ArrayList <BakariDataModel> arrayList = new ArrayList <>();

                     */
                   /* ViewPager pager = findViewById(R.id.view_pager);
                    pager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), arrayList));*/


                }

                break;


        }

    }

    private void addBakariDetail() {

        if (binding.etTagNo.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno_bakri));
            return;
        } else if (binding.etAge.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_age_bakari));
            return;
        } else if (binding.etWeight.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_weight_bakari));
            return;
        } else if (binding.etAvarage.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_average_milk_production));
            return;
        } else if (binding.spnaslbakari.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_nasl_bakari));
            return;
        } else {


            if (tagno == null || tagno.length == 0) {
                tagno = new String[bakari_count];

                tagno[0] = binding.etTagNo.getText().toString().trim();
            } else {
                for (int i = 0; i < tagno.length; i++) {
                    if (binding.etTagNo.getText().toString().trim().equalsIgnoreCase(tagno[i])) {
                        Dialogs.showColorDialog(getContext(), getString(R.string.invalid_tag_no));
                        return;
                    }
                }

                tagno[recordNo - 1] = binding.etTagNo.getText().toString().trim();
            }

            bakariDataModelList.add(new BakariDataModel(binding.etTagNo.getText().toString().trim(),
                    binding.etAge.getText().toString().trim(),
                    binding.etWeight.getText().toString().trim(),
                    binding.etAvarage.getText().toString().trim(),
                    binding.spnaslbakari.getText().toString().trim(),
                    bakari_nasl_id));

            BakariDataModel bakariDataModel = new BakariDataModel(binding.etTagNo.getText().toString().trim(),
                    binding.etAge.getText().toString().trim(),
                    binding.etWeight.getText().toString().trim(),
                    binding.etAvarage.getText().toString().trim(),
                    binding.spnaslbakari.getText().toString().trim(),
                    bakari_nasl_id);
            if (bakariDataModel.save() > 0) {
                binding.etAvarage.setText("");
                binding.etAge.setText("");
                binding.etTagNo.setText("");
                bakari_nasl_id = 0;
                binding.etWeight.setText("");
                binding.spnaslbakari.setText("");
                record_count = record_count + 1;
                recordNo = recordNo + 1;
                binding.count.setText(String.valueOf(record_count));
                binding.privious.setVisibility(View.VISIBLE);
            }

        }

    }


    private void privious() {

        binding.addBakari.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }
        binding.count.setText(String.valueOf(record_count));


        BakariDataModel bakariDataModel = bakariDataModelList.get(record_count - 1);

        binding.etTagNo.setText(bakariDataModel.getTag_no());
        binding.etAge.setText(bakariDataModel.getAge());
        binding.etWeight.setText(bakariDataModel.getWeight());
        binding.spnaslbakari.setText(bakariDataModel.getNasl());
        binding.etAvarage.setText(bakariDataModel.getMilk_procution());

        binding.next.setVisibility(View.VISIBLE);
        binding.addBakari.setVisibility(View.GONE);


    }

    private void next() {

        binding.privious.setVisibility(View.VISIBLE);

        record_count = record_count + 1;
        binding.count.setText(String.valueOf(record_count));
        if (record_count == recordNo || record_count==bakari_count) {
            binding.next.setVisibility(View.GONE);
            if (record_count!=bakari_count){
            binding.addBakari.setVisibility(View.VISIBLE);
            }
            binding.etAvarage.setText("");
            binding.etAge.setText("");
            binding.etTagNo.setText("");
            binding.etWeight.setText("");
            binding.spnaslbakari.setText("");
            bakari_nasl_id = 0;
        } else {

            if (record_count <= BakariDataModel.count(BakariDataModel.class)) {

                BakariDataModel bakariDataModel = bakariDataModelList.get(record_count - 1);
                binding.etTagNo.setText(bakariDataModel.getTag_no());
                binding.etAge.setText(bakariDataModel.getAge());
                binding.etWeight.setText(bakariDataModel.getWeight());
                binding.spnaslbakari.setText(bakariDataModel.getNasl());
                binding.etAvarage.setText(bakariDataModel.getMilk_procution());

                bakari_nasl_id = bakariDataModel.getNaslid();
                BakariDataModel dataModel = bakariDataModelList.get(record_count - 1);

                dataModel.setTag_no(binding.etTagNo.getText().toString().trim());
                dataModel.setAge(binding.etAge.getText().toString().trim());
                dataModel.setWeight(binding.etWeight.getText().toString().trim());
                dataModel.setMilk_procution(binding.etAvarage.getText().toString().trim());
                dataModel.setNasl(binding.spnaslbakari.getText().toString().trim());
                dataModel.setNaslid(bakari_nasl_id);
            }
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
//                        geoTAG(uri.toString(), mLocation.getLatitude(), mLocation.getLongitude());

                        Glide.with(getContext()).load(uri).into(binding.ivBakra);
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

    private Boolean save() {

        if (binding.edtTagNo.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno_bakra));
            return false;
        } else if (binding.edtAgeBakra.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_age_bakara));
            return false;
        } else if (binding.edtWeightBakra.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_weight_bakara));
            return false;
        } else if (binding.tvPhyProof.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_physical_proof_date));
            return false;
        } else if (imageBytes == null) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_phisical_proof_image));
            return false;
        } else {
            if (binding.day.getText().toString().trim().isEmpty()) {

            } else {
                if (!mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString().trim()),
                        binding.spMonth.getSelectedItemPosition() + 1,
                        Integer.valueOf(binding.spYear.getSelectedItem().toString()))) {

                    //mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString().trim()),binding.spMonth.getSelectedItemPosition()+1,Integer.valueOf(binding.spYear.getSelectedItem().toString()));
                    Dialogs.showColorDialog(getContext(), getString(R.string.invalid_Date));
                    // Toast.makeText(this, getString(R.string.invalid_Date), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }


            return true;
        }


    }

    private void loadList(String date, String type) {

        Immunization im = new Immunization(type, date);
        im.save();

        immunizations.clear();
        Log.e(TAG, "" + Immunization.count(Immunization.class));
        immunizations = Immunization.listAll(Immunization.class);
        setupRecyclerView();
    }

    private void setupRecyclerView() {

        binding.recyclerImmunization.setLayoutManager(new LinearLayoutManager(this));

        teekaAdapter = new TeekaAdapter(getContext(), immunizations, this);

        binding.recyclerImmunization.setAdapter(teekaAdapter);
        teekaAdapter.notifyDataSetChanged();


    }


    private void submitRecord() throws JSONException {

        if (bakariDataModelList.size() < bakari_count) {
            Dialogs.showColorDialog(getContext(), getString(R.string.fill_detail_of_all_goats));
            return;
        }
        if (save()) {

            if (binding.edtBakariCount.getText().toString().isEmpty()) {
                Dialogs.showColorDialog(getContext(), getString(R.string.enter_bakari_count));
                return;
            }

            JsonObject mobject = new JsonObject();
            mobject.addProperty("tag_no", binding.edtTagNo.getText().toString().trim());
            mobject.addProperty("dd", binding.day.getText().toString().trim());
            mobject.addProperty("mm", binding.spMonth.getSelectedItemPosition() + 1);
            mobject.addProperty("yy", binding.spYear.getSelectedItem().toString());
            mobject.addProperty("physical_proof_date", mDatePickerDialog.changeFormate(binding.tvPhyProof.getText().toString().trim()));
            mobject.addProperty("weight", binding.edtWeightBakra.getText().toString().trim());
            mobject.addProperty("age", binding.edtAgeBakra.getText().toString().trim());
            mobject.addProperty("nasl", bakara_nasl_id);
            mobject.addProperty("deworming_status", criminashak);
            mobject.addProperty("deworming_date", mDatePickerDialog.changeFormate(binding.edtDateCrimNashak.getText().toString().trim()));

            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < immunizations.size(); i++) {
                Immunization immunization = immunizations.get(i);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("teeka_type", immunization.getTeekaname());
                jsonObject.addProperty("teeka_date", mDatePickerDialog.changeFormate(immunization.getTeekadate()));
                jsonArray.add(jsonObject);
            }
            mobject.add("teekakaran", jsonArray);


            JsonArray marray = new JsonArray();

            Log.e(TAG,""+bakariDataModelList.size());

            for (int i = 0; i < bakariDataModelList.size(); i++) {

                BakariDataModel dataModel = bakariDataModelList.get(i);
                Log.e(TAG,""+dataModel.getTag_no()+"\t"+dataModel.getWeight()+"\t"+dataModel.getAge());
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("tag_no", dataModel.getTag_no());
                jsonObject.addProperty("weight", dataModel.getWeight());
                jsonObject.addProperty("age", dataModel.getAge());
                jsonObject.addProperty("average_milk_production", dataModel.getMilk_procution());
                jsonObject.addProperty("nasl", dataModel.getNaslid());
                jsonObject.addProperty("note", "");

                marray.add(jsonObject);


            }

            com.tekzee.racp.utils.Log.view(TAG, "bakara_data" + mobject.toString());
            com.tekzee.racp.utils.Log.view(TAG, "bakari_data" + marray.toString());

            mvpPresenter.saveData(imageBytes, mobject, marray);








           /* mRecord.clear();
            mRecord = Record.listAll(Record.class);

            Log.e(TAG, "as----" + Myobject.toString());
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("status_receipt", 1);
            jsonObject.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            jsonObject.addProperty("form_id", 1);
            jsonObject.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(this, Constant.mtg_member_id));
            jsonObject.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(this, Constant.mtg_group_id));

            jsonObject.add("data", Myobject);


            Log.e(TAG, "final" + jsonObject.toString());*/

            // mvpPresenter.saveData(jsonObject);

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

        /*List <Integer> month = new ArrayList <>();
        for (int i = 1; i <= 12; i++) {
            month.add(i);
        }
        ArrayAdapter <Integer> adapter1 = new ArrayAdapter <Integer>(this,
                R.layout.spinner_item, month);
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spMonth.setAdapter(adapter1);*/


       /* List <Integer> year = new ArrayList <>();
        for (int i = 2015; i <= mDatePickerDialog.getYear(); i++) {
            year.add(i);
        }
        ArrayAdapter <Integer> adapter2 = new ArrayAdapter <Integer>(this,
                R.layout.spinner_item, year);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spYear.setAdapter(adapter2);*/
    }

    private void ShowDialog() {

        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.bakare_availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("status_receipt", 0);
                jsonObject.addProperty("user_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.USER_ID));
                jsonObject.addProperty("form_id", 1);
                jsonObject.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_member_id));
                jsonObject.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_group_id));
                jsonObject.add("data", Myobject);
                try {
                    mvpPresenter.saveData(jsonObject);

                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Data mData = new Data(jsonObject.toString());
                mData.save();

            }


        });


    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.showColorDialog(getContext(), commonResult.getMessage());
    }

    @Override
    public Activity getContext() {
        return this;
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {


        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), new Dialogs.okClickListner() {
            @Override
            public void onOkClickListner() {
                finish();
            }
        }, "  ");
        Record.deleteAll(Record.class);

        for (int i = 0; i < Myobject.size(); i++) {
            Myobject.remove(i);
        }


        recordNo = 1;
        record_count = 1;
        ClearVeiws();
        binding.txtno.setText(String.valueOf(record_count));
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(FormRecordDataResponse successResult) {

        binding.edtTagNo.setFocusable(false);

        binding.tvPhyProof.setClickable(false);
        binding.receiptLayout3.setClickable(false);

        binding.edtTagNo.setText(String.valueOf(successResult.getData().getTagNo()));

        binding.tvPhyProof.setText(mDatePickerDialog.changeFormate(successResult.getData().getDatePhysicalProof()));

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.receiptLayout3.setVisibility(View.VISIBLE);
        binding.receiptLayout3.setText(mDatePickerDialog.changeFormate(successResult.getData().getDateReceipt()));
        binding.immunization.setVisibility(View.GONE);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);
        binding.spTeeka.setVisibility(View.GONE);
        binding.teekaDate.setVisibility(View.GONE);
        binding.addmore.setVisibility(View.GONE);
        binding.recyclerImmunization.setEnabled(false);

        String ppr = successResult.getData().getDatePpr();
        String et = successResult.getData().getDateEt();
        String fmd = successResult.getData().getDateFmd();
        String hs = successResult.getData().getDateHs();


        String[] date = ppr.split(",");
        for (int i = 0; i < date.length; i++) {
            if (!date[i].isEmpty() && date[i] != null && !date[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.ppr), date[i]));
            }

        }
        String[] date1 = et.split(",");
        for (int i = 0; i < date1.length; i++) {
            if (!date1[i].isEmpty() && date1[i] != null && !date1[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.et), date1[i]));
            }
        }

        String[] date2 = fmd.split(",");
        for (int i = 0; i < date2.length; i++) {
            if (!date2[i].isEmpty() && date2[i] != null && !date2[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.fmd), date2[i]));
            }
        }

        String[] date3 = hs.split(",");
        for (int i = 0; i < date3.length; i++) {
            if (!date3[i].isEmpty() && date3[i] != null && !date3[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.hs), date3[i]));
            }
        }
        setupRecyclerView();


    }

    @Override
    public void onDateSelected(int adapterPosition, List <Immunization> immunizations, TextView tv_date) {

        mDatePickerDialog.getdate(getContext(), tv_date);
        Immunization immunization = immunizations.get(adapterPosition);
        immunization.setTeekadate(tv_date.getText().toString().trim());
        teekaAdapter.notifyDataSetChanged();

    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {
        if (type.equalsIgnoreCase("bakara_nasl")) {
            binding.spNaslBakara.setText(model.getGrampanchayatName());
            bakara_nasl_id = model.getGrampanchayatId();

        }
        if (type.equalsIgnoreCase("bakari_nasl")) {
            binding.spnaslbakari.setText(model.getGrampanchayatName());
            bakari_nasl_id = model.getGrampanchayatId();
        }
    }

    @Override
    public String getLatitude() {
        return String.valueOf(mLocation.getLatitude());
    }

    @Override
    public String getLongitude() {
        return String.valueOf(mLocation.getLongitude());
    }

    @Override
    public void onRetrived(ResponseDataRetrived successResult) {
        binding.edtTagNo.setFocusable(false);

        binding.tvPhyProof.setClickable(false);
        binding.receiptLayout3.setClickable(false);

        binding.edtTagNo.setText(String.valueOf(successResult.getData().getBakraData().getTagNo()));
       /* binding.edtAgeBakra.setText(String.valueOf(successResult.getData().getBakraData().getTagNo()));
        binding.edtTagNo.setText(String.valueOf(successResult.getData().getBakraData().getTagNo()));
*/
       binding.tvPhyProof.setText(mDatePickerDialog.changeFormate(successResult.getData().getBakraData().getDatePhysicalProof()));

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.receiptLayout3.setVisibility(View.VISIBLE);
        binding.receiptLayout3.setText(mDatePickerDialog.changeFormate(successResult.getData().getBakraData().getDateReceipt()));
        binding.immunization.setVisibility(View.GONE);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);
        binding.spTeeka.setVisibility(View.GONE);
        binding.teekaDate.setVisibility(View.GONE);
        binding.addmore.setVisibility(View.GONE);

        binding.btPhysicalVerification.setVisibility(View.VISIBLE);
        binding.recyclerImmunization.setEnabled(false);
        GlideApp.with(getContext())
                .load(successResult.getData().getPhysicalProofData().getImageUpload())
                .placeholder(R.drawable.bakari_awas)
                .error(R.drawable.bakari_awas)
                .into(binding.ivBakra);

        binding.ivBakra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to show image in full screen:
                new PhotoFullPopupWindow(getContext(), R.layout.popup_photo_full, view, successResult.getData().getPhysicalProofData().getImageUpload(), null);

            }
        });


        String ppr = successResult.getData().getBakraData().getDatePpr();
        String et = successResult.getData().getBakraData().getDateEt();
        String fmd = successResult.getData().getBakraData().getDateFmd();
        String hs = successResult.getData().getBakraData().getDateHs();


        String[] date = ppr.split(",");
        for (int i = 0; i < date.length; i++) {
            if (!date[i].isEmpty() && date[i] != null && !date[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.ppr), date[i]));
            }

        }
        String[] date1 = et.split(",");
        for (int i = 0; i < date1.length; i++) {
            if (!date1[i].isEmpty() && date1[i] != null && !date1[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.et), date1[i]));
            }
        }

        String[] date2 = fmd.split(",");
        for (int i = 0; i < date2.length; i++) {
            if (!date2[i].isEmpty() && date2[i] != null && !date2[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.fmd), date2[i]));
            }
        }

        String[] date3 = hs.split(",");
        for (int i = 0; i < date3.length; i++) {
            if (!date3[i].isEmpty() && date3[i] != null && !date3[i].equalsIgnoreCase("null")) {
                immunizations.add(new Immunization(getString(R.string.hs), date3[i]));
            }
        }
        setupRecyclerView();


        for (int i = 0; i<successResult.getData().getBakriData().size(); i++){

            BakriDatum data = successResult.getData().getBakriData().get(i);

            bakariDataModelList.add(new BakariDataModel(String.valueOf(data.getBakriTagNo()),
                   String.valueOf(data.getBakriAge()),
                    String.valueOf(data.getBakriWeight()),
                    String.valueOf(data.getAverageMilkProduction()),
                    String.valueOf(data.getBakriNasl()),1));

            BakariDataModel bakariDataModel = new BakariDataModel(String.valueOf(data.getBakriTagNo()),
                    String.valueOf(data.getBakriAge()),
                    String.valueOf(data.getBakriWeight()),
                    String.valueOf(data.getAverageMilkProduction()),
                    String.valueOf(data.getBakriNasl()),
                    0);
            bakariDataModel.save();

        }

        binding.linearLayoutBakariDetail.setVisibility(View.VISIBLE);

        binding.etTagNo.setText(String.valueOf(successResult.getData().getBakriData().get(0).getBakriTagNo()));
        binding.etWeight.setText(String.valueOf(successResult.getData().getBakriData().get(0).getBakriWeight()));
        binding.etAge.setText(String.valueOf(successResult.getData().getBakriData().get(0).getBakriAge()));
        binding.etAvarage.setText(String.valueOf(successResult.getData().getBakriData().get(0).getAverageMilkProduction()));
        binding.spnaslbakari.setText(String.valueOf(successResult.getData().getBakriData().get(0).getBakriNasl()));

        binding.addBakari.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);
        bakari_count = successResult.getData().getBakriData().size();

        binding.edtBakariCount.setText(String.valueOf(bakari_count));




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

    private void ClearVeiws() {
        binding.edtTagNo.setText("");
        binding.tvPhyProof.setText("");
       /* binding.edtBeemaVivran.setText("");
        binding.edtPolicyNo.setText("");*/
        binding.day.setText("");
        binding.spMonth.setSelection(0);
        binding.spYear.setSelection(0);
    }


    public void onMyLocationChanged(Location location) {
        this.mLocation = location;
    }
}
