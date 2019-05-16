package com.tekzee.racp.ui.Form.pashu_chiktsak_shivir;

import android.content.Context;

import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.model.RetrivedPashuChikithsResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface PashuChikitshaView extends BaseView {
    void SuccessfullSave(FormSubmitResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);

    Context getContext();

    void onSuccessfullyRetrived(RetrivedPashuChikithsResponse successResult);
}
