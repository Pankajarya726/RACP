package com.tekzee.racp.ui.Form.beema_detail;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormBeemaDeatailBinding;
import com.tekzee.racp.ui.Form.beema_detail.model.BeemaDetail;
import com.tekzee.racp.ui.Form.beema_detail.model.RetrivedBeemaDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.SnackbarUtils;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;

public class BeemaDeatailActivity extends MvpActivity <BeemaDetailPresenter> implements BeemaDetailView, View.OnClickListener {

    private static String tag = BeemaDeatailActivity.class.getSimpleName();
    private FormBeemaDeatailBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private List <BeemaDetail> detailList = new ArrayList <>();
    private int table_id;
    private int animaltype_id= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_beema_deatail);

        getSupportActionBar().setTitle(R.string.form_4);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table_id = getIntent().getIntExtra("table_id",0);
        if (table_id != 0){
            getFormRecordData();
        }else {
            ShowDialog();
        }


        SpinnerData();
        BeemaDetail.deleteAll(BeemaDetail.class);

        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.txtno.setText(String.valueOf(recordNo));
        binding.edtDatefrom.setOnClickListener(this);
        binding.edtDateto.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.checkNo.setOnClickListener(this);
        binding.edtDateDeath.setOnClickListener(this);
        binding.checkYes.setOnClickListener(this);
        binding.radioClaim.check(R.id.radio_yes);

        if (binding.checkYes.isChecked()) {
            binding.edtDateDeath.setVisibility(View.VISIBLE);
            binding.tvDeath.setVisibility(View.VISIBLE);
        } else {
            binding.edtDateDeath.setVisibility(View.GONE);
            binding.tvDeath.setVisibility(View.GONE);
        }
    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 4);

        mvpPresenter.getFormRecordData(jsonObject);
    }

    @Override
    protected BeemaDetailPresenter createPresenter() {
        return new BeemaDetailPresenter(this);
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

            case R.id.check_yes:
                if (binding.checkYes.isChecked()) {
                    if (binding.checkNo.isChecked()) {
                        binding.checkNo.setChecked(false);
                    }
                    binding.checkYes.setChecked(true);
                    binding.edtDateDeath.setVisibility(View.VISIBLE);
                    binding.tvDeath.setVisibility(View.VISIBLE);

                } else {
                    binding.checkYes.setChecked(false);
                    binding.edtDateDeath.setVisibility(View.GONE);
                    binding.tvDeath.setVisibility(View.GONE);
                }
                break;


            case R.id.check_no:

                if (binding.checkNo.isChecked()) {
                    if (binding.checkYes.isChecked()) {
                        binding.checkYes.setChecked(false);
                    }
                    binding.checkNo.setChecked(true);
                    binding.edtDateDeath.setVisibility(View.GONE);
                    binding.tvDeath.setVisibility(View.GONE);
                } else {
                    binding.checkNo.setChecked(false);
                    binding.edtDateDeath.setVisibility(View.VISIBLE);
                    binding.tvDeath.setVisibility(View.VISIBLE);
                }
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
                    e.printStackTrace();
                }
                break;

            case R.id.tv_addRecord:
                addRecordinSqlite();
                break;

            case R.id.edt_date_death:
                mDatePickerDialog.getdate(this, binding.edtDateDeath);
                break;

            case R.id.edt_datefrom:
                mDatePickerDialog.getdate(this, binding.edtDatefrom);
                break;
            case R.id.edt_dateto:
                mDatePickerDialog.getdate(this, binding.edtDateto);
                break;
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

        detailList = BeemaDetail.listAll(BeemaDetail.class);


        if (record_count <= BeemaDetail.count(BeemaDetail.class)) {

            BeemaDetail detail = detailList.get(record_count - 1);

            binding.day.setText(detail.getDd());
            binding.spMonth.setSelection(Integer.valueOf(detail.getMm()) - 1);
            binding.spAnimalType.setSelection(detail.getAnimal_id());
            binding.edtPolicyNo.setText(detail.getPolicy_no());
            binding.edtTagNo.setText(String.valueOf(detail.getTagNo()));
            binding.edtDatefrom.setText(detail.getDateFrom());
            binding.edtDateto.setText(detail.getDateTo());

            if (detail.getDeathCondition().equalsIgnoreCase(getString(R.string.yes))){
                binding.tvDeath.setVisibility(View.VISIBLE);
                binding.edtDateDeath.setVisibility(View.VISIBLE);
                binding.edtDateDeath.setText(detail.getDeath_date());
                binding.checkYes.setChecked(true);
            }else {
                binding.edtDateDeath.setVisibility(View.GONE);
                binding.tvDeath.setVisibility(View.GONE);
                binding.checkNo.setChecked(true);
            }

        } else {

            binding.day.setText("");
            binding.spMonth.setSelection(0);
            binding.edtTagNo.setText("");
            binding.edtPolicyNo.setText("");
            binding.edtDateto.setText("");
            binding.edtDatefrom.setText("");
            binding.checkYes.setChecked(false);
            binding.checkNo.setChecked(false);
            binding.edtDateDeath.setText("");
            binding.tvDeath.setVisibility(View.GONE);
            binding.edtDateDeath.setVisibility(View.GONE);
            binding.privious.setVisibility(View.VISIBLE);


        }


    }

    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }
        Log.e(tag, String.valueOf(BeemaDetail.count(BeemaDetail.class)));

        // BeemaDetail beemaDetail =   BeemaDetail.findById(BeemaDetail.class,record_count);

        detailList = BeemaDetail.listAll(BeemaDetail.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));

        BeemaDetail detail = detailList.get(record_count - 1);

        binding.edtTagNo.setText(String.valueOf(detail.getTagNo()));
        binding.edtDatefrom.setText(detail.getDateFrom());
        binding.edtDateto.setText(detail.getDateTo());
        binding.spAnimalType.setSelection(detail.getAnimal_id());
        binding.day.setText(detail.getDd());
        binding.edtPolicyNo.setText(detail.getPolicy_no());

        if (detail.getDeathCondition().equalsIgnoreCase(getString(R.string.yes))){
            binding.tvDeath.setVisibility(View.VISIBLE);
            binding.edtDateDeath.setVisibility(View.VISIBLE);
            binding.edtDateDeath.setText(detail.getDeath_date());
            binding.checkYes.setChecked(true);
        }else {
            binding.edtDateDeath.setVisibility(View.GONE);
            binding.tvDeath.setVisibility(View.GONE);
            binding.checkNo.setChecked(true);
        }



        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);


    }

    private boolean addRecordinSqlite() {

        if (binding.edtTagNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_tagno));
            return false;
        } else if (binding.edtDatefrom.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_start_Date));
            return false;
        } else if (binding.edtDateto.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_end_Date));
            return false;
        } else if (binding.edtPolicyNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_policy_no));
            return false;
        } else if (!binding.checkNo.isChecked() && !binding.checkYes.isChecked()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.death_condition));
            return false;
        } else if (binding.checkYes.isChecked() && binding.edtDateDeath.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_date_death));
            return false;

        } else {

            String day;
            if (binding.day.getText().toString().isEmpty()) {
                day = "01";

            }
            else {
                day = binding.day.getText().toString();
                if (Integer.valueOf(binding.day.getText().toString()) > 31) {
                    Dialogs.showColorDialog(getContext(),getString(R.string.invalid_Date));
                    return false;
                }
            }

            Toast.makeText(this, "record added" + BeemaDetail.count(BeemaDetail.class), Toast.LENGTH_LONG).show();

            String death_date;
            String condition;

            int selectId = binding.radioClaim.getCheckedRadioButtonId();
            Log.e(tag, "" + binding.radioClaim.getCheckedRadioButtonId());

            RadioButton radioButton;
            radioButton = findViewById(selectId);
            radioButton.getText().toString();
            if (binding.checkYes.isChecked()) {

                condition = binding.checkYes.getText().toString();
                death_date = binding.edtDateDeath.getText().toString();
                Log.e(tag, "" + condition + "" + death_date);
            } else {
                condition = binding.checkNo.getText().toString();
                death_date = "";
            }

            BeemaDetail beemaDetail = new BeemaDetail(day,
                    binding.spMonth.getSelectedItem().toString(),
                    binding.spYear.getSelectedItem().toString(),
                    binding.spAnimalType.getSelectedItem().toString(),
                    binding.spAnimalType.getSelectedItemPosition(),
                    binding.edtTagNo.getText().toString(),
                    binding.edtPolicyNo.getText().toString(),
                    binding.edtDatefrom.getText().toString(),
                    binding.edtDateto.getText().toString(),
                    condition,
                    death_date,
                    radioButton.getText().toString());
            beemaDetail.save();

            Toast.makeText(this, "record added" + BeemaDetail.count(BeemaDetail.class), Toast.LENGTH_LONG).show();

            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));
            binding.day.setText("");
            binding.spMonth.setSelection(0);
            binding.edtTagNo.setText("");
            binding.edtPolicyNo.setText("");
            binding.edtDateto.setText("");
            binding.edtDatefrom.setText("");
            binding.checkYes.setChecked(false);
            binding.checkNo.setChecked(false);
            binding.edtDateDeath.setText("");
            binding.tvDeath.setVisibility(View.GONE);
            binding.edtDateDeath.setVisibility(View.GONE);
            binding.privious.setVisibility(View.VISIBLE);
            record_count++;
            return true;

        }

    }

    private void submitRecord() throws JSONException {

        if (addRecordinSqlite()) {

            detailList.clear();
            detailList = BeemaDetail.listAll(BeemaDetail.class);
            Log.e(tag, "size is" + detailList.size());

            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                BeemaDetail detail = detailList.get(i);

                jsonObject.addProperty("animaltype_id", detail.getAnimal_id() + 1);
                jsonObject.addProperty("tag_no", detail.getTagNo());
                jsonObject.addProperty("date_from_beema", detail.getDateFrom());
                jsonObject.addProperty("date_to_beema", detail.getDateTo());
                jsonObject.addProperty("death_stage", detail.getDeathCondition());
                jsonObject.addProperty("claim_receipt_stage", detail.getClaim_condition());
                jsonObject.addProperty("date_death", detail.getDeath_date());
                jsonObject.addProperty("policy_no", detail.getPolicy_no());
                jsonObject.addProperty("dd", detail.getDd());
                jsonObject.addProperty("mm", detail.getMm());
                jsonObject.addProperty("yy", detail.getYy());
                jsonArray.add(jsonObject);

            }
            JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(this, Constant.mtg_group_id));
            json.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(this, Constant.mtg_member_id));
            json.addProperty("form_id", 4);
            json.addProperty("status_receipt", 1);

            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());

            mvpPresenter.saveForm(json);

        }
    }

    private void SpinnerData() {
        List <String> animal = new ArrayList <>();
        animal.add(getString(R.string.bakra));
        animal.add(getString(R.string.bakari));
        ArrayAdapter <String> adapter_animal = new ArrayAdapter <String>(this,
                R.layout.spinner_item, animal);
        adapter_animal.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spAnimalType.setAdapter(adapter_animal);

        List <Integer> month = new ArrayList <>();
        for (int i = 1; i <= 12; i++) {
            month.add(i);
        }
        ArrayAdapter <Integer> adapter1 = new ArrayAdapter <Integer>(this,
                R.layout.spinner_item, month);
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spMonth.setAdapter(adapter1);


        List <Integer> year = new ArrayList <>();
        for (int i = 2015; i <= 2030; i++) {
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
        dialog.setTitle(getResources().getString(R.string.form_1));
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
                            jsonObject.put("form_id", 4);
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
    public Context getContext() {
        return this;
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowDialog(getContext(), successResult.getMessage());
        BeemaDetail.deleteAll(BeemaDetail.class);
        recordNo = 1;
        record_count = 1;
        binding.checkNo.setChecked(false);
        binding.checkYes.setChecked(false);
        binding.edtDateDeath.setVisibility(View.GONE);
        binding.tvDeath.setVisibility(View.GONE);
        binding.txtno.setText(String.valueOf(record_count));
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);

    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.ShowDialog(getContext(), commonResult.getMessage());

    }

    @Override
    public void onSuccessfullyRetrived(RetrivedBeemaDataResponse successResult) {

        binding.receiptDate.setVisibility(View.VISIBLE);
        binding.edtTagNo.setFocusable(false);
        binding.edtTagNo.setText(String.valueOf(successResult.getData().getTagNo()));
        binding.receiptDate.setText(String.valueOf(successResult.getData().getDateReceipt()));



        binding.edtPolicyNo.setText(String.valueOf(successResult.getData().getPolicyNo()));
        binding.edtPolicyNo.setFocusable(false);

        binding.edtDateto.setText(String.valueOf(successResult.getData().getDateToBeema()));
        binding.edtDateto.setClickable(false);

        binding.edtDatefrom.setText(String.valueOf(successResult.getData().getDateFromBeema()));
        binding.edtDatefrom.setFocusable(false);

        binding.checkYes.setClickable(false);
        binding.checkNo.setClickable(false);

        if (successResult.getData().getDeathStage().equalsIgnoreCase(getString(R.string.yes))){

            binding.checkYes.setChecked(true);
            binding.edtDateDeath.setVisibility(View.VISIBLE);
            binding.edtDateDeath.setClickable(false);
            binding.edtDateDeath.setText(String.valueOf(successResult.getData().getDateDeath()));
        }
        else {
            binding.checkNo.setChecked(true);
        }
        /*binding.edtNote.setText(String.valueOf(successResult.getData().getn()));
        binding.edtNote.setFocusable(false);*/

        binding.claincondition.setText(String.valueOf(successResult.getData().getClaimReceiptStage()));
        binding.claincondition.setVisibility(View.VISIBLE);
       binding.layoutRadioGroup.setVisibility(View.GONE);

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);

    }
}
