package com.tekzee.racp.ui.Form.beema_detail;

import android.content.Context;

import com.tekzee.racp.ui.Form.beema_detail.model.RetrivedBeemaDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface BeemaDetailView extends BaseView {
    Context getContext();

    void SuccessfullSave(FormSubmitResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);

    void onSuccessfullyRetrived(RetrivedBeemaDataResponse successResult);
}
