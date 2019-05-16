package com.tekzee.racp.ui.selectMtgPreson;

import android.util.Log;

import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.selectMtgPreson.model.GetMtgMemberResponse;
import com.tekzee.racp.utils.NetworkUtils;

public class MgtPersonPresenter extends BasePresenter <MgtPersonView> {
    public MgtPersonPresenter(SelectMgtPerson selectMgtPerson) {
        attachView(selectMgtPerson);
    }

    public void getMtgPerson(int id) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getMTGMemberByMtgGroupId(id), new ApiCallback <GetMtgMemberResponse>() {
                @Override
                public void onSuccess(GetMtgMemberResponse successResult) {
                    //Log.e("", String.valueOf(successResult));

                    if (successResult.getSuccess()) {

                        mvpView.onSuccess(successResult);

                    } else {
                        //  Log.e("", String.valueOf(successResult.getMessage()));
                        mvpView.onNoInternetConnectivity(new CommonResult(false, successResult.getMessage()));
                    }
                }


                @Override
                public void onFailure(CommonResult commonResult) {
                    Log.e("", String.valueOf(commonResult.getMessage()));
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
