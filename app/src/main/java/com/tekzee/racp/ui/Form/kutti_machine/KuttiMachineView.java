package com.tekzee.racp.ui.Form.kutti_machine;

import android.content.Context;

import com.tekzee.racp.ui.Form.clean_milkkit.model.RetrivedMilkKitResponse;
import com.tekzee.racp.ui.Form.kutti_machine.mode.RetrivedKuttiMachineResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface KuttiMachineView extends BaseView {
    void SuccessfullSave(FormSubmitResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);

    Context getContext();

    void onSuccessfullyRetrived(RetrivedKuttiMachineResponse successResult);
}
