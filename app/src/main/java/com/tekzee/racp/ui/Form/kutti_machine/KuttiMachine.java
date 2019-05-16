package com.tekzee.racp.ui.Form.kutti_machine;

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
import com.tekzee.racp.databinding.FormKuttiMachineBinding;
import com.tekzee.racp.ui.Form.kutti_machine.mode.DataKuttiMachine;
import com.tekzee.racp.ui.Form.kutti_machine.mode.RetrivedKuttiMachineResponse;
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

public class KuttiMachine extends MvpActivity <KuttiMachinePresenter> implements KuttiMachineView, View.OnClickListener {
    private static String tag = KuttiMachine.class.getSimpleName();

    private List <DataKuttiMachine> detailList = new ArrayList <>();
    private FormKuttiMachineBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private int table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_kutti_machine);
        getSupportActionBar().setTitle(R.string.form_8);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        table_id = getIntent().getIntExtra("table_id", 0);
        if (table_id != 0) {
            getFormRecordData();
        } else {

            ShowDialog();
        }
        SpinnerData();
        DataKuttiMachine.deleteAll(DataKuttiMachine.class);

        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.tvNo.setText(String.valueOf(recordNo));
        binding.checkProofNo.setOnClickListener(this);
        binding.checkProofYes.setOnClickListener(this);
        binding.checkUseNo.setOnClickListener(this);
        binding.checkUseYes.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);

    }

    @Override
    protected KuttiMachinePresenter createPresenter() {
        return new KuttiMachinePresenter(this);
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
                    e.printStackTrace();
                }

                break;

        }
    }

    private Boolean addRecordinSqlite() {

        Log.e(tag, "size is" + detailList.size());


        if (!binding.checkProofYes.isChecked() && !binding.checkProofNo.isChecked()) {
            SnackbarUtils.snackBarTop(binding.edtNote, getString(R.string.physical_proof));
            return false;
        } else if (Integer.valueOf(binding.day.getText().toString()) > 31) {
            Toast.makeText(this, getString(R.string.invalid_Date), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!binding.checkUseYes.isChecked() && !binding.checkUseNo.isChecked()) {
            SnackbarUtils.snackBarTop(binding.edtNote, getString(R.string.in_use_or_not));
            return false;
        } else if (binding.edtNote.getText().toString().isEmpty()) {
            SnackbarUtils.snackBarTop(binding.edtNote, getString(R.string.note));
            return false;
        } else {

            String proof, use;
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

            DataKuttiMachine details = new DataKuttiMachine(binding.day.getText().toString(),
                    binding.spMonth.getSelectedItem().toString(),
                    binding.spMonth.getSelectedItemPosition(),
                    binding.spYear.getSelectedItem().toString(),
                    binding.spYear.getSelectedItemPosition(),
                    binding.spMachine.getSelectedItem().toString(),
                    binding.spMachine.getSelectedItemPosition(),
                    use,
                    binding.checkUseYes.isChecked(),
                    proof,
                    binding.checkProofYes.isChecked(),
                    binding.edtNote.getText().toString()
            );
            details.save();


            recordNo = recordNo + 1;
            binding.tvNo.setText(String.valueOf(recordNo));
            binding.day.setText("");
            binding.checkProofYes.setChecked(false);
            binding.checkProofNo.setChecked(false);
            binding.checkUseYes.setChecked(false);
            binding.checkUseNo.setChecked(false);
            binding.spMonth.setSelection(0);
            binding.spYear.setSelection(0);
            binding.spMachine.setSelection(0);
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
        Log.e(tag, String.valueOf(DataKuttiMachine.count(DataKuttiMachine.class)));

        // BeemaDetail beemaDetail =   BeemaDetail.findById(BeemaDetail.class,record_count);

        detailList.clear();
        detailList = DataKuttiMachine.listAll(DataKuttiMachine.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));

        DataKuttiMachine detail = detailList.get(record_count - 1);

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

        binding.edtNote.setText(detail.getNote());
        binding.day.setText(detail.getDd());
        binding.spMonth.setSelection(detail.getMm_id());
        binding.spYear.setSelection(detail.getYy_id(), true);
        binding.spMachine.setSelection(detail.getMachine_id());

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
        binding.tvNo.setText(String.valueOf(record_count));

        // detailList = BeemaDetail.listAll(BeemaDetail.class);
        detailList.clear();
        detailList = DataKuttiMachine.listAll(DataKuttiMachine.class);

        if (record_count <= DataKuttiMachine.count(DataKuttiMachine.class)) {

            DataKuttiMachine detail = detailList.get(record_count - 1);

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
            binding.edtNote.setText(detail.getNote());
            binding.day.setText(detail.getDd());
            binding.spMonth.setSelection(detail.getMm_id());
            binding.spYear.setSelection(detail.getYy_id(), true);
            binding.spMachine.setSelection(detail.getMachine_id());
            binding.edtNote.setText(detail.getNote());
        } else {
            binding.day.setText("");
            binding.checkProofYes.setChecked(false);
            binding.checkProofNo.setChecked(false);
            binding.checkUseYes.setChecked(false);
            binding.checkUseNo.setChecked(false);
            binding.spMonth.setSelection(0);
            binding.spYear.setSelection(0);
            binding.spMachine.setSelection(0);
            binding.edtNote.setText("");

        }

    }

    private void submitRecord() throws JSONException {


        if (addRecordinSqlite()) {

            detailList.clear();
            detailList = DataKuttiMachine.listAll(DataKuttiMachine.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                DataKuttiMachine detail = detailList.get(i);

                jsonObject.addProperty("physical_proof", detail.getProof());
                jsonObject.addProperty("usability", detail.getUse());
                jsonObject.addProperty("machine_type", detail.getMachine_type());
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
            json.addProperty("form_id", 8);
            json.addProperty("status_receipt", 1);

            mvpPresenter.saveForm(json);

            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());
        }

    }

    private void ShowDialog() {

        ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.form_8));
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
                            jsonObject.put("form_id", 8);
                            jsonObject.put("mtg_member_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_member_id));
                            jsonObject.put("mtg_group_id", Utility.getIngerSharedPreferences(getActivityContext(), Constant.mtg_group_id));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }).show();
    }

    private void SpinnerData() {
        List <String> machine = new ArrayList <>();
        machine.add(getString(R.string.power));
        machine.add(getString(R.string.manual));
        ArrayAdapter <String> adapter_machine = new ArrayAdapter <String>(this,
                R.layout.spinner_item, machine);
        adapter_machine.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spMachine.setAdapter(adapter_machine);

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

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.showColorDialog(getContext(), successResult.getMessage());
        DataKuttiMachine.deleteAll(DataKuttiMachine.class);
        record_count = 1;
        recordNo = 1;
        binding.tvNo.setText("1");
        binding.next.setVisibility(View.GONE);
        binding.privious.setVisibility(View.GONE);
        binding.day.setText("");
        binding.checkProofYes.setChecked(false);
        binding.checkProofNo.setChecked(false);
        binding.checkUseYes.setChecked(false);
        binding.checkUseNo.setChecked(false);
        binding.spMonth.setSelection(0);
        binding.spYear.setSelection(0);
        binding.spMachine.setSelection(0);
        binding.edtNote.setText("");
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
    public void onSuccessfullyRetrived(RetrivedKuttiMachineResponse successResult) {

        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        binding.checkProofYes.setClickable(false);
        binding.checkProofNo.setClickable(false);
        binding.checkUseNo.setClickable(false);
        binding.checkUseYes.setClickable(false);
        binding.edtNote.setFocusable(false);

        binding.spMachine.setClickable(false);

        binding.receiptDate.setText(String.valueOf(successResult.getData().getDateReceipt()));
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

        binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        if (successResult.getData().getMachineType().equalsIgnoreCase(getString(R.string.power))) {
            binding.spMachine.setSelection(0);
        } else {
            binding.spMachine.setSelection(1);
        }

        binding.spMachine.setEnabled(false);
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);


    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 8);

        mvpPresenter.getFormRecordData(jsonObject);
    }
}
