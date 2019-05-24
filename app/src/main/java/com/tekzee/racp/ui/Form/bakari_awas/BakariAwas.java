package com.tekzee.racp.ui.Form.bakari_awas;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.constant.GlideApp;
import com.tekzee.racp.databinding.FormBakariAwasBinding;
import com.tekzee.racp.ui.Form.bakari_awas.model.RetrivedBakariAwasResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.ImagePickerActivity;
import com.tekzee.racp.ui.base.MvpMapActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Log;
import com.tekzee.racp.utils.PhotoFullPopupWindow;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BakariAwas extends MvpMapActivity <BakariAwasPresenter> implements BakariAwasView, View.OnClickListener {
    private static final String TAG = BakariAwas.class.getSimpleName();
    byte[] data;
    byte[] imageBytes;
    private FormBakariAwasBinding binding;
    private InputStream imageInputStream = null;
    private Location mLocation;
    private String usability = "";
    private String physical_proof = "";
    private String latitude = "";
    private String longitude = "";
    private int table_id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_bakari_awas);


        getSupportActionBar().setTitle(R.string.form_12);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table_id = getIntent().getIntExtra("table_id", 0);

        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowDialog();
        }


        SpinnerData();
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.checkProofYes.setOnClickListener(this);
        binding.checkProofNo.setOnClickListener(this);
        binding.checkUseYes.setOnClickListener(this);
        binding.checkUseNo.setOnClickListener(this);
        binding.edtDateAvail.setOnClickListener(this);
        binding.ivBakriawas.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
    }

    @Override
    protected BakariAwasPresenter createPresenter() {
        return new BakariAwasPresenter(this);
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

            case R.id.check_proof_yes:


                if (binding.checkProofYes.isChecked()) {

                    if (binding.checkProofNo.isChecked()) {
                        binding.checkProofNo.setChecked(false);
                    }
                    binding.checkProofYes.setChecked(true);
                    physical_proof = binding.checkProofYes.getText().toString();
                } else {

                    binding.checkProofYes.setChecked(false);
                }
                break;


            case R.id.check_proof_no:
                if (binding.checkProofNo.isChecked()) {
                    if (binding.checkProofYes.isChecked()) {
                        binding.checkProofYes.setChecked(false);
                    }
                    binding.checkProofNo.setChecked(true);
                    physical_proof = binding.checkProofNo.getText().toString();
                } else {
                    binding.checkProofNo.setChecked(false);

                }
                break;


            case R.id.check_use_yes:
                if (binding.checkUseYes.isChecked()) {

                    if (binding.checkUseNo.isChecked()) {
                        binding.checkUseNo.setChecked(false);
                    }
                    binding.checkUseYes.setChecked(true);
                    usability = binding.checkUseYes.getText().toString();
                } else {

                    binding.checkUseYes.setChecked(false);
                }
                break;

            case R.id.check_use_no:
                if (binding.checkUseNo.isChecked()) {
                    if (binding.checkUseYes.isChecked()) {
                        binding.checkUseYes.setChecked(false);
                    }
                    binding.checkUseNo.setChecked(true);
                    usability = binding.checkUseNo.getText().toString();
                } else {
                    binding.checkUseNo.setChecked(false);

                }
                break;

            case R.id.iv_bakriawas:


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


//                PermissionListener permissionlistener = new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted() {
//                        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getContext())
//                                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
//                                    @Override
//                                    public void onImageSelected(Uri uri) {
//
//                                        final BitmapFactory.Options options = new BitmapFactory.Options();
//                                        options.inJustDecodeBounds = true;
//                                        BitmapFactory.decodeFile(uri.getPath(), options);
//
//                                        InputStream iStream = null;
//
//                                        try {
//                                            iStream = getContentResolver().openInputStream(uri);
//                                            Bitmap bitmap = BitmapFactory.decodeStream(iStream);
//                                            Bitmap resized = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
//                                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                                            resized.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//                                            data = bos.toByteArray();
//                                            // file = Base64.encodeToString(data, 0);
//
//                                            GlideModuleConstant.setImageBitmap(binding.ivBakriawas, getContext(), resized);
//
////                                        binding.imgUserEdit.setVisibility(View.GONE);
////                                        binding.imgSave.setVisibility(View.VISIBLE);
//
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                })
//                                .create();
//
//                        tedBottomPicker.show(getSupportFragmentManager());
//
//                    }
//
//                    @Override
//                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//                        Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT)
//                                .show();
//                    }
//                };
//
//                TedPermission.with(getContext())
//                        .setPermissionListener(permissionlistener)
//                        .setDeniedTitle("Permission denied")
//                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
//                        .setGotoSettingButtonText("Open")
//                        .setPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
//                        .check();

                break;


            case R.id.edt_date_avail:
                mDatePickerDialog.getdate(getContext(), binding.edtDateAvail);
                break;

            case R.id.tv_save:
                addBakariAwas();
                break;

        }


    }

    public void onMyLocationChanged(Location location) {
        this.mLocation = location;
    }

    private void addBakariAwas() {

        Log.view(TAG, "lattitude" + mLocation.getLatitude() + "****Lontitude" + mLocation.getLongitude());

        if (binding.edtDateAvail.getText().toString().isEmpty()) {

            Dialogs.showColorDialog(getContext(), getString(R.string.enter_awas_build_date));
        } else if (!binding.checkUseNo.isChecked() && !binding.checkUseYes.isChecked()) {

            Dialogs.showColorDialog(getContext(), getString(R.string.in_use_or_not));
        } else if (!binding.checkProofNo.isChecked() && !binding.checkProofYes.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.physical_proof));
        } else {
            if (binding.day.getText().toString().isEmpty()) {

            } else if (!mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),
                    binding.spMonth.getSelectedItemPosition() + 1,
                    Integer.valueOf(binding.spYear.getSelectedItem().toString()))) {

                //mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),binding.spMonth.getSelectedItemPosition()+1,Integer.valueOf(binding.spYear.getSelectedItem().toString()));
                Dialogs.showColorDialog(getContext(), getString(R.string.invalid_Date));
                // Toast.makeText(this, getString(R.string.invalid_Date), Toast.LENGTH_SHORT).show();
                return;
            }
            if (imageBytes == null) {
                Dialogs.showColorDialog(getContext(), getString(R.string.select_image));
                return;
            }


            latitude = String.valueOf(mLocation.getLatitude());
            longitude = String.valueOf(mLocation.getLongitude());


            mvpPresenter.uploadDocument(imageBytes);

        }
    }

    private void ShowDialog() {

        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {
                finish();
            }
        });



       /* ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.form_12));
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

    private void SpinnerData() {

        List <Integer> month = new ArrayList <>();
        for (int i = 1; i <= 12; i++) {
            month.add(i);
        }
        ArrayAdapter <Integer> adapter1 = new ArrayAdapter <Integer>(this,
                R.layout.spinner_item, month);
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spMonth.setAdapter(adapter1);


        List <Integer> year = new ArrayList <>();
        for (int i = 2015; i <= mDatePickerDialog.getYear(); i++) {
            year.add(i);
        }
        ArrayAdapter <Integer> adapter2 = new ArrayAdapter <Integer>(this,
                R.layout.spinner_item, year);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spYear.setAdapter(adapter2);
    }

    @Override
    public Activity getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult result) {

        Dialogs.showColorDialog(getContext(), result.getMessage());
    }

    @Override
    public String getPhysicalProof() {
        return physical_proof;
    }

    @Override
    public String getUsability() {
        return usability;

    }

    @Override
    public String getNote() {
        return binding.edtNote.getText().toString().trim();
    }

    @Override
    public String getDateCreationGoatHouse() {
        return binding.edtDateAvail.getText().toString().trim();
    }

    @Override
    public String getDD() {
        return binding.day.getText().toString().trim();
    }

    @Override
    public String getMM() {
        return binding.spMonth.getSelectedItem().toString().trim();
    }

    @Override
    public String getYY() {
        return binding.spYear.getSelectedItem().toString().trim();
    }

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    @Override
    public void onSuccessFullSave(FormSubmitResponse successResult) {
        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(),this);
        //finish();

    }

    @Override
    public void onSuccessfullyRetrived(RetrivedBakariAwasResponse successResult) {

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.checkProofYes.setClickable(false);
        binding.checkProofNo.setClickable(false);
        binding.checkUseNo.setClickable(false);
        binding.checkUseYes.setClickable(false);
        binding.edtNote.setFocusable(false);
        binding.tvSave.setVisibility(View.GONE);


        if (!String.valueOf(successResult.getData().getNote()).equalsIgnoreCase("null")){
            binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        }
        binding.receiptDate.setVisibility(View.VISIBLE);
        binding.receiptDate.setText(String.valueOf(successResult.getData().getDateReceipt()));
        if (successResult.getData().getPhysicalProof().equalsIgnoreCase(getString(R.string.yes))) {
            binding.checkProofYes.setChecked(true);
        } else {
            binding.checkProofNo.setChecked(true);
        }


        if (successResult.getData().getUsability().equalsIgnoreCase(getString(R.string.yes))) {
            binding.checkUseYes.setChecked(true);
        } else {
            binding.checkUseNo.setChecked(true);
        }

        binding.edtDateAvail.setText(String.valueOf(successResult.getData().getDateCreationGoatHouse()));
        binding.edtDateAvail.setEnabled(false);

        //binding.ivBakriawas.setClickable(false);
        //GlideModuleConstant.setImage(binding.ivBakriawas, getContext(),successResult.getData().getImageUpload());
        GlideApp.with(getContext())
                .load(successResult.getData().getImageUpload())
                .placeholder(R.drawable.bakari_awas)
                .error(R.drawable.bakari_awas)
                .into(binding.ivBakriawas);

        binding.ivBakriawas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to show image in full screen:
                new PhotoFullPopupWindow(getContext(), R.layout.popup_photo_full, view, successResult.getData().getImageUpload(), null);

            }
        });

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

                        Glide.with(getContext()).load(uri).into(binding.ivBakriawas);
                        try {
                            imageInputStream = getContentResolver().openInputStream(uri);
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

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 12);

        mvpPresenter.getFormRecordData(jsonObject);
    }

    @Override
    public void onOkClickListner() {
        this.finish();
    }
}
