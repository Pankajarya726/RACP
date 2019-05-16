package com.tekzee.racp.ui.add_animal_owner;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.AddPashupalakBinding;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.SnackbarUtils;
import com.tekzee.racp.utils.Utility;

public class AddOwnerActivity extends MvpActivity <AddOwnerPresenter> implements AddOwnerView, View.OnClickListener {

    private AddPashupalakBinding binding;

    private int gram_panchayat_id;
    private int gram_id;
    private int category_id,vidhansabha_id,tehsil_id;
    private int mtgGroup_id, identification_type_id, cast_category_id, former_type_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.add_pashupalak);

        getSupportActionBar().setTitle(R.string.add_animal_owner);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        binding.btnSave.setOnClickListener(this);
        binding.gramPanchayat.setOnClickListener(this);
        binding.gram.setOnClickListener(this);
        binding.mtggroup.setOnClickListener(this);
        binding.pashupalakUnit.setOnClickListener(this);
        binding.edtIdentificationtype.setOnClickListener(this);
        binding.edtFarmertype.setOnClickListener(this);
        binding.edtPashupalakCtg.setOnClickListener(this);
        binding.vidhansabha.setOnClickListener(this);
        binding.tehsil.setOnClickListener(this);
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
    protected AddOwnerPresenter createPresenter() {
        return new AddOwnerPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pashupalak_unit:
                mvpPresenter.getPashuPalakCategory();
                break;

            case R.id.gramPanchayat:
                mvpPresenter.getGramPanchayat();
                break;

            case R.id.gram:
                mvpPresenter.getGram(gram_panchayat_id);
                break;
            case R.id.mtggroup:
                mvpPresenter.getMtgGroup(gram_id, Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                break;

            case R.id.edt_identificationtype:
                mvpPresenter.getIdentificationType();
                break;

            case R.id.edt_pashupalak_ctg:
                mvpPresenter.getCastCategory();
                break;

            case R.id.edt_farmertype:
                mvpPresenter.getFarmerType();
                break;

            case R.id.vidhansabha:
                mvpPresenter.getVidhanSabhaKshetra(105/*Utility.getIngerSharedPreferences(getContext(),Constant.USER_ID)*/);
                break;

            case R.id.tehsil:
                mvpPresenter.getTehsil(105/*Utility.getIngerSharedPreferences(getContext(),Constant.USER_ID)*/);
                break;

            case R.id.btn_save:
                // addPashuPalak();
                break;

        }
    }

    private void addPashuPalak() {

        if (binding.edtFormerName.getText().toString().trim().isEmpty()) {
            SnackbarUtils.snackBarTop(binding.edtFatherName, getString(R.string.formar_name));
        } else if (binding.edtFatherName.getText().toString().trim().isEmpty()) {
            SnackbarUtils.snackBarTop(binding.edtFatherName, getString(R.string.father_name));
        } else if (binding.edtMoNumber.getText().toString().trim().isEmpty()) {
            SnackbarUtils.snackBarTop(binding.edtMoNumber, getString(R.string.mobile_number));
        } else if (binding.edtMoNumber.getText().toString().length() != 10) {
            SnackbarUtils.snackBarTop(binding.edtMoNumber, getString(R.string.invalid_no));
        } else if (binding.edtIdentificationtype.getText().toString().equalsIgnoreCase(getString(R.string.identification_type))) {
            SnackbarUtils.snackBarTop(binding.edtMoNumber, getString(R.string.identification_type));
        } else if (binding.edtIdentificationNo.getText().toString().equalsIgnoreCase(getString(R.string.identification_no))) {
            SnackbarUtils.snackBarTop(binding.edtMoNumber, getString(R.string.identification_no));
        } else if (binding.edtFarmertype.getText().toString().equalsIgnoreCase(getString(R.string.former_type))) {
            SnackbarUtils.snackBarTop(binding.edtMoNumber, getString(R.string.former_type));
        } else if (binding.pashupalakUnit.getText().toString().equalsIgnoreCase(getString(R.string.pashu_palak_unit))) {
            SnackbarUtils.snackBarTop(binding.edtMoNumber, getString(R.string.pashu_palak_unit));
        } else if (binding.edtPashupalakCtg.getText().toString().equalsIgnoreCase(getString(R.string.pashu_palak_ctg))) {
            SnackbarUtils.snackBarTop(binding.edtMoNumber, getString(R.string.pashu_palak_ctg));
        } else if (binding.gramPanchayat.getText().toString().equalsIgnoreCase(getString(R.string.gram_panchayat))) {
            SnackbarUtils.snackBarTop(binding.gramPanchayat, getString(R.string.gram_panchayat));
        } else if (binding.gram.getText().toString().equalsIgnoreCase(getString(R.string.village))) {
            SnackbarUtils.snackBarTop(binding.gram, getString(R.string.village));
        } else if (binding.mtggroup.getText().toString().equalsIgnoreCase(getString(R.string.mtg_group))) {
            SnackbarUtils.snackBarTop(binding.mtggroup, getString(R.string.mtg_group));
        } else {

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("pashuPalakName", binding.edtFormerName.getText().toString().trim());
            jsonObject.addProperty("fatherHusbandName", binding.edtFatherName.getText().toString().trim());
            jsonObject.addProperty("mobile", binding.edtMoNumber.getText().toString().trim());
            jsonObject.addProperty("identification_type_id",identification_type_id);
            jsonObject.addProperty("identification_no", binding.edtIdentificationNo.getText().toString().trim());
            jsonObject.addProperty("former_type_id", former_type_id);
            jsonObject.addProperty("pashupalak_unit_id",category_id);
            jsonObject.addProperty("pashupalak_category_id",cast_category_id);
            jsonObject.addProperty("gramPanchyatId", gram_panchayat_id);
            jsonObject.addProperty("gramId", gram_id);
            jsonObject.addProperty("mtgGroup", mtgGroup_id);
            jsonObject.addProperty("userId", Utility.getIngerSharedPreferences(AddOwnerActivity.this, Constant.USER_ID));

            mvpPresenter.addPashuPalak(jsonObject);

        }

    }


    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {

        hideSoftKeyboard();
        if (type.equalsIgnoreCase("Gram_panchayat")) {
            binding.gramPanchayat.setText(model.getGrampanchayatName());
            gram_panchayat_id = model.getGrampanchayatId();
        }
        if (type.equalsIgnoreCase("Gram")) {
            binding.gram.setText(model.getGrampanchayatName());
            gram_id = model.getGrampanchayatId();
        }
        if (type.equalsIgnoreCase("PashuPalakCategory")) {
            binding.pashupalakUnit.setText(model.getGrampanchayatName());
            category_id = model.getGrampanchayatId();
        }
        if (type.equalsIgnoreCase("MtgGroup")) {
            binding.mtggroup.setText(model.getGrampanchayatName());
            mtgGroup_id = model.getGrampanchayatId();
        }
        if (type.equalsIgnoreCase("identificationtype")) {
            binding.edtIdentificationtype.setText(model.getGrampanchayatName());
            identification_type_id = model.getGrampanchayatId();

        }
        if (type.equalsIgnoreCase("castCategory")) {
            binding.edtPashupalakCtg.setText(model.getGrampanchayatName());
            cast_category_id = model.getGrampanchayatId();
        }
        if (type.equalsIgnoreCase("farmertype")) {
            binding.edtFarmertype.setText(model.getGrampanchayatName());
            former_type_id = model.getGrampanchayatId();
        }
        if (type.equalsIgnoreCase("vidhanasabha")) {
            binding.vidhansabha.setText(model.getGrampanchayatName());
            vidhansabha_id = model.getGrampanchayatId();
        }
        if (type.equalsIgnoreCase("tahsil")) {
            binding.tehsil.setText(model.getGrampanchayatName());
            tehsil_id = model.getGrampanchayatId();
        }


    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.showcolorDialog(getContext(), commonResult.getMessage());

    }

    @Override
    public void onAddPashuPalakSuccess(AddPashuPalakResponse successResult) {
        Dialogs.showcolorDialog(this, successResult.getMessage());
    }
}
