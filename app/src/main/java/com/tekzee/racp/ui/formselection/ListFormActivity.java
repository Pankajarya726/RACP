package com.tekzee.racp.ui.formselection;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Toast;

import com.tekzee.racp.R;
import com.tekzee.racp.databinding.ActivityListFormBinding;
import com.tekzee.racp.ui.Form.adoption.Adoption;
import com.tekzee.racp.ui.Form.ajola.AjolaActivity;
import com.tekzee.racp.ui.Form.bachne_yogya_bakara_bakari.ForSale;
import com.tekzee.racp.ui.Form.bakara_rotation.RotationActivity;
import com.tekzee.racp.ui.Form.bakari_vitran.BakriVitranActivity;
import com.tekzee.racp.ui.Form.bakari_awas.BakariAwas;
import com.tekzee.racp.ui.Form.beema_detail.BeemaDeatailActivity;
import com.tekzee.racp.ui.Form.clean_milkkit.MilkKitActivity;
import com.tekzee.racp.ui.Form.dana_pani_bartan.DanaPaniBartan;
import com.tekzee.racp.ui.Form.feed_suppliment.FeedSuppliment;
import com.tekzee.racp.ui.Form.form_2.Form2Activity;
import com.tekzee.racp.ui.Form.kutti_machine.KuttiMachine;
import com.tekzee.racp.ui.Form.mtg_meeting.MtgMeeting;
import com.tekzee.racp.ui.Form.mtg_prasikshan.MtgTraining;
import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.PahsuChikitsha;
import com.tekzee.racp.ui.Form.vipran_talika.VipranTableActivity;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.FormActivity;
import com.tekzee.racp.ui.Form.weighting_machine.WeightMachine;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.ui.formselection.model.FormData;
import com.tekzee.racp.ui.formselection.model.GetAllFormResponse;

import java.util.ArrayList;
import java.util.List;

public class ListFormActivity extends MvpActivity<ListFormPresenter> implements ListFormView {
    private static final String TAG = ListFormActivity.class.getSimpleName();

    private ActivityListFormBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private FormAdapter mAdapter;
    private List<FormData> formData = new ArrayList <>();
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_list_form);

        getSupportActionBar().setTitle(R.string.fill_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        value = getIntent().getIntExtra("filled_form",0);

        mvpPresenter.getAllForm();

        if (formData.size()>0){
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mAdapter.getFilter().filter(s.toString());
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });}

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //startActivity(new Intent(ForSale.this, HomeActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void filter(String text) {
        ArrayList<FormData> filteredList = new ArrayList<>();

        for (FormData item : formData) {
            if (item.getFormName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }

    @Override
    protected ListFormPresenter createPresenter() {
        return new ListFormPresenter(this);
    }

    private void setUpRecyclerView() {

        mAdapter = new FormAdapter (this,formData,this);
        binding.recyclerFormlist.setHasFixedSize(true);
        binding.recyclerFormlist.setAdapter(mAdapter);
        mAdapter.setmCallBack(this);


    }



    @Override
    public void onItemSelected(int adapterPosition, List <FormData> formData) {
        FormData formData1 = formData.get(adapterPosition);
        int id = formData1.getId();
        switch (id-1){

            case 0:

                if (value==1){
                    Intent i1 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i1.putExtra("form_id",id);
                    startActivity(i1);
                }
                else {
                    startActivity(new Intent(ListFormActivity.this, FormActivity.class));
                    mvpPresenter.mvpView.hideSoftKeyboard();
                }

                break;

            case 1:
                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, Form2Activity.class));
                }
                break;

            case 2:
                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, BakriVitranActivity.class));
                }

                break;

            case 3:

                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, BeemaDeatailActivity.class));
                }

                break;

            case 4:


                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, VipranTableActivity.class));
                }

                break;

            case 5:
                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, WeightMachine.class));
                }


                break;

            case 6:
                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, MilkKitActivity.class));
                }


                break;

            case 7:

                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, KuttiMachine.class));
                }

                break;

            case 8:
                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, DanaPaniBartan.class));
                }

                break;

            case 9:

                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, AjolaActivity.class));
                }

                break;

            case 10:
                if (value==1){
                    Intent i2 = new Intent(ListFormActivity.this, FormDataActivity.class);
                    i2.putExtra("form_id",id);
                    startActivity(i2);
                }else {
                    startActivity(new Intent(ListFormActivity.this, FeedSuppliment.class));
                }

                break;

            case 11:
                startActivity(new Intent(ListFormActivity.this, BakariAwas.class));
                break;

            case 12:
                startActivity(new Intent(ListFormActivity.this, ForSale.class));
                break;

            case 13:
                startActivity(new Intent(ListFormActivity.this, PahsuChikitsha.class));
                break;
            case 14:
                startActivity(new Intent(ListFormActivity.this, MtgTraining.class));
                break;

            case 15:
                startActivity(new Intent(ListFormActivity.this, MtgMeeting.class));
                break;

            case 16:
                startActivity(new Intent(ListFormActivity.this, Adoption.class));
                break;

            case 17:
                startActivity(new Intent(ListFormActivity.this, RotationActivity.class));
                break;


        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onGetFormSuccess(GetAllFormResponse successResult) {

        Toast.makeText(getContext(),successResult.getMessage(),Toast.LENGTH_SHORT).show();
        formData.addAll(successResult.getData());
        setUpRecyclerView();
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {

    }
}
