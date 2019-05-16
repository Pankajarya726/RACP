package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormVitritbakrokavivranBinding;
import com.tekzee.racp.sqlite.SqliteDB;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Data;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormRecordDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Record;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.KeyboardUtils;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;


public class FormActivity extends MvpActivity <FormPresenter> implements FormView, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private final static String tag = FormActivity.class.getSimpleName();
    SqliteDB sqliteDB = new SqliteDB(this);
    List <Immunization> immunizations = new ArrayList <>();
    List <Record> mRecord = new ArrayList <>();
    JsonArray Myobject = new JsonArray();
    TeekaAdapter teekaAdapter;
    private FormVitritbakrokavivranBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private int table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_vitritbakrokavivran);
        KeyboardUtils.hideSoftInput(this);

        Log.e(tag, "panding record to submit online " + Data.count(Data.class));


        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {

            getFormRecordData();
        } else {

            ShowDialog();
            SpinnerData();

        }

        getSupportActionBar().setTitle(R.string.form_1);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Immunization.deleteAll(Immunization.class);
        Record.deleteAll(Record.class);


        binding.txtno.setText(String.valueOf(recordNo));
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvPhyProof.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.teekaDate.setOnClickListener(this);
        binding.addmore.setOnClickListener(this);
        binding.addmore.setVisibility(View.GONE);
    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 1);

        mvpPresenter.getFormRecordData(jsonObject);

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
    protected FormPresenter createPresenter() {
        return new FormPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_phy_proof:
                mDatePickerDialog.getdate(this, binding.tvPhyProof);
                break;

            case R.id.tv_addRecord:

                save();

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

            case R.id.teeka_date:
                DialogFragment datePicker = new mDatePickerDialog();
                datePicker.show(getSupportFragmentManager(), "date picker");
                //mDatePickerDialog.getdate(this, binding.teekaDate);


                break;

            case R.id.addmore:

              /*  if (binding.teekaDate.getText().toString().isEmpty()) {
                    Toast.makeText(this, R.string.select_date_first, Toast.LENGTH_LONG).show();
                } else {
                    loadList();
                }
                binding.teekaDate.setText("");*/

        }

    }

    private Boolean save() {

        if (binding.edtTagNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno));
            return false;
        } else if (binding.edtBeemaVivran.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_beema_detial));
            return false;
        } else if (binding.edtPolicyNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_policy_no));
            return false;
        } else if (binding.tvPhyProof.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_physical_proof_date));
            return false;
        } else {
            if (binding.day.getText().toString().isEmpty()) {

            } else {
                if (Integer.valueOf(binding.day.getText().toString()) > 31) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.invalid_avail_date));
                    return false;
                }
            }


            immunizations.clear();

            immunizations = Immunization.listAll(Immunization.class);
            Log.e(tag, "size of immunisation list" + immunizations.size());

            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < immunizations.size(); i++) {
                Immunization imm = immunizations.get(i);
                Log.e(tag, "data is " + imm.getTeekaname() + "" + imm.getTeekadate());
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("teeka_type", imm.getTeekaname());
                jsonObject.addProperty("teeka_date", imm.getTeekadate());
                jsonArray.add(jsonObject);
            }
            JsonObject object = new JsonObject();
            object.addProperty("tag_no", binding.edtTagNo.getText().toString());
            object.addProperty("beema_detail", binding.edtBeemaVivran.getText().toString());
            if (binding.day.getText().toString().isEmpty()) {
                object.addProperty("dd", "01");
            } else {
                object.addProperty("dd", binding.day.getText().toString());
            }
            object.addProperty("mm", binding.spMonth.getSelectedItem().toString());
            object.addProperty("yy", binding.spYear.getSelectedItem().toString());
            object.addProperty("policy_no", binding.edtPolicyNo.getText().toString());
            object.addProperty("physical_proof_date", binding.tvPhyProof.getText().toString());
            object.add("teekakaran", jsonArray);

            Log.e(tag, "record 1 data is " + object.toString());

            Myobject.add(object);
            Record record = new Record(recordNo, object.toString());
            record.save();

            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));

            binding.edtTagNo.setText("");
            binding.tvPhyProof.setText("");
            binding.edtBeemaVivran.setText("");
            binding.edtPolicyNo.setText("");
            binding.day.setText("");
            binding.spMonth.setSelection(0);
            binding.spYear.setSelection(0);
            Immunization.deleteAll(Immunization.class);
            immunizations.clear();
            binding.privious.setVisibility(View.VISIBLE);
            record_count = record_count + 1;
            setupRecyclerView();
            return true;
        }


    }

    private void loadList(String date, String type) {

        Immunization im = new Immunization(type, date);
        im.save();

        immunizations.clear();
        Log.e(tag, "" + Immunization.count(Immunization.class));
        immunizations = Immunization.listAll(Immunization.class);
        setupRecyclerView();
    }

    private void setupRecyclerView() {

        binding.recyclerImmunization.setLayoutManager(new LinearLayoutManager(this));

        teekaAdapter = new TeekaAdapter(getContext(), immunizations, this);

        binding.recyclerImmunization.setAdapter(teekaAdapter);
        teekaAdapter.notifyDataSetChanged();


    }

    private void next() {

        binding.privious.setVisibility(View.VISIBLE);
        record_count = record_count + 1;
        if (record_count == recordNo) {
            binding.next.setVisibility(View.GONE);
            binding.tvAddRecord.setVisibility(View.VISIBLE);
            binding.tvSave.setVisibility(View.VISIBLE);
            binding.addmore.setVisibility(View.VISIBLE);
            binding.immunization.setVisibility(View.VISIBLE);
        }
        binding.txtno.setText(String.valueOf(record_count));

        mRecord.clear();
        immunizations.clear();

        mRecord = Record.listAll(Record.class);

        Log.e(tag, "record count" + record_count);

        if (record_count < Record.count(Record.class)) {
            Record record = mRecord.get(record_count - 1);

            Log.e(tag, "record" + record.getData());


            try {
                JSONObject jsonObject = new JSONObject(record.getData());

                binding.day.setText(jsonObject.getString("dd"));
                binding.edtTagNo.setText(jsonObject.getString("tag_no"));
                binding.edtBeemaVivran.setText(jsonObject.getString("beema_detail"));
                binding.edtPolicyNo.setText(jsonObject.getString("policy_no"));
                binding.tvPhyProof.setText(jsonObject.getString("physical_proof_date"));

                JSONArray jsonArray = jsonObject.getJSONArray("teekakaran");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    immunizations.add(new Immunization(object.getString("teeka_type"), object.getString("teeka_date")));
                }

                setupRecyclerView();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

            binding.edtTagNo.setText("");
            binding.edtBeemaVivran.setText("");
            binding.edtPolicyNo.setText("");
            binding.tvPhyProof.setText("");
            setupRecyclerView();

        }

    }

    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        binding.immunization.setVisibility(View.GONE);
        binding.addmore.setVisibility(View.GONE);

        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }
        binding.txtno.setText(String.valueOf(record_count));


        mRecord.clear();
        immunizations.clear();

        Log.e(tag, "record_count" + record_count);


        mRecord = Record.listAll(Record.class);

        Record record = mRecord.get(record_count - 1);

        Log.e(tag, "record" + record.getData());

        try {
            JSONObject jsonObject = new JSONObject(record.getData());

            binding.edtTagNo.setText(jsonObject.getString("tag_no"));
            binding.edtBeemaVivran.setText(jsonObject.getString("beema_detail"));
            binding.edtPolicyNo.setText(jsonObject.getString("policy_no"));
            binding.tvPhyProof.setText(jsonObject.getString("physical_proof_date"));


            JSONArray jsonArray = jsonObject.getJSONArray("teekakaran");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                immunizations.add(new Immunization(object.getString("teeka_type"), object.getString("teeka_date")));
            }

            setupRecyclerView();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);


    }

    private void submitRecord() throws JSONException {
        if (save()) {

            mRecord.clear();
            mRecord = Record.listAll(Record.class);


            Log.e(tag, "as----" + Myobject.toString());
            JsonObject jsonObject = new JsonObject();


            jsonObject.addProperty("status_receipt", 1);
            jsonObject.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            jsonObject.addProperty("form_id", 1);
            jsonObject.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(this, Constant.mtg_member_id));
            jsonObject.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(this, Constant.mtg_group_id));
            jsonObject.add("data", Myobject);


            Log.e(tag, "final" + jsonObject.toString());

            mvpPresenter.saveData(jsonObject);

        }
    }

    private void SpinnerData() {
        List <String> teeka_type = new ArrayList <>();
        teeka_type.add("PPR");
        teeka_type.add("ET");
        teeka_type.add("FMD");
        teeka_type.add("HS");

        ArrayAdapter <String> adapter = new ArrayAdapter <String>(this,
                R.layout.spinner_item, teeka_type);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spTeeka.setAdapter(adapter);


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

    private void ShowDialog() {
        ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setColor("#FF6500");
        dialog.setTitle(getResources().getString(R.string.form_1));
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
                            jsonObject.put("form_id", 1);
                            jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_member_id));
                            jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_group_id));

                            Data mData = new Data(jsonObject.toString());
                            mData.save();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }).show();
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.showColorDialog(getContext(), commonResult.getMessage());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {


        Dialogs.showColorDialog(getContext(), successResult.getMessage());
        Record.deleteAll(Record.class);

        for (int i = 0; i < Myobject.size(); i++) {
            Myobject.remove(i);
        }


        recordNo = 1;
        record_count = 1;
        binding.txtno.setText(String.valueOf(record_count));
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(FormRecordDataResponse successResult) {

        binding.edtTagNo.setFocusable(false);
        binding.edtPolicyNo.setFocusable(false);
        binding.edtBeemaVivran.setFocusable(false);
        binding.tvPhyProof.setClickable(false);
        binding.receiptLayout3.setClickable(false);

        binding.edtTagNo.setText(String.valueOf(successResult.getData().getTagNo()));

        binding.edtPolicyNo.setText(successResult.getData().getPolicyNo());

        binding.edtBeemaVivran.setText(successResult.getData().getBeemaDetail());

        binding.tvPhyProof.setText(successResult.getData().getDatePhysicalProof());


        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.receiptLayout3.setVisibility(View.VISIBLE);

        binding.receiptLayout3.setText(successResult.getData().getDateReceipt());

        binding.immunization.setVisibility(View.GONE);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);
        binding.spTeeka.setVisibility(View.GONE);
        binding.teekaDate.setVisibility(View.GONE);
        binding.addmore.setVisibility(View.GONE);


        String ppr = successResult.getData().getDatePpr();
        String et = successResult.getData().getDateEt();
        String fmd = successResult.getData().getDateFmd();
        String hs = successResult.getData().getDateHs();


        String date[] = ppr.split(",");
        for (int i = 0; i < date.length; i++) {
            immunizations.add(new Immunization(getString(R.string.ppr), date[i]));
        }
        String date1[] = et.split(",");
        for (int i = 0; i < date1.length; i++) {
            immunizations.add(new Immunization(getString(R.string.et), date1[i]));
        }

        String date2[] = fmd.split(",");
        for (int i = 0; i < date2.length; i++) {
            immunizations.add(new Immunization(getString(R.string.fmd), date2[i]));
        }

        String date3[] = hs.split(",");
        for (int i = 0; i < date3.length; i++) {
            immunizations.add(new Immunization(getString(R.string.hs), date3[i]));
        }
        setupRecyclerView();


    }

    @Override
    public void onDateSelected(int adapterPosition, List <Immunization> immunizations, TextView tv_date) {

        mDatePickerDialog.getdate(getContext(), tv_date);
        Immunization immunization = immunizations.get(adapterPosition);
        immunization.setTeekadate(tv_date.getText().toString());
        teekaAdapter.notifyDataSetChanged();

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = dateFormatter.format(c.getTime());

        loadList(currentDateString, binding.spTeeka.getSelectedItem().toString());

        binding.teekaDate.setText("");

    }
}
