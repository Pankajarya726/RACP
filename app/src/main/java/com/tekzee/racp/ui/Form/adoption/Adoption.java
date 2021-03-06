package com.tekzee.racp.ui.Form.adoption;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
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
import com.tekzee.racp.databinding.FormAdoptionBinding;
import com.tekzee.racp.ui.Form.adoption.model.DataAdoption;
import com.tekzee.racp.ui.Form.adoption.model.RetrivedAdoptionResponse;
import com.tekzee.racp.ui.Form.mtg_prasikshan.model.DataMtgTraingin;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.ImagePickerActivity;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpMapActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Adoption extends MvpMapActivity <AdoptionPresenter> implements AdoptionView, View.OnClickListener, Dialogs.okClickListner {
    private String tag = Adoption.class.getSimpleName();
    private String TAG = Adoption.class.getSimpleName();

    private FormAdoptionBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private int district_id = 0;
    private int vidhansabhi_id = 0;
    private int tehsil_id = 0;
    private int gram_panchayat_id = 0;
    private int gram_id = 0;
    private int form_id;
    private InputStream imageInputStream = null;
    private byte[] imageBytes;
    private String image_path = "";
    private int table_id;
    private List <DataAdoption> detailList = new ArrayList <>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_adoption);

        getSupportActionBar().setTitle(R.string.form_17);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        form_id = getIntent().getIntExtra("form_id", 0);
        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {
            getFormRecordData();
        } else {
          //  ShowSelectionDialog();
        }


        DataAdoption.deleteAll(DataAdoption.class);

        binding.txtno.setText(String.valueOf(recordNo));
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.edtDistrict.setOnClickListener(this);
        binding.edtVidhansabha.setOnClickListener(this);
        binding.edtTehsil.setOnClickListener(this);
        binding.edtGramPanchayat.setOnClickListener(this);
        binding.edtVillage.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.edtActivity.setOnClickListener(this);
        binding.ivAdoption.setOnClickListener(this);


    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 17);


        mvpPresenter.getFormRecordData(jsonObject);
    }

    @Override
    protected AdoptionPresenter createPresenter() {
        return new AdoptionPresenter(this);
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

            case R.id.edt_activity:
                mvpPresenter.getActvitys();
                break;

            case R.id.edt_district:
                mvpPresenter.getDistrict();
                break;

            case R.id.edt_vidhansabha:
                mvpPresenter.getVidhansabha(district_id);
                break;

            case R.id.edt_tehsil:
                mvpPresenter.getTehsil(vidhansabhi_id);
                break;

            case R.id.edt_gramPanchayat:
                mvpPresenter.getGramPanchayat(tehsil_id);
                break;

            case R.id.edt_village:
                mvpPresenter.getGram(gram_panchayat_id);
                break;

            case R.id.tv_addRecord:
                saveRecord();
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

            case R.id.iv_Adoption:

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

                        Glide.with(getContext()).load(uri).into(binding.ivAdoption);
                        try {
                            imageInputStream = getContentResolver().openInputStream(uri);

                            com.tekzee.racp.utils.Log.view(TAG,"uri = = "+uri);
                            com.tekzee.racp.utils.Log.view(TAG,"imageInputStream = = "+imageInputStream);

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


    private Boolean saveRecord() {


        if (binding.edtActivity.getText().toString().isEmpty()) {

            Dialogs.showColorDialog(getContext(), getString(R.string.enter_activity));
            return false;
        } else if (binding.edtName.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_animal_owner_name));
            return false;
        } else if (binding.edtNameFather.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_father_name));
            return false;
        } else if (binding.edtAddress.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_address));
            return false;
        } else if (binding.edtDistrict.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_district));
            return false;
        } else if (district_id == 0) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_district));
            return false;
        } else if (binding.edtVidhansabha.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_vidhansabha));
            return false;
        } else if (vidhansabhi_id == 0) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_vidhansabha));
            return false;
        } else if (binding.edtTehsil.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_tehsil));
            return false;
        } else if (tehsil_id == 0) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_tehsil));
            return false;
        } else if (binding.edtGramPanchayat.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_gramPanchayat));
            return false;
        } else if (gram_panchayat_id == 0) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_gramPanchayat));
            return false;
        } else if (binding.edtVillage.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_village));
            return false;
        } else if (gram_id == 0) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_village));
            return false;
        } else if (binding.edtMobile.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_mobile));
            return false;
        } else if (binding.edtMobile.getText().toString().length() != 10) {
            Dialogs.showColorDialog(getContext(), getString(R.string.invalid_no));
            return false;
        } else {


            DataAdoption details = new DataAdoption(
                    binding.edtActivity.getText().toString(),
                    binding.edtName.getText().toString(),
                    binding.edtNameFather.getText().toString(),
                    binding.edtAddress.getText().toString(),
                    binding.edtDistrict.getText().toString(),
                    district_id,
                    binding.edtVidhansabha.getText().toString(),
                    vidhansabhi_id,
                    binding.edtTehsil.getText().toString(),
                    tehsil_id,
                    binding.edtGramPanchayat.getText().toString(),
                    gram_panchayat_id,
                    binding.edtVillage.getText().toString(),
                    gram_id,
                    binding.edtMobile.getText().toString(),
                    binding.edtNote.getText().toString()
            );
            details.save();


            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));


            ClearFeild();

            binding.privious.setVisibility(View.VISIBLE);
            record_count = record_count + 1;
            return true;

        }


    }

    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }

        binding.txtno.setText(String.valueOf(record_count));

        Log.e(tag, String.valueOf(DataMtgTraingin.count(DataMtgTraingin.class)));

        detailList.clear();
        detailList = DataAdoption.listAll(DataAdoption.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));


        DataAdoption detail = detailList.get(record_count - 1);

        binding.edtActivity.setText(detail.getActivity());
        binding.edtNameFather.setText(detail.getFahter_name());
        binding.edtName.setText(detail.getName());
        binding.edtAddress.setText(detail.getAddress());
        binding.edtDistrict.setText(detail.getDistrict());
        binding.edtVidhansabha.setText(detail.getVidhansabha());
        binding.edtTehsil.setText(detail.getTehsil());
        binding.edtGramPanchayat.setText(detail.getGrampanchayat());
        binding.edtVillage.setText(detail.getGram());
        binding.edtMobile.setText(detail.getMobile());
        binding.edtNote.setText(detail.getNote());
        DisableView();


        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);


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
        detailList = DataAdoption.listAll(DataAdoption.class);
        com.tekzee.racp.utils.Log.view(tag, "+" + record_count);
        com.tekzee.racp.utils.Log.view(tag, "+" + DataAdoption.count(DataMtgTraingin.class));

        if (record_count <= DataAdoption.count(DataAdoption.class)) {

            DataAdoption detail = detailList.get(record_count - 1);

            binding.edtActivity.setText(detail.getActivity());
            binding.edtNameFather.setText(detail.getFahter_name());
            binding.edtName.setText(detail.getName());
            binding.edtAddress.setText(detail.getAddress());
            binding.edtDistrict.setText(detail.getDistrict());
            binding.edtVidhansabha.setText(detail.getVidhansabha());
            binding.edtTehsil.setText(detail.getTehsil());
            binding.edtGramPanchayat.setText(detail.getGrampanchayat());
            binding.edtVillage.setText(detail.getGram());
            binding.edtMobile.setText(detail.getMobile());
            binding.edtNote.setText(detail.getNote());
            DisableView();
        } else {

            ClearFeild();


        }

    }

    private void submitRecord() throws JSONException {


        if (saveRecord()) {

            detailList.clear();
            detailList = DataAdoption.listAll(DataAdoption.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                DataAdoption detail = detailList.get(i);

                jsonObject.addProperty("activity", detail.getActivity());
                jsonObject.addProperty("pashupalak_name", detail.getName());
                jsonObject.addProperty("pashupalak_father_husband_name", detail.getFahter_name());
                jsonObject.addProperty("pashupalak_address", detail.getAddress());
                jsonObject.addProperty("pashupalak_mobile", detail.getMobile());
                jsonObject.addProperty("note", detail.getNote());
                jsonObject.addProperty("district_id", detail.getDistrict_id());
                jsonObject.addProperty("vidhanasabha_id", detail.getVidhansabha_id());
                jsonObject.addProperty("tahsil_id", detail.getTehsil_id());
                jsonObject.addProperty("grampanchayat_id", detail.getGramPanchayat_id());
                jsonObject.addProperty("gram_id", detail.getGram_id());

                jsonArray.add(jsonObject);


            }
            JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("form_id", form_id);


            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());


            mvpPresenter.saveForm(json);
        }

    }

    public void ClearFeild() {
        binding.edtActivity.setText("");
        binding.edtNameFather.setText("");
        binding.edtName.setText("");
        binding.edtAddress.setText("");
        binding.edtDistrict.setText("");
        binding.edtVidhansabha.setText("");
        binding.edtTehsil.setText("");
        binding.edtGramPanchayat.setText("");
        binding.edtVillage.setText("");
        binding.edtMobile.setText("");
        binding.edtNote.setText("");
        EnableView();
    }

    public void DisableView() {
        binding.edtActivity.setEnabled(false);
        binding.edtName.setEnabled(false);
        binding.edtNameFather.setEnabled(false);
        binding.edtAddress.setEnabled(false);
        binding.edtMobile.setEnabled(false);
        binding.edtNote.setEnabled(false);
        binding.edtDistrict.setEnabled(false);
        binding.edtVidhansabha.setEnabled(false);
        binding.edtTehsil.setEnabled(false);
        binding.edtGramPanchayat.setEnabled(false);
        binding.edtVillage.setEnabled(false);
    }

    public void EnableView() {
        binding.edtActivity.setEnabled(true);
        binding.edtName.setEnabled(true);
        binding.edtNameFather.setEnabled(true);
        binding.edtAddress.setEnabled(true);
        binding.edtMobile.setEnabled(true);
        binding.edtNote.setEnabled(true);
        binding.edtDistrict.setEnabled(true);
        binding.edtVidhansabha.setEnabled(true);
        binding.edtTehsil.setEnabled(true);
        binding.edtGramPanchayat.setEnabled(true);
        binding.edtVillage.setEnabled(true);
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
    public void onGramPanchayatSelected(GramPanchayat model, String type) {
        if (type.equalsIgnoreCase("activity")) {
           // district_id = model.getGrampanchayatId();
            binding.edtActivity.setText(model.getGrampanchayatName());
           // vidhansabhi_id = 0;
          //  binding.edtVidhansabha.setText("");
        }


        if (type.equalsIgnoreCase("district")) {
            district_id = model.getGrampanchayatId();
            binding.edtDistrict.setText(model.getGrampanchayatName());
            vidhansabhi_id = 0;
            binding.edtVidhansabha.setText("");
        }
        if (type.equalsIgnoreCase("vidhanasabha")) {
            vidhansabhi_id = model.getGrampanchayatId();
            binding.edtVidhansabha.setText(model.getGrampanchayatName());
            tehsil_id = 0;
            binding.edtTehsil.setText("");
        }
        if (type.equalsIgnoreCase("tahsil")) {
            tehsil_id = model.getGrampanchayatId();
            binding.edtTehsil.setText(model.getGrampanchayatName());
            gram_panchayat_id = 0;
            binding.edtGramPanchayat.setText("");
        }
        if (type.equalsIgnoreCase("grampanchayat")) {
            gram_panchayat_id = model.getGrampanchayatId();
            binding.edtGramPanchayat.setText(model.getGrampanchayatName());
            binding.edtVillage.setText("");
            gram_id = 0;
        }
        if (type.equalsIgnoreCase("Gram")) {
            gram_id = model.getGrampanchayatId();
            binding.edtVillage.setText(model.getGrampanchayatName());
        }

    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {


        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), this," ");
        DataAdoption.deleteAll(DataAdoption.class);

        record_count = 1;
        recordNo = 1;
        binding.txtno.setText(String.valueOf(record_count));

        ClearFeild();
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessfullyRetrived(RetrivedAdoptionResponse successResult) {


        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);
        DisableView();

        binding.edtActivity.setText(String.valueOf(successResult.getData().getActivity()));
        binding.edtName.setText(String.valueOf(successResult.getData().getPashupalakName()));
        // binding.edtNameFather.setText(String.valueOf(successResult.getData().get()));

        binding.edtNameFather.setText(String.valueOf(successResult.getData().getPashupalakFatherHusbandName()));
        binding.edtDistrict.setText(String.valueOf(successResult.getData().getDistrictName()));
        binding.edtAddress.setText(String.valueOf(successResult.getData().getPashupalakAddress()));
        binding.edtMobile.setText(String.valueOf(successResult.getData().getPashupalakMobile()));
        binding.edtVidhansabha.setText(String.valueOf(successResult.getData().getVidhanasabhaName()));
        binding.edtTehsil.setText(String.valueOf(successResult.getData().getTahsilName()));
        binding.edtGramPanchayat.setText(String.valueOf(successResult.getData().getGrampanchayatName()));
        binding.edtVillage.setText(String.valueOf(successResult.getData().getGramName()));

        if (successResult.getData().getNote()!=null && !successResult.getData().getNote().equalsIgnoreCase("null"))
        {
            binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        }



    }

    private void ShowSelectionDialog() {
        final Dialog dialog = new Dialog(getContext());
        try {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.custom_dialog);
            dialog.getWindow().setLayout(-1, -2);
            dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            TextView textView = dialog.findViewById(R.id.yes);
            TextView textView1 = dialog.findViewById(R.id.no);

            TextView msg = dialog.findViewById(R.id.dialog_msg);


            msg.setText(getString(R.string.form_17));

            dialog.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                    dialog.dismiss();
                }
            });
            dialog.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                    Intent intent = new Intent(getContext(), FormDataActivity.class);
                    intent.putExtra("form_id", form_id);
                    startActivity(intent);

                    dialog.dismiss();

                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onOkClickListner() {
        this.finish();
    }
}
