package com.tekzee.racp.ui.Form.adoption;

import android.app.Activity;

import com.tekzee.racp.ui.Form.adoption.model.RetrivedAdoptionResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface AdoptionView extends BaseView {

    Activity getContext();

    void onNoInternetConnectivity(CommonResult commonResult);

    void onGramPanchayatSelected(GramPanchayat model, String type);

    void SuccessfullSave(FormSubmitResponse successResult);

    void onSuccessfullyRetrived(RetrivedAdoptionResponse successResult);
}
