package com.tekzee.racp.ui.dashboard;

import android.util.Log;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.dashboard.model.DashboardDataResponse;
import com.tekzee.racp.utils.NetworkUtils;

public class DashboardPresenter extends BasePresenter <DashboardView> {
    private static String tag = DashboardPresenter.class.getSimpleName();
    public DashboardPresenter(DashboardView view) {
        attachView(view);
    }

    public void getDashBoardData(JsonObject input) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...",false);
        // mvpView.hideSoftKeyboard();
        if(!NetworkUtils.isNetworkConnected(mvpView.getContext()))
        {
            Log.e(tag,"no internate");
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        }else {
            addSubscription(apiStores.getDashboardData(input), new ApiCallback <DashboardDataResponse>() {
                @Override
                public void onSuccess(DashboardDataResponse successResult) {
                    if(successResult.getSuccess())
                    {
                        Log.e("asd0",successResult.getMessage());
                        mvpView.onSuccess(successResult);
                    }else {
                        mvpView.onNoInternetConnectivity(new CommonResult(false,successResult.getMessage()));
                    }
                }

                @Override
                public void onFailure(CommonResult commonResult) {

                    mvpView.onNoFailure(commonResult);
                }

                @Override
                public void onFinish() {
                    mvpView.hideProgressDialog();
                }
            });
        }

    }
}
