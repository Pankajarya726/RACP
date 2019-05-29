package com.tekzee.racp.ui.selectMtgGroup;

import android.content.Context;

import com.tekzee.racp.sqlite.tables.MtgGroup;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.selectMtgGroup.model.Data;
import com.tekzee.racp.ui.selectMtgGroup.model.GetMtgResponse;

import java.util.List;

public interface SelectMtgView extends BaseView {

    void onItemSelected(int position, List <Data> mgtgroup);


    Context getContext();

    void onNoInternetConnectivity(CommonResult commonResult);


    void onSuccess(GetMtgResponse successResult);

    void onGetMtgGroup(List<MtgGroup> mtgGroupList);
}
