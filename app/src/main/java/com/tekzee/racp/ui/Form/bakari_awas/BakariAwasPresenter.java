package com.tekzee.racp.ui.Form.bakari_awas;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.Form.bakari_awas.model.RetrivedBakariAwasResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.NetworkUtils;
import com.tekzee.racp.utils.Utility;
import com.tekzee.racp.utils.mDatePickerDialog;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BakariAwasPresenter extends BasePresenter <BakariAwasView> {
    public BakariAwasPresenter(BakariAwasView bakariAwas) {
        attachView(bakariAwas);
    }

    public void uploadDocument(byte[] imageBytes) {
        if(!NetworkUtils.isNetworkConnected(mvpView.getContext()))
        {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        }else {


            RequestBody user_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.USER_ID)));
            RequestBody form_id = RequestBody.create(MultipartBody.FORM, "12");
            RequestBody mtg_member_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_member_id)));
            RequestBody mtg_group_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_group_id)));
            RequestBody status_receipt = RequestBody.create(MultipartBody.FORM, "1");
            RequestBody physical_proof = RequestBody.create(MultipartBody.FORM, mvpView.getPhysicalProof());
            RequestBody usability = RequestBody.create(MultipartBody.FORM, mvpView.getUsability());
            RequestBody note = RequestBody.create(MultipartBody.FORM, mvpView.getNote());
            RequestBody date_creation_goat_house = RequestBody.create(MultipartBody.FORM, mDatePickerDialog.changeFormate(mvpView.getDateCreationGoatHouse()));
            RequestBody dd = RequestBody.create(MultipartBody.FORM, mvpView.getDD());
            RequestBody mm = RequestBody.create(MultipartBody.FORM, mvpView.getMM());
            RequestBody yy = RequestBody.create(MultipartBody.FORM, mvpView.getYY());
            RequestBody img_lat = RequestBody.create(MultipartBody.FORM, mvpView.getLatitude());
            RequestBody img_long = RequestBody.create(MultipartBody.FORM, mvpView.getLongitude());

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
            MultipartBody.Part formData = MultipartBody.Part.createFormData("image_upload", "image_" + System.currentTimeMillis() + ".jpg", requestFile);



            mvpView.hideSoftKeyboard();
            mvpView.showProgressDialog("Please wait...",false);


            addSubscription(apiStores.uploadImage(formData,user_id,form_id,mtg_member_id,mtg_group_id,status_receipt,physical_proof,usability,note,date_creation_goat_house,dd,mm,yy,img_lat,img_long), new ApiCallback <FormSubmitResponse>() {
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

    public void getFormRecordData(JsonObject jsonObject) {

        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getBakariAwasData(jsonObject), new ApiCallback <RetrivedBakariAwasResponse>() {
                @Override
                public void onSuccess(RetrivedBakariAwasResponse successResult) {


                    if (successResult.getSuccess()) {
                        mvpView.onSuccessfullyRetrived(successResult);


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
