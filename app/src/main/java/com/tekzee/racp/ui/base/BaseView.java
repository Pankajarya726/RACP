package com.tekzee.racp.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;

public interface BaseView {
    ProgressDialog showProgressDialog(CharSequence message, boolean cancelable);
    void hideProgressDialog();

    //check network connection
    boolean isNetworkConnected();
    Context getActivityContext();

    void hideSoftKeyboard();
    void showSoftKeyboard(EditText view);

    void snackBarBottom(int view_id, String message);
    void snackBarTop(int view_id, String message);
}
