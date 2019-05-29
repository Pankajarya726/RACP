package com.tekzee.racp.ui.addMGTgroup;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.tekzee.racp.R;
import com.tekzee.racp.databinding.AddMtgGroupBinding;
import com.tekzee.racp.sqlite.tables.formtable.FormMtgGroup;
import com.tekzee.racp.ui.addMGTgroup.model.AddMtgResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.home.HomeActivity;
import com.tekzee.racp.utils.Dialogs;

import java.util.ArrayList;
import java.util.List;

public class AddMtgActivity extends MvpActivity <AddMtgPresenter> implements AddMtgView, View.OnClickListener, Dialogs.okClickListner {
    List <String> gramPanchayats = new ArrayList <>();
    private AddMtgGroupBinding binding;
    private int gram_panchayat_id = 0;
    private int gram_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.add_mtg_group);

        getSupportActionBar().setTitle(R.string.add_mtggroup);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.gramPanchayat.setOnClickListener(this);
        binding.gram.setOnClickListener(this);
        binding.btnSave.setOnClickListener(this);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, HomeActivity.class));
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
    protected AddMtgPresenter createPresenter() {
        return new AddMtgPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.gramPanchayat:
                mvpPresenter.getGramPanchayat();
                break;

            case R.id.gram:
                mvpPresenter.getGram(gram_panchayat_id);
                break;

            case R.id.btn_save:
                addMtg();
                break;
        }

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult result) {
        Dialogs.showColorDialog(getContext(), result.getMessage());
    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {
        if (type.equalsIgnoreCase("Gram_panchayat")) {
            binding.gramPanchayat.setText(model.getGrampanchayatName());
            binding.gram.setText("");
            gram_panchayat_id = model.getGrampanchayatId();
        } else {
            binding.gram.setText(model.getGrampanchayatName());
            gram_id = model.getGrampanchayatId();
        }

    }

    @Override
    public void onAddMtgSuccess(AddMtgResponse successResult) {
        Log.e(this.getPackageName(), successResult.getMessage());

        binding.edtMtgname.setText("");
        binding.gram.setText("");
        binding.gramPanchayat.setText("");

        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), this,"  ");

    }

    @Override
    public String getName() {
        return binding.edtMtgname.getText().toString();
    }

    @Override
    public int getGramPachayatId() {
        return gram_panchayat_id;
    }

    @Override
    public int GramId() {
        return gram_id;
    }

    private void addMtg() {

        if (binding.edtMtgname.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.enter_mtg_name));

        } else if (binding.gramPanchayat.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_gramPanchayat));
        } else if (binding.gram.getText().toString().isEmpty()) {
            Dialogs.showColorDialog(getContext(), getString(R.string.select_village));
        } else {


            FormMtgGroup formMtgGroup = new FormMtgGroup(binding.edtMtgname.getText().toString().trim(),
                    binding.gramPanchayat.getText().toString().trim(),
                    String.valueOf(gram_panchayat_id),
                    binding.gram.getText().toString().trim(),
                    String.valueOf(gram_id));
            if (formMtgGroup.save()>0){
                Dialogs.ShowCustomDialog(getContext(), getString(R.string.mtg_successfull_added), this,"  ");
            }

         /*   JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("mtgName", getName());
            jsonObject.addProperty("gramPanchayatId", getGramPachayatId());
            jsonObject.addProperty("gramId", GramId());
            jsonObject.addProperty("userId", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
            mvpPresenter.addMtgGroup(jsonObject);
       */ }
    }

    @Override
    public void onOkClickListner() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();

    }


}
