package com.tekzee.racp.ui.formselection;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formselection.model.GetAllFormResponse;
import com.tekzee.racp.utils.NetworkUtils;
import com.tekzee.racp.utils.Utility;

public class ListFormPresenter extends BasePresenter<ListFormView> {
    public ListFormPresenter(ListFormActivity listFormActivity) {
        attachView(listFormActivity);
    }

    public void getAllForm() {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getAllFormtype(), new ApiCallback <GetAllFormResponse>() {
                @Override
                public void onSuccess(GetAllFormResponse successResult) {


                    if (successResult.getSuccess()) {
                        mvpView.onGetFormSuccess(successResult);


                    } else {
                        mvpView.onNoInternetConnectivity(new CommonResult(false, successResult.getMessage()));
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

    public void getAllForm1() {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.USER_ID));
        jsonObject.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_member_id));
        jsonObject.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_group_id));

        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getAllFormtype1(jsonObject), new ApiCallback <GetAllFormResponse>() {
                @Override
                public void onSuccess(GetAllFormResponse successResult) {


                    if (successResult.getSuccess()) {
                        mvpView.onGetFormSuccess(successResult);


                    } else {
                        mvpView.onNoInternetConnectivity(new CommonResult(false, successResult.getMessage()));
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
