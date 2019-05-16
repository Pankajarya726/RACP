package com.tekzee.racp.ui.info;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tekzee.racp.R;
import com.tekzee.racp.databinding.ActivityInformationBinding;
import com.tekzee.racp.ui.base.MvpActivity;

public class InfoActivity extends MvpActivity<InfoPresenter> implements InfoView{
    private ActivityInformationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_information);

        getSupportActionBar().setTitle(R.string.add_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);


    }

    @Override
    protected InfoPresenter createPresenter() {
        return new InfoPresenter(this);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
