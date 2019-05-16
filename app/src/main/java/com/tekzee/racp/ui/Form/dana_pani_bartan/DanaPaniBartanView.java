package com.tekzee.racp.ui.Form.dana_pani_bartan;

import android.content.Context;

import com.tekzee.racp.ui.Form.dana_pani_bartan.model.RetrivedDanaPaniResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface DanaPaniBartanView extends BaseView {
    void SuccessfullSave(FormSubmitResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);

    Context getContext();

    void onSuccessfullyRetrived(RetrivedDanaPaniResponse successResult);
}
