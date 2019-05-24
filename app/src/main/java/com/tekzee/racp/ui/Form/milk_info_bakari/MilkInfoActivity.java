package com.tekzee.racp.ui.Form.milk_info_bakari;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormMilkInfoBinding;
import com.tekzee.racp.ui.Form.milk_info_bakari.model.RetrivedMilkInfoResponse;
import com.tekzee.racp.ui.Form.vipran_talika.model.Datum;
import com.tekzee.racp.ui.Form.vipran_talika.model.MtgMemberResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Log;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import java.util.ArrayList;
import java.util.List;

public class MilkInfoActivity extends MvpActivity <MilkInfoPresenter> implements MilkInfoView, View.OnClickListener,Dialogs.okClickListner {

    private static final String TAG = MilkInfoActivity.class.getSimpleName();


    private FormMilkInfoBinding binding;

    List <Datum> dataList = new ArrayList <>();
    private int mtg_group_id = 0;
    private int mtg_member_id = 0;
    private int gramPanchayat_id = 0;
    private int gram_id = 0;
    private String pashupalak_name = "";
    private String mobile = "";
    private String address = "";
    private String  totle_amount = "";
    private String upbhog_amount = "";
    private String available_amount = "";
    private int isMtgMember = 0;
    private int table_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_milk_info);
        getSupportActionBar().setTitle(R.string.form19);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        table_id = getIntent().getIntExtra("table_id",0);
        if (table_id != 0){

            getFormRecordData();
        }else {
            ShowSelectionDialog();
        }


        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.spMtgGroup.setOnClickListener(this);
        binding.spMtgPerson.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.edtGramPanchayat.setOnClickListener(this);
        binding.edtVillage.setOnClickListener(this);



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
    protected MilkInfoPresenter createPresenter() {
        return new MilkInfoPresenter(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int getMtgGroupId() {
        return mtg_group_id;
    }

    @Override
    public int getMtgMemberId() {
        return mtg_member_id;
    }

    @Override
    public String getPashuPalakName() {
        return binding.edtName.getText().toString();
    }

    @Override
    public int getGramId() {
        return gram_id;
    }

    @Override
    public int getGramPanchayatId() {
        return gramPanchayat_id;
    }

    @Override
    public String getAddress() {
        return binding.edtAddress.getText().toString();
    }

    @Override
    public String getMobile() {
        return binding.edtMobile.getText().toString();
    }

    @Override
    public String getTotleMilkProduction() {
        return binding.edtTotleAmount.getText().toString();
    }

    @Override
    public String getUpbhogMatra() {
        return binding.edtUpbhogAmount.getText().toString();
    }

    @Override
    public String getUplabdhMatra() {
        return binding.edtAvailAmount.getText().toString();
    }

    @Override
    public void onNoInternetConnectivity(CommonResult message) {
        Dialogs.showColorDialog(getContext(),message.getMessage());

    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {

        if (type.equalsIgnoreCase("mtggroup")) {
            binding.spMtgGroup.setTextColor(getResources().getColor(R.color.black));
            binding.spMtgGroup.setText(model.getGrampanchayatName());
            mtg_group_id = model.getGrampanchayatId();

        }
        if (type.equalsIgnoreCase("pashupalak")) {
            binding.spMtgPerson.setTextColor(getResources().getColor(R.color.black));
            binding.spMtgPerson.setText(model.getGrampanchayatName());
            mtg_member_id = model.getGrampanchayatId();

            mvpPresenter.getPashupalakDetail(mtg_member_id);

        }
        if (type.equalsIgnoreCase("Gram_panchayat")) {
            binding.edtGramPanchayat.setTextColor(getResources().getColor(R.color.black));
            binding.edtGramPanchayat.setText(model.getGrampanchayatName());
            gramPanchayat_id = model.getGrampanchayatId();


        }

        if (type.equalsIgnoreCase("Gram")) {
            binding.edtVillage.setTextColor(getResources().getColor(R.color.black));
            binding.edtVillage.setText(model.getGrampanchayatName());
            gram_id = model.getGrampanchayatId();

        }

    }

    @Override
    public void onGetMemberDetail(MtgMemberResponse successResult) {
        dataList.addAll(successResult.getData());
        if (dataList.size() >= 0) {

            binding.edtName.setText(successResult.getData().get(0).getPashupalakName());
            binding.edtName.setEnabled(false);
            binding.edtGramPanchayat.setText(successResult.getData().get(0).getGrampanchayatName());
            binding.edtGramPanchayat.setEnabled(false);
            binding.edtMobile.setText(successResult.getData().get(0).getPashupalakMobile());
            binding.edtMobile.setEnabled(false);
            binding.edtVillage.setText(successResult.getData().get(0).getGramName());
            binding.edtVillage.setEnabled(false);

            binding.edtAddress.setEnabled(false);
            binding.edtAddress.setText(successResult.getData().get(0).getPashupalakAddress());
            gramPanchayat_id = successResult.getData().get(0).getGrampanchayatId();
            gram_id = successResult.getData().get(0).getGramId();


        }
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.ShowCustomDialog(getContext(),successResult.getMessage(),this);

        ClearVeiw();
    }



    @Override
    public void onSuccessfullyRetrived(RetrivedMilkInfoResponse successResult) {


      DisableView();


        if (successResult.getData().getIsMtgMember()==0){
            binding.layoutMtgselection.setVisibility(View.GONE);

        }
        if (successResult.getData().getIsMtgMember()==1){


            binding.layoutMtgselection.setVisibility(View.VISIBLE);


            binding.spMtgGroup.setText(String.valueOf(successResult.getData().getMtggroupName()));
            binding.spMtgPerson.setText(String.valueOf(successResult.getData().getPashupalakName()));

        }
        binding.edtName.setText(String.valueOf(successResult.getData().getPashupalakName()));
        binding.edtGramPanchayat.setText(String.valueOf(successResult.getData().getGrampanchayatName()));
        binding.edtVillage.setText(String.valueOf(successResult.getData().getGramName()));

        binding.edtMobile.setText(String.valueOf(successResult.getData().getPashupalakMobile()));
        binding.edtAddress.append(String.valueOf(successResult.getData().getPashupalakAddress()));
        binding.edtAddress.setEnabled(false);
        binding.edtTotleAmount.setText(String.valueOf(successResult.getData().getTotalMilkProductionPerDay()));
        binding.edtUpbhogAmount.setText(String.valueOf(successResult.getData().getConsumptionQuantityPerDay()));
        binding.edtAvailAmount.setText(String.valueOf(successResult.getData().getQuantitySalePerDay()   ));

        binding.tvSave.setVisibility(View.GONE);

    }

    private void ClearVeiw() {
        binding.edtTotleAmount.setText("");
        binding.edtAvailAmount.setText("");
        binding.edtUpbhogAmount.setText("");
        EnableView();
    }
    private void DisableView() {
        binding.spMtgPerson.setEnabled(false);
        binding.spMtgGroup.setEnabled(false);
        binding.edtGramPanchayat.setEnabled(false);
        binding.edtVillage.setEnabled(false);
        binding.edtMobile.setEnabled(false);
        binding.edtAvailAmount.setEnabled(false);
        binding.edtUpbhogAmount.setEnabled(false);
        binding.edtTotleAmount.setEnabled(false);
        binding.edtName.setEnabled(false);
    }

    private void EnableView(){
        binding.spMtgPerson.setEnabled(true);
        binding.spMtgGroup.setEnabled(true);
        binding.edtGramPanchayat.setEnabled(true);
        binding.edtVillage.setEnabled(true);
        binding.edtMobile.setEnabled(true);
        binding.edtAvailAmount.setEnabled(true);
        binding.edtUpbhogAmount.setEnabled(true);
        binding.edtTotleAmount.setEnabled(true);
        binding.edtName.setEnabled(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.edt_gramPanchayat:
                mvpPresenter.getGramPanchayat();
                break;

            case R.id.edt_village:
                mvpPresenter.getGram(gramPanchayat_id);
                break;

            case R.id.sp_mtg_group:
                mvpPresenter.getMtgName();
                break;

            case R.id.sp_mtg_person:
                mvpPresenter.getMtgMemberList(mtg_group_id);
                break;

            case R.id.tv_save:
                submitRecord();
                break;


        }
    }

    private void submitRecord() {

        if (binding.edtName.getText().toString().isEmpty()){
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_animal_owner_name));
        }else if (binding.edtMobile.getText().toString().isEmpty()){
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_mobile));
        } else if (binding.edtAddress.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_address));
        }else if (binding.edtTotleAmount.getText().toString().isEmpty()){
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_total_amount));
        }else if (binding.edtUpbhogAmount.getText().toString().isEmpty()){
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_upbhod_amount));
        }else if (binding.edtAvailAmount.getText().toString().isEmpty()){
            Dialogs.showColorDialog(getContext(),getString(R.string.enter_avail_amount));
        }else if (gramPanchayat_id==0){
            Dialogs.showColorDialog(getContext(),getString(R.string.select_gramPanchayat));
        }else if (gram_id ==0){
            Dialogs.showColorDialog(getContext(),getString(R.string.select_village));
        }else {

            JsonObject jsonObject= new JsonObject();
            jsonObject.addProperty("is_mtg_member",isMtgMember);
            jsonObject.addProperty("user_id", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
            jsonObject.addProperty("form_id",20);
            jsonObject.addProperty("mtg_member_id",getMtgMemberId());
            jsonObject.addProperty("mtg_group_id",getMtgGroupId());
            jsonObject.addProperty("pashupalak_name",getPashuPalakName());
            jsonObject.addProperty("pashupalak_mobile",getMobile());
            jsonObject.addProperty("pashupalak_address",getAddress());
            jsonObject.addProperty("grampanchayat_id",getGramPanchayatId());
            jsonObject.addProperty("gram_id",getGramId());


            JsonArray jsonArray = new JsonArray();
             JsonObject object = new JsonObject();
            object.addProperty("total_milk_production_per_day",getTotleMilkProduction());
            object.addProperty("consumption_quantity_per_day",getUpbhogMatra());
            object.addProperty("quantity_sale_per_day",getUplabdhMatra());

            jsonArray.add(object);

            jsonObject.add("data",jsonArray);


            Log.view(TAG,jsonObject.toString());

            mvpPresenter.saveForm(jsonObject);
        }
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


            msg.setText(getString(R.string.form19));

            dialog.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                    dialog.dismiss();
                    ShowDialog();
                }
            });
            dialog.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard();
                     Intent intent = new Intent(getContext(), FormDataActivity.class);
                    intent.putExtra("form_id", 20);
                    startActivity(intent);

                    dialog.dismiss();

                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 20);

        mvpPresenter.getFormRecordData(jsonObject);
    }

    private void ShowDialog() {


        Dialogs.ShowSelectionDialog(getContext(), getString(R.string.is_mtg_member_or_not), new Dialogs.DialogClickListner() {
            @Override
            public void onOkClick() {
                binding.layoutMtgselection.setVisibility(View.VISIBLE);
                isMtgMember = 1;
            }

            @Override
            public void onNoClick() {
                binding.layoutMtgselection.setVisibility(View.GONE);

                isMtgMember = 0;
            }
        });

        /*ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.form19));
        dialog.setColor("#FF6500");
        dialog.setContentText(R.string.is_mtg_member_or_not);

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
                    }
                }).show();*/
    }

    @Override
    public void onOkClickListner() {
        this.finish();
    }


}
