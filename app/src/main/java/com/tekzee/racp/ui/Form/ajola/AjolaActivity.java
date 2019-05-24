package com.tekzee.racp.ui.Form.ajola;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormAjolaBinding;
import com.tekzee.racp.ui.Form.ajola.model.DataAjola;
import com.tekzee.racp.ui.Form.ajola.model.Datum;
import com.tekzee.racp.ui.Form.ajola.model.GetAnimalTypeResponse;
import com.tekzee.racp.ui.Form.ajola.model.RetrivedAjolaResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AjolaActivity extends MvpActivity <AjolaPresenter> implements AjolaView, View.OnClickListener, Dialogs.okClickListner {
    private static String tag = AjolaActivity.class.getSimpleName();
    List <Datum> animal = new ArrayList <>();
    private FormAjolaBinding binding;
    private List <DataAjola> detailList = new ArrayList <>();
    private int recordNo = 1;
    private int record_count = 1;
    private int table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_ajola);
        getSupportActionBar().setTitle(R.string.form_10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table_id = getIntent().getIntExtra("table_id", 0);


        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowDialog();
            SpinnerData();
        }


        DataAjola.deleteAll(DataAjola.class);

        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.checkProofNo.setOnClickListener(this);
        binding.checkProofYes.setOnClickListener(this);
        binding.checkYes.setOnClickListener(this);
        binding.checkNo.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.layoutFaitpercent.setVisibility(View.GONE);

    }

    @Override
    protected AjolaPresenter createPresenter() {
        return new AjolaPresenter(this);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //startActivity(new Intent(ForSale.this, HomeActivity.class));
            this.finish();
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

            case R.id.next:
                next();
                break;

            case R.id.privious:
                privious();
                break;

            case R.id.tv_save:
                try {
                    submitRecord();
                } catch (JSONException e) {

                }
                break;


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


            case R.id.check_yes:
                if (binding.checkYes.isChecked()) {
                    if (binding.checkNo.isChecked()) {
                        binding.checkNo.setChecked(false);
                    }
                    binding.checkYes.setChecked(true);
                    binding.layoutFaitpercent.setVisibility(View.VISIBLE);

                } else {
                    binding.checkYes.setChecked(false);
                }
                break;


            case R.id.check_no:
                if (binding.checkNo.isChecked()) {
                    if (binding.checkYes.isChecked()) {
                        binding.checkYes.setChecked(false);
                    }
                    binding.checkNo.setChecked(true);
                    binding.layoutFaitpercent.setVisibility(View.GONE);
                } else {
                    binding.checkNo.setChecked(false);
                }
                break;
        }

    }

    private Boolean addRecordinSqlite() {

        Log.e(tag, "size is" + detailList.size());

        if (!binding.checkProofYes.isChecked() && !binding.checkProofNo.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.physical_proof));
            return false;
        } else if (!binding.checkYes.isChecked() && !binding.checkNo.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.fait_percent_check));
            return false;
        } else if (binding.edtProductionAverage.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_average_milk_production));
            return false;
        } else if (binding.edtAmountMilkBefore.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_milk_amount_before_ajola));
            return false;
        } else if (binding.edtAmountMilkAfter.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_milk_amount_after_ajola));
            return false;
        } else if (binding.checkYes.isChecked() && binding.edtFaitBefore.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_fait_percent_before));
            return false;

        } else if (binding.checkYes.isChecked() && binding.edtFaitAfter.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_fait_percent_after));
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

                    Dialogs.showColorDialog(getContext(), getString(R.string.invalid_avail_date));
                    return false;
                }
            }
            String proof, use, faitpercent_before, faitpercent_after;
            if (binding.checkYes.isChecked()) {
                use = binding.checkYes.getText().toString();
                faitpercent_before = binding.edtFaitBefore.getText().toString();
                faitpercent_after = binding.edtFaitAfter.getText().toString();
            } else {
                use = binding.checkNo.getText().toString();
                faitpercent_before = binding.edtFaitBefore.getText().toString();
                faitpercent_after = binding.edtFaitAfter.getText().toString();
            }


            if (binding.checkProofYes.isChecked()) {
                proof = binding.checkProofYes.getText().toString();
            } else {
                proof = binding.checkProofNo.getText().toString();
            }


            DataAjola details = new DataAjola(day,
                    binding.spMonth.getSelectedItem().toString(),
                    binding.spMonth.getSelectedItemPosition(),
                    binding.spYear.getSelectedItem().toString(),
                    binding.spYear.getSelectedItemPosition(),
                    proof,
                    binding.checkProofYes.isChecked(),
                    binding.spType.getSelectedItem().toString(),
                    binding.spType.getSelectedItemPosition(),
                    binding.edtProductionAverage.getText().toString(),
                    binding.edtAmountMilkBefore.getText().toString(),
                    binding.edtAmountMilkAfter.getText().toString(),
                    use,
                    binding.checkYes.isChecked(),
                    binding.edtFaitBefore.getText().toString(),
                    binding.edtFaitAfter.getText().toString());

            details.save();

            Toast.makeText(this, "record added", Toast.LENGTH_LONG).show();
            Log.e(tag, "recoud count" + DataAjola.count(DataAjola.class));

            recordNo = recordNo + 1;
            binding.tvNo.setText(String.valueOf(recordNo));

            ClearVeiw();


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
        binding.tvNo.setText(String.valueOf(record_count));

        detailList.clear();
        detailList = DataAjola.listAll(DataAjola.class);

        if (record_count <= DataAjola.count(DataAjola.class)) {

            DataAjola detail = detailList.get(record_count - 1);

            if (detail.isPhisical_proof()) {
                binding.checkProofYes.setChecked(true);
                binding.checkProofNo.setChecked(false);
            } else {
                binding.checkProofNo.setChecked(true);
                binding.checkProofYes.setChecked(false);
            }

            if (detail.isFait_check()) {
                Log.e(tag, "true" + detail.isFait_check());
                binding.checkYes.setChecked(true);
                binding.checkNo.setChecked(false);
                binding.layoutFaitpercent.setVisibility(View.VISIBLE);
                binding.edtFaitBefore.setText(detail.getFait_before());
                binding.edtFaitAfter.setText(detail.getFait_after());
            } else {
                Log.e(tag, "false" + detail.isFait_check());
                binding.checkNo.setChecked(true);
                binding.checkYes.setChecked(false);
                binding.layoutFaitpercent.setVisibility(View.GONE);

            }

            binding.day.setText(detail.getDd());
            binding.spMonth.setSelection(detail.getMm_id());
            binding.spYear.setSelection(detail.getYy_id());
            binding.spType.setSelection(detail.getAnimal_id());
            binding.edtProductionAverage.setText(detail.getAverage_milk_production());
            binding.edtAmountMilkBefore.setText(detail.getMilk_amount_before());
            binding.edtAmountMilkAfter.setText(detail.getMilk_amount_after());
            binding.tvAddRecord.setVisibility(View.GONE);
            binding.next.setVisibility(View.VISIBLE);
            DisableView();

        } else {

            ClearVeiw();

        }

    }


    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }

        Log.e(tag, String.valueOf(DataAjola.count(DataAjola.class)));

        binding.tvNo.setText(String.valueOf(record_count));

        detailList = DataAjola.listAll(DataAjola.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));

        DataAjola detail = detailList.get(record_count - 1);

        if (detail.isPhisical_proof()) {
            binding.checkProofYes.setChecked(true);
            binding.checkProofNo.setChecked(false);
        } else {
            binding.checkProofNo.setChecked(true);
            binding.checkProofYes.setChecked(false);
        }

        if (detail.isFait_check()) {
            Log.e(tag, "true" + detail.isFait_check());
            binding.checkYes.setChecked(true);
            binding.checkNo.setChecked(false);
            binding.layoutFaitpercent.setVisibility(View.VISIBLE);
            binding.edtFaitBefore.setText(detail.getFait_before());
            binding.edtFaitAfter.setText(detail.getFait_after());


        } else {
            Log.e(tag, "false" + detail.isFait_check());
            binding.checkNo.setChecked(true);
            binding.checkYes.setChecked(false);
            binding.layoutFaitpercent.setVisibility(View.GONE);

        }

        binding.day.setText(detail.getDd());
        binding.spMonth.setSelection(detail.getMm_id());
        binding.spYear.setSelection(detail.getYy_id());
        binding.spType.setSelection(detail.getAnimal_id());
        binding.edtProductionAverage.setText(detail.getAverage_milk_production());
        binding.edtAmountMilkBefore.setText(detail.getMilk_amount_before());
        binding.edtAmountMilkAfter.setText(detail.getMilk_amount_after());
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);
        DisableView();


    }

    private void submitRecord() throws JSONException {


        if (addRecordinSqlite()) {

            detailList.clear();
            detailList = DataAjola.listAll(DataAjola.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();


            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();
                DataAjola detail = detailList.get(i);


                for (int k = 0; k < animal.size(); k++) {

                    Datum datum = animal.get(k);
                    Log.e(tag, detail.getAnimal_type());
                    Log.e(tag, datum.getAnimaltypeName());

                    if (detail.getAnimal_type().equalsIgnoreCase(datum.getAnimaltypeName())) {
                        Log.e(tag, "animal type id" + datum.getAnimaltypeId());

                        jsonObject.addProperty("animaltype_id", datum.getAnimaltypeId());
                    }

                }


                jsonObject.addProperty("physical_proof", detail.getProof());
                jsonObject.addProperty("average_milk_production", detail.getAverage_milk_production());
                jsonObject.addProperty("fat_check_status", detail.getFaitpercent());
                jsonObject.addProperty("fat_before_ajola", detail.getFait_before());
                jsonObject.addProperty("fat_after_ajola", detail.getFait_after());
                jsonObject.addProperty("milk_quantity_before_ajola", detail.getMilk_amount_before());
                jsonObject.addProperty("milk_quantity_after_ajola", detail.getMilk_amount_after());
                jsonObject.addProperty("dd", detail.getDd());
                jsonObject.addProperty("mm", detail.getMm());
                jsonObject.addProperty("yy", detail.getYy());
                jsonArray.add(jsonObject);


            }
            JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(this, Constant.mtg_group_id));
            json.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(this, Constant.mtg_member_id));
            json.addProperty("form_id", 10);
            json.addProperty("status_receipt", 1);

            mvpPresenter.saveForm(json);

            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());
        }

    }


    public void ClearVeiw() {
        binding.spMonth.setSelection(0);
        binding.spYear.setSelection(0);
        binding.spType.setSelection(0);
        binding.checkProofYes.setChecked(false);
        binding.checkProofNo.setChecked(false);
        binding.checkYes.setChecked(false);
        binding.checkNo.setChecked(false);
        binding.edtAmountMilkAfter.setText("");
        binding.edtAmountMilkBefore.setText("");
        binding.edtProductionAverage.setText("");
        binding.edtFaitAfter.setText("");
        binding.edtFaitBefore.setText("");
        binding.layoutFaitpercent.setVisibility(View.GONE);
        EnableView();
    }

    public void DisableView() {
        binding.checkProofYes.setClickable(false);
        binding.checkProofNo.setClickable(false);
        binding.checkNo.setClickable(false);
        binding.checkYes.setClickable(false);
        binding.edtAmountMilkAfter.setEnabled(false);
        binding.edtAmountMilkBefore.setEnabled(false);
        binding.edtProductionAverage.setEnabled(false);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);
        binding.edtFaitAfter.setEnabled(false);
        binding.edtFaitBefore.setEnabled(false);
        binding.spType.setEnabled(false);

    }

    public void EnableView() {
        binding.checkProofYes.setClickable(true);
        binding.checkProofNo.setClickable(true);
        binding.checkNo.setClickable(true);
        binding.checkYes.setClickable(true);
        binding.edtAmountMilkAfter.setEnabled(true);
        binding.edtAmountMilkBefore.setEnabled(true);
        binding.edtProductionAverage.setEnabled(true);
        binding.tvAddRecord.setVisibility(View.VISIBLE);
        binding.tvSave.setVisibility(View.VISIBLE);
        binding.edtFaitAfter.setEnabled(true);
        binding.edtFaitBefore.setEnabled(true);
        binding.spType.setEnabled(true);
    }


    private void SpinnerData() {

        mvpPresenter.getAnimalType();

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

        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("status_receipt", 0);
                    jsonObject.put("user_id", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                    jsonObject.put("form_id", 10);
                    jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_member_id));
                    jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_group_id));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });



       /* ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.form_10));
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
                            jsonObject.put("user_id", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                            jsonObject.put("form_id", 10);
                            jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_member_id));
                            jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_group_id));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }).show();*/
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {
        //Toast.makeText(getContext(), successResult.getMessage(), Toast.LENGTH_SHORT).show();
        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), this);

        DataAjola.deleteAll(DataAjola.class);

        record_count = 1;
        recordNo = 1;


        binding.tvNo.setText(String.valueOf(record_count));
        ClearVeiw();

        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);


    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {

        // Toast.makeText(getContext(), commonResult.getMessage(), Toast.LENGTH_SHORT).show();
        Dialogs.showColorDialog(getContext(), commonResult.getMessage());

    }

    @Override
    public void onGetAnimelType(GetAnimalTypeResponse successResult) {

        animal.addAll(successResult.getData());

        Log.e(tag, "" + animal.size());

        List <String> type = new ArrayList <>();
        for (int i = 0; i < animal.size(); i++) {
            Datum datum = animal.get(i);
            type.add(datum.getAnimaltypeName());

        }
        ArrayAdapter <String> adapter = new ArrayAdapter <String>(this,
                R.layout.spinner_item, type);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spType.setAdapter(adapter);

    }

    @Override
    public void onSuccessfullyRetrived(RetrivedAjolaResponse successResult) {

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);

        DisableView();


        binding.layoutAnimelType.setVisibility(View.GONE);
        binding.animal.setVisibility(View.VISIBLE);
        binding.receiptDate.setVisibility(View.VISIBLE);


        binding.edtProductionAverage.setText(String.valueOf(successResult.getData().getAverageMilkProduction()));
        binding.edtAmountMilkBefore.setText(String.valueOf(successResult.getData().getMilkQuantityBeforeAjola()));
        binding.edtAmountMilkAfter.setText(String.valueOf(successResult.getData().getMilkQuantityAfterAjola()));


        binding.receiptDate.setText(String.valueOf(successResult.getData().getDateReceipt()));
        if (successResult.getData().getPhysicalProof().equalsIgnoreCase(getString(R.string.yes))) {
            binding.checkProofYes.setChecked(true);
        } else {
            binding.checkProofNo.setChecked(true);
        }


        Log.e(tag, "animal type id" + successResult.getData().getAnimaltypeId());
        if (Integer.valueOf(successResult.getData().getAnimaltypeId()) == 2) {
            binding.animal.setText(getString(R.string.bakari));
        }
        if (Integer.valueOf(successResult.getData().getAnimaltypeId()) == 5) {
            binding.animal.setText(getString(R.string.cow));
        }
        if (Integer.valueOf(successResult.getData().getAnimaltypeId()) == 6) {
            binding.animal.setText(getString(R.string.bhais));
        }

        if (successResult.getData().getFatCheckStatus().equalsIgnoreCase(getString(R.string.yes))) {
            binding.checkYes.setChecked(true);
            binding.layoutFaitpercent.setVisibility(View.VISIBLE);
            binding.edtFaitBefore.setText(String.valueOf(successResult.getData().getFatBeforeAjola()));
            binding.edtFaitAfter.setText(String.valueOf(successResult.getData().getFatAfterAjola()));

        } else {
            binding.checkNo.setChecked(true);
        }


    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 10);

        mvpPresenter.getFormRecordData(jsonObject);
    }

    @Override
    public void onOkClickListner() {

        this.finish();
    }


}
