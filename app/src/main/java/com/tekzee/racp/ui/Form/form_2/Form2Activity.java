package com.tekzee.racp.ui.Form.form_2;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.ActivityFrom2Binding;
import com.tekzee.racp.ui.Form.form_2.Model.Record2;
import com.tekzee.racp.ui.Form.form_2.Model.RetrivedDataResponse;
import com.tekzee.racp.ui.Form.form_2.Model.mData;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Form2Activity extends MvpActivity <Form2Presenter> implements Form2View, View.OnClickListener,Dialogs.okClickListner {
    private static final String tag = Form2Activity.class.getSimpleName();
    List <mData> dataList = new ArrayList <>();
    List <Record2> record2s = new ArrayList <>();
    private ActivityFrom2Binding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private int nasl_id;
    private int table_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_from2);

        getSupportActionBar().setTitle(R.string.form_2);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowDialog();
        }


        setDataInSpinner();
        mData.deleteAll(mData.class);
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.tvNo.setText(String.valueOf(recordNo));
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.spNasl.setOnClickListener(this);

    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 2);

        mvpPresenter.getFormRecordData(jsonObject);

    }

    @Override
    protected Form2Presenter createPresenter() {
        return new Form2Presenter(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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

            case R.id.tv_addRecord:
                addRecordinSqlite();
                break;

            case R.id.privious:
                privious();
                break;


            case R.id.next:
                next();
                break;

            case R.id.sp_nasl:
                mvpPresenter.getNasl();
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

    private Boolean addRecordinSqlite() {

        if (binding.edtTagNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno));
            return false;
        } else if (binding.edtAge.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_age));
            return false;
        } else if (binding.edtAvarage.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_average_milk_production));
            return false;
        } else if (binding.spNasl.getText().toString().isEmpty() ){
            Dialogs.showColorDialog(getContext(), getString(R.string.select_nasl));
            return false;
        } else {

            if (!binding.day.getText().toString().isEmpty()) {
                if (!mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),
                        binding.spMonth.getSelectedItemPosition() + 1,
                        Integer.valueOf(binding.spYear.getSelectedItem().toString()))) {

                    //mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),binding.spMonth.getSelectedItemPosition()+1,Integer.valueOf(binding.spYear.getSelectedItem().toString()));
                    Dialogs.showColorDialog(getContext(), getString(R.string.invalid_Date));
                    // Toast.makeText(this, getString(R.string.invalid_Date), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            mData data = new mData(binding.edtTagNo.getText().toString(),
                    binding.day.getText().toString(),
                    binding.spMonth.getSelectedItem().toString(),
                    binding.spYear.getSelectedItem().toString(),
                    binding.edtAge.getText().toString(),
                    binding.edtAvarage.getText().toString(),
                    nasl_id,
                    binding.spNasl.getText().toString(),
                    binding.edtNote.getText().toString());
            data.save();

            Log.e(tag, "record count = " + mData.count(mData.class));


            recordNo = recordNo + 1;
            binding.tvNo.setText(String.valueOf(recordNo));
            clearAllField();
            binding.privious.setVisibility(View.VISIBLE);
            record_count = record_count + 1;
            return true;

        }
    }

    private void privious() {
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }
        binding.tvNo.setText(String.valueOf(record_count));

        dataList.clear();
        dataList = mData.listAll(mData.class);

        mData data = dataList.get(record_count - 1);

        binding.day.setText(data.getDd());
        binding.spMonth.setSelection(Integer.valueOf(data.getMm()) + 1);
        binding.edtTagNo.setText(data.getTag_no());
        binding.edtAge.setText(data.getAge());
        binding.edtAvarage.setText(data.getAverage());
        binding.spNasl.setText(data.getNasl());
        binding.edtNote.setText(data.getNote());
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
        }
        binding.tvNo.setText(String.valueOf(record_count));

        dataList.clear();
        dataList = mData.listAll(mData.class);

        if (record_count <= mData.count(mData.class)) {
            mData data = dataList.get(record_count - 1);


            binding.day.setText(data.getDd());
            binding.spMonth.setSelection(Integer.valueOf(data.getMm()) + 1);
            binding.edtTagNo.setText(data.getTag_no());
            binding.edtAge.setText(data.getAge());
            binding.edtAvarage.setText(data.getAverage());
            binding.spNasl.setText(data.getNasl());
            binding.edtNote.setText(data.getNote());
            DisableView();


        } else {

            clearAllField();
        }

    }

    private void submitRecord() throws JSONException {

       if( addRecordinSqlite()) {


           dataList.clear();
           dataList = mData.listAll(mData.class);

           JsonArray jsonArray = new JsonArray();
           for (int i = 0; i < dataList.size(); i++) {

               mData data = dataList.get(i);
               JsonObject jsonObject = new JsonObject();
               jsonObject.addProperty("tag_no", data.getTag_no());
               jsonObject.addProperty("dd", data.getDd());
               jsonObject.addProperty("mm", data.getMm());
               jsonObject.addProperty("yy", data.getYy());
               jsonObject.addProperty("age", data.getAge());
               jsonObject.addProperty("average_milk_production", data.getAverage());
               jsonObject.addProperty("nasl", data.getNasl_Selection());
               jsonObject.addProperty("note", data.getNote());
               jsonArray.add(jsonObject);

           }


           JsonObject jsonObject = new JsonObject();

           jsonObject.addProperty("status_receipt", 1);
           jsonObject.addProperty("form_id", 2);
           jsonObject.addProperty("user_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.USER_ID));
           jsonObject.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_member_id));
           jsonObject.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_group_id));
           jsonObject.add("data", jsonArray);
           Log.e(tag, "final json is" + jsonObject.toString());

           mvpPresenter.saveForm(jsonObject);
       }

    }

    private void setDataInSpinner() {


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

    private void ShowDialog() {

        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.bakariya_availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("status_receipt", 0);
                    jsonObject.put("user_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.USER_ID));
                    jsonObject.put("form_id", 2);
                    jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_member_id));
                    jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_group_id));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

/*
        ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.form_2));
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

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.showColorDialog(getContext(), commonResult.getMessage());

    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(),this,"  ");

        mData.deleteAll(mData.class);
        recordNo = 1;
        record_count = 1;
        binding.tvNo.setText(String.valueOf(record_count));

        clearAllField();
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);
    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {

        if (type.equalsIgnoreCase("nasla")) {
            binding.spNasl.setText(model.getGrampanchayatName());
            nasl_id = model.getGrampanchayatId();
        }

    }

    @Override
    public void onSuccessfullyRetrived(RetrivedDataResponse successResult) {

        binding.receiptDate.setVisibility(View.VISIBLE);

        DisableView();


        binding.edtTagNo.setText(String.valueOf(successResult.getData().getTagNo()));
        binding.receiptDate.setText(String.valueOf(successResult.getData().getDateReceipt()));
        binding.edtAge.setText(String.valueOf(successResult.getData().getAge()));
        binding.edtAvarage.setText(String.valueOf(successResult.getData().getAverageMilkProduction()));
        binding.spNasl.setText(String.valueOf(successResult.getData().getNaslaName()));
        if (!String.valueOf(successResult.getData().getNote()).equalsIgnoreCase("null")){
            binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        }
        binding.layoutEditReceipt.setVisibility(View.GONE);
        binding.layoutTextReceipt.setVisibility(View.GONE);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);


    }

    private void DisableView() {
        binding.edtTagNo.setEnabled(false);
        binding.edtAge.setEnabled(false);
        binding.edtAvarage.setEnabled(false);
        binding.spNasl.setClickable(false);
        binding.edtNote.setEnabled(false);
        binding.spMonth.setEnabled(false);
        binding.spYear.setEnabled(false);
        binding.day.setEnabled(false);
    }

    public void clearAllField() {
        binding.day.setText("");
        binding.edtTagNo.setText("");
        binding.edtNote.setText("");
        binding.edtAvarage.setText("");
        binding.edtAge.setText("");
        binding.spNasl.setText("");
        binding.spNasl.setHint(getString(R.string.nasl));
        EnableView();

    }

    private void EnableView() {
        binding.edtTagNo.setEnabled(true);
        binding.edtAge.setEnabled(true);
        binding.edtAvarage.setEnabled(true);
        binding.spNasl.setClickable(true);
        binding.edtNote.setEnabled(true);
        binding.spMonth.setEnabled(true);
        binding.spYear.setEnabled(true);
        binding.day.setEnabled(true);
    }

    @Override
    public void onOkClickListner() {
this.finish();
    }
}
