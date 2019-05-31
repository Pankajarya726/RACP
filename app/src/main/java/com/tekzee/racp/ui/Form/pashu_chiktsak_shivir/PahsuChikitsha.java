package com.tekzee.racp.ui.Form.pashu_chiktsak_shivir;

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
import com.tekzee.racp.databinding.FormPahsuChikitshaShivirBinding;
import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.model.DataChikitsha;
import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.model.RetrivedPashuChikithsResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.ImagePickerActivity;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PahsuChikitsha extends MvpActivity <PashuChikitshaPresenter> implements PashuChikitshaView, Dialogs.okClickListner,View.OnClickListener {

    private static String tag = PahsuChikitsha.class.getSimpleName();
    private static String TAG = PahsuChikitsha.class.getSimpleName();

    private FormPahsuChikitshaShivirBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private List <DataChikitsha> detailList = new ArrayList <>();
    private int form_id;
    private int table_id;
    private InputStream imageInputStream = null;
    private byte[] imageBytes;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_pahsu_chikitsha_shivir);
        getSupportActionBar().setTitle(R.string.form_14);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        form_id = getIntent().getIntExtra("form_id", 0);
        table_id = getIntent().getIntExtra("table_id",0);

        if (table_id !=0){
            getFormRecordData();
        }

        DataChikitsha.deleteAll(DataChikitsha.class);
        binding.txtno.setText(String.valueOf(recordNo));
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.checkAvailableNo.setOnClickListener(this);
        binding.checkAvailableYes.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.edtDate.setOnClickListener(this);
        binding.ivShivir.setOnClickListener(this);

    }

    @Override
    protected PashuChikitshaPresenter createPresenter() {
        return new PashuChikitshaPresenter(this);
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


            case R.id.check_available_yes:
                if (binding.checkAvailableYes.isChecked()) {
                    if (binding.checkAvailableNo.isChecked()) {
                        binding.checkAvailableNo.setChecked(false);
                    }
                    binding.checkAvailableYes.setChecked(true);

                } else {
                    binding.checkAvailableYes.setChecked(false);
                }
                break;


            case R.id.check_available_no:
                if (binding.checkAvailableNo.isChecked()) {
                    if (binding.checkAvailableYes.isChecked()) {
                        binding.checkAvailableYes.setChecked(false);
                    }
                    binding.checkAvailableNo.setChecked(true);
                } else {
                    binding.checkAvailableNo.setChecked(false);
                }
                break;


            case R.id.edt_date:
                mDatePickerDialog.getdate(this, binding.edtDate);
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

            case R.id.iv_shivir:
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

                        Glide.with(getContext()).load(uri).into(binding.ivShivir);

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


        if (binding.edtPlace.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_shivir_place));
            return false;
        } else if (binding.edtDate.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_shivir_orginization_date));
            return false;
        } else if (binding.edtCount.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_count));
            return false;
        } else if (binding.edtBig.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_count_big));
            return false;
        } else if (binding.edtSmall.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_count_small));
            return false;
        } else if (!binding.checkAvailableYes.isChecked() && !binding.checkAvailableNo.isChecked()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.availebilityornot));
            return false;
        } else {

            String use;
            if (binding.checkAvailableYes.isChecked()) {
                use = binding.checkAvailableYes.getText().toString();
            } else {
                use = binding.checkAvailableNo.getText().toString();
            }


            DataChikitsha details = new DataChikitsha(
                    binding.edtPlace.getText().toString(),
                    binding.edtDate.getText().toString(),
                    binding.edtCount.getText().toString(),
                    binding.edtBig.getText().toString(),
                    binding.edtSmall.getText().toString(),
                    use,
                    binding.checkAvailableYes.isChecked(),
                    binding.edtNote.getText().toString()
            );
            details.save();


            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));

            ClearView();


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
        Log.e(tag, String.valueOf(DataChikitsha.count(DataChikitsha.class)));

        binding.txtno.setText(String.valueOf(record_count));
        // BeemaDetail beemaDetail =   BeemaDetail.findById(BeemaDetail.class,record_count);

        detailList.clear();
        detailList = DataChikitsha.listAll(DataChikitsha.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));

        DataChikitsha detail = detailList.get(record_count - 1);

        if (detail.isUse()) {

            binding.checkAvailableYes.setChecked(true);
            binding.checkAvailableNo.setChecked(false);
        } else {

            binding.checkAvailableNo.setChecked(true);
            binding.checkAvailableYes.setChecked(false);

        }

        binding.edtNote.setText(detail.getNote());
        binding.edtPlace.setText(detail.getPlace());
        binding.edtDate.setText(detail.getDate());
        binding.edtCount.setText(detail.getCount());
        binding.edtBig.setText(detail.getBig());
        binding.edtSmall.setText(detail.getSmall());


        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);
        DisableView();


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

        // detailList = BeemaDetail.listAll(BeemaDetail.class);
        detailList.clear();
        detailList = DataChikitsha.listAll(DataChikitsha.class);

        if (record_count <= DataChikitsha.count(DataChikitsha.class)) {

            DataChikitsha detail = detailList.get(record_count - 1);


            if (detail.isUse()) {

                binding.checkAvailableYes.setChecked(true);
                binding.checkAvailableNo.setChecked(false);
            } else {

                binding.checkAvailableNo.setChecked(true);
                binding.checkAvailableYes.setChecked(false);

            }

            binding.edtNote.setText(detail.getNote());
            binding.edtPlace.setText(detail.getPlace());
            binding.edtDate.setText(detail.getDate());
            binding.edtCount.setText(detail.getCount());
            binding.edtBig.setText(detail.getBig());
            binding.edtSmall.setText(detail.getSmall());
            DisableView();
        } else {
            ClearView();

        }

    }

    private void submitRecord() throws JSONException {


        if (saveRecord()) {

            detailList.clear();
            detailList = DataChikitsha.listAll(DataChikitsha.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                DataChikitsha detail = detailList.get(i);
                jsonObject.addProperty("shivir_sthal", detail.getPlace());
                jsonObject.addProperty("pashu_shivir_aayojan_date", mDatePickerDialog.changeFormate(detail.getDate()));
                jsonObject.addProperty("present_mtgmember_number", detail.getCount());
                jsonObject.addProperty("bade_pashu", detail.getBig());
                jsonObject.addProperty("chote_pashu", detail.getSmall());
                jsonObject.addProperty("biomedical_waste_disposal", detail.getBiomedical_west());
                jsonObject.addProperty("note", detail.getNote());
                jsonArray.add(jsonObject);


            }
            JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("form_id", form_id);

            mvpPresenter.saveForm(json);

            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());
        }

    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(),successResult.getMessage(),this,"  ");

        record_count =1;
        recordNo =1;
        binding.txtno.setText(String.valueOf(record_count));
        DataChikitsha.deleteAll(DataChikitsha.class);

        ClearView();
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);

    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        //Toast.makeText(getContext(),commonResult.getMessage(),Toast.LENGTH_SHORT).show();
        Dialogs.showColorDialog(getContext(),commonResult.getMessage());
    }

    @Override
    public Activity getContext() {
        return this;
    }

    @Override
    public void onSuccessfullyRetrived(RetrivedPashuChikithsResponse successResult) {

        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);

        DisableView();


        binding.edtPlace.setText(String.valueOf(successResult.getData().getShivirSthal()));
        binding.edtCount.setText(String.valueOf(successResult.getData().getPresentMtgmemberNumber()));
        binding.edtDate.setText(mDatePickerDialog.changeFormate(String.valueOf(successResult.getData().getPashuShivirAayojanDate())));
        binding.edtBig.setText(String.valueOf(successResult.getData().getBadePashu()));
        binding.edtSmall.setText(String.valueOf(successResult.getData().getChotePashu()));

        if (!String.valueOf(successResult.getData().getNote()).equalsIgnoreCase("null")){
            binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        }


        if (successResult.getData().getBiomedicalWasteDisposal().equalsIgnoreCase(getString(R.string.yes))){
            binding.checkAvailableYes.setChecked(true);
        }else {
            binding.checkAvailableNo.setChecked(true);
        }


    }



    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 14);

        mvpPresenter.getFormRecordData(jsonObject);
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

            TextView msg = dialog.findViewById(R.id.dialog_msg);


            msg.setText(getString(R.string.form_14));

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
                    intent.putExtra("form_id",form_id);
                    startActivity(intent);

                    dialog.dismiss();

                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void ClearView() {
        binding.edtNote.setText("");
        binding.edtSmall.setText("");
        binding.edtBig.setText("");
        binding.edtPlace.setText("");
        binding.edtDate.setText("");
        binding.edtCount.setText("");
        binding.checkAvailableNo.setChecked(false);
        binding.checkAvailableYes.setChecked(false);
        EnableView();
    }
    private void DisableView() {

        binding.edtPlace.setEnabled(false);
        binding.edtDate.setClickable(false);
        binding.edtCount.setEnabled(false);
        binding.edtBig.setEnabled(false);
        binding.edtSmall.setEnabled(false);
        binding.checkAvailableNo.setClickable(false);
        binding.checkAvailableYes.setClickable(false);
        binding.edtNote.setEnabled(false);

    }
    private void EnableView() {

        binding.edtPlace.setEnabled(true);
        binding.edtDate.setClickable(true);
        binding.edtCount.setEnabled(true);
        binding.edtBig.setEnabled(true);
        binding.edtSmall.setEnabled(true);
        binding.checkAvailableNo.setClickable(true);
        binding.checkAvailableYes.setClickable(true);
        binding.edtNote.setEnabled(true);

    }


    @Override
    public void onOkClickListner() {
        this.finish();

    }
}
