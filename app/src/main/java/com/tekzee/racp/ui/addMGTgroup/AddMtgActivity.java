package com.tekzee.racp.ui.addMGTgroup;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.AddMtgGroupBinding;
import com.tekzee.racp.ui.addMGTgroup.model.AddMtgResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayatResponse;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.KeyboardUtils;
import com.tekzee.racp.utils.SnackbarUtils;
import com.tekzee.racp.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class AddMtgActivity extends MvpActivity<AddMtgPresenter> implements AddMtgView, View.OnClickListener {
    private AddMtgGroupBinding binding;
    private int gram_panchayat_id = 0;
    private int gram_id = 0;

    List<String> gramPanchayats = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.add_mtg_group);

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
            //startActivity(new Intent(ForSale.this, HomeActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected AddMtgPresenter createPresenter() {
        return new AddMtgPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

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
        Dialogs.showcolorDialog(getContext(),result.getMessage());
    }

    @Override
    public void onGramPanchayatSelected(GramPanchayat model, String type) {
        if (type.equalsIgnoreCase("Gram_panchayat")) {
            binding.gramPanchayat.setText(model.getGrampanchayatName());
            binding.gram.setText("");
            gram_panchayat_id  = model.getGrampanchayatId();
        } else {
            binding.gram.setText(model.getGrampanchayatName());
            gram_id = model.getGrampanchayatId();
        }

    }

    @Override
    public void onAddMtgSuccess(AddMtgResponse successResult) {
        Log.e(this.getPackageName(),successResult.getMessage());

        binding.edtMtgname.setText("");
        binding.gram.setText("");
        binding.gramPanchayat.setText("");

        Dialogs.showcolorDialog(getContext(),successResult.getMessage());

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

        if (binding.edtMtgname.getText().toString().trim().isEmpty()){
            Dialogs.showcolorDialog(getContext(),getString(R.string.enter_mtg_name));

        }else if (binding.gramPanchayat.getText().toString().isEmpty())
        {
            Dialogs.showcolorDialog(getContext(),getString(R.string.select_gramPanchayat));
        }else if (binding.gram.getText().toString().isEmpty())
        {
            Dialogs.showcolorDialog(getContext(),getString(R.string.select_village));
        }else {

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("mtgName",getName());
            jsonObject.addProperty("gramPanchayatId",getGramPachayatId());
            jsonObject.addProperty("gramId",GramId());
            jsonObject.addProperty("userId", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
            mvpPresenter.addMtgGroup(jsonObject);
        }
    }
}
