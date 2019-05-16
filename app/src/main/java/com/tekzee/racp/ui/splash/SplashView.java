package com.tekzee.racp.ui.splash;

import android.content.Context;

import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

import org.json.JSONObject;

public interface SplashView extends BaseView {


    void startHomeActivity();
    void startLoginActivity();
    Context getContext();
    void onValidateAppVersionSuccess(JSONObject jsonObject);
    void onNoInternetConnectivity(CommonResult result);
    void showMandatoryUpdatePopup(String message);

    void showNonMandatoryUpdatePopup(String message, JSONObject jsonObject);
}
