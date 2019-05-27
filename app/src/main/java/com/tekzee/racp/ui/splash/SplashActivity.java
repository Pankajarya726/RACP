package com.tekzee.racp.ui.splash;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.tekzee.racp.R;
import com.tekzee.racp.RacpApplication;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.ActivitySplashBinding;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.home.HomeActivity;
import com.tekzee.racp.ui.login.activity.Login;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;

import org.json.JSONObject;

import java.util.Locale;

import cn.refactor.lib.colordialog.ColorDialog;

public class SplashActivity extends MvpActivity <SplashPresenter> implements SplashView {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private final int SCREEN_DURATION = 3000;
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        Utility.setSharedPreference(getContext(), Constant.language_code, "hi");

        setAppLanguage();

        Log.e(TAG, " " + Utility.getSharedPreferencesBoolean(this, Constant.isVerifyOtp));
        mvpPresenter.validateAppversion(RacpApplication.versionCode, RacpApplication.versionName);


        Log.d("version code" + RacpApplication.versionCode, "version name " + RacpApplication.versionName);

    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }


    @Override
    public void startHomeActivity() {

        startActivity(new Intent(getContext(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @Override
    public void startLoginActivity() {
        Log.d("TAG", "firebase token" + FirebaseInstanceId.getInstance().getToken());
        startActivity(new Intent(getContext(), Login.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onValidateAppVersionSuccess(JSONObject jsonObject) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Utility.getSharedPreferencesBoolean(getContext(), Constant.isVerifyOtp)) {

                    startHomeActivity();
                } else {
                    startLoginActivity();
                }

            }
        }, 3000);

    }

    @Override
    public void onNoInternetConnectivity(CommonResult result) {
       // Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_LONG).show();

        Dialogs.showColorDialog(getContext(),result.getMessage());
        mvpPresenter.checkInternetPermission(getContext(),3000);
      /*  Log.e(TAG, result.getMessage());
        ColorDialog dialog = new ColorDialog(getContext());
        dialog.setCancelable(false);
        dialog.setColor("#FF6500");
        dialog.setTitle(getResources().getString(R.string.app_name));
        dialog.setContentText(result.getMessage());
        dialog.setContentTextColor("#FFFFFF");
        dialog.setPositiveListener("ok", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {

                dialog.dismiss();
                mvpPresenter.checkInternetPermission(getContext(),3000);
            }
        }).show();*/

    }

    @Override
    public void showMandatoryUpdatePopup(String message) {

        ColorDialog dialog = new ColorDialog(getContext());
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.app_name));
        dialog.setContentText(message);
        dialog.setPositiveListener("Update", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {

                final String appPackageName = getPackageName(); // package name of the app
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    finish();
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).show();

    }

    @Override
    public void showNonMandatoryUpdatePopup(String message, JSONObject jsonObject) {
        ColorDialog dialog = new ColorDialog(getContext());
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.app_name));
        dialog.setContentText(message);
        dialog.setPositiveListener(getText(R.string.Update), new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog dialog) {

                final String appPackageName = getPackageName(); // package name of the app
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    finish();
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        })
                .setNegativeListener(getText(R.string.not_now), new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {

                        dialog.dismiss();

                        mvpPresenter.checkInternetPermission(getContext(), 3000);
                    }
                }).show();

    }


    private void setAppLanguage() {
        try {
            Resources res = getContext().getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.setLocale(new Locale((Utility.getSharedPreferences(getContext(), Constant.language_code)).toLowerCase())); // API 17+ only.
            res.updateConfiguration(conf, dm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
