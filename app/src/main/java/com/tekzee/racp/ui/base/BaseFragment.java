package com.tekzee.racp.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;


import com.tekzee.racp.utils.KeyboardUtils;
import com.tekzee.racp.utils.NetworkUtils;


public class BaseFragment extends Fragment implements BaseView {

    private ProgressDialog progressDialog;
    private Activity mActivity;
    private Context context;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        context = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public ProgressDialog showProgressDialog(CharSequence message, boolean cancelable) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
        return progressDialog;
    }
    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

    @Override
    public boolean isNetworkConnected(){
        return NetworkUtils.isNetworkConnected(getActivity());
    }

    @Override
    public void hideSoftKeyboard() {
        KeyboardUtils.hideSoftInput(getActivity());
    }

    @Override
    public void showSoftKeyboard(EditText editText) {
        KeyboardUtils.showSoftInput(editText,getActivity());
    }

    @Override
    public Context getActivityContext() {
        return mActivity;
    }


    @Override
    public void snackBarBottom(int view_id, String message) {

    }

    @Override
    public void snackBarTop(int view_id, String message) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

    }


    /*@Override
    public void snackBarBottom(int view_id, String message) {
        SnackbarUtils.snackBarBottom(getActivity().findViewById(view_id),message);

    }

    @Override
    public void snackBarTop(int view_id, String message) {
        SnackbarUtils.snackBarTop(getActivity().findViewById(view_id),message);

    }*/
}
