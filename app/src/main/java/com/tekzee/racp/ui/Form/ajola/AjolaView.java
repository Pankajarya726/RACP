package com.tekzee.racp.ui.Form.ajola;

import android.content.Context;

import com.tekzee.racp.ui.Form.ajola.model.Datum;
import com.tekzee.racp.ui.Form.ajola.model.GetAnimalTypeResponse;
import com.tekzee.racp.ui.Form.ajola.model.RetrivedAjolaResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface AjolaView extends BaseView {

    Context getContext();

    void SuccessfullSave(FormSubmitResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);

    void onGetAnimelType(GetAnimalTypeResponse successResult);


    void onSuccessfullyRetrived(RetrivedAjolaResponse successResult);
}
