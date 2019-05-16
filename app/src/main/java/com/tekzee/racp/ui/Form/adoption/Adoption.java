package com.tekzee.racp.ui.Form.adoption;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormAdoptionBinding;
import com.tekzee.racp.ui.Form.adoption.model.DataAdoption;
import com.tekzee.racp.ui.Form.adoption.model.RetrivedAdoptionResponse;
import com.tekzee.racp.ui.Form.mtg_prasikshan.model.DataMtgTraingin;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Adoption extends MvpActivity <AdoptionPresenter> implements AdoptionView, View.OnClickListener {
    private String tag = Adoption.class.getSimpleName();

    private FormAdoptionBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private int district_id;
    private int vidhansabhi_id;
    private int tehsil_id;
    private int gram_panchayat_id;
    private int gram_id;
    private int form_id;
    private int table_id;
    private List<DataAdoption> detailList = new ArrayList <>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_adoption);

        getSupportActionBar().setTitle(R.string.form_17);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        form_id = getIntent().getIntExtra("form_id",0);
        table_id = getIntent().getIntExtra("table_id",0);
        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowSelectionDialog();
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
    public void onClick(View v) {
        switch (v.getId()) {

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


        }

    }



    private Boolean saveRecord() {


        if (binding.edtActivity.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_activity), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtName.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtNameFather.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_father_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtAddress.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_address), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtDistrict.getText().toString().equalsIgnoreCase(getString(R.string.district))) {
            Toast.makeText(this, getString(R.string.select_district), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtVidhansabha.getText().toString().equalsIgnoreCase(getString(R.string.vidhansabha))) {
            Toast.makeText(this, getString(R.string.select_vidhansabha), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtTehsil.getText().toString().equalsIgnoreCase(getString(R.string.tehsil))) {
            Toast.makeText(this, getString(R.string.select_tehsil), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtGramPanchayat.getText().toString().equalsIgnoreCase(getString(R.string.gram_panchayat))) {
            Toast.makeText(this, getString(R.string.select_gramPanchayat), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtVillage.getText().toString().equalsIgnoreCase(getString(R.string.village))) {
            Toast.makeText(this, getString(R.string.select_village), Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.edtMobile.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_mobile), Toast.LENGTH_SHORT).show();
            return false;
        }  else {




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

            binding.edtActivity.setText("");
            binding.edtNameFather.setText("");
            binding.edtName.setText("");
            binding.edtAddress.setText("");
            binding.edtDistrict.setHint(getString(R.string.district));
            binding.edtVidhansabha.setHint(getString(R.string.vidhansabha));
            binding.edtTehsil.setHint(getString(R.string.tehsil));
            binding.edtGramPanchayat.setHint(getString(R.string.gram_panchayat));
            binding.edtVillage.setHint(getString(R.string.village));
            binding.edtMobile.setText("");

            binding.edtNote.setText("");

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

        if (record_count <= DataMtgTraingin.count(DataMtgTraingin.class)) {

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
        } else {

            binding.edtActivity.setText("");
            binding.edtNameFather.setText("");
            binding.edtName.setText("");
            binding.edtAddress.setText("");
            binding.edtDistrict.setText(getString(R.string.district));
            binding.edtVidhansabha.setText(getString(R.string.vidhansabha));
            binding.edtTehsil.setText(getString(R.string.tehsil));
            binding.edtGramPanchayat.setText(getString(R.string.gram_panchayat));
            binding.edtVillage.setText(getString(R.string.village));
            binding.edtMobile.setText("");

            binding.edtNote.setText("");


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

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Toast.makeText(getContext(),commonResult.getMessage(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {


        if (type.equalsIgnoreCase("district")){
            district_id = model.getGrampanchayatId();
            binding.edtDistrict.setText(model.getGrampanchayatName());
        } if (type.equalsIgnoreCase("vidhanasabha")){
            vidhansabhi_id = model.getGrampanchayatId();
            binding.edtVidhansabha.setText(model.getGrampanchayatName());
        } if (type.equalsIgnoreCase("tahsil")){
            tehsil_id = model.getGrampanchayatId();
            binding.edtTehsil.setText(model.getGrampanchayatName());
        } if (type.equalsIgnoreCase("grampanchayat")){
            gram_panchayat_id = model.getGrampanchayatId();
            binding.edtGramPanchayat.setText(model.getGrampanchayatName());
        } if (type.equalsIgnoreCase("Gram")){
            gram_id = model.getGrampanchayatId();
            binding.edtVillage.setText(model.getGrampanchayatName());
        }

    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Toast.makeText(getContext(),successResult.getMessage(),Toast.LENGTH_SHORT).show();

        DataAdoption.deleteAll(DataAdoption.class);

        record_count =1;
        recordNo =1;
        binding.txtno.setText(String.valueOf(record_count));

        binding.edtActivity.setText("");
        binding.edtNameFather.setText("");
        binding.edtName.setText("");
        binding.edtAddress.setText("");
        binding.edtDistrict.setHint(getString(R.string.district));
        binding.edtVidhansabha.setHint(getString(R.string.vidhansabha));
        binding.edtTehsil.setHint(getString(R.string.tehsil));
        binding.edtGramPanchayat.setHint(getString(R.string.gram_panchayat));
        binding.edtVillage.setHint(getString(R.string.village));
        binding.edtMobile.setText("");
        binding.edtNote.setText("");
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessfullyRetrived(RetrivedAdoptionResponse successResult) {

        binding.edtActivity.setFocusable(false);
        binding.edtName.setFocusable(false);
        binding.edtNameFather.setFocusable(false);
        binding.edtAddress.setFocusable(false);
        binding.edtMobile.setFocusable(false);
        binding.edtNote.setFocusable(false);
        binding.edtDistrict.setClickable(false);
        binding.edtVidhansabha.setClickable(false);
        binding.edtTehsil.setClickable(false);
        binding.edtGramPanchayat.setClickable(false);
        binding.edtVillage.setClickable(false);

        binding.edtActivity.setText(String.valueOf(successResult.getData().getActivity()));
        binding.edtName.setText(String.valueOf(successResult.getData().getPashupalakName()));
       // binding.edtNameFather.setText(String.valueOf(successResult.getData().get()));
        binding.edtAddress.setText(String.valueOf(successResult.getData().getPashupalakAddress()));
        binding.edtMobile.setText(String.valueOf(successResult.getData().getPashupalakMobile()));
        binding.edtVidhansabha.setText(String.valueOf(successResult.getData().getVidhanasabhaId()));
        binding.edtTehsil.setText(String.valueOf(successResult.getData().getTahsilId()));
        binding.edtGramPanchayat.setText(String.valueOf(successResult.getData().getGrampanchayatId()));
        binding.edtVillage.setText(String.valueOf(successResult.getData().getGramId()));
        binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));



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
}