package com.tekzee.racp.ui.Form.beema_detail;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;

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
import com.tekzee.racp.databinding.FormBeemaDeatailBinding;
import com.tekzee.racp.ui.Form.beema_detail.model.BeemaDetail;
import com.tekzee.racp.ui.Form.beema_detail.model.Datum;
import com.tekzee.racp.ui.Form.beema_detail.model.RetrivedBeemaDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.ImagePickerActivity;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpMapActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BeemaDeatailActivity extends MvpMapActivity <BeemaDetailPresenter> implements AdapterView.OnItemSelectedListener, BeemaDetailView, View.OnClickListener, Dialogs.okClickListner {

    private static String TAG = BeemaDeatailActivity.class.getSimpleName();


    private FormBeemaDeatailBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private List <Datum> taglist = new ArrayList <>();
    private List <BeemaDetail> detailList = new ArrayList <>();
    private int table_id;
    private int animaltype_id = 0;
    private InputStream imageInputStream = null;
    private byte[] imageBytes;
    private Location mLocation;
    private String lattitude;
    private String longitude;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_beema_deatail);

        getSupportActionBar().setTitle(R.string.form_4);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowDialog();
        }


        SpinnerData();
        BeemaDetail.deleteAll(BeemaDetail.class);

        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.txtno.setText(String.valueOf(recordNo));
        binding.edtDatefrom.setOnClickListener(this);
        binding.edtDateto.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.checkNo.setOnClickListener(this);
        binding.edtDateDeath.setOnClickListener(this);
        binding.checkYes.setOnClickListener(this);
        binding.radioClaim.check(R.id.radio_pending);
        binding.checkNo.setChecked(true);
        binding.ivBeema.setOnClickListener(this);
        binding.spAnimalType.setOnClickListener(this);


    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 4);

        mvpPresenter.getFormRecordData(jsonObject);
    }

    @Override
    protected BeemaDetailPresenter createPresenter() {
        return new BeemaDetailPresenter(this);
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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.check_yes:
                if (binding.checkYes.isChecked()) {
                    if (binding.checkNo.isChecked()) {
                        binding.checkNo.setChecked(false);
                    }
                    binding.checkYes.setChecked(true);
                    binding.layoutDeath.setVisibility(View.VISIBLE);

                } else {
                    binding.checkYes.setChecked(false);
                    binding.layoutDeath.setVisibility(View.GONE);
                }
                break;


            case R.id.check_no:

                if (binding.checkNo.isChecked()) {
                    if (binding.checkYes.isChecked()) {
                        binding.checkYes.setChecked(false);
                    }
                    binding.checkNo.setChecked(true);
                    binding.layoutDeath.setVisibility(View.GONE);
                } else {
                    binding.checkNo.setChecked(false);
                    binding.layoutDeath.setVisibility(View.GONE);
                }
                break;


            case R.id.sp_animal_type:
                mvpPresenter.getAnimalType();
                break;


            case R.id.next:
                next();
                break;

            case R.id.privious:
                privious();
                break;

            case R.id.tv_save:
                try {
                    submitRecord();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.tv_addRecord:
                addRecordinSqlite();
                break;

            case R.id.edt_date_death:
                mDatePickerDialog.getdate(this, binding.edtDateDeath);
                break;

            case R.id.edt_datefrom:
                mDatePickerDialog.getdate(this, binding.edtDatefrom);
                break;
            case R.id.edt_dateto:
                mDatePickerDialog.getdate(this, binding.edtDateto);
                break;


            case R.id.iv_beema:
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

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        this.finish();
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


    // navigating user to app settings
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
//                        String fileLocation = Utility.getFileName(getAppContext(), uri);
//                        Log.view(TAG,"fileLocation: "+fileLocation);
//                        geoTag(uri.toString(), mLocation.getLatitude(), mLocation.getLongitude());

                        Glide.with(getContext()).load(uri).into(binding.ivBeema);

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


    private void next() {
        binding.privious.setVisibility(View.VISIBLE);
        record_count = record_count + 1;
        if (record_count == recordNo) {
            binding.next.setVisibility(View.GONE);
            binding.tvAddRecord.setVisibility(View.VISIBLE);
            binding.tvSave.setVisibility(View.VISIBLE);
        }
        binding.txtno.setText(String.valueOf(record_count));

        detailList.clear();

        detailList = BeemaDetail.listAll(BeemaDetail.class);


        if (record_count <= BeemaDetail.count(BeemaDetail.class)) {

            BeemaDetail detail = detailList.get(record_count - 1);
            binding.edtTagNo.setText(String.valueOf(detail.getTagno()));
            binding.edtDatefrom.setText(detail.getDate_from());
            binding.edtDateto.setText(detail.getDate_to());
            binding.spAnimalType.setText(detail.getAnimal_type());
            binding.edtPolicyNo.setText(detail.getPolicy_no());
            binding.edtCompanyName.setText(detail.getCompany_name());
            if (detail.getReceipt_date().isEmpty()) {
                binding.tvPratiDate.setVisibility(View.GONE);
                binding.edtPraptiDate.setVisibility(View.GONE);
            } else {
                binding.tvPratiDate.setVisibility(View.VISIBLE);
                binding.edtPraptiDate.setVisibility(View.VISIBLE);
                binding.edtPraptiDate.setText(detail.getReceipt_date());
            }

            if (detail.getDate_conditoin().equalsIgnoreCase(getString(R.string.yes))) {
                binding.tvDeath.setVisibility(View.VISIBLE);
                binding.edtDateDeath.setVisibility(View.VISIBLE);
                binding.edtDateDeath.setText(detail.getDeath_date());
                binding.checkYes.setChecked(true);
            } else {
                binding.edtDateDeath.setVisibility(View.GONE);
                binding.tvDeath.setVisibility(View.GONE);
                binding.checkNo.setChecked(true);
            }


            DisableView();
        } else {


            ClearView();
            binding.privious.setVisibility(View.VISIBLE);


        }


    }

    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }

        binding.txtno.setText(String.valueOf(record_count));
        Log.e(TAG, String.valueOf(BeemaDetail.count(BeemaDetail.class)));


        detailList = BeemaDetail.listAll(BeemaDetail.class);
        Log.e(TAG, String.valueOf(detailList.size()));
        Log.e(TAG, String.valueOf(record_count));

        BeemaDetail detail = detailList.get(record_count - 1);

        binding.edtTagNo.setText(String.valueOf(detail.getTagno()));
        binding.edtDatefrom.setText(detail.getDate_from());
        binding.edtDateto.setText(detail.getDate_to());
        binding.spAnimalType.setText(detail.getAnimal_type());
        binding.edtPolicyNo.setText(detail.getPolicy_no());
        binding.edtCompanyName.setText(detail.getCompany_name());
        if (detail.getReceipt_date().isEmpty()) {
            binding.tvPratiDate.setVisibility(View.GONE);
            binding.edtPraptiDate.setVisibility(View.GONE);
        } else {
            binding.tvPratiDate.setVisibility(View.VISIBLE);
            binding.edtPraptiDate.setVisibility(View.VISIBLE);
            binding.edtPraptiDate.setText(detail.getReceipt_date());
        }

        if (detail.getDate_conditoin().equalsIgnoreCase(getString(R.string.yes))) {
            binding.tvDeath.setVisibility(View.VISIBLE);
            binding.edtDateDeath.setVisibility(View.VISIBLE);
            binding.edtDateDeath.setText(detail.getDeath_date());
            binding.checkYes.setChecked(true);
        } else {
            binding.edtDateDeath.setVisibility(View.GONE);
            binding.tvDeath.setVisibility(View.GONE);
            binding.checkNo.setChecked(true);
        }


        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);
        DisableView();


    }

    private boolean addRecordinSqlite() {

        if (binding.spAnimalType.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_animal_type));
            return false;
        } else if (binding.edtTagNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno));
            return false;
        } else if (binding.edtCompanyName.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_company_name));
            return false;
        } else if (binding.edtPolicyNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_policy_no));
            return false;
        } else if (binding.edtDatefrom.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_start_Date));
            return false;
        } else if (binding.edtDateto.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_end_Date));
            return false;
        } else if (!binding.checkNo.isChecked() && !binding.checkYes.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.death_condition));
            return false;
        } else if (binding.checkYes.isChecked() && binding.edtDateDeath.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_date_death));
            return false;

        } else {


            String death_date;
            String condition;

            int selectId = binding.radioClaim.getCheckedRadioButtonId();
            Log.e(TAG, "" + binding.radioClaim.getCheckedRadioButtonId());

            RadioButton radioButton;
            radioButton = findViewById(selectId);
            radioButton.getText().toString();

            if (binding.checkYes.isChecked()) {

                condition = binding.checkYes.getText().toString();
                death_date = binding.edtDateDeath.getText().toString();
                Log.e(TAG, "" + condition + "" + death_date);
            } else {
                condition = binding.checkNo.getText().toString();
                death_date = "";
            }

            BeemaDetail beemaDetail = new BeemaDetail(binding.spAnimalType.getText().toString().trim(),
                    animaltype_id,
                    binding.edtTagNo.getText().toString().trim(),
                    binding.edtPraptiDate.getText().toString().trim(),
                    binding.edtCompanyName.getText().toString().trim(),
                    binding.edtPolicyNo.getText().toString().trim(),
                    binding.edtDatefrom.getText().toString().trim(),
                    binding.edtDateto.getText().toString().trim(),
                    condition,
                    death_date,
                    radioButton.getText().toString()
            );
            beemaDetail.save();


            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));

            ClearView();
            binding.privious.setVisibility(View.VISIBLE);


            record_count++;
            return true;

        }

    }

    private void submitRecord() throws JSONException {
        if (imageBytes == null) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_phisical_proof_image));
            return;
        } else if (addRecordinSqlite()) {

            detailList.clear();
            detailList = BeemaDetail.listAll(BeemaDetail.class);
            Log.e(TAG, "size is" + detailList.size());

            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                BeemaDetail detail = detailList.get(i);

                Log.e(TAG,detail.getTagno());

                jsonObject.addProperty("animaltype_id", detail.getAnimal_id());
                jsonObject.addProperty("tag_no", detail.getTagno());
                jsonObject.addProperty("date_from_beema", mDatePickerDialog.changeFormate(detail.getDate_from()));
                jsonObject.addProperty("date_to_beema", mDatePickerDialog.changeFormate(detail.getDate_to()));
                jsonObject.addProperty("death_stage", detail.getDate_conditoin());
                jsonObject.addProperty("claim_receipt_stage", detail.getClaim_condition());
                jsonObject.addProperty("date_death", mDatePickerDialog.changeFormate(detail.getDeath_date()));
                jsonObject.addProperty("policy_no", detail.getPolicy_no());
                jsonObject.addProperty("company_name", detail.getCompany_name());
                jsonObject.addProperty("dd", "");
                jsonObject.addProperty("mm", "");
                jsonObject.addProperty("yy", "");
                jsonArray.add(jsonObject);

            }


           /* JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(this, Constant.mtg_group_id));
            json.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(this, Constant.mtg_member_id));
            json.addProperty("form_id", 4);
            json.addProperty("status_receipt", 1);

            json.add("data", jsonArray);
            Log.e(TAG, "data is" + json.toString());*/

            //mvpPresenter.saveForm(json);
            mvpPresenter.saveForm(jsonArray,imageBytes);

        }
    }

    private void SpinnerData() {

/*
        List <String> animal = new ArrayList <>();
        animal.add(getString(R.string.racp_bakra));
        animal.add(getString(R.string.racp_bakari));
        animal.add(getString(R.string.self_bakra));
        animal.add(getString(R.string.self_bakri));
        ArrayAdapter <String> adapter_animal = new ArrayAdapter <String>(this,
                R.layout.spinner_item, animal);
        adapter_animal.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spAnimalType.setAdapter(adapter_animal);

        CalenderUtils.loadMonths(getContext(),binding.spMonth,binding.spYear);*/
    }


    private void ShowDialog() {

        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.beema_availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("status_receipt", 0);
                    jsonObject.put("user_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.USER_ID));
                    jsonObject.put("form_id", 4);
                    jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_member_id));
                    jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_group_id));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });


        /*ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.form_1));
        dialog.setColor("#5eabbb");
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
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("status_receipt", 0);
                            jsonObject.put("user_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.USER_ID));
                            jsonObject.put("form_id", 4);
                            jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_member_id));
                            jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_group_id));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }).show();*/
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
        BeemaDetail.deleteAll(BeemaDetail.class);
        recordNo = 1;
        record_count = 1;
        ClearView();
        binding.txtno.setText(String.valueOf(record_count));
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);

    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.ShowCustomDialog(getContext(), commonResult.getMessage(), this);
    }

    @Override
    public void onSuccessfullyRetrived(RetrivedBeemaDataResponse successResult) {

        binding.receiptDate.setVisibility(View.VISIBLE);

        DisableView();

        binding.edtTagNo.setText(String.valueOf(successResult.getData().getTagNo()));
        binding.receiptDate.setText(mDatePickerDialog.changeFormate(String.valueOf(successResult.getData().getDateReceipt())));


        binding.edtPolicyNo.setText(String.valueOf(successResult.getData().getPolicyNo()));

        binding.edtDateto.setText(String.valueOf(mDatePickerDialog.changeFormate(successResult.getData().getDateToBeema())));

        binding.edtDatefrom.setText(String.valueOf(mDatePickerDialog.changeFormate(successResult.getData().getDateFromBeema())));


        if (successResult.getData().getDeathStage().equalsIgnoreCase(getString(R.string.yes))) {

            binding.checkYes.setChecked(true);
            binding.edtDateDeath.setVisibility(View.VISIBLE);
            binding.tvDeath.setVisibility(View.VISIBLE);
            binding.edtDateDeath.setClickable(false);
            binding.edtDateDeath.setText(mDatePickerDialog.changeFormate(String.valueOf(successResult.getData().getDateDeath())));
        } else {
            binding.checkNo.setChecked(true);
        }
        /*binding.edtNote.setText(String.valueOf(successResult.getData().getn()));
        binding.edtNote.setFocusable(false);*/

        binding.claincondition.setText(String.valueOf(successResult.getData().getClaimReceiptStage()));
        binding.claincondition.setVisibility(View.VISIBLE);


        binding.layoutRadioGroup.setVisibility(View.GONE);

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);

    }

    @Override
    public void onTagNoSelected(GramPanchayat model, String type) {
        if (type.equalsIgnoreCase("TAG")) {
            // binding.edtTagNo.setText(String.valueOf(model.getGrampanchayatId()));
            binding.edtTagNo.setText(String.valueOf(model.getGrampanchayatName()));

            Log.e(TAG, "" + taglist.size());
            Log.e(TAG, "" + Integer.valueOf(model.getGrampanchayatName()));

            for (int i = 0; i < taglist.size(); i++) {
                com.tekzee.racp.utils.Log.view(TAG, "" + taglist.get(i).getTagNo() + "\t" + taglist.get(i).getDateReceipt());


                if (String.valueOf(taglist.get(i).getTagNo()).equalsIgnoreCase(model.getGrampanchayatName())) {
                    binding.tvPratiDate.setVisibility(View.VISIBLE);
                    binding.edtPraptiDate.setVisibility(View.VISIBLE);
                    binding.edtPraptiDate.setText(mDatePickerDialog.changeFormate(String.valueOf(taglist.get(i).getDateReceipt())));
                }
            }


        }
    }

    @Override
    public void onTagNoRetrived(ArrayList <Datum> tagList) {

        this.taglist = tagList;

    }

    @Override
    public void onAnimalTypeSelected(GramPanchayat model, String type) {

        if (type.equalsIgnoreCase("animaltype")) {
            animaltype_id = model.getGrampanchayatId();
            binding.spAnimalType.setText(model.getGrampanchayatName());
            mvpPresenter.getTagNo(Utility.getIngerSharedPreferences(getContext(), Constant.mtg_member_id), model.getGrampanchayatId());
        }
    }

    @Override
    public String getLettitude() {
        return String.valueOf(mLocation.getLatitude());
    }

    @Override
    public String getLongitude() {
        return String.valueOf(mLocation.getLongitude());
    }

    private void ClearView() {

        binding.day.setText("");
        binding.spMonth.setSelection(0);
        binding.edtTagNo.setText("");
        binding.edtPolicyNo.setText("");
        binding.edtDateto.setText("");
        binding.edtDatefrom.setText("");
        binding.checkYes.setChecked(false);
        binding.checkNo.setChecked(false);
        binding.spAnimalType.setText("");
        animaltype_id = 0;
        binding.edtDateDeath.setText("");
        binding.edtPraptiDate.setVisibility(View.GONE);
        binding.edtPraptiDate.setText("");
        binding.tvPratiDate.setVisibility(View.GONE);
        binding.tvDeath.setVisibility(View.GONE);
        binding.layoutDeath.setVisibility(View.GONE);

        EnableView();
    }

    private void DisableView() {
        binding.edtTagNo.setEnabled(false);
        binding.spAnimalType.setEnabled(false);
        binding.edtPolicyNo.setEnabled(false);
        binding.edtDateto.setClickable(false);
        binding.edtDatefrom.setEnabled(false);
        binding.checkYes.setClickable(false);
        binding.checkNo.setClickable(false);
        binding.spMonth.setEnabled(false);
        binding.spYear.setEnabled(false);
        binding.day.setEnabled(false);
    }

    private void EnableView() {
        binding.edtTagNo.setEnabled(true);
        binding.spAnimalType.setEnabled(true);
        binding.edtPolicyNo.setEnabled(true);
        binding.edtDateto.setClickable(true);
        binding.edtDatefrom.setEnabled(true);
        binding.checkYes.setClickable(true);
        binding.checkNo.setClickable(true);
        binding.spMonth.setEnabled(true);
        binding.spYear.setEnabled(true);
        binding.day.setEnabled(true);
    }

    @Override
    public void onOkClickListner() {
        this.finish();
    }

    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {

        //    mvpPresenter.getTagNo(Utility.getIngerSharedPreferences(getContext(), Constant.mtg_member_id), position + 1);

    }

    @Override
    public void onNothingSelected(AdapterView <?> parent) {

    }

    public void onMyLocationChanged(Location location) {
        this.mLocation = location;
    }
}
