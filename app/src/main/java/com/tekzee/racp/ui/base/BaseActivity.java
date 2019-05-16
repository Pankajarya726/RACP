package com.tekzee.racp.ui.base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tekzee.racp.utils.KeyboardUtils;
import com.tekzee.racp.utils.NetworkUtils;
import com.tekzee.racp.utils.SnackbarUtils;

public class BaseActivity extends AppCompatActivity implements BaseView{




    private ProgressDialog progressDialog;
    private Context context;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        context = this;
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        context = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        context = this;

    }



    public ProgressDialog showProgressDialog(CharSequence message, boolean cancelable) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
        return progressDialog;
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void hideSoftKeyboard() {
        KeyboardUtils.hideSoftInput(this);
    }

    @Override
    public void showSoftKeyboard(EditText view) {
        KeyboardUtils.showSoftInput(view, this);
    }

    @Override
    public void snackBarBottom(int view_id, String message) {
        SnackbarUtils.snackBarBottom(findViewById(view_id),message);
    }

    @Override
    public void snackBarTop(int view_id, String message) {
        SnackbarUtils.snackBarTop(findViewById(view_id),message);
    }


}
