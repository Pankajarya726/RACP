package com.tekzee.racp.ui.Form.bakari_awas;

import android.Manifest;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.GlideModuleConstant;
import com.tekzee.racp.databinding.FormBakariAwasBinding;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.utils.mDatePickerDialog;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;
import gun0912.tedbottompicker.TedBottomPicker;

public class BakariAwas extends MvpActivity <BakariAwasPresenter> implements BakariAwasView, View.OnClickListener {
    private static String tag = BakariAwas.class.getSimpleName();
    byte[] data;
    private FormBakariAwasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_bakari_awas);


        getSupportActionBar().setTitle(R.string.form_12);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ShowDialog();
        SpinnerData();
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.checkProofYes.setOnClickListener(this);
        binding.checkProofNo.setOnClickListener(this);
        binding.checkUseYes.setOnClickListener(this);
        binding.checkUseNo.setOnClickListener(this);
        binding.ivBakriawas.setOnClickListener(this);
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
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.check_proof_yes:


                if (binding.checkProofYes.isChecked()) {

                    if (binding.checkProofNo.isChecked()) {
                        binding.checkProofNo.setChecked(false);
                    }
                    binding.checkProofYes.setChecked(true);
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
                } else {
                    binding.checkUseNo.setChecked(false);

                }
                break;

            case R.id.iv_bakriawas:

                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getContext())
                                .setOnImageSelectedListener(uri -> {

                                    final BitmapFactory.Options options = new BitmapFactory.Options();
                                    options.inJustDecodeBounds = true;
                                    BitmapFactory.decodeFile(uri.getPath(), options);

                                    InputStream iStream = null;

                                    try {
                                        iStream = getContentResolver().openInputStream(uri);
                                        Bitmap bitmap = BitmapFactory.decodeStream(iStream);
                                        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
                                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                        resized.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                                        data = bos.toByteArray();
                                        // file = Base64.encodeToString(data, 0);
                                        GlideModuleConstant.setImageBitmap(binding.ivBakriawas, getContext(), resized);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                })
                                .create();

                        tedBottomPicker.show(getSupportFragmentManager());

                    }

                    @Override
                    public void onPermissionDenied(ArrayList <String> deniedPermissions) {
                        Toast.makeText(getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT)
                                .show();
                    }
                };

                TedPermission.with(getContext())
                        .setPermissionListener(permissionlistener)
                        .setDeniedTitle("Permission denied")
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setGotoSettingButtonText("Open")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;

        }


    }

    private void ShowDialog() {
        ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);

        dialog.setTitle(getResources().getString(R.string.form_3));
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
                }).show();
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
        for (int i = 2015; i <= 2019; i++) {
            year.add(i);
        }
        ArrayAdapter <Integer> adapter2 = new ArrayAdapter <Integer>(this,
                R.layout.spinner_item, year);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spYear.setAdapter(adapter2);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
