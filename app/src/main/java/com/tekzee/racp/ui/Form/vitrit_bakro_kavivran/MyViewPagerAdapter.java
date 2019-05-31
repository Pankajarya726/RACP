package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.BakariDataModel;

import java.util.ArrayList;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<BakariDataModel> arrayList;

    public MyViewPagerAdapter(FragmentManager fragmentManager, ArrayList <BakariDataModel> arrayList) {
        super(fragmentManager);
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Fragment getItem(int position) {
       return PageFragment.newInstance(arrayList.get(position));
        //return null;
    }
}