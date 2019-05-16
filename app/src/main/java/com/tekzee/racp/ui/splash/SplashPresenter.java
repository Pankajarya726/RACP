package com.tekzee.racp.ui.splash;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.NetworkUtils;
import com.tekzee.racp.utils.Utility;

import org.json.JSONObject;


public class SplashPresenter extends BasePresenter <SplashView> {
    private static final String TAG = SplashPresenter.class.getSimpleName();
    private Context context;

    public SplashPresenter(SplashView view) {
        attachView(view);
    }

    public void checkInternetPermission(final Context context, int screen_duration) {

        mvpView.hideSoftKeyboard();
        if (NetworkUtils.isNetworkConnected(context)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Log.e(TAG, " " + Utility.getSharedPreferencesBoolean(context, Constant.isVerifyOtp));
                    Log.e(TAG, " " + Utility.getIngerSharedPreferences(context, Constant.USER_ID));
                    if (Utility.getSharedPreferencesBoolean(context, Constant.isVerifyOtp) && Utility.getIngerSharedPreferences(context, Constant.USER_ID) != 0) {
                        mvpView.startHomeActivity();
                    } else
                        mvpView.startLoginActivity();

                }
            }, 2000);

        } else {

            if (Utility.getSharedPreferencesBoolean(context, Constant.isVerifyOtp) && Utility.getIngerSharedPreferences(context, Constant.USER_ID) != 0) {
                mvpView.startHomeActivity();
            } else
                mvpView.startLoginActivity();
            // mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        }

    }

    public void validateAppversion(int versionCode, String versionName) {
        mvpView.hideSoftKeyboard();
        Log.e(TAG, "" + NetworkUtils.isNetworkConnected(mvpView.getContext()));
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            checkInternetPermission(mvpView.getContext(), 3000);
        } else {
            JsonObject input = new JsonObject();
            try {

                Log.e(TAG, versionName);
                input.addProperty("userID", "");
                input.addProperty("appVersion", versionName);

                Log.e(TAG, "" + input);

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.e(TAG, "input : " + input);
            mvpView.showProgressDialog("Please wait...", false);

            addSubscription(apiStores.validateAppVersion(input), new ApiCallback <JsonObject>() {
                @Override
                public void onSuccess(JsonObject successResult) {
                    try {
                        JSONObject jsonObject = new JSONObject(successResult.toString());

                        if (jsonObject.getBoolean("success")) {
                            Log.e(TAG, "success msg1 : " + jsonObject.getBoolean("success"));

                            mvpView.onValidateAppVersionSuccess(jsonObject);

                        } else if (!jsonObject.getBoolean("success")) {

                            if (jsonObject.getInt("isMandatory") == 0) {

                                mvpView.showMandatoryUpdatePopup(jsonObject.getString("message"));

                            } else {

                                mvpView.showNonMandatoryUpdatePopup(jsonObject.getString("message"), jsonObject);

                            }

                        } else {

                            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.something_went_wrong)));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(CommonResult commonResult) {
                    mvpView.onNoInternetConnectivity(new CommonResult(false, commonResult.getMessage()));
                }

                @Override
                public void onFinish() {
                    mvpView.hideProgressDialog();
                }
            });
        }
    }
}
