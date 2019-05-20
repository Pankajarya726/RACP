package com.tekzee.racp.ui.Form.feed_suppliment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormFeedSupplimentBinding;
import com.tekzee.racp.ui.Form.feed_suppliment.model.DataFeedSuppliment;
import com.tekzee.racp.ui.Form.feed_suppliment.model.RetrivedFeedSupplimentResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.DataFormatException;

import cn.refactor.lib.colordialog.ColorDialog;

public class FeedSuppliment extends MvpActivity <FeedSupplimentPresenter> implements AdapterView.OnItemSelectedListener, FeedSupplimentView, View.OnClickListener, DatePickerDialog.OnDateSetListener, AdapterView.OnItemClickListener {
    private static String tag = FeedSuppliment.class.getSimpleName();
    int recordNo = 1;
    int record_count = 1;
    List <DataFeedSuppliment> detailList = new ArrayList <>();
    private FormFeedSupplimentBinding binding;
    int table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_feed_suppliment);

        getSupportActionBar().setTitle(R.string.form_11);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table_id = getIntent().getIntExtra("table_id",0);

        if (table_id != 0){
            getFormRecordData();
        }else {
            ShowDialog();
        }


        setDataInSpinner();

        DataFeedSuppliment.deleteAll(DataFeedSuppliment.class);
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.txtno.setText("01");
        binding.edtStartDate.setOnClickListener(this);
        binding.edtAfterStart90.setClickable(false);
        binding.edtStartDate.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);

        binding.spAnimalType.setOnItemSelectedListener(this);



    }

    @Override
    protected FeedSupplimentPresenter createPresenter() {
        return new FeedSupplimentPresenter(this);
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
            case R.id.edt_start_date:
                DialogFragment datePicker = new mDatePickerDialog();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;


            case R.id.tv_addRecord:
                addRecordinSqlite();

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

    private boolean addRecordinSqlite() {

        Log.e(tag,"addrecord");
        if (binding.edtAge.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.age), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtStartweight.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.weight), Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.edtStartmilkproduction.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.weight), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtTagNo.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.tag_no), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtSupplimetnAmount.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.feed_suppliment_amount), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtStartDate.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_policy_no), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtAfterStart90.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.date), Toast.LENGTH_SHORT).show();
            return false;
        } else {

            String day;
            if (binding.day.getText().toString().isEmpty()) {
                day = "01";

            } else {
                day = binding.day.getText().toString();
                if (!mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),
                        binding.spMonth.getSelectedItemPosition() + 1,
                        Integer.valueOf(binding.spYear.getSelectedItem().toString()))) {

                    //mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),binding.spMonth.getSelectedItemPosition()+1,Integer.valueOf(binding.spYear.getSelectedItem().toString()));
                    Dialogs.showColorDialog(getContext(), getString(R.string.invalid_Date));
                    // Toast.makeText(this, getString(R.string.invalid_Date), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }


            DataFeedSuppliment detail = new DataFeedSuppliment(day,
                    binding.spMonth.getSelectedItem().toString(),
                    binding.spMonth.getSelectedItemPosition(),
                    binding.spYear.getSelectedItem().toString(),
                    binding.spYear.getSelectedItemPosition(),
                    binding.spAnimalType.getSelectedItem().toString(),
                    binding.spAnimalType.getSelectedItemPosition(),
                    binding.edtTagNo.getText().toString(),
                    binding.edtAge.getText().toString(),
                    binding.edtStartweight.getText().toString(),
                    binding.edtStartmilkproduction.getText().toString(),
                    binding.edtSupplimetnAmount.getText().toString(),
                    binding.edtStartDate.getText().toString(),
                    binding.edtAfterStart90.getText().toString(),
                    binding.edtWeight1.getText().toString(),
                    binding.spCoatshining1.getSelectedItem().toString(),
                    binding.spHeatfrequency1.getSelectedItem().toString(),
                    binding.spMilkproduciton1.getSelectedItem().toString(),
                    binding.edtWeight2.getText().toString(),
                    binding.spCoatshining2.getSelectedItem().toString(),
                    binding.spHeatfrequency2.getSelectedItem().toString(),
                    binding.spMilkproduciton2.getSelectedItem().toString(),
                    binding.edtWeight3.getText().toString(),
                    binding.spCoatshining3.getSelectedItem().toString(),
                    binding.spHeatfrequency3.getSelectedItem().toString(),
                    binding.spMilkproduciton3.getSelectedItem().toString()
            );
            detail.save();

            Toast.makeText(this, "record added" + DataFeedSuppliment.count(DataFeedSuppliment.class), Toast.LENGTH_LONG).show();

            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));

            binding.spMonth.setSelection(0);
            binding.spYear.setSelection(0);
            binding.spAnimalType.setSelection(0);

            binding.edtAge.setText("");
            binding.edtTagNo.setText("");
            binding.edtStartweight.setText("");
            binding.edtSupplimetnAmount.setText("");
            binding.edtStartmilkproduction.setText("");
            binding.edtStartDate.setText("");
            binding.edtAfterStart90.setText("");
            binding.edtWeight1.setText("");
            binding.spCoatshining1.setSelection(0);
            binding.spHeatfrequency1.setSelection(0);
            binding.spMilkproduciton1.setSelection(0);
            binding.edtWeight2.setText("");
            binding.spCoatshining2.setSelection(0);
            binding.spHeatfrequency2.setSelection(0);
            binding.spMilkproduciton2.setSelection(0);
            binding.edtWeight3.setText("");
            binding.spCoatshining3.setSelection(0);
            binding.spHeatfrequency3.setSelection(0);
            binding.spMilkproduciton3.setSelection(0);

            binding.privious.setVisibility(View.VISIBLE);
            record_count++;

            return true;

        }

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

        detailList = DataFeedSuppliment.listAll(DataFeedSuppliment.class);


        if (record_count <= DataFeedSuppliment.count(DataFeedSuppliment.class)) {

            DataFeedSuppliment detail = detailList.get(record_count - 1);

            binding.day.setText(detail.getDd());
            binding.spMonth.setSelection(detail.getMm_id());
            binding.spYear.setSelection(detail.getYy_id());
            binding.spAnimalType.setSelection(detail.getAnimal_type_id());
            binding.edtTagNo.setText(detail.getTag_no());
            binding.edtAge.setText(detail.getStart_age());
            binding.edtStartweight.setText(detail.getStart_weight());
            binding.edtStartmilkproduction.setText(detail.getStart_milk_producion());
            binding.edtSupplimetnAmount.setText(detail.getStart_amount_feedsuppliment());
            binding.edtStartDate.setText(detail.getStart_date());
            binding.edtAfterStart90.setText(detail.getDate_90day());
            binding.edtWeight1.setText(detail.getWeight_1());
            binding.edtWeight2.setText(detail.getWeight_2());
            binding.edtWeight3.setText(detail.getWeight_3());
            binding.spCoatshining1.setSelection(0);
            binding.spCoatshining2.setSelection(0);
            binding.spCoatshining3.setSelection(0);
            binding.spHeatfrequency1.setSelection(0);
            binding.spHeatfrequency2.setSelection(0);
            binding.spHeatfrequency3.setSelection(0);
            binding.spMilkproduciton1.setSelection(0);
            binding.spMilkproduciton2.setSelection(0);
            binding.spMilkproduciton3.setSelection(0);

        } else {

            binding.spMonth.setSelection(0);
            binding.spYear.setSelection(0);
            binding.spAnimalType.setSelection(0);

            binding.edtAge.setText("");
            binding.edtTagNo.setText("");
            binding.edtStartweight.setText("");
            binding.edtSupplimetnAmount.setText("");
            binding.edtStartmilkproduction.setText("");
            binding.edtStartDate.setText("");
            binding.edtAfterStart90.setText("");
            binding.edtWeight1.setText("");
            binding.spCoatshining1.setSelection(0);
            binding.spHeatfrequency1.setSelection(0);
            binding.spMilkproduciton1.setSelection(0);
            binding.edtWeight2.setText("");
            binding.spCoatshining2.setSelection(0);
            binding.spHeatfrequency2.setSelection(0);
            binding.spMilkproduciton2.setSelection(0);
            binding.edtWeight3.setText("");
            binding.spCoatshining3.setSelection(0);
            binding.spHeatfrequency3.setSelection(0);
            binding.spMilkproduciton3.setSelection(0);

        }
    }

    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }
        Log.e(tag, String.valueOf(DataFeedSuppliment.count(DataFeedSuppliment.class)));

        detailList = DataFeedSuppliment.listAll(DataFeedSuppliment.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));

        DataFeedSuppliment detail = detailList.get(record_count - 1);



        binding.day.setText(detail.getDd());
        binding.spMonth.setSelection(detail.getMm_id());
        binding.spYear.setSelection(detail.getYy_id());
        binding.spAnimalType.setSelection(detail.getAnimal_type_id());
        binding.edtTagNo.setText(detail.getTag_no());
        binding.edtAge.setText(detail.getStart_age());
        binding.edtStartweight.setText(detail.getStart_weight());
        binding.edtStartmilkproduction.setText(detail.getStart_milk_producion());
        binding.edtSupplimetnAmount.setText(detail.getStart_amount_feedsuppliment());
        binding.edtStartDate.setText(detail.getStart_date());
        binding.edtAfterStart90.setText(detail.getDate_90day());
        binding.edtWeight1.setText(detail.getWeight_1());
        binding.edtWeight2.setText(detail.getWeight_2());
        binding.edtWeight3.setText(detail.getWeight_3());
        binding.spCoatshining1.setSelection(0);
        binding.spCoatshining2.setSelection(0);
        binding.spCoatshining3.setSelection(0);
        binding.spHeatfrequency1.setSelection(0);
        binding.spHeatfrequency2.setSelection(0);
        binding.spHeatfrequency3.setSelection(0);
        binding.spMilkproduciton1.setSelection(0);
        binding.spMilkproduciton2.setSelection(0);
        binding.spMilkproduciton3.setSelection(0);



        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);


    }


    private void submitRecord() throws JSONException {

        if (addRecordinSqlite()) {

            detailList.clear();
            detailList = DataFeedSuppliment.listAll(DataFeedSuppliment.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                DataFeedSuppliment detail = detailList.get(i);

                jsonObject.addProperty("animaltype_id", detail.getAnimal_type_id() + 1);
                jsonObject.addProperty("tag_no", detail.getTag_no());
                jsonObject.addProperty("age", detail.getStart_age());
                jsonObject.addProperty("weight_start", detail.getStart_weight());
                jsonObject.addProperty("milk_production_start", detail.getStart_milk_producion());
                jsonObject.addProperty("feed_supliment_amount", detail.getStart_amount_feedsuppliment());
                jsonObject.addProperty("feed_suppliment_start_date", detail.getStart_date());
                jsonObject.addProperty("date_after_90_days", detail.getDate_90day());
                jsonObject.addProperty("dd", detail.getDd());
                jsonObject.addProperty("mm", detail.getMm());
                jsonObject.addProperty("yy", detail.getYy());

                JsonArray jsonArray1 = new JsonArray();

                for (int j=0; j<3;j++){
                    JsonObject object = new JsonObject();
                    if (j==0){
                        object.addProperty("suppliment_effect_month",getString(R.string.month)+(j+1));
                        object.addProperty("effeted_weight",detail.getWeight_1());
                        object.addProperty("coat_shining",detail.getCoat_shinning_1());
                        object.addProperty("milk_production",detail.getMilk_production_1());
                        object.addProperty("heat_frquency",detail.getHeat_friquency_1());
                        jsonArray1.add(object);
                    } if (j==1){
                        object.addProperty("suppliment_effect_month",getString(R.string.month)+(j+1));
                        object.addProperty("effeted_weight",detail.getWeight_2());
                        object.addProperty("coat_shining",detail.getCoat_shinning_2());
                        object.addProperty("milk_production",detail.getMilk_production_2());
                        object.addProperty("heat_frquency",detail.getHeat_friquency_2());
                        jsonArray1.add(object);
                    } if (j==2){
                        object.addProperty("suppliment_effect_month",getString(R.string.month)+(j+1));
                        object.addProperty("effeted_weight",detail.getWeight_3());
                        object.addProperty("coat_shining",detail.getCoat_shinning_3());
                        object.addProperty("milk_production",detail.getMilk_production_3());
                        object.addProperty("heat_frquency",detail.getHeat_friquency_3());
                        jsonArray1.add(object);
                    }



                }

                jsonObject.add("affected_array",jsonArray1);


                jsonArray.add(jsonObject);

            }
            JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(this, Constant.mtg_group_id));
            json.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(this, Constant.mtg_member_id));
            json.addProperty("form_id", 11);
            json.addProperty("status_receipt", 1);

            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());

  mvpPresenter.saveForm(json);

        }
    }


    private void ShowDialog() {
        ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.form_11));
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
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("status_receipt", 0);
                            jsonObject.put("user_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.USER_ID));
                            jsonObject.put("form_id", 11);
                            jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_member_id));
                            jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_group_id));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }).show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = dateFormatter.format(c.getTime());

        binding.edtStartDate.setText(currentDateString);
        c.add(Calendar.DATE, 90);
        String currentDateString1 = dateFormatter.format(c.getTime());

        binding.edtAfterStart90.setText(currentDateString1);

    }

    @Override
    public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
        if (position == 0 || position==2) {
            binding.layoutMilkproducion3.setVisibility(View.GONE);
            binding.layoutMilkproducion1.setVisibility(View.GONE);
            binding.layoutMilkproducion2.setVisibility(View.GONE);

        }
        if (position ==1){
            binding.layoutMilkproducion3.setVisibility(View.VISIBLE);
            binding.layoutMilkproducion1.setVisibility(View.VISIBLE);
            binding.layoutMilkproducion2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView <?> parent) {

    }
    @Override
    public void onItemClick(AdapterView <?> parent, View view, int position, long id) {

        if (position == 0 || position==2) {
            binding.layoutMilkproducion3.setVisibility(View.GONE);
            binding.layoutMilkproducion1.setVisibility(View.GONE);
            binding.layoutMilkproducion2.setVisibility(View.GONE);

        }if (position ==1){
            binding.layoutMilkproducion3.setVisibility(View.VISIBLE);
            binding.layoutMilkproducion1.setVisibility(View.VISIBLE);
            binding.layoutMilkproducion2.setVisibility(View.VISIBLE);
        }
    }

    private void setDataInSpinner() {

        List <Integer> year = new ArrayList <>();
        for (int i = 2015; i < mDatePickerDialog.getYear(); i++) {
            year.add(i);
        }
        ArrayAdapter <Integer> adapter = new ArrayAdapter <Integer>(this,
                R.layout.spinner_item, year);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spYear.setAdapter(adapter);

        List <Integer> month = new ArrayList <>();
        for (int i = 1; i <= 12; i++) {
            month.add(i);
        }
        ArrayAdapter <Integer> adaptermonth = new ArrayAdapter <Integer>(this,
                R.layout.spinner_item, month);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spMonth.setAdapter(adaptermonth);

        List <String> animal = new ArrayList <>();
        animal.add(getString(R.string.bakra));
        animal.add(getString(R.string.bakari));
        animal.add(getString(R.string.memna));
        ArrayAdapter <String> adapter_animal = new ArrayAdapter <String>(this,
                R.layout.spinner_item, animal);
        adapter_animal.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spAnimalType.setAdapter(adapter_animal);

        List <String> effect = new ArrayList <>();
        effect.add("");
        effect.add(getString(R.string.increase));
        effect.add(getString(R.string.dicrease));
        effect.add(getString(R.string.not_change));


        ArrayAdapter <String> adapter_effect = new ArrayAdapter <String>(this,
                R.layout.spinner_item, effect);
        adapter_effect.setDropDownViewResource(R.layout.spinner_dropdown_item);

        binding.spCoatshining1.setAdapter(adapter_effect);
        binding.spCoatshining2.setAdapter(adapter_effect);
        binding.spCoatshining3.setAdapter(adapter_effect);
        binding.spMilkproduciton1.setAdapter(adapter_effect);
        binding.spMilkproduciton2.setAdapter(adapter_effect);
        binding.spMilkproduciton3.setAdapter(adapter_effect);
        binding.spHeatfrequency1.setAdapter(adapter_effect);
        binding.spHeatfrequency2.setAdapter(adapter_effect);
        binding.spHeatfrequency3.setAdapter(adapter_effect);

    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.showColorDialog(getContext(),commonResult.getMessage());

    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {
        Toast.makeText(getContext(),successResult.getMessage(),Toast.LENGTH_SHORT).show();


        binding.spMonth.setSelection(0);
        binding.spYear.setSelection(0);
        binding.spAnimalType.setSelection(0);

        binding.edtAge.setText("");
        binding.edtTagNo.setText("");
        binding.edtStartweight.setText("");
        binding.edtSupplimetnAmount.setText("");
        binding.edtStartmilkproduction.setText("");
        binding.edtStartDate.setText("");
        binding.edtAfterStart90.setText("");
        binding.edtWeight1.setText("");
        binding.spCoatshining1.setSelection(0);
        binding.spHeatfrequency1.setSelection(0);
        binding.spMilkproduciton1.setSelection(0);
        binding.edtWeight2.setText("");
        binding.spCoatshining2.setSelection(0);
        binding.spHeatfrequency2.setSelection(0);
        binding.spMilkproduciton2.setSelection(0);
        binding.edtWeight3.setText("");
        binding.spCoatshining3.setSelection(0);
        binding.spHeatfrequency3.setSelection(0);
        binding.spMilkproduciton3.setSelection(0);

        record_count =1;
        recordNo =1;
        binding.txtno.setText(String.valueOf(record_count));

        DataFeedSuppliment.deleteAll(DataFeedSuppliment.class);


        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);


    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onSuccessfullyRetrived(RetrivedFeedSupplimentResponse successResult) {

        Log.e(tag,"deta retrived");

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);

        binding.edtTagNo.setFocusable(false);
        binding.edtAge.setFocusable(false);
        binding.edtStartweight.setFocusable(false);
        binding.edtSupplimetnAmount.setFocusable(false);
        binding.edtStartmilkproduction.setFocusable(false);
        binding.edtTagNo.setFocusable(false);
        binding.edtStartDate.setClickable(false);
        binding.edtWeight1.setFocusable(false);
        binding.edtWeight2.setFocusable(false);
        binding.edtWeight3.setFocusable(false);
        binding.spAnimalType.setEnabled(false);
        binding.spCoatshining1.setEnabled(false);
        binding.spCoatshining2.setEnabled(false);
        binding.spCoatshining3.setEnabled(false);
        binding.spHeatfrequency1.setEnabled(false);
        binding.spHeatfrequency2.setEnabled(false);
        binding.spHeatfrequency3.setEnabled(false);
        binding.spMilkproduciton1.setEnabled(false);
        binding.spMilkproduciton2.setEnabled(false);
        binding.spMilkproduciton3.setEnabled(false);


        if (Integer.valueOf(successResult.getData().getAnimaltypeId())==1){
            binding.spAnimalType.setSelection(0);
        }
        if (Integer.valueOf(successResult.getData().getAnimaltypeId())==2){
            binding.spMilkproduciton1.setVisibility(View.VISIBLE);
            binding.spMilkproduciton2.setVisibility(View.VISIBLE);
            binding.spMilkproduciton3.setVisibility(View.VISIBLE);
            binding.spAnimalType.setSelection(1);
        }
        if (Integer.valueOf(successResult.getData().getAnimaltypeId())==3){
            binding.spAnimalType.setSelection(2);
        }


        String weight = successResult.getData().getEffetedWeight();
        String coat_Shining = successResult.getData().getCoatShining();
        String milk_production  =successResult.getData().getMilkProduction();
        String heay_friquendy = successResult.getData().getHeatFrquency();




       // String weight1[] = weight.split(",");


        //Log.e(tag, weight+"****"+weight1[0]);
       /* binding.edtWeight1.setText(String.valueOf(weight1[0]));
        binding.edtWeight2.setText(String.valueOf(weight1[1]));*/
        //binding.edtWeight3.setText(String.valueOf(weight1[2]));











        binding.edtTagNo.setText(String.valueOf(Integer.valueOf(successResult.getData().getTagNo())));
        binding.edtAge.setText(String.valueOf(Integer.valueOf(successResult.getData().getAge())));

        binding.edtStartweight.setText(String.valueOf(successResult.getData().getWeightStart()));

        Log.e(tag,successResult.getData().getTagNo().toString());
        binding.edtStartmilkproduction.setText(String.valueOf(successResult.getData().getMilkProductionStart()));
        binding.edtSupplimetnAmount.setText(String.valueOf(successResult.getData().getFeedSuplimentAmount()));
        binding.edtStartDate.setText(String.valueOf(successResult.getData().getFeedSupplimentStartDate()));
        binding.edtAfterStart90.setText(String.valueOf(successResult.getData().getDateAfter90Days()));

    }

    private void getFormRecordData() {

        JsonObject json = new JsonObject();
        json.addProperty("table_id", table_id);
        json.addProperty("form_id", 11);

        mvpPresenter.getFormRecordData1(json);
    }
}
