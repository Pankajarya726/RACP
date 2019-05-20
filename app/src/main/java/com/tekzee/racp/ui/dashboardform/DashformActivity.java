package com.tekzee.racp.ui.dashboardform;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListPopupWindow;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.ActivityDashformBinding;
import com.tekzee.racp.ui.Form.adoption.Adoption;
import com.tekzee.racp.ui.Form.bakara_rotation.RotationActivity;
import com.tekzee.racp.ui.Form.mtg_meeting.MtgMeeting;
import com.tekzee.racp.ui.Form.mtg_prasikshan.MtgTraining;
import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.PahsuChikitsha;
import com.tekzee.racp.ui.addMGTgroup.AddMtgActivity;
import com.tekzee.racp.ui.add_animal_owner.AddOwnerActivity;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.dashboard.GridAdapter;
import com.tekzee.racp.ui.dashboard.model.DashboardDataResponse;
import com.tekzee.racp.ui.dashboard.model.Homemenu;
import com.tekzee.racp.ui.formdata.FormDataActivity;
import com.tekzee.racp.ui.selectMtgGroup.SelectMtgActivity;
import com.tekzee.racp.utils.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class DashformActivity extends MvpActivity<DashboardFromPresenter> implements DashboardFormView, AdapterView.OnItemClickListener {

    private String tag = DashformActivity.class.getSimpleName();
    private ActivityDashformBinding binding;
    private List <Homemenu> homemenuList = new ArrayList <>();
    private int value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dashform);
        getSupportActionBar().setTitle(R.string.dashboard_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        Log.e("ladsghaksgha;lsghlasdgh","kasdgkjadhsgkjasgh");
        getDashboardData();

        binding.gridview.setOnItemClickListener(this);


    }

    private void getDashboardData() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
        jsonObject.addProperty("mobile", Utility.getSharedPreferences(getContext(), Constant.Mobile));
        mvpPresenter.getDashBoardData(jsonObject);
    }

    @Override
    protected DashboardFromPresenter createPresenter() {
        return new DashboardFromPresenter(this);
    }


    @Override
    public void onItemClick(AdapterView <?> parent, View view, int position, long id) {


        Homemenu m = homemenuList.get(position);

        switch (position) {


            case 0:
                Log.e(tag,"formid = "+m.getMenuId());
                Intent intent5 = new Intent(getContext(), FormDataActivity.class);
                intent5.putExtra("form_id", m.getMenuId());

                startActivity(intent5);
                break;

            case 1:
                Intent intent6 = new Intent(getContext(), FormDataActivity.class);
                intent6.putExtra("form_id", m.getMenuId());
                startActivity(intent6);
                Log.e("TAG", "id" + Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                break;

            case 2:
                Intent intent7 = new Intent(getContext(), FormDataActivity.class);
                intent7.putExtra("form_id", m.getMenuId());
                startActivity(intent7);
                Log.e("TAG", "id" + Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                break;

            case 3:
                Intent intent8 = new Intent(getContext(), FormDataActivity.class);
                intent8.putExtra("form_id", m.getMenuId());
                startActivity(intent8);
                Log.e("TAG", "id" + Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                break;

            case 4:
                Intent intent9 = new Intent(getContext(), FormDataActivity.class);
                intent9.putExtra("form_id", m.getMenuId());
                startActivity(intent9);
                Log.e("TAG", "id" + Utility.getIngerSharedPreferences(getContext(), Constant.USER_ID));
                break;

        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult commonResult) {

    }

    @Override
    public void onSuccess(DashboardDataResponse successResult) {

        homemenuList.addAll(successResult.getData().getHomemenu());

        Log.e(tag,"size"+homemenuList.size());

        setUpGridView(homemenuList);


    }
    private void setUpGridView(List <Homemenu> homemenus) {

        GridAdapter gridAdapter;
        gridAdapter = new GridAdapter(getContext(), homemenus);
        binding.gridview.setAdapter(gridAdapter);

    }

    @Override
    public void onNoFailure(CommonResult commonResult) {

    }
}
