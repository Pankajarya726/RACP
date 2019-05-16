package com.tekzee.racp.ui.Form.form_2;

import android.content.Context;

import com.tekzee.racp.ui.Form.form_2.Model.GetNaslResponse;
import com.tekzee.racp.ui.Form.form_2.Model.RetrivedDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface Form2View extends BaseView {
    Context getContext();

    void onNoInternetConnectivity(CommonResult commonResult);



    void SuccessfullSave(FormSubmitResponse successResult);

    void onGramPanchayatSelected(GramPanchayat model, String type);

    void onSuccessfullyRetrived(RetrivedDataResponse successResult);
}
