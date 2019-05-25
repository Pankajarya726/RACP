package com.tekzee.racp.ui.Form.pashu_chiktsak_shivir;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormPahsuChikitshaShivirBinding;
import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.model.DataChikitsha;
import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.model.RetrivedPashuChikithsResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PahsuChikitsha extends MvpActivity <PashuChikitshaPresenter> implements PashuChikitshaView, Dialogs.okClickListner,View.OnClickListener {

    private static String tag = PahsuChikitsha.class.getSimpleName();

    private FormPahsuChikitshaShivirBinding binding;
    private int recordNo = 1;
    private int record_count = 1;
    private List <DataChikitsha> detailList = new ArrayList <>();
    private int form_id;
    private int table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_pahsu_chikitsha_shivir);
        getSupportActionBar().setTitle(R.string.form_14);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        form_id = getIntent().getIntExtra("form_id", 0);
        table_id = getIntent().getIntExtra("table_id",0);

        if (table_id !=0){
            getFormRecordData();
        }

        DataChikitsha.deleteAll(DataChikitsha.class);
        binding.txtno.setText(String.valueOf(recordNo));
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.checkAvailableNo.setOnClickListener(this);
        binding.checkAvailableYes.setOnClickListener(this);
        binding.tvAddRecord.setOnClickListener(this);
        binding.privious.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.edtDate.setOnClickListener(this);

    }

    @Override
    protected PashuChikitshaPresenter createPresenter() {
        return new PashuChikitshaPresenter(this);
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


            case R.id.edt_date:
                mDatePickerDialog.getdate(this, binding.edtDate);
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
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_shivir_place));
            return false;
        } else if (binding.edtDate.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_shivir_orginization_date));
            return false;
        } else if (binding.edtCount.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_count));
            return false;
        } else if (binding.edtBig.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_count_big));
            return false;
        } else if (binding.edtSmall.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_count_small));
            return false;
        } else if (!binding.checkAvailableYes.isChecked() && !binding.checkAvailableNo.isChecked()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.availebilityornot));
            return false;
        } else {

            String use;
            if (binding.checkAvailableYes.isChecked()) {
                use = binding.checkAvailableYes.getText().toString();
            } else {
                use = binding.checkAvailableNo.getText().toString();
            }


            DataChikitsha details = new DataChikitsha(
                    binding.edtPlace.getText().toString(),
                    binding.edtDate.getText().toString(),
                    binding.edtCount.getText().toString(),
                    binding.edtBig.getText().toString(),
                    binding.edtSmall.getText().toString(),
                    use,
                    binding.checkAvailableYes.isChecked(),
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



    private void privious() {

        binding.tvSave.setVisibility(View.GONE);
        record_count = record_count - 1;
        if (record_count == 1) {
            binding.privious.setVisibility(View.GONE);
        }
        Log.e(tag, String.valueOf(DataChikitsha.count(DataChikitsha.class)));

        binding.txtno.setText(String.valueOf(record_count));
        // BeemaDetail beemaDetail =   BeemaDetail.findById(BeemaDetail.class,record_count);

        detailList.clear();
        detailList = DataChikitsha.listAll(DataChikitsha.class);
        Log.e(tag, String.valueOf(detailList.size()));
        Log.e(tag, String.valueOf(record_count));

        DataChikitsha detail = detailList.get(record_count - 1);

        if (detail.isUse()) {

            binding.checkAvailableYes.setChecked(true);
            binding.checkAvailableNo.setChecked(false);
        } else {

            binding.checkAvailableNo.setChecked(true);
            binding.checkAvailableYes.setChecked(false);

        }

        binding.edtNote.setText(detail.getNote());
        binding.edtPlace.setText(detail.getPlace());
        binding.edtDate.setText(detail.getDate());
        binding.edtCount.setText(detail.getCount());
        binding.edtBig.setText(detail.getBig());
        binding.edtSmall.setText(detail.getSmall());


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

        // detailList = BeemaDetail.listAll(BeemaDetail.class);
        detailList.clear();
        detailList = DataChikitsha.listAll(DataChikitsha.class);

        if (record_count <= DataChikitsha.count(DataChikitsha.class)) {

            DataChikitsha detail = detailList.get(record_count - 1);


            if (detail.isUse()) {

                binding.checkAvailableYes.setChecked(true);
                binding.checkAvailableNo.setChecked(false);
            } else {

                binding.checkAvailableNo.setChecked(true);
                binding.checkAvailableYes.setChecked(false);

            }

            binding.edtNote.setText(detail.getNote());
            binding.edtPlace.setText(detail.getPlace());
            binding.edtDate.setText(detail.getDate());
            binding.edtCount.setText(detail.getCount());
            binding.edtBig.setText(detail.getBig());
            binding.edtSmall.setText(detail.getSmall());
            DisableView();
        } else {
            ClearView();

        }

    }

    private void submitRecord() throws JSONException {


        if (saveRecord()) {

            detailList.clear();
            detailList = DataChikitsha.listAll(DataChikitsha.class);
            Log.e(tag, "size is" + detailList.size());
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < recordNo - 1; i++) {
                JsonObject jsonObject = new JsonObject();

                DataChikitsha detail = detailList.get(i);
                jsonObject.addProperty("shivir_sthal", detail.getPlace());
                jsonObject.addProperty("pashu_shivir_aayojan_date", mDatePickerDialog.changeFormate(detail.getDate()));
                jsonObject.addProperty("present_mtgmember_number", detail.getCount());
                jsonObject.addProperty("bade_pashu", detail.getBig());
                jsonObject.addProperty("chote_pashu", detail.getSmall());
                jsonObject.addProperty("biomedical_waste_disposal", detail.getBiomedical_west());
                jsonObject.addProperty("note", detail.getNote());
                jsonArray.add(jsonObject);


            }
            JsonObject json = new JsonObject();
            json.addProperty("user_id", Utility.getIngerSharedPreferences(this, Constant.USER_ID));
            json.addProperty("form_id", form_id);

            mvpPresenter.saveForm(json);

            json.add("data", jsonArray);
            Log.e(tag, "data is" + json.toString());
        }

    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(),successResult.getMessage(),this,"  ");

        record_count =1;
        recordNo =1;
        binding.txtno.setText(String.valueOf(record_count));
        DataChikitsha.deleteAll(DataChikitsha.class);

        ClearView();
        binding.privious.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);

    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        //Toast.makeText(getContext(),commonResult.getMessage(),Toast.LENGTH_SHORT).show();
        Dialogs.showColorDialog(getContext(),commonResult.getMessage());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onSuccessfullyRetrived(RetrivedPashuChikithsResponse successResult) {

        binding.tvAddRecord.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);
        binding.next.setVisibility(View.GONE);

        DisableView();


        binding.edtPlace.setText(String.valueOf(successResult.getData().getShivirSthal()));
        binding.edtCount.setText(String.valueOf(successResult.getData().getPresentMtgmemberNumber()));
        binding.edtDate.setText(String.valueOf(successResult.getData().getPashuShivirAayojanDate()));
        binding.edtBig.setText(String.valueOf(successResult.getData().getBadePashu()));
        binding.edtSmall.setText(String.valueOf(successResult.getData().getChotePashu()));

        if (!String.valueOf(successResult.getData().getNote()).equalsIgnoreCase("null")){
            binding.edtNote.setText(String.valueOf(successResult.getData().getNote()));
        }


        if (successResult.getData().getBiomedicalWasteDisposal().equalsIgnoreCase(getString(R.string.yes))){
            binding.checkAvailableYes.setChecked(true);
        }else {
            binding.checkAvailableNo.setChecked(true);
        }


    }



    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 14);

        mvpPresenter.getFormRecordData(jsonObject);
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

            TextView msg = dialog.findViewById(R.id.dialog_msg);


            msg.setText(getString(R.string.form_14));

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
                    intent.putExtra("form_id",form_id);
                    startActivity(intent);

                    dialog.dismiss();

                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void ClearView() {
        binding.edtNote.setText("");
        binding.edtSmall.setText("");
        binding.edtBig.setText("");
        binding.edtPlace.setText("");
        binding.edtDate.setText("");
        binding.edtCount.setText("");
        binding.checkAvailableNo.setChecked(false);
        binding.checkAvailableYes.setChecked(false);
        EnableView();
    }
    private void DisableView() {

        binding.edtPlace.setEnabled(false);
        binding.edtDate.setClickable(false);
        binding.edtCount.setEnabled(false);
        binding.edtBig.setEnabled(false);
        binding.edtSmall.setEnabled(false);
        binding.checkAvailableNo.setClickable(false);
        binding.checkAvailableYes.setClickable(false);
        binding.edtNote.setEnabled(false);

    }
    private void EnableView() {

        binding.edtPlace.setEnabled(true);
        binding.edtDate.setClickable(true);
        binding.edtCount.setEnabled(true);
        binding.edtBig.setEnabled(true);
        binding.edtSmall.setEnabled(true);
        binding.checkAvailableNo.setClickable(true);
        binding.checkAvailableYes.setClickable(true);
        binding.edtNote.setEnabled(true);

    }


    @Override
    public void onOkClickListner() {
        this.finish();

    }
}
