package com.tekzee.racp.ui.Form.bakari_awas;

import android.app.Activity;

import com.tekzee.racp.ui.Form.bakari_awas.model.RetrivedBakariAwasResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface BakariAwasView extends BaseView {

    Activity getContext();
    void onNoInternetConnectivity(CommonResult result);
    String getPhysicalProof();
    String getUsability();
    String getNote();
    String getDateCreationGoatHouse();
    String getDD();
    String getMM();
    String getYY();

   String getLatitude();
   String getLongitude();

    void onSuccessFullSave(FormSubmitResponse successResult);

    void onSuccessfullyRetrived(RetrivedBakariAwasResponse successResult);
}
