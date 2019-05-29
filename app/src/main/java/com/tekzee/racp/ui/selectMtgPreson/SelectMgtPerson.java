package com.tekzee.racp.ui.selectMtgPreson;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.tekzee.racp.databinding.ActivitySelectMgtPersonBinding;
import com.tekzee.racp.sqlite.SqliteDB;
import com.tekzee.racp.sqlite.tables.LlwMTGMember;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formselection.ListFormActivity;
import com.tekzee.racp.ui.selectMtgPreson.model.Datum;
import com.tekzee.racp.ui.selectMtgPreson.model.GetMtgMemberResponse;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class SelectMgtPerson extends MvpActivity <MgtPersonPresenter> implements MgtPersonView {
    private String tag = SelectMgtPerson.class.getSimpleName();

    private ActivitySelectMgtPersonBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private MgtPersonAdapter mAdapter;
    private List <Datum> mtgperson = new ArrayList <>();
    private int group_id;
    private SqliteDB sqliteDB = new SqliteDB(getContext());
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_mgt_person);
        getSupportActionBar().setTitle(R.string.selcet_mtg_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        value = getIntent().getIntExtra("filled_form", 0);
        group_id = getIntent().getIntExtra("MtgGroupId", 0);
        mvpPresenter.getMtgPerson(getIntent().getIntExtra("MtgGroupId", 0));

    }

    @Override
    protected MgtPersonPresenter createPresenter() {
        return new MgtPersonPresenter(this);
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        this.finish();
    }


    private void setUpRecyclerView() {

        binding.recyclerMgtperson.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerMgtperson.setLayoutManager(layoutManager);
        mAdapter = new MgtPersonAdapter(this, mtgperson, this);
        binding.recyclerMgtperson.setAdapter(mAdapter);

        binding.searchMtgperson.addTextChangedListener(new TextWatcher() {
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

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //startActivity(new Intent(ForSale.this, HomeActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void filter(String toString) {

        ArrayList <Datum> filteredList = new ArrayList <>();

        for (Datum item : mtgperson) {
            if (item.getPashupalakName().toLowerCase().contains(toString.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }

    @Override
    public void onItemSelected(int adapterPosition, List <Datum> mtgperson) {

        Datum datum = mtgperson.get(adapterPosition);
        datum.getPashupalakId();

        Utility.setIntegerSharedPreference(getContext(), Constant.mtg_group_id, group_id);
        Utility.setIntegerSharedPreference(getContext(), Constant.mtg_member_id, datum.getPashupalakId());


        Intent intent = new Intent(SelectMgtPerson.this, ListFormActivity.class);
        Log.e(tag, "" + datum.getPashupalakId() + "    " + group_id);

        intent.putExtra("filled_form", value);
        startActivity(intent);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {
        Log.e(tag, commonResult.getMessage());

       // Dialogs.showColorDialog(getContext(), commonResult.getMessage());

        Cursor cursor = sqliteDB.getMtgMember(group_id);
        if (cursor.getCount() == 0) {
            Dialogs.ShowCustomDialog(getContext(), commonResult.getMessage(), new Dialogs.okClickListner() {
                @Override
                public void onOkClickListner() {
                    finish();
                }
            });
        } else {
            while (cursor.moveToNext()) {
                mtgperson.add(new Datum(cursor.getInt(1), cursor.getString(2)));
            }
            setUpRecyclerView();

        }

    }

    @Override
    public void onSuccess(GetMtgMemberResponse successResult) {


        mtgperson.addAll(successResult.getData());
        Log.e(tag, "size is " + mtgperson.size());

        setUpRecyclerView();

    }

    @Override
    public void onGetMtgMember(List <LlwMTGMember> llwMTGMemberList) {

        //setUpRecyclerView(llwMTGMemberList);

    }
}
