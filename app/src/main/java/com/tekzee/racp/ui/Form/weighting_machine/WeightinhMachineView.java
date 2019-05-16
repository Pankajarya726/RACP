package com.tekzee.racp.ui.Form.weighting_machine;

import android.content.Context;

import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.weighting_machine.model.GetAnimalCetegoryResponse;
import com.tekzee.racp.ui.Form.weighting_machine.model.RetrivedWeightingMachineResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface WeightinhMachineView extends BaseView {
    Context getContext();

    void onGetAnimelCetegory(GetAnimalCetegoryResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);

    void onGramPanchayatSelected(GramPanchayat model, String type);


    String getBakriWeight();

    void SuccessfullSave(FormSubmitResponse successResult);

    void onSuccessfullyRetrived(RetrivedWeightingMachineResponse successResult);
}
