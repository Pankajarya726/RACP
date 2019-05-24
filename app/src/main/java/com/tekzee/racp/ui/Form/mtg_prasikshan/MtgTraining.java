package com.tekzee.racp.ui.Form.mtg_prasikshan;

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
import com.tekzee.racp.databinding.FormMtgPrasikshanBinding;
import com.tekzee.racp.ui.Form.mtg_prasikshan.model.DataMtgTraingin;
import com.tekzee.racp.ui.Form.mtg_prasikshan.model.RetrivedMtgTrainingResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MtgTraining extends MvpActivity <MtgTrainingPresenter> implements MtgTrainginVeiw, View.OnClickListener,Dialogs.okClickListner {
    private static String tag = MtgTraining.class.getSimpleName();

    private FormMtgPrasikshanBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private int form_id;
    private int mtg_id;
    private int table_id;
    private List <DataMtgTraingin> detailList = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_mtg_prasikshan);

        getSupportActionBar().setTitle(R.string.form_15);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        form_id = getIntent().getIntExtra("form_id", 0);
        table_id = getIntent().getIntExtra("table_id", 0);

        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowSelectionDialog();
        }

        DataMtgTraingin.deleteAll(DataMtgTraingin.class);

        binding.txtno.setText(String.valueOf(recordNo));
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.edtTrainingdate.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.edtMtgname.setOnClickListener(this);

    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 15);

        mvpPresenter.getFormRecordData(jsonObject);
    }


    @Override
    protected MtgTrainingPresenter createPresenter() {
        return new MtgTrainingPresenter(this);
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

            case R.id.edt_trainingdate:
                mDatePickerDialog.getdate(this, binding.edtTrainingdate);
                break;

            case R.id.edt_mtgname:
                mvpPresenter.getMtgGroup(Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                break;

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


        if (binding.edtPlace.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_training_place));
            return false;
        } else if (binding.edtTrainingdate.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_date_trainging));
            return false;
        } else if (binding.edtCount.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_treainee_count));
            return false;
        } else if (binding.edtMtgname.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_mtg_name));
            return false;
        } else {

            Log.e(tag, "mtg_id" + mtg_id);


            DataMtgTraingin details = new DataMtgTraingin(
                    binding.edtMtgname.getText().toString(),
                    mtg_id,
                    binding.edtTrainingdate.getText().toString(),
                    binding.edtPlace.getText().toString(),
                    binding.edtCount.getText().toString(),
                    binding.edtNote.getText().toString()
            );
            details.save();


            recordNo = recordNo + 1;
            binding.txtno.setText(String.valueOf(recordNo));


            ClearView();

            binding.privious.setVisibility(View.VISIBLE);
            record_count = record_count + 1;
            return true;

        }


    }

    private void ClearView() {
        binding.edtNote.setText("");
        binding.edtMtgname.setText("");
        binding.edtPlace.setText("");
        binding.edtTrainingdate.setText("");
        binding.edtCount.setText("");
        EnableView();
    }

    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }

        binding.txtno.setText(String.valueOf(record_count));

        Log.e(tag, String.valueOf(DataMtgTraingin.count(DataMtgTraingin.class)));

        detailList.clear();
        detailList = DataMtgTraingin.listAll(DataMtgTraingin.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));


        DataMtgTraingin detail = detailList.get(record_count - 1);

        binding.edtNote.setText(detail.getNote());
        binding.edtPlace.setText(detail.getPlace());
        binding.edtMtgname.setText(detail.getName());
        binding.edtCount.setText(detail.getCount());
        binding.edtTrainingdate.setText(detail.getDate());


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
        binding.txtno.setText(String.valueOf(record_count));


        detailList.clear();
        detailList = DataMtgTraingin.listAll(DataMtgTraingin.class);

        if (record_count <= DataMtgTraingin.count(DataMtgTraingin.class)) {

            DataMtgTraingin detail = detailList.get(record_count - 1);


            binding.edtNote.setText(detail.getNote());
            binding.edtPlace.setText(detail.getPlace());
            binding.edtMtgname.setText(detail.getName());
            binding.edtCount.setText(detail.getCount());
            binding.edtTrainingdate.setText(detail.getDate());
            DisableView();
        } else {
            ClearView();
        }

    }

    private void submitRecord() throws JSONException {


        if (saveRecord()) {

            detailList.clear();
            detailList = DataMtgTraingin.listAll(DataMtgTraingin.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                DataMtgTraingin detail = detailList.get(i);

                jsonObject.addProperty("mtggroup_id", detail.getMtg_id());
                jsonObject.addProperty("training_date", detail.getDate());
                jsonObject.addProperty("trainee_number", detail.getCount());
                jsonObject.addProperty("training_place", detail.getPlace());
                jsonObject.addProperty("note", detail.getNote());

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
    public void onNoInternetConnectivity(CommonResult message) {
        Toast.makeText(getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {
        binding.edtMtgname.setText(model.getGrampanchayatName());
        mtg_id = model.getGrampanchayatId();

    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(),this);

//        Toast.makeText(getContext(), successResult.getMessage(), Toast.LENGTH_SHORT).show();

        DataMtgTraingin.deleteAll(DataMtgTraingin.class);
        record_count = 1;
        recordNo = 1;

        binding.txtno.setText(String.valueOf(recordNo));
        ClearView();

        binding.next.setVisibility(View.GONE);
        binding.privious.setVisibility(View.GONE);


    }

    @Override
    public void onSuccessfullyRetrived(RetrivedMtgTrainingResponse successResult) {

        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);

        DisableView();

        binding.edtTrainingdate.setText(String.valueOf(successResult.getData().getTrainingDate()));
        binding.edtCount.setText(String.valueOf(successResult.getData().getTraineeNumber()));
        binding.edtPlace.setText(String.valueOf(successResult.getData().getTrainingPlace()));
        if (!String.valueOf(successResult.getData().getNote()).equalsIgnoreCase("null")){
            binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        }
        binding.edtMtgname.setText(String.valueOf(successResult.getData().getMtggroupName()));

    }

    private void DisableView() {
        binding.edtMtgname.setClickable(false);
        binding.edtTrainingdate.setClickable(false);
        binding.edtCount.setEnabled(false);
        binding.edtPlace.setEnabled(false);
        binding.edtNote.setEnabled(false);
    }

    private void EnableView() {
        binding.edtMtgname.setClickable(true);
        binding.edtTrainingdate.setClickable(true);
        binding.edtCount.setEnabled(true);
        binding.edtPlace.setEnabled(true);
        binding.edtNote.setEnabled(true);
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


            msg.setText(getString(R.string.form_15));

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

    @Override
    public void onOkClickListner() {
        this.finish();
    }
}
