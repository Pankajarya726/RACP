package com.tekzee.racp.ui.Form.mtg_prasikshan;

import android.content.Context;

import com.tekzee.racp.ui.Form.mtg_prasikshan.model.RetrivedMtgTrainingResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

import javax.xml.validation.Validator;

public interface MtgTrainginVeiw  extends BaseView {
    Context getContext();

    void onNoInternetConnectivity(CommonResult message);

    void onGramPanchayatSelected(GramPanchayat model, String type);

    void SuccessfullSave(FormSubmitResponse successResult);

    void onSuccessfullyRetrived(RetrivedMtgTrainingResponse successResult);
}
