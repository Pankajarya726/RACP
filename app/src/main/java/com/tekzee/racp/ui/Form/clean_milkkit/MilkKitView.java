package com.tekzee.racp.ui.Form.clean_milkkit;

import android.content.Context;

import com.tekzee.racp.ui.Form.clean_milkkit.model.RetrivedMilkKitResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface MilkKitView extends BaseView {
    Context getContext();

    void SuccessfullSave(FormSubmitResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);

    void onSuccessfullyRetrived(RetrivedMilkKitResponse successResult);
}
