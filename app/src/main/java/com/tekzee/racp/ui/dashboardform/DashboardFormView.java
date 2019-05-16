package com.tekzee.racp.ui.dashboardform;

import android.content.Context;

import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.dashboard.model.DashboardDataResponse;

public interface DashboardFormView extends BaseView {
    Context getContext();

    void onNoInternetConnectivity(CommonResult commonResult);

    void onSuccess(DashboardDataResponse successResult);

    void onNoFailure(CommonResult commonResult);
}
