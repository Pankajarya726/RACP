package com.tekzee.racp.ui.Form.bakara_rotation;

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
import com.tekzee.racp.databinding.FormBakaraRotationBinding;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class RotationActivity extends MvpActivity <RotationPresenter> implements RotationView, View.OnClickListener {

    private static String tag = RotationActivity.class.getSimpleName();
    private FormBakaraRotationBinding binding;

    private int form_id;
    private int recordNo = 1;
    private int record_count = 1    ;
    private int table_id;

    private List <DataBakaraRotation> detailList = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_bakara_rotation);
        getSupportActionBar().setTitle(R.string.form_18);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        form_id = getIntent().getIntExtra("form_id", 0);
        table_id = getIntent().getIntExtra("table_id",0);
        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowSelectionDialog();
        }



        DataBakaraRotation.deleteAll(DataBakaraRotation.class);
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.next.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);


    }

    @Override
    protected RotationPresenter createPresenter() {
        return new RotationPresenter(this);
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


        if (binding.edtTagNo.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_tagno), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtName.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtMtgname.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_mtg_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtAddress.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_address), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtMobileNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_mobile), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtMobileNumber.getText().toString().length() != 10) {
            Toast.makeText(this, getString(R.string.invalid_no), Toast.LENGTH_SHORT).show();
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
        } else if (binding.edtName1.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtMtgname1.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_mtg_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtAddress1.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_address), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtMobileNumber1.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_mobile), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtMobileNumber1.getText().toString().length() != 10) {
            Toast.makeText(this, getString(R.string.invalid_no), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtVidhansabha1.getText().toString().equalsIgnoreCase(getString(R.string.vidhansabha))) {
            Toast.makeText(this, getString(R.string.select_vidhansabha), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtTehsil1.getText().toString().equalsIgnoreCase(getString(R.string.tehsil))) {
            Toast.makeText(this, getString(R.string.select_tehsil), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtGramPanchayat1.getText().toString().equalsIgnoreCase(getString(R.string.gram_panchayat))) {
            Toast.makeText(this, getString(R.string.select_gramPanchayat), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtVillage1.getText().toString().equalsIgnoreCase(getString(R.string.village))) {
            Toast.makeText(this, getString(R.string.select_village), Toast.LENGTH_SHORT).show();
            return false;
        } else {


            DataBakaraRotation details = new DataBakaraRotation(
                    binding.edtTagNo.getText().toString(),
                    binding.edtName.getText().toString(),
                    binding.edtMtgname.getText().toString(),
                    binding.edtAddress.getText().toString(),
                    binding.edtMobileNumber.getText().toString(),
                    binding.edtVidhansabha.getText().toString(),
                    binding.edtGramPanchayat.getText().toString(),
                    binding.edtVillage.getText().toString(),
                    binding.edtTehsil.getText().toString(),
                    binding.edtName1.getText().toString(),
                    binding.edtMtgname1.getText().toString(),
                    binding.edtAddress1.getText().toString(),
                    binding.edtMobileNumber1.getText().toString(),
                    binding.edtVidhansabha1.getText().toString(),
                    binding.edtGramPanchayat1.getText().toString(),
                    binding.edtVillage1.getText().toString(),
                    binding.edtTehsil1.getText().toString());
            details.save();


            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));

            binding.edtTagNo.setText("");
            binding.edtName.setText("");
            binding.edtName1.setText("");
            binding.edtMtgname.setText("");
            binding.edtAddress.setText("");
            binding.edtMobileNumber.setText("");
            binding.edtVidhansabha.setText("");
            binding.edtGramPanchayat.setText("");
            binding.edtTehsil.setText("");
            binding.edtVillage.setText("");binding.edtName.setText("");
            binding.edtMtgname1.setText("");
            binding.edtAddress1.setText("");
            binding.edtMobileNumber1.setText("");
            binding.edtVidhansabha1.setText("");
            binding.edtGramPanchayat1.setText("");
            binding.edtTehsil1.setText("");
            binding.edtVillage1.setText("");


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

        Log.e(tag, String.valueOf(DataBakaraRotation.count(DataBakaraRotation.class)));

        detailList.clear();
        detailList = DataBakaraRotation.listAll(DataBakaraRotation.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));


        DataBakaraRotation detail = detailList.get(record_count - 1);
        binding.edtTagNo.setText(detail.getTagno());
        binding.edtName.setText(detail.getName_before());
        binding.edtName1.setText(detail.getName_after());
        binding.edtMtgname.setText(detail.getMtg_name_before());
        binding.edtAddress.setText(detail.getAddress_before());
        binding.edtMobileNumber.setText(detail.getMobile_before());
        binding.edtVidhansabha.setText(detail.getVidhansabha_before());
        binding.edtGramPanchayat.setText(detail.getGrampanchayat_before());
        binding.edtTehsil.setText(detail.getTehsil_before());
        binding.edtVillage.setText(detail.getGram_before());
        binding.edtMtgname1.setText(detail.getMtg_name_after());
        binding.edtAddress1.setText(detail.getAddress_after());
        binding.edtMobileNumber1.setText(detail.getMobile_after());
        binding.edtVidhansabha1.setText(detail.getVidhansabha_after());
        binding.edtGramPanchayat1.setText(detail.getGrampanchayat_after());
        binding.edtTehsil1.setText(detail.getTehsil_after());
        binding.edtVillage1.setText(detail.getGram_after());


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
        detailList = DataBakaraRotation.listAll(DataBakaraRotation.class);

        if (record_count <= DataBakaraRotation.count(DataBakaraRotation.class)) {

            DataBakaraRotation detail = detailList.get(record_count - 1);

            binding.edtTagNo.setText(detail.getTagno());
            binding.edtName.setText(detail.getName_before());
            binding.edtName1.setText(detail.getName_after());
            binding.edtMtgname.setText(detail.getMtg_name_before());
            binding.edtAddress.setText(detail.getAddress_before());
            binding.edtMobileNumber.setText(detail.getMobile_before());
            binding.edtVidhansabha.setText(detail.getVidhansabha_before());
            binding.edtGramPanchayat.setText(detail.getGrampanchayat_before());
            binding.edtTehsil.setText(detail.getTehsil_before());
            binding.edtVillage.setText(detail.getGram_before());
            binding.edtMtgname1.setText(detail.getMtg_name_after());
            binding.edtAddress1.setText(detail.getAddress_after());
            binding.edtMobileNumber1.setText(detail.getMobile_after());
            binding.edtVidhansabha1.setText(detail.getVidhansabha_after());
            binding.edtGramPanchayat1.setText(detail.getGrampanchayat_after());
            binding.edtTehsil1.setText(detail.getTehsil_after());
            binding.edtVillage1.setText(detail.getGram_after());
        } else {

            binding.edtTagNo.setText("");
            binding.edtName.setText("");
            binding.edtName1.setText("");
            binding.edtMtgname.setText("");
            binding.edtAddress.setText("");
            binding.edtMobileNumber.setText("");
            binding.edtVidhansabha.setText("");
            binding.edtGramPanchayat.setText("");
            binding.edtTehsil.setText("");
            binding.edtVillage.setText("");binding.edtName.setText("");
            binding.edtMtgname1.setText("");
            binding.edtAddress1.setText("");
            binding.edtMobileNumber1.setText("");
            binding.edtVidhansabha1.setText("");
            binding.edtGramPanchayat1.setText("");
            binding.edtTehsil1.setText("");
            binding.edtVillage1.setText("");


        }

    }

    private void submitRecord() throws JSONException {


        if (saveRecord()) {

            detailList.clear();
            detailList = DataBakaraRotation.listAll(DataBakaraRotation.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                DataBakaraRotation detail = detailList.get(i);

                jsonObject.addProperty("tag_no", detail.getTagno());
                jsonObject.addProperty("before_mtg_group_name", detail.getMtg_name_before());
                jsonObject.addProperty("before_pashupalak_name", detail.getName_before());
                jsonObject.addProperty("before_pashupalak_pata", detail.getAddress_before());
                jsonObject.addProperty("before_mobile_no", detail.getMobile_before());
                jsonObject.addProperty("before_vidhansabha", detail.getVidhansabha_before());
                jsonObject.addProperty("before_gram_panchayat", detail.getGrampanchayat_before());
                jsonObject.addProperty("before_gram", detail.getGram_before());
                jsonObject.addProperty("before_tehsil", detail.getTehsil_before());
                jsonObject.addProperty("after_mtg_group_name", detail.getMtg_name_after());
                jsonObject.addProperty("after_pashupalak_name", detail.getName_after());
                jsonObject.addProperty("after_pashupalak_pata", detail.getAddress_after());
                jsonObject.addProperty("after_mobile_no", detail.getMobile_after());
                jsonObject.addProperty("after_vidhansabha", detail.getVidhansabha_after());
                jsonObject.addProperty("after_gram_panchayat", detail.getGrampanchayat_after());
                jsonObject.addProperty("after_gram", detail.getGram_after());
                jsonObject.addProperty("after_tehsil", detail.getTehsil_after());

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
    public void SuccessfullSave(FormSubmitResponse successResult) {
            Toast.makeText(getContext(),successResult.getMessage(),Toast.LENGTH_SHORT).show();

            DataBakaraRotation.deleteAll(DataBakaraRotation.class);
        record_count =1;
        recordNo =1;
        binding.txtno.setText(String.valueOf(record_count));
        binding.edtTagNo.setText("");
        binding.edtName.setText("");
        binding.edtName1.setText("");
        binding.edtMtgname.setText("");
        binding.edtAddress.setText("");
        binding.edtMobileNumber.setText("");
        binding.edtVidhansabha.setText("");
        binding.edtGramPanchayat.setText("");
        binding.edtTehsil.setText("");
        binding.edtVillage.setText("");binding.edtName.setText("");
        binding.edtMtgname1.setText("");
        binding.edtAddress1.setText("");
        binding.edtMobileNumber1.setText("");
        binding.edtVidhansabha1.setText("");
        binding.edtGramPanchayat1.setText("");
        binding.edtTehsil1.setText("");
        binding.edtVillage1.setText("");
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Toast.makeText(getContext(),commonResult.getMessage(),Toast.LENGTH_SHORT).show();
    }
    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 18);

       // mvpPresenter.getFormRecordData(jsonObject);
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


            msg.setText(getString(R.string.form_18));

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
