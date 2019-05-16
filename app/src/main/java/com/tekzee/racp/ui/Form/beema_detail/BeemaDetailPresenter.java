package com.tekzee.racp.ui.Form.beema_detail;


import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.ui.Form.beema_detail.model.RetrivedBeemaDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.NetworkUtils;

import org.json.JSONException;

public class BeemaDetailPresenter extends BasePresenter <BeemaDetailView> {


    public BeemaDetailPresenter(BeemaDetailView view) {
        attachView(view);
    }

    public void saveForm(JsonObject jsonObject) {

        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getString(com.tekzee.racp.R.string.no_internet)));
        } else {
            addSubscription(apiStores.addDetailsGoatsDistributedInsurance(jsonObject), new ApiCallback <FormSubmitResponse>() {

                @Override
                public void onSuccess(FormSubmitResponse successResult) throws JSONException {
                    if (successResult.getSuccess()) {
                        mvpView.SuccessfullSave(successResult);


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

    public void getFormRecordData(JsonObject jsonObject) {
            mvpView.hideSoftKeyboard();
            mvpView.showProgressDialog("Please wait...", false);
            // mvpView.hideSoftKeyboard();
            if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
                mvpView.hideProgressDialog();
                mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
            } else {
                addSubscription(apiStores.getBeemaDetailData(jsonObject), new ApiCallback <RetrivedBeemaDataResponse>() {
                    @Override
                    public void onSuccess(RetrivedBeemaDataResponse successResult) {


                        if (successResult.getSuccess()) {
                            mvpView.onSuccessfullyRetrived(successResult);


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
