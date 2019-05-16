package com.tekzee.racp.ui.login;

import android.content.Context;

import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.login.model.RequestOtpResponse;
import com.tekzee.racp.ui.login.model.VerifyOtpResponse;

public interface LoginView extends BaseView {


    Context getContext();
    void onNoInternetConnectivity(CommonResult result);
    void onRequestOtpSuccess(RequestOtpResponse successResult);
    void onRequestOtpFailure(String message);
    void onVerifyOtpSuccess(VerifyOtpResponse verifyOtpResponse);


}
