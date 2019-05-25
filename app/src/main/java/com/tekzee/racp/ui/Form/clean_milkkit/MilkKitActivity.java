package com.tekzee.racp.ui.Form.clean_milkkit;

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
import com.tekzee.racp.databinding.FormMilkKitBinding;
import com.tekzee.racp.ui.Form.beema_detail.model.BeemaDetail;
import com.tekzee.racp.ui.Form.clean_milkkit.model.DataCleanMilkKit;
import com.tekzee.racp.ui.Form.clean_milkkit.model.RetrivedMilkKitResponse;
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

public class MilkKitActivity extends MvpActivity <MilkKitPresenter> implements MilkKitView, View.OnClickListener ,Dialogs.okClickListner{
    private static String tag = MilkKitActivity.class.getSimpleName();
    int record_count = 1;
    int recordNo = 1;
    private FormMilkKitBinding binding;
    private int table_id;
    private List <DataCleanMilkKit> detailList = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_milk_kit);

        getSupportActionBar().setTitle(R.string.form_7);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowDialog();
        }


        SpinnerData();

        DataCleanMilkKit.deleteAll(DataCleanMilkKit.class);
        binding.edtDatevisiting.setText(mDatePickerDialog.showDate());
        binding.txtno.setText(String.valueOf(record_count));
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.checkAvailableNo.setOnClickListener(this);
        binding.checkAvailableYes.setOnClickListener(this);
        binding.checkProofYes.setOnClickListener(this);
        binding.checkProofNo.setOnClickListener(this);
        binding.checkUseYes.setOnClickListener(this);
        binding.checkUseNo.setOnClickListener(this);
        binding.checkPossitive.setOnClickListener(this);
        binding.checkNegative.setOnClickListener(this);
        binding.edtTestDate.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.privious.setOnClickListener(this);

    }

    @Override
    protected MilkKitPresenter createPresenter() {
        return new MilkKitPresenter(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.privious:
                privious();
                Log.e(tag, "privious is" + detailList.size());
                break;

            case R.id.next:
                next();
                Log.e(tag, "next is" + detailList.size());
                break;

            case R.id.tv_addRecord:
                addRecordinSqlite();
                Log.e(tag, "tv_addRecord is" + detailList.size());
                break;

            case R.id.tv_save:
                try {
                    submitRecord();
                } catch (JSONException e) {
                    e.printStackTrace();
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

            case R.id.check_available_yes:
                if (binding.checkAvailableYes.isChecked()) {
                    if (binding.checkAvailableNo.isChecked()) {
                        binding.checkAvailableNo.setChecked(false);
                    }
                    binding.checkAvailableYes.setChecked(true);
                } else {
                    binding.checkAvailableYes.setChecked(false);
                }
                break;

            case R.id.check_available_no:
                if (binding.checkAvailableNo.isChecked()) {
                    if (binding.checkAvailableYes.isChecked()) {
                        binding.checkAvailableYes.setChecked(false);
                    }
                    binding.checkAvailableNo.setChecked(true);
                } else {
                    binding.checkAvailableNo.setChecked(false);
                }
                break;

            case R.id.check_possitive:
                if (binding.checkPossitive.isChecked()) {
                    if (binding.checkNegative.isChecked()) {
                        binding.checkNegative.setChecked(false);
                    }
                    binding.checkPossitive.setChecked(true);
                } else {
                    binding.checkPossitive.setChecked(false);
                }
                break;

            case R.id.check_negative:
                if (binding.checkNegative.isChecked()) {
                    if (binding.checkPossitive.isChecked()) {
                        binding.checkPossitive.setChecked(false);
                    }
                    binding.checkNegative.setChecked(true);
                } else {
                    binding.checkNegative.setChecked(false);
                }
                break;


            case R.id.edt_test_date:
                mDatePickerDialog.getdate(this, binding.edtTestDate);
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

        // detailList = BeemaDetail.listAll(BeemaDetail.class);
        detailList.clear();
        detailList = DataCleanMilkKit.listAll(DataCleanMilkKit.class);


        if (detailList.size() >= record_count) {

            DataCleanMilkKit detail = detailList.get(record_count - 1);

            if (detail.isProof()) {
                binding.checkProofYes.setChecked(true);
                binding.checkProofNo.setChecked(false);
            } else {
                binding.checkProofNo.setChecked(true);
                binding.checkProofYes.setChecked(false);
            }

            if (detail.isUse()) {
                Log.e(tag, "true" + detail.isUse());
                binding.checkUseYes.setChecked(true);
                binding.checkUseNo.setChecked(false);
            } else {
                Log.e(tag, "false" + detail.isUse());
                binding.checkUseNo.setChecked(true);
                binding.checkUseYes.setChecked(false);

            }

            if (detail.isAvailability()) {
                binding.checkAvailableYes.setChecked(true);
                binding.checkAvailableNo.setChecked(false);
            } else {
                binding.checkAvailableNo.setChecked(true);
                binding.checkAvailableYes.setChecked(false);
            }


            if (detail.isResult()) {
                binding.checkPossitive.setChecked(true);
                binding.checkNegative.setChecked(false);
            } else {
                binding.checkNegative.setChecked(true);
                binding.checkPossitive.setChecked(false);
            }


            binding.txtno.setText(String.valueOf(record_count));
            binding.day.setText(detail.getDd());
            binding.spMonth.setSelection(detail.getMm_id());
            binding.spYear.setSelection(detail.getYy_id());
            binding.edtTestDate.setText(detail.getTest_date());
            binding.edtBeforeuse.setText(detail.getBefore_use());
            binding.edtAfteruser.setText(detail.getAfter_use());
            binding.edtNote.setText(detail.getNote());
            DisableView();
        } else {

            ClearView();
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


        List <DataCleanMilkKit> list = new ArrayList <>();
        detailList.clear();

        list = DataCleanMilkKit.listAll(DataCleanMilkKit.class);

        Log.e(tag, String.valueOf(list.size()));
        Log.e(tag, String.valueOf(record_count));

        DataCleanMilkKit detail = list.get(record_count - 1);

        if (detail.isProof()) {
            binding.checkProofYes.setChecked(true);
            binding.checkProofNo.setChecked(false);
        } else {
            binding.checkProofNo.setChecked(true);
            binding.checkProofYes.setChecked(false);
        }

        if (detail.isUse()) {
            Log.e(tag, "true" + detail.isUse());
            binding.checkUseYes.setChecked(true);
            binding.checkUseNo.setChecked(false);
        } else {
            Log.e(tag, "false" + detail.isUse());
            binding.checkUseNo.setChecked(true);
            binding.checkUseYes.setChecked(false);

        }

        if (detail.isAvailability()) {
            binding.checkAvailableYes.setChecked(true);
            binding.checkAvailableNo.setChecked(false);
        } else {
            binding.checkAvailableNo.setChecked(true);
            binding.checkAvailableYes.setChecked(false);
        }


        if (detail.isResult()) {
            binding.checkPossitive.setChecked(true);
            binding.checkNegative.setChecked(false);
        } else {
            binding.checkNegative.setChecked(true);
            binding.checkPossitive.setChecked(false);
        }


        binding.txtno.setText(String.valueOf(record_count));
        binding.day.setText(detail.getDd());
        binding.spMonth.setSelection(detail.getMm_id());
        binding.spYear.setSelection(detail.getYy_id());
        binding.edtTestDate.setText(detail.getTest_date());
        binding.edtBeforeuse.setText(detail.getBefore_use());
        binding.edtAfteruser.setText(detail.getAfter_use());
        binding.edtNote.setText(detail.getNote());
        DisableView();

        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);


    }

    private Boolean addRecordinSqlite() {

        Log.e(tag, "size is" + detailList.size());

        if (!binding.checkProofYes.isChecked() && !binding.checkProofNo.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.physical_proof));
            return false;
        } else if (!binding.checkUseYes.isChecked() && !binding.checkUseNo.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.in_use_or_not));
            return false;
        } else if (!binding.checkAvailableYes.isChecked() && !binding.checkAvailableNo.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.reajent_availability));
            return false;
        } else if (binding.edtTestDate.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_test_date));
            return false;
        } else if (!binding.checkPossitive.isChecked() && !binding.checkNegative.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_result_after_test));
            return false;
        } else if (binding.edtBeforeuse.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.before_use_milkkit));
            return false;
        } else if (binding.edtAfteruser.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.after_use_milkkit));
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

            String proof, use, available, result;
            if (binding.checkUseYes.isChecked()) {
                use = binding.checkUseYes.getText().toString();
            } else {
                use = binding.checkUseNo.getText().toString();
            }
            if (binding.checkProofYes.isChecked()) {
                proof = binding.checkProofYes.getText().toString();
            } else {
                proof = binding.checkProofNo.getText().toString();
            }
            if (binding.checkAvailableYes.isChecked()) {
                available = binding.checkAvailableYes.getText().toString();
            } else {
                available = binding.checkAvailableNo.getText().toString();
            }
            if (binding.checkPossitive.isChecked()) {
                result = binding.checkPossitive.getText().toString();
            } else {
                result = binding.checkNegative.getText().toString();
            }


            DataCleanMilkKit details = new DataCleanMilkKit(day,
                    binding.spMonth.getSelectedItem().toString(),
                    binding.spMonth.getSelectedItemPosition(),
                    binding.spYear.getSelectedItem().toString(),
                    binding.spYear.getSelectedItemPosition(),
                    proof,
                    binding.checkProofYes.isChecked(),
                    use,
                    binding.checkUseYes.isChecked(),
                    available,
                    binding.checkAvailableYes.isChecked(),
                    binding.edtDatevisiting.getText().toString(),
                    binding.edtTestDate.getText().toString(),
                    result,
                    binding.checkPossitive.isChecked(),
                    binding.edtBeforeuse.getText().toString(),
                    binding.edtAfteruser.getText().toString(),
                    binding.edtNote.getText().toString());
            details.save();

            Log.e(tag, "recoud count" + DataCleanMilkKit.count(DataCleanMilkKit.class));

            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));

            ClearView();
            binding.privious.setVisibility(View.VISIBLE);
            record_count++;
            return true;

        }

    }

    private void submitRecord() throws JSONException {

        boolean success = addRecordinSqlite();

        if (success) {

            detailList.clear();
            detailList = DataCleanMilkKit.listAll(DataCleanMilkKit.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < detailList.size(); i++) {
                JsonObject jsonObject = new JsonObject();

                DataCleanMilkKit detail = detailList.get(i);

                jsonObject.addProperty("physical_proof", detail.getPhy_proof());
                jsonObject.addProperty("usability", detail.getUsability());
                jsonObject.addProperty("reagent_availability", detail.getAvailable());
                jsonObject.addProperty("test_date", detail.getTest_date());
                jsonObject.addProperty("thanela_rog_result", detail.getMresult());
                jsonObject.addProperty("before_use_clean_milk_keet", detail.getBefore_use());
                jsonObject.addProperty("after_use_clean_milk_keet", detail.getAfter_use());
                jsonObject.addProperty("llw_visiting_date", detail.getVisiting_date());
                jsonObject.addProperty("note", detail.getNote());
                jsonObject.addProperty("dd", detail.getDd());
                jsonObject.addProperty("mm", detail.getMm());
                jsonObject.addProperty("yy", detail.getYy());
                jsonArray.add(jsonObject);


            }
            JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(this, Constant.mtg_group_id));
            json.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(this, Constant.mtg_member_id));
            json.addProperty("status_receipt", 1);
            json.addProperty("form_id", 7);


            mvpPresenter.saveForm(json);
            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());
        }
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
    public void onBackPressed() {
        // TODO Auto-generated method stub
        this.finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(),this,"  ");

        DataCleanMilkKit.deleteAll(DataCleanMilkKit.class);

        record_count = 1;
        recordNo = 1;
        binding.txtno.setText("1");

        ClearView();
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);

    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Dialogs.ShowCustomDialog(getContext(), commonResult.getMessage(),this);

    }

    @Override
    public void onSuccessfullyRetrived(RetrivedMilkKitResponse successResult) {

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.receiptDate.setVisibility(View.VISIBLE);
        DisableView();

        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);


        binding.receiptDate.setText(String.valueOf(successResult.getData().getDateReceipt()));
        binding.edtDatevisiting.setText(String.valueOf(successResult.getData().getLlwVisitingDate()));
        binding.edtTestDate.setText(String.valueOf(successResult.getData().getTestDate()));
        binding.edtAfteruser.setText(String.valueOf(successResult.getData().getAfterUseCleanMilkKeet()));
        binding.edtBeforeuse.setText(String.valueOf(successResult.getData().getBeforeUseCleanMilkKeet()));
        if (!String.valueOf(successResult.getData().getNote()).equalsIgnoreCase("null")){
            binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        }

        if (successResult.getData().getPhysicalProof().equalsIgnoreCase(getString(R.string.yes))) {
            binding.checkProofYes.setChecked(true);
        } else {
            binding.checkProofNo.setChecked(true);
        }
        if (successResult.getData().getUsability().equalsIgnoreCase(getString(R.string.yes))) {
            binding.checkUseYes.setChecked(true);
        } else {
            binding.checkUseNo.setChecked(true);
        }
        if (successResult.getData().getReagentAvailability().equalsIgnoreCase(getString(R.string.yes))) {
            binding.checkAvailableYes.setChecked(true);
        } else {
            binding.checkAvailableNo.setChecked(true);
        }
        if (successResult.getData().getThanelaRogResult().equalsIgnoreCase(getString(R.string.possitive))) {
            binding.checkPossitive.setChecked(true);
        } else {
            binding.checkNegative.setChecked(true);
        }

    }


    private void ShowDialog() {



        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.milkkit_availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("status_receipt", 0);
                    jsonObject.put("user_id", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                    jsonObject.put("form_id", 7);
                    jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_member_id));
                    jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_group_id));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });



/*
        ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.form_7));
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
                            jsonObject.put("form_id", 7);
                            jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_member_id));
                            jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_group_id));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }).show();*/
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


        int year1 = mDatePickerDialog.getYear();
        List <Integer> year = new ArrayList <>();
        for (int i = 2015; i <= year1; i++) {
            year.add(i);
        }
        ArrayAdapter <Integer> adapter2 = new ArrayAdapter <Integer>(this,
                R.layout.spinner_item, year);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spYear.setAdapter(adapter2);
    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 7);

        mvpPresenter.getFormRecordData(jsonObject);
    }

    private void ClearView() {
        binding.day.setText("");
        binding.spMonth.setSelection(0);
        binding.spYear.setSelection(0);
        binding.checkProofYes.setChecked(false);
        binding.checkProofNo.setChecked(false);
        binding.checkUseYes.setChecked(false);
        binding.checkUseNo.setChecked(false);
        binding.checkAvailableYes.setChecked(false);
        binding.checkAvailableNo.setChecked(false);
        binding.checkPossitive.setChecked(false);
        binding.checkNegative.setChecked(false);
        binding.edtTestDate.setText("");
        binding.edtAfteruser.setText("");
        binding.edtBeforeuse.setText("");
        binding.edtNote.setText("");
        EnableView();
    }

    private void EnableView() {

        binding.checkNegative.setClickable(true);
        binding.checkPossitive.setClickable(true);
        binding.checkAvailableNo.setClickable(true);
        binding.checkAvailableYes.setClickable(true);
        binding.checkProofYes.setClickable(true);
        binding.checkUseNo.setClickable(true);
        binding.checkUseYes.setClickable(true);
        binding.checkProofNo.setClickable(true);
        binding.edtTestDate.setClickable(true);
        binding.edtAfteruser.setEnabled(true);
        binding.edtBeforeuse.setEnabled(true);
        binding.edtNote.setEnabled(true);
        binding.day.setEnabled(true);
        binding.spYear.setEnabled(true);
        binding.spMonth.setEnabled(true);
    }

    private void DisableView() {
        binding.checkNegative.setClickable(false);
        binding.checkPossitive.setClickable(false);
        binding.checkAvailableNo.setClickable(false);
        binding.checkAvailableYes.setClickable(false);
        binding.checkProofYes.setClickable(false);
        binding.checkUseNo.setClickable(false);
        binding.checkUseYes.setClickable(false);
        binding.checkProofNo.setClickable(false);

        binding.edtTestDate.setClickable(false);
        binding.edtAfteruser.setEnabled(false);
        binding.edtBeforeuse.setEnabled(false);
        binding.edtNote.setEnabled(false);

        binding.day.setEnabled(false);
        binding.spYear.setEnabled(false);
        binding.spMonth.setEnabled(false);
    }

    @Override
    public void onOkClickListner() {
this.finish();
    }
}
