package com.tekzee.racp.ui.Form.feed_suppliment;

import android.content.Context;

import com.tekzee.racp.ui.Form.feed_suppliment.model.RetrivedFeedSupplimentResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface FeedSupplimentView extends BaseView {
    void onNoInternetConnectivity(CommonResult commonResult);

    void SuccessfullSave(FormSubmitResponse successResult);

    Context getContext();

    void onSuccessfullyRetrived(RetrivedFeedSupplimentResponse successResult);
}
