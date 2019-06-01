package com.tekzee.racp.ui.physicalVerification;

import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.NetworkUtils;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class VerificationPresenter extends BasePresenter<VerificationView> {


    public VerificationPresenter(VerificationView verificationView) {
        attachView(verificationView);

    }



    public void verifyFrom(byte[] imageBytes) {
        if(!NetworkUtils.isNetworkConnected(mvpView.getContext()))
        {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        }else {


            RequestBody user_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.USER_ID)));
            RequestBody form_id = RequestBody.create(MultipartBody.FORM, mvpView.getFormId());
            RequestBody mtg_member_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_member_id)));
            RequestBody mtg_group_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_group_id)));
            RequestBody date_physical_proof = RequestBody.create(MultipartBody.FORM, mDatePickerDialog.changeFormate(mvpView.getProofDate()));
            RequestBody img_lat = RequestBody.create(MultipartBody.FORM, mvpView.getLatidude());
            RequestBody img_long = RequestBody.create(MultipartBody.FORM, mvpView.getLongitude());
            RequestBody note = RequestBody.create(MultipartBody.FORM, mvpView.getNote());

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
            MultipartBody.Part formData = MultipartBody.Part.createFormData("image_upload", "image_" + System.currentTimeMillis() + ".jpg", requestFile);



            mvpView.hideSoftKeyboard();
            mvpView.showProgressDialog("Please wait...",false);


            addSubscription(apiStores.physicalObservation(formData,user_id,form_id,mtg_member_id,mtg_group_id,date_physical_proof,img_lat,img_long,note), new ApiCallback <FormSubmitResponse>() {
                @Override
                public void onSuccess(FormSubmitResponse successResult) {
                    if(successResult.getSuccess())
                    {
                        mvpView.onSuccessFullSave(successResult);
                    }else {
                        mvpView.onNoInternetConnectivity(new CommonResult(false,successResult.getMessage()));
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
