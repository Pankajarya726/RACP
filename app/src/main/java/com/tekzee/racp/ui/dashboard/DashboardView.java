package com.tekzee.racp.ui.dashboard;

import android.content.Context;

import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.dashboard.model.DashboardDataResponse;

public interface DashboardView extends BaseView {
    Context getContext();
    void onNoInternetConnectivity(CommonResult result);

    void onSuccess(DashboardDataResponse successResult);

    void onNoFailure(CommonResult commonResult);
}
