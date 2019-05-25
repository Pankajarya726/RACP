package com.tekzee.racp.ui.selectMtgGroup;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;

import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.ActivitySelectMgtBinding;
import com.tekzee.racp.sqlite.SqliteDB;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.selectMtgGroup.model.Data;
import com.tekzee.racp.ui.selectMtgGroup.model.GetMtgResponse;
import com.tekzee.racp.ui.selectMtgPreson.SelectMgtPerson;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class SelectMtgActivity extends MvpActivity <SelectMtgPresenter> implements SelectMtgView {

    private static String tag = SelectMtgActivity.class.getSimpleName();
    private List <Data> mtggroup = new ArrayList <>();
    private SelectMtgAdapter mtgAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ActivitySelectMgtBinding binding;
    private SqliteDB sqliteDB = new SqliteDB(getContext());
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_mgt);

        getSupportActionBar().setTitle(R.string.selcet_mtg_group);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        value = getIntent().getIntExtra("filled_form",0);



        Utility.setIntegerSharedPreference(getContext(),Constant.mtg_member_id,0);
        Utility.setIntegerSharedPreference(getContext(),Constant.mtg_group_id,0);

        Log.e("tag", "id" + Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
        mvpPresenter.getMtgGroup(Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));

        binding.searchMtggroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mAdapter.getFilter().filter(s.toString());
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    private void filter(String text) {
        ArrayList <Data> filteredList = new ArrayList <>();

        for (Data item : mtggroup) {
            if (item.getMtggroupName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mtgAdapter.filterList(filteredList);
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
    protected SelectMtgPresenter createPresenter() {
        return new SelectMtgPresenter(this);
    }


    @Override
    public void onItemSelected(int position, List <Data> mgtgroup) {
        Data data = mgtgroup.get(position);

        Intent i = new Intent(SelectMtgActivity.this, SelectMgtPerson.class);
        i.putExtra("MtgGroupId", data.getMtggroupId());
        i.putExtra("filled_form",value);
        startActivity(i);

        //  startActivity(new Intent(SelectMtgActivity.this, SelectMtgActivity.class));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {

        Dialogs.ShowCustomDialog(getContext(), commonResult.getMessage(), new Dialogs.okClickListner() {
            @Override
            public void onOkClickListner() {
                finish();
            }
        });

       /* Cursor cursor = sqliteDB.getMtgGroup();
        if (cursor.getCount() == 0) {
            Dialogs.showColorDialog(getContext(), commonResult.getMessage());
        }
        else{
            while (cursor.moveToNext()){
                mtggroup.add(new Data(cursor.getInt(0),cursor.getString(1)));
            }
            setUpRecyclerView();
        }*/


    }

    @Override
    public void onSuccess(GetMtgResponse successResult) {
        sqliteDB.clearMtgGroup();
        sqliteDB.clearMtgMember();
        mtggroup.addAll(successResult.getData());




        Log.e(tag,"Size is "+mtggroup.size());

        for (int i=0; i<mtggroup.size();i++){
            Data data = mtggroup.get(i);
            sqliteDB.addMtgGroup(data.getMtggroupId(),data.getMtggroupName());
            mvpPresenter.getMtgMember(data.getMtggroupId());

        }
        setUpRecyclerView();
    }


    private void setUpRecyclerView() {

        binding.recyclerMtggroup.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerMtggroup.setLayoutManager(layoutManager);
        mtgAdapter = new SelectMtgAdapter(this, mtggroup, this);
        binding.recyclerMtggroup.setAdapter(mtgAdapter);
    }




}
