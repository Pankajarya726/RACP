package com.tekzee.racp.ui.Form.vipran_talika;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.FormVipranTableBinding;
import com.tekzee.racp.ui.Form.vipran_talika.model.Datum;
import com.tekzee.racp.ui.Form.vipran_talika.model.MtgMemberResponse;
import com.tekzee.racp.ui.Form.vipran_talika.model.RetrivedVipranTalikaResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.dashboardform.DashformActivity;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.refactor.lib.colordialog.ColorDialog;

public class VipranTableActivity extends MvpActivity <VipranPresenter> implements VipranView, View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private static String TAG = VipranTableActivity.class.getSimpleName();
    TeekaAdapter teekaAdapter;
    List <Datum> dataList = new ArrayList <>();
    List <Immunization> immunizations = new ArrayList <>();
    List <Datum> datumList = new ArrayList <>();
    private FormVipranTableBinding binding;
    private int isMtgMember = 0;
    private int mtg_id = 0;
    private int mtg_member_id = 0;
    private int animal_id =0;
    private int bechan_yogya = 0;
    private int gram_id = 0;
    private int gram_panchayat_id = 0;
    private int form_id;
    private int table_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.form_vipran_table);

        getSupportActionBar().setTitle(R.string.form_5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        form_id = getIntent().getIntExtra("form_id",0);
        table_id = getIntent().getIntExtra("table_id",0);

        if (table_id != 0) {
            getFormRecordData();
        } else {
            ShowSelectionDialog();
        }


        SpinnerData();
        binding.tvDate.setText(mDatePickerDialog.showDate());
        binding.spMtgGroup.setOnClickListener(this);
        binding.spMtgPerson.setOnClickListener(this);
        binding.animelType.setOnClickListener(this);
        binding.teekaDate.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
        binding.checkNo.setOnClickListener(this);
        binding.checkYes.setOnClickListener(this);
        binding.edtGramPanchayat.setOnClickListener(this);
        binding.edtVillage.setOnClickListener(this);

    }

    @Override
    protected VipranPresenter createPresenter() {
        return new VipranPresenter(this);
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

            case R.id.teeka_date:
                DialogFragment datePicker = new mDatePickerDialog();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;

            case R.id.check_yes:
                if (binding.checkYes.isChecked()) {
                    if (binding.checkNo.isChecked()) {
                        binding.checkNo.setChecked(false);
                    }
                    binding.checkYes.setChecked(true);
                    bechan_yogya  =1;
                } else {
                    binding.checkYes.setChecked(false);
                    bechan_yogya =0;
                }
                break;


            case R.id.check_no:
                if (binding.checkNo.isChecked()) {
                    if (binding.checkYes.isChecked()) {
                        binding.checkYes.setChecked(false);
                    }
                    binding.checkNo.setChecked(true);
                    bechan_yogya = 0;
                } else {
                    binding.checkNo.setChecked(false);
                    bechan_yogya = 0;
                }
                break;

            case R.id.edt_gramPanchayat:
                mvpPresenter.getGramPanchayat();
                break;

            case R.id.edt_village:
                mvpPresenter.getGram(gram_panchayat_id);
                break;

            case R.id.sp_mtg_group:
                mvpPresenter.getMtgName();
                break;

            case R.id.sp_mtg_person:
                mvpPresenter.getMtgMemberList(mtg_id);
                break;

            case R.id.animel_type:
                mvpPresenter.getAnimalType();
                break;

            case R.id.tv_save:
                submitrecord();
                break;

        }

    }

    private void submitrecord() {

        if (isMtgMember==1){

            if (binding.spMtgGroup.getText().toString().equalsIgnoreCase(getString(R.string.selcet_mtg_group))){
                Dialogs.showColorDialog(getContext(),getString(R.string.selcet_mtg_group));
                return;
            }
            else if (binding.spMtgPerson.getText().toString().equalsIgnoreCase(getString(R.string.selcet_mtg_person))){

                Dialogs.showColorDialog(getContext(),getString(R.string.selcet_mtg_person));
                return;
            }
            else if (binding.edtTagNo.getText().toString().isEmpty()) {
                Dialogs.showColorDialog(getContext(), getString(R.string.enter_tagno));
                return;
            }

        }

        if (binding.edtName.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_name));
            return;

        }else if (binding.edtGramPanchayat.getText().toString().isEmpty()) {

            Dialogs.showColorDialog(getContext(), getString(R.string.enter_gram_panchayat));
            return;

        } else if (binding.edtVillage.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_village));
            return;


        } else if (binding.edtMobile.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_number));
            return;

        } else if (binding.edtMobile.getText().toString().length()!=10) {
            Dialogs.showColorDialog(getContext(), getString(R.string.invalid_no));
            return;
        }  else if (binding.edtAddress.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_address));
            return;
        }  else if (binding.animelType.getText().toString().equalsIgnoreCase(getString(R.string.animal_type))) {
            Dialogs.showColorDialog(getContext(), getString(R.string.animal_type));
            return;
        } else if (binding.edtAge.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_age));
            return;
        }else if (binding.edtWeight.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_weight));
            return;
        }else if (!binding.checkYes.isChecked() && !binding.checkNo.isChecked()){
            Dialogs.showColorDialog(getContext(),getString(R.string.avail_for_sale));
            return;
        }else {


            JsonArray jsonArray = new JsonArray();

            for (int i = 0;i<immunizations.size();i++){
                Immunization im = immunizations.get(i);
                JsonObject obj = new JsonObject();
                obj.addProperty("teeka_type",im.getTeekaname());
                obj.addProperty("teeka_date",im.getTeekadate());
                jsonArray.add(obj);
            }

            JsonObject mobject = new JsonObject();
            mobject.addProperty("animaltype_id",animal_id);
            mobject.addProperty("bechan_yogya",bechan_yogya);
            mobject.addProperty("tag_no",binding.edtTagNo.getText().toString());
            mobject.addProperty("age",binding.edtAge.getText().toString());
            mobject.addProperty("weight",binding.edtWeight.getText().toString());
            mobject.add("teekakaran",jsonArray);



            JsonArray marray = new JsonArray();
            marray.add(mobject);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("is_mtg_member",isMtgMember);
            jsonObject.addProperty("user_id", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
            jsonObject.addProperty("form_id",19);
            jsonObject.addProperty("mtg_member_id",mtg_member_id);
            jsonObject.addProperty("mtg_group_id",mtg_id);
            jsonObject.addProperty("pashupalak_name",binding.edtName.getText().toString());
            jsonObject.addProperty("pashupalak_mobile",binding.edtMobile.getText().toString());
            jsonObject.addProperty("pashupalak_address",binding.edtAddress.getText().toString());
            jsonObject.addProperty("grampanchayat_id",gram_panchayat_id);
            jsonObject.addProperty("gram_id",gram_id);
            jsonObject.add("data",marray);

            mvpPresenter.saveForm(jsonObject);


        }


    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onDateSelected(int adapterPosition, List <Immunization> immunizations, TextView tv_date) {

        Immunization im = immunizations.get(adapterPosition);
        mDatePickerDialog.getdate(getContext(), tv_date);
        im.setTeekadate(tv_date.getText().toString());
        teekaAdapter.notifyDataSetChanged();

    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {
        if (type.equalsIgnoreCase("mtggroup")) {
            binding.spMtgGroup.setTextColor(getResources().getColor(R.color.black));
            binding.spMtgGroup.setText(model.getGrampanchayatName());
            mtg_id = model.getGrampanchayatId();

        }
        if (type.equalsIgnoreCase("animaltype")) {
            binding.animelType.setTextColor(getResources().getColor(R.color.black));
            binding.animelType.setText(model.getGrampanchayatName());
            animal_id = model.getGrampanchayatId();

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
            gram_panchayat_id = model.getGrampanchayatId();


        }

        if (type.equalsIgnoreCase("Gram")) {
            binding.edtVillage.setTextColor(getResources().getColor(R.color.black));
            binding.edtVillage.setText(model.getGrampanchayatName());
            gram_id = model.getGrampanchayatId();

        }
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {

        Dialogs.showColorDialog(getContext(),commonResult.getMessage());

    }

    @Override
    public void onGetMemberDetail(MtgMemberResponse successResult) {
        dataList.addAll(successResult.getData());
        if (dataList.size() >= 0) {

            binding.edtName.setText(successResult.getData().get(0).getPashupalakName());
            binding.edtGramPanchayat.setText(successResult.getData().get(0).getGrampanchayatName());
            binding.edtGramPanchayat.setEnabled(false);
            binding.edtMobile.setText(successResult.getData().get(0).getPashupalakMobile());
            binding.edtVillage.setText(successResult.getData().get(0).getGramName());
            binding.edtVillage.setEnabled(false);

            binding.edtAddress.setEnabled(false);
            binding.edtAddress.setText(successResult.getData().get(0).getPashupalakAddress());
            gram_panchayat_id = successResult.getData().get(0).getGrampanchayatId();
            gram_id = successResult.getData().get(0).getGramId();


        }
    }

    @Override
    public void SuccessfullSave(FormSubmitResponse successResult) {

        Dialogs.showColorDialog(getContext(),successResult.getMessage());
        binding.animelType.setHint(getString(R.string.animal_type));
        binding.edtTagNo.setText("");
        binding.edtAge.setText("");
        binding.edtWeight.setText("");
        binding.checkNo.setChecked(false);
        binding.checkYes.setChecked(false);
        int animal_id =0;
        int bechan_yogya = 0;
        immunizations.clear();
        setupRecyclerView();


    }

    @Override
    public void onSuccessfullyRetrived(RetrivedVipranTalikaResponse successResult) {

        binding.spMtgPerson.setEnabled(false);
        binding.spMtgGroup.setEnabled(false);
        binding.edtTagNo.setFocusable(false);
        binding.edtGramPanchayat.setEnabled(false);
        binding.edtVillage.setEnabled(false);
        binding.edtMobile.setEnabled(false);

        binding.animelType.setEnabled(false);
        binding.edtTagNo.setEnabled(false);
        binding.edtAge.setEnabled(false);
        binding.edtWeight.setEnabled(false);
        binding.checkYes.setClickable(false);
        binding.checkNo.setClickable(false);
        binding.spTeeka.setVisibility(View.GONE);
        binding.teekaDate.setVisibility(View.GONE);
        binding.layoutTeeka.setVisibility(View.GONE);
        binding.edtName.setEnabled(false);


        if (successResult.getData().getIsMtgMember()==0){
            binding.layoutMtgselection.setVisibility(View.GONE);
            binding.edtTagNo.setVisibility(View.GONE);
            binding.tvTagNo.setVisibility(View.GONE);
        }
        if (successResult.getData().getIsMtgMember()==1){


            binding.layoutMtgselection.setVisibility(View.VISIBLE);
            binding.edtTagNo.setVisibility(View.VISIBLE);
            binding.tvTagNo.setVisibility(View.VISIBLE);

            binding.spMtgGroup.setText(String.valueOf(successResult.getData().getMtggroupName()));
            binding.spMtgPerson.setText(String.valueOf(successResult.getData().getPashupalakName()));
            binding.edtTagNo.setText(String.valueOf(successResult.getData().getTagNo()));

        }
        binding.edtName.setText(String.valueOf(successResult.getData().getPashupalakName()));
        binding.edtGramPanchayat.setText(String.valueOf(successResult.getData().getGrampanchayatName()));
        binding.edtVillage.setText(String.valueOf(successResult.getData().getGramName()));

        binding.edtMobile.setText(String.valueOf(successResult.getData().getPashupalakMobile()));
        binding.edtAddress.append(String.valueOf(successResult.getData().getPashupalakMobile()));
        binding.edtAddress.setEnabled(false);
        binding.animelType.setText(String.valueOf(successResult.getData().getAnimalType()));
        binding.edtAge.setText(String.valueOf(successResult.getData().getAge()));
        binding.edtWeight.setText(String.valueOf(successResult.getData().getWeight()));

        if (successResult.getData().getBechanYogya()==1){
            binding.checkYes.setChecked(true);
        }else {
            binding.checkNo.setChecked(true);
        }



        binding.tvSave.setVisibility(View.GONE);
        binding.immunization.setVisibility(View.GONE);


        String ppr = successResult.getData().getDatePpr();
        String et = successResult.getData().getDateEt();
        String fmd = successResult.getData().getDateFmd();
        String hs = successResult.getData().getDateHs();

        if (ppr.isEmpty()){
            immunizations.add(new Immunization(getString(R.string.ppr),""));
        }
        else {
            String date[] = ppr.split(",");
            for (int i = 0; i < date.length; i++) {
                immunizations.add(new Immunization(getString(R.string.ppr), date[i]));
            }
        }
        String date1[] = et.split(",");
        for (int i = 0; i < date1.length; i++) {
            immunizations.add(new Immunization(getString(R.string.et), date1[i]));
        }

        String date2[] = fmd.split(",");
        for (int i = 0; i < date2.length; i++) {
            immunizations.add(new Immunization(getString(R.string.fmd), date2[i]));
        }

        String date3[] = hs.split(",");
        for (int i = 0; i < date3.length; i++) {
            immunizations.add(new Immunization(getString(R.string.hs), date3[i]));
        }
        setupRecyclerView();


    }


    private void ShowDialog() {

        ColorDialog dialog = new ColorDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.form_5));
        dialog.setColor("#FF6500");
        dialog.setContentText(R.string.is_mtg_member_or_not);

        dialog.setPositiveListener(getText(R.string.yes), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {

                binding.layoutMtgselection.setVisibility(View.VISIBLE);
                isMtgMember = 1;


                dialog.dismiss();

            }
        })
                .setNegativeListener(getText(R.string.no), new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {

                        binding.layoutMtgselection.setVisibility(View.GONE);
                        binding.edtTagNo.setVisibility(View.GONE);
                        binding.tvTagNo.setVisibility(View.GONE);
                        isMtgMember = 0;

                        dialog.dismiss();
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

        loadList(currentDateString, binding.spTeeka.getSelectedItem().toString());

        binding.teekaDate.setText("");
    }

    private void loadList(String format, String name) {
        immunizations.add(new Immunization(name, format));
        Immunization immunization = new Immunization(name, format);
        immunization.save();

        setupRecyclerView();

    }

    private void setupRecyclerView() {

        binding.rvImmunization.setLayoutManager(new LinearLayoutManager(this));

        teekaAdapter = new TeekaAdapter(getContext(), immunizations, this);

        binding.rvImmunization.setAdapter(teekaAdapter);
        teekaAdapter.notifyDataSetChanged();


    }

    private void SpinnerData() {
        List <String> teeka_type = new ArrayList <>();
        teeka_type.add("PPR");
        teeka_type.add("ET");
        teeka_type.add("FMD");
        teeka_type.add("HS");

        ArrayAdapter <String> adapter = new ArrayAdapter <String>(this,
                R.layout.spinner_item, teeka_type);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spTeeka.setAdapter(adapter);

    }

    private void getFormRecordData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("table_id", table_id);
        jsonObject.addProperty("form_id", 19);

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

            TextView textView = dialog.findViewById(R.id.yes);
            TextView textView1 = dialog.findViewById(R.id.no);

            TextView msg = dialog.findViewById(R.id.dialog_msg);


            msg.setText(getString(R.string.form_5));

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
                    intent.putExtra("form_id", 19);
                    startActivity(intent);

                    dialog.dismiss();

                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
