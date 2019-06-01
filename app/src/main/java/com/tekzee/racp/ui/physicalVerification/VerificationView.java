package com.tekzee.racp.ui.physicalVerification;

import android.app.Activity;

import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface VerificationView extends BaseView {

    Activity getContext();

    String getProofDate();
    String getNote();
    String getFormId();
    String getLatidude();
    String getLongitude();

    void onSuccessFullSave(FormSubmitResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);
}
