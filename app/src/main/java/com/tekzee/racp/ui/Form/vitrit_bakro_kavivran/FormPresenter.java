package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran;

import android.util.Log;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormRecordDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.add_animal_owner.AddPashuPalakResponse;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class FormPresenter extends BasePresenter<FormView> {
    private static String tag = FormPresenter.class.getSimpleName();


    public FormPresenter(FormActivity view) {
        attachView(view);
        mvpView.hideSoftKeyboard();
    }

    public void saveData(JsonObject jsonObject) throws JSONException {


        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.addDetailsGoatsDistributeds(jsonObject), new ApiCallback <FormSubmitResponse>() {

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
            addSubscription(apiStores.getFormRecordData(jsonObject), new ApiCallback <FormRecordDataResponse>() {
                @Override
                public void onSuccess(FormRecordDataResponse successResult) {


                    if (successResult.getSuccess()) {
                        mvpView.onSuccess(successResult);


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
