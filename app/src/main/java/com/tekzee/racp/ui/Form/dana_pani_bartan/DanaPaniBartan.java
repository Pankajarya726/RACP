package com.tekzee.racp.ui.Form.dana_pani_bartan;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.ActivityDanaPaniBartanBinding;
import com.tekzee.racp.ui.Form.dana_pani_bartan.model.DataDanaPaniBartan;
import com.tekzee.racp.ui.Form.dana_pani_bartan.model.RetrivedDanaPaniResponse;
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

public class DanaPaniBartan extends MvpActivity <DanaPaniBartanPresenter> implements DanaPaniBartanView, Dialogs.okClickListner, View.OnClickListener {

    private static String tag = DanaPaniBartan.class.getSimpleName();
    private ActivityDanaPaniBartanBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private List <DataDanaPaniBartan> detailList = new ArrayList <>();
    private int table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dana_pani_bartan);

            getSupportActionBar().setTitle(R.string.form_9);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        table_id = getIntent().getIntExtra("table_id", 0);

        if (table_id != 0) {
            getFormRecordData();
        } else {

            ShowDialog();
        }

        SpinnerData();
        DataDanaPaniBartan.deleteAll(DataDanaPaniBartan.class);

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
    protected DanaPaniBartanPresenter createPresenter() {
        return new DanaPaniBartanPresenter(this);
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
            Dialogs.showColorDialog(getContext(), getString(R.string.physical_proof));

            return false;
        } else if (!binding.checkUseYes.isChecked() && !binding.checkUseNo.isChecked()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.in_use_or_not));

            return false;
        } else {

            if (binding.day.getText().toString().isEmpty()) {

            } else if (!mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),
                    binding.spMonth.getSelectedItemPosition() + 1,
                    Integer.valueOf(binding.spYear.getSelectedItem().toString()))) {

                //mDatePickerDialog.validateDate(Integer.valueOf(binding.day.getText().toString()),binding.spMonth.getSelectedItemPosition()+1,Integer.valueOf(binding.spYear.getSelectedItem().toString()));
                Dialogs.showColorDialog(getContext(), getString(R.string.invalid_Date));
                // Toast.makeText(this, getString(R.string.invalid_Date), Toast.LENGTH_SHORT).show();
                return false;
            }
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

            DataDanaPaniBartan details = new DataDanaPaniBartan(binding.day.getText().toString(),
                    binding.spMonth.getSelectedItem().toString(),
                    binding.spMonth.getSelectedItemPosition(),
                    binding.spYear.getSelectedItem().toString(),
                    binding.spYear.getSelectedItemPosition(),
                    use,
                    binding.checkUseYes.isChecked(),
                    proof,
                    binding.checkProofYes.isChecked(),
                    binding.edtNote.getText().toString()
            );
            details.save();


            recordNo = recordNo + 1;
            binding.tvNo.setText(String.valueOf(recordNo));

            ClearView();
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
        Log.e(tag, String.valueOf(DataDanaPaniBartan.count(DataDanaPaniBartan.class)));


        binding.tvNo.setText(String.valueOf(record_count));

        detailList.clear();
        detailList = DataDanaPaniBartan.listAll(DataDanaPaniBartan.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));

        DataDanaPaniBartan detail = detailList.get(record_count - 1);

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

        binding.spMonth.setClickable(false);

        binding.tvAddRecord.setVisibility(View.GONE);
        binding.next.setVisibility(View.VISIBLE);
        DisableView();


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
        detailList = DataDanaPaniBartan.listAll(DataDanaPaniBartan.class);

        if (record_count <= DataDanaPaniBartan.count(DataDanaPaniBartan.class)) {

            DataDanaPaniBartan detail = detailList.get(record_count - 1);

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
            binding.edtNote.setText(detail.getNote());
            DisableView();
        } else {
            ClearView();

        }

    }

    private void submitRecord() throws JSONException {


        if (addRecordinSqlite()) {

            detailList.clear();
            detailList = DataDanaPaniBartan.listAll(DataDanaPaniBartan.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                DataDanaPaniBartan detail = detailList.get(i);

                jsonObject.addProperty("physical_proof", detail.getProof());
                jsonObject.addProperty("usability", detail.getUse());
                jsonObject.addProperty("note", detail.getNote());
                jsonObject.addProperty("dd", detail.getDd());
                jsonObject.addProperty("mm", detail.getMm_id()+1);
                jsonObject.addProperty("yy", detail.getYy());
                jsonArray.add(jsonObject);


            }
            JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(this, Constant.mtg_group_id));
            json.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(this, Constant.mtg_member_id));
            json.addProperty("form_id", 9);
            json.addProperty("status_receipt", 1);

            mvpPresenter.saveForm(json);

            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());
        }

    }


    private void ShowDialog() {


        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.dana_pani_bartan_availornot), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {

            }

            @Override
            public void onNoClick() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("status_receipt", 0);
                    jsonObject.put("user_id", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                    jsonObject.put("form_id", 9);
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
        dialog.setTitle(getResources().getString(R.string.form_9));
        dialog.setColor("#5eabbb");
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
                            jsonObject.put("form_id", 9);
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
        CalenderUtils.loadMonths(getContext(),binding.spMonth,binding.spYear);
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), this,"  ");
        DataDanaPaniBartan.deleteAll(DataDanaPaniBartan.class);
        record_count = 1;
        recordNo = 1;
        binding.tvNo.setText("1");
        binding.next.setVisibility(View.GONE);
        binding.privious.setVisibility(View.GONE);
        ClearView();

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
    public void onSuccessfullyRetrived(RetrivedDanaPaniResponse successResult) {


        binding.receiptLayout1.setVisibility(View.GONE);
        binding.receiptLayout2.setVisibility(View.GONE);
        DisableView();
        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);


        if (!String.valueOf(successResult.getData().getNote()).equalsIgnoreCase("null")){
            binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        }
        binding.receiptDate.setVisibility(View.VISIBLE);
        binding.receiptDate.setText(mDatePickerDialog.changeFormate(String.valueOf(successResult.getData().getDateReceipt())));
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


    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 9);

        mvpPresenter.getFormRecordData(jsonObject);
    }

    private void ClearView() {
        binding.day.setText("");
        binding.checkProofYes.setChecked(false);
        binding.checkProofNo.setChecked(false);
        binding.checkUseYes.setChecked(false);
        binding.checkUseNo.setChecked(false);
        binding.spMonth.setSelection(0);
        binding.spYear.setSelection(0);
        binding.edtNote.setText("");
        EnableView();

    }

    private void EnableView() {
        binding.checkProofYes.setClickable(true);
        binding.checkProofNo.setClickable(true);
        binding.checkUseNo.setClickable(true);
        binding.checkUseYes.setClickable(true);
        binding.edtNote.setEnabled(true);
        binding.day.setEnabled(true);
        binding.spYear.setEnabled(true);
        binding.spMonth.setEnabled(true);
    }

    private void DisableView() {
        binding.checkProofYes.setClickable(false);
        binding.checkProofNo.setClickable(false);
        binding.checkUseNo.setClickable(false);
        binding.checkUseYes.setClickable(false);
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
