package com.tekzee.racp.ui.formdata;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.model.FormDataResponse;
import com.tekzee.racp.utils.NetworkUtils;
import com.tekzee.racp.utils.Utility;

public class FormDataPresenter extends BasePresenter<FormDataView> {
    public FormDataPresenter(FormDataActivity formDataActivity) {

        attachView(formDataActivity);
    }

    public void getAllFormRecordList(JsonObject input) {

       /* JsonObject input = new JsonObject();
        input.addProperty("user_id", Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.USER_ID));
        input.addProperty("form_id", 1);
        input.addProperty("mtg_member_id", Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_member_id));
        input.addProperty("mtg_group_id", Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_group_id));
*/
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getAllFormRecordList(input), new ApiCallback <FormDataResponse>() {
                @Override
                public void onSuccess(FormDataResponse successResult) {


                    if (successResult.getSuccess()) {
                        mvpView.onSuccess(successResult);


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
