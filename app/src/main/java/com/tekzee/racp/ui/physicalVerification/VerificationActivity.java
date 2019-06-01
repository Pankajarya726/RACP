package com.tekzee.racp.ui.physicalVerification;

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
import android.view.View;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.ActivityVerificationBinding;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.ImagePickerActivity;
import com.tekzee.racp.ui.base.MvpMapActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Log;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import java.io.InputStream;
import java.util.List;

public class VerificationActivity extends MvpMapActivity <VerificationPresenter> implements View.OnClickListener, VerificationView {

    private static final String TAG = VerificationActivity.class.getSimpleName();
    private ActivityVerificationBinding binding;

    private byte[] imageBytes;
    private InputStream imageInputStream = null;
    private int form_id;
    private Location mLocation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verification);

        getSupportActionBar().setTitle(R.string.physical_verification);


        form_id = getIntent().getIntExtra("form_id", 0);

        binding.checkNo.setOnClickListener(this);
        binding.checkYes.setOnClickListener(this);
        binding.tvPhyProof.setOnClickListener(this);
        binding.imgVerification.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);


    }

    @Override
    protected VerificationPresenter createPresenter() {
        return new VerificationPresenter(this);
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
                    binding.layoutPhysicalProof.setVisibility(View.VISIBLE);

                } else {
                    binding.checkYes.setChecked(false);
                    binding.layoutPhysicalProof.setVisibility(View.GONE);
                }
                break;


            case R.id.check_no:

                if (binding.checkNo.isChecked()) {
                    if (binding.checkYes.isChecked()) {
                        binding.checkYes.setChecked(false);
                    }
                    binding.checkNo.setChecked(true);
                    binding.layoutPhysicalProof.setVisibility(View.GONE);
                } else {
                    binding.checkNo.setChecked(false);
                    binding.layoutPhysicalProof.setVisibility(View.GONE);
                }
                break;


            case R.id.tv_phy_proof:
                mDatePickerDialog.getdate(this, binding.tvPhyProof);
                break;


            case R.id.img_verification:

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

            case R.id.tv_save:
                subimt();
                break;

        }
    }

    private void subimt() {
        if (!binding.checkYes.isChecked() && !binding.checkNo.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.is_physical_verification));
            return;
        } else if (binding.checkYes.isChecked() && binding.tvPhyProof.getText().toString().trim().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_physical_proof_date));
            return;
        } else if (imageBytes == null) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_phisical_proof_image));
            return;
        } else {


            mvpPresenter.verifyFrom(imageBytes);


        }

    }

    @Override
    public Activity getContext() {
        return this;
    }

    @Override
    public String getProofDate() {
        return binding.tvPhyProof.getText().toString().trim();
    }

    @Override
    public String getNote() {
        return binding.edtNote.getText().toString().trim();
    }

    @Override
    public String getFormId() {
        return String.valueOf(form_id);
    }

    @Override
    public String getLatidude() {
        return String.valueOf(mLocation.getLatitude());
    }

    @Override
    public String getLongitude() {
        return String.valueOf(mLocation.getLongitude());
    }

    @Override
    public void onSuccessFullSave(FormSubmitResponse successResult) {
        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), new Dialogs.okClickListner() {
            @Override
            public void onOkClickListner() {
                finish();

            }
        }, "  ");

    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.showColorDialog(getContext(),commonResult.getMessage());

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

                        Glide.with(getContext()).load(uri).into(binding.imgVerification);
                        try {
                            imageInputStream = getContentResolver().openInputStream(uri);

                            Log.view(TAG, "uri = = " + uri);
                            Log.view(TAG, "imageInputStream = = " + imageInputStream);

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

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        this.mLocation = location;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
