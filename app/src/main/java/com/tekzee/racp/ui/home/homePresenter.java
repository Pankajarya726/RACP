package com.tekzee.racp.ui.home;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.home.model.SideMenuResponse;
import com.tekzee.racp.utils.NetworkUtils;

public class homePresenter extends BasePresenter<HomeView> {


    public homePresenter(HomeActivity homeActivity) {
        attachView(homeActivity);
    }

    public void getSideMenuData(JsonObject input) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...",false);
        // mvpView.hideSoftKeyboard();
        if(!NetworkUtils.isNetworkConnected(mvpView.getContext()))
        {

            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        }else {
            addSubscription(apiStores.getSideMenuData(input), new ApiCallback <SideMenuResponse>() {
                @Override
                public void onSuccess(SideMenuResponse successResult) {
                    if(successResult.getSuccess())
                    {
                        mvpView.onSuccess(successResult);
                    }else {
                        mvpView.onNoInternetConnectivity(new CommonResult(false,successResult.getMessage()));
                    }
                }

                @Override
                public void onFailure(CommonResult commonResult) {
                    mvpView.onNoInternetConnectivity(commonResult);
                }

                @Override
                public void onFinish() {
                    mvpView.hideProgressDialog();
                }
            });
        }

    }
















}
