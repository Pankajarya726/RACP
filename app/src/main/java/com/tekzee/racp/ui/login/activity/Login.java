package com.tekzee.racp.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.databinding.ActivityLoginBinding;
import com.tekzee.racp.ui.base.MvpActivity;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.home.HomeActivity;
import com.tekzee.racp.ui.login.LoginPresenter;
import com.tekzee.racp.ui.login.LoginView;
import com.tekzee.racp.ui.login.model.RequestOtpResponse;
import com.tekzee.racp.ui.login.model.VerifyOtpResponse;
import com.tekzee.racp.utils.Dialogs;
import com.tekzee.racp.utils.Utility;

public class Login extends MvpActivity <LoginPresenter> implements LoginView, View.OnClickListener {
    private static final String TAG = Login.class.getSimpleName();
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.imgNext.setOnClickListener(this);
        binding.btSubmit.setVisibility(View.GONE);
        binding.btSubmit.setOnClickListener(this);

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNoInternetConnectivity(CommonResult result) {

        Dialogs.showColorDialog(getContext(), result.getMessage());


        binding.edtMobileNumber.setEnabled(true);
        binding.imgNext.setClickable(true);
//        Dialogs.ShowDialog(getContext(),result.getMessage());

    }

    @Override
    public void onRequestOtpSuccess(RequestOtpResponse successResult) {
        Log.e(TAG, successResult.getMessage());

        Dialogs.ShowCustomDialog(getContext(), successResult.getMessage(), new Dialogs.okClickListner() {
            @Override
            public void onOkClickListner() {

                binding.btSubmit.setVisibility(View.VISIBLE);

            }
        }, " ");

        Utility.setSharedPreference(getContext(), Constant.Mobile, binding.edtMobileNumber.getText().toString().trim());

        binding.edtMobileNumber.setEnabled(true);

    }

    @Override
    public void onRequestOtpFailure(String message) {
        Dialogs.showColorDialog(getContext(), message);

    }


    @Override
    public void onVerifyOtpSuccess(VerifyOtpResponse response) {

//        Dialogs.showColorDialog(getContext(), response.getMessage());

       Utility.setSharedPreferenceBoolean(getContext(), Constant.isVerifyOtp, true);
      /*  Utility.setIntegerSharedPreference(getContext(), Constant.USER_ID, response.getData().getUserId());
        Utility.setSharedPreference(getContext(), Constant.UserName, response.getData().getUserName());
        Utility.setSharedPreference(getContext(), Constant.UserRole, response.getData().getRoleName());
        Utility.setIntegerSharedPreference(getContext(), Constant.RoleId, response.getData().getRoleId());
        Utility.setSharedPreference(getContext(), Constant.UserEmail, response.getData().getEmail());
        Utility.setSharedPreference(getContext(), Constant.Mobile, response.getData().getMobile());
        Utility.setSharedPreference(getContext(), Constant.image, response.getData().getProfileImage());
*/
        Log.e(TAG, String.valueOf(Utility.getSharedPreferencesBoolean(getContext(), Constant.isVerifyOtp)));
        startActivity(new Intent(Login.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_next:
                if (binding.edtMobileNumber.getText().toString().trim().isEmpty()) {

                    Dialogs.showColorDialog(getContext(), getString(R.string.number));
//                    SnackbarUtils.snackBarBottom(binding.edtMobileNumber,getString(R.string.enter_number));
                    // binding.edtMobileNumber.setError(getString(R.string.enter_number));
                } else if (binding.edtMobileNumber.getText().toString().trim().length() < 10) {
                    binding.edtMobileNumber.setError(getResources().getString(R.string.invlid_number));
                } else {

                    callRequestOtpApi();
                    binding.imgNext.setClickable(false);
                    binding.edtMobileNumber.setEnabled(false);
                }


                break;

            case R.id.bt_submit:

                if (binding.edtMobileNumber.getText().toString().trim().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.number));

                } else if (binding.edtMobileNumber.getText().toString().trim().length() < 10) {
                    binding.edtMobileNumber.setError(getResources().getString(R.string.invlid_number));

                } else if (binding.edtOtp.getText().toString().trim().isEmpty()) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.invalid_otp));

                } else if (binding.edtOtp.getText().toString().trim().length() < 4 ) {
                    Dialogs.showColorDialog(getContext(), getString(R.string.invalid_otp));


                }else{

                    callVerifyOtpApi();
                }

                break;


        }
    }

    private void callRequestOtpApi() {

        JsonObject input = new JsonObject();
        input.addProperty("mobile", binding.edtMobileNumber.getText().toString().trim());
        mvpPresenter.requestOtp(input);


    }

    private void callVerifyOtpApi() {
        JsonObject input = new JsonObject();
        input.addProperty("mobile", binding.edtMobileNumber.getText().toString().trim());
        input.addProperty("otp", binding.edtOtp.getText().toString().trim());
        mvpPresenter.verifyOtp(input);

    }
}
