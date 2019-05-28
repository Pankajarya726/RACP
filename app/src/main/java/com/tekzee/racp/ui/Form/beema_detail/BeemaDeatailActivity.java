package com.tekzee.racp.ui.Form.beema_detail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

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
import com.tekzee.racp.utils.CalenderUtils;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BeemaDeatailActivity extends MvpActivity <BeemaDetailPresenter> implements BeemaDetailView, View.OnClickListener, Dialogs.okClickListner {

    private static String tag = BeemaDeatailActivity.class.getSimpleName();
    private FormBeemaDeatailBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private List <BeemaDetail> detailList = new ArrayList <>();
    private int table_id;
    private int animaltype_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_beema_deatail);

        getSupportActionBar().setTitle(R.string.form_4);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {
            getFormRecordData();
        } else {
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

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        this.finish();
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

            if (detail.getDeathCondition().equalsIgnoreCase(getString(R.string.yes))) {
                binding.tvDeath.setVisibility(View.VISIBLE);
                binding.edtDateDeath.setVisibility(View.VISIBLE);
                binding.edtDateDeath.setText(detail.getDeath_date());
                binding.checkYes.setChecked(true);
            } else {
                binding.edtDateDeath.setVisibility(View.GONE);
                binding.tvDeath.setVisibility(View.GONE);
                binding.checkNo.setChecked(true);
            }

            DisableView();
        } else {


            ClearView();
            binding.privious.setVisibility(View.VISIBLE);


        }


    }

    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }

        binding.txtno.setText(String.valueOf(record_count));
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

        if (detail.getDeathCondition().equalsIgnoreCase(getString(R.string.yes))) {
            binding.tvDeath.setVisibility(View.VISIBLE);
            binding.edtDateDeath.setVisibility(View.VISIBLE);
            binding.edtDateDeath.setText(detail.getDeath_date());
            binding.checkYes.setChecked(true);
        } else {
            binding.edtDateDeath.setVisibility(View.GONE);
            binding.tvDeath.setVisibility(View.GONE);
            binding.checkNo.setChecked(true);
        }


        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);
        DisableView();


    }

    private boolean addRecordinSqlite() {

        if (binding.edtTagNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno));
            return false;
        } else if (binding.edtDatefrom.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_start_Date));
            return false;
        } else if (binding.edtDateto.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_end_Date));
            return false;
        } else if (binding.edtPolicyNo.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_policy_no));
            return false;
        } else if (!binding.checkNo.isChecked() && !binding.checkYes.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.death_condition));
            return false;
        } else if (binding.checkYes.isChecked() && binding.edtDateDeath.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_date_death));
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
                    String.valueOf(binding.spMonth.getSelectedItemPosition()),
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


            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));

            ClearView();
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
                jsonObject.addProperty("date_from_beema", mDatePickerDialog.changeFormate(detail.getDateFrom()));
                jsonObject.addProperty("date_to_beema", mDatePickerDialog.changeFormate(detail.getDateTo()));
                jsonObject.addProperty("death_stage", detail.getDeathCondition());
                jsonObject.addProperty("claim_receipt_stage", detail.getClaim_condition());
                jsonObject.addProperty("date_death", mDatePickerDialog.changeFormate(detail.getDeath_date()));
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

        CalenderUtils.loadMonths(getContext(),binding.spMonth,binding.spYear);
    }


    private void ShowDialog() {

        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.beema_availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {
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
        });


        /*ColorDialog dialog = new ColorDialog(this);
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
                }).show();*/
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), new Dialogs.okClickListner() {
            @Override
            public void onOkClickListner() {
                finish();
            }
        },"  ");
        BeemaDetail.deleteAll(BeemaDetail.class);
        recordNo = 1;
        record_count = 1;
        ClearView();
        binding.txtno.setText(String.valueOf(record_count));
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);

    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.ShowCustomDialog(getContext(), commonResult.getMessage(), this);
    }

    @Override
    public void onSuccessfullyRetrived(RetrivedBeemaDataResponse successResult) {

        binding.receiptDate.setVisibility(View.VISIBLE);

        DisableView();

        binding.edtTagNo.setText(String.valueOf(successResult.getData().getTagNo()));
        binding.receiptDate.setText(mDatePickerDialog.changeFormate(String.valueOf(successResult.getData().getDateReceipt())));


        binding.edtPolicyNo.setText(String.valueOf(successResult.getData().getPolicyNo()));

        binding.edtDateto.setText(String.valueOf(mDatePickerDialog.changeFormate(successResult.getData().getDateToBeema())));

        binding.edtDatefrom.setText(String.valueOf(mDatePickerDialog.changeFormate(successResult.getData().getDateFromBeema())));


        if (successResult.getData().getDeathStage().equalsIgnoreCase(getString(R.string.yes))) {

            binding.checkYes.setChecked(true);
            binding.edtDateDeath.setVisibility(View.VISIBLE);
            binding.tvDeath.setVisibility(View.VISIBLE);
            binding.edtDateDeath.setClickable(false);
            binding.edtDateDeath.setText(mDatePickerDialog.changeFormate(String.valueOf(successResult.getData().getDateDeath())));
        } else {
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

    private void ClearView() {
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
        EnableView();
    }

    private void DisableView() {
        binding.edtTagNo.setEnabled(false);
        binding.spAnimalType.setEnabled(false);
        binding.edtPolicyNo.setEnabled(false);
        binding.edtDateto.setClickable(false);
        binding.edtDatefrom.setEnabled(false);
        binding.checkYes.setClickable(false);
        binding.checkNo.setClickable(false);
        binding.spMonth.setEnabled(false);
        binding.spYear.setEnabled(false);
        binding.day.setEnabled(false);
    }

    private void EnableView() {
        binding.edtTagNo.setEnabled(true);
        binding.spAnimalType.setEnabled(true);
        binding.edtPolicyNo.setEnabled(true);
        binding.edtDateto.setClickable(true);
        binding.edtDatefrom.setEnabled(true);
        binding.checkYes.setClickable(true);
        binding.checkNo.setClickable(true);
        binding.spMonth.setEnabled(true);
        binding.spYear.setEnabled(true);
        binding.day.setEnabled(true);
    }

    @Override
    public void onOkClickListner() {
        this.finish();
    }
}
