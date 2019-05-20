package com.tekzee.racp.ui.formdata;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.ActivityFormDataBinding;
import com.tekzee.racp.ui.Form.adoption.Adoption;
import com.tekzee.racp.ui.Form.ajola.AjolaActivity;
import com.tekzee.racp.ui.Form.bakara_rotation.RotationActivity;
import com.tekzee.racp.ui.Form.bakari_awas.BakariAwas;
import com.tekzee.racp.ui.Form.bakari_vitran.BakriVitranActivity;
import com.tekzee.racp.ui.Form.beema_detail.BeemaDeatailActivity;
import com.tekzee.racp.ui.Form.clean_milkkit.MilkKitActivity;
import com.tekzee.racp.ui.Form.dana_pani_bartan.DanaPaniBartan;
import com.tekzee.racp.ui.Form.feed_suppliment.FeedSuppliment;
import com.tekzee.racp.ui.Form.form_2.Form2Activity;
import com.tekzee.racp.ui.Form.kutti_machine.KuttiMachine;
import com.tekzee.racp.ui.Form.milk_info_bakari.MilkInfoActivity;
import com.tekzee.racp.ui.Form.mtg_meeting.MtgMeeting;
import com.tekzee.racp.ui.Form.mtg_prasikshan.MtgTraining;
import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.PahsuChikitsha;
import com.tekzee.racp.ui.Form.vipran_talika.VipranTableActivity;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.FormActivity;
import com.tekzee.racp.ui.Form.weighting_machine.WeightMachine;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.model.Datum;
import com.tekzee.racp.ui.formdata.model.FormDataResponse;
import com.tekzee.racp.ui.home.HomeActivity;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class FormDataActivity extends MvpActivity <FormDataPresenter> implements FormDataView {
    List <Datum> datumList = new ArrayList <>();
    private ActivityFormDataBinding binding;
    private int form_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form_data);


        form_id = getIntent().getIntExtra("form_id", 0);

        Log.e("form data activity", "" + form_id);

        getSupportActionBar().setTitle(R.string.list_submitted_from);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);


        loadList();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getContext(), HomeActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected FormDataPresenter createPresenter() {
        return new FormDataPresenter(this);
    }

    private void loadList() {


        JsonObject input = new JsonObject();
        input.addProperty("user_id", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
        input.addProperty("form_id", form_id);
        input.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_member_id));
        input.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(getContext(), Constant.mtg_group_id));

        mvpPresenter.getAllFormRecordList(input);

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {

        Dialogs.showColorDialog(getContext(),commonResult.getMessage());

    }

    @Override
    public void onSuccess(FormDataResponse successResult) {

        datumList.addAll(successResult.getData());

        Log.e("tag", "list size" + datumList.size());


        for (int i = 0; i < datumList.size(); i++) {

            Datum datum = datumList.get(i);

            Log.e("tagno  = ", "" + datum.getTagNo());
            Log.e("receipt date  = ", "" + datum.getDateReceipt());
            Log.e("table id  = ", "" + datum.getTableId());
            Log.e("table id  = ", "" + datum.getTableId());
        }

        setUpRecyclerView();

    }

    private void setUpRecyclerView() {

        binding.recyclerList.setLayoutManager(new LinearLayoutManager(getContext()));
        FormDataAdapter adapter = new FormDataAdapter(getContext(), datumList, this);

        binding.recyclerList.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(int adapterPosition, List <Datum> formData) {

        Datum datum = datumList.get(adapterPosition);
        switch (form_id) {

            case 1:

                Intent i = new Intent(getContext(), FormActivity.class);
                i.putExtra("table_id", datum.getTableId());
                startActivity(i);
                break;

            case 2:

                Intent i2 = new Intent(getContext(), Form2Activity.class);
                i2.putExtra("table_id", datum.getTableId());
                startActivity(i2);
                break;

            case 3:

                Intent i3 = new Intent(getContext(), BakriVitranActivity.class);
                i3.putExtra("table_id", datum.getTableId());
                startActivity(i3);
                break;

            case 4:

                Intent i4 = new Intent(getContext(), BeemaDeatailActivity.class);
                i4.putExtra("table_id", datum.getTableId());
                startActivity(i4);
                break;

            case 6:
                Intent i6 = new Intent(getContext(), WeightMachine.class);
                i6.putExtra("table_id", datum.getTableId());
                startActivity(i6);
                break;
            case 7:
                Intent i7 = new Intent(getContext(), MilkKitActivity.class);
                i7.putExtra("table_id", datum.getTableId());
                startActivity(i7);
                break;
            case 8:
                Intent i8 = new Intent(getContext(), KuttiMachine.class);
                i8.putExtra("table_id", datum.getTableId());
                startActivity(i8);
                break;
            case 9:
                Intent i9 = new Intent(getContext(), DanaPaniBartan.class);
                i9.putExtra("table_id", datum.getTableId());
                startActivity(i9);
                break;
            case 10:
                Intent i10 = new Intent(getContext(), AjolaActivity.class);
                i10.putExtra("table_id", datum.getTableId());
                startActivity(i10);

                break;
            case 11:
                Intent i11 = new Intent(getContext(), FeedSuppliment.class);
                i11.putExtra("table_id", datum.getTableId());
                startActivity(i11);
                break;
            case 12:
                Intent i12 = new Intent(getContext(), BakariAwas.class);
                i12.putExtra("table_id", datum.getTableId());
                startActivity(i12);
                break;

            case 14:
                Intent i14 = new Intent(getContext(), PahsuChikitsha.class);
                i14.putExtra("table_id", datum.getTableId());
                startActivity(i14);
                break;

            case 15:
                Intent i15 = new Intent(getContext(), MtgTraining.class);
                i15.putExtra("table_id", datum.getTableId());
                startActivity(i15);

                break;
            case 16:
                Intent i16 = new Intent(getContext(), MtgMeeting.class);
                i16.putExtra("table_id", datum.getTableId());
                startActivity(i16);

                break;
            case 17:
                Intent i17 = new Intent(getContext(), Adoption.class);
                i17.putExtra("table_id", datum.getTableId());
                startActivity(i17);
                break;

            case 18:
                Intent i18 = new Intent(getContext(), RotationActivity.class);
                i18.putExtra("table_id", datum.getTableId());
                startActivity(i18);
                break;

            case 19:
                Intent i19 = new Intent(getContext(), VipranTableActivity.class);
                i19.putExtra("table_id", datum.getTableId());
                startActivity(i19);

           case 20:
                Intent i20 = new Intent(getContext(), MilkInfoActivity.class);
                i20.putExtra("table_id", datum.getTableId());
                startActivity(i20);

                break;


        }


    }
}
