package com.tekzee.racp.ui.home;

import android.content.Context;

import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.home.model.SideMenuResponse;

public interface HomeView extends BaseView {
    Context getContext();
    void onNoInternetConnectivity(CommonResult result);

    void onSuccess(SideMenuResponse successResult);
}
