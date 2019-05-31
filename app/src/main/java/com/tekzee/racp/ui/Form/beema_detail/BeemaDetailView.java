package com.tekzee.racp.ui.Form.beema_detail;

import android.app.Activity;

import com.tekzee.racp.ui.Form.beema_detail.model.Datum;
import com.tekzee.racp.ui.Form.beema_detail.model.RetrivedBeemaDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

import java.util.ArrayList;

public interface BeemaDetailView extends BaseView {
    Activity getContext();

    void SuccessfullSave(FormSubmitResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);

    void onSuccessfullyRetrived(RetrivedBeemaDataResponse successResult);

    void onTagNoSelected(GramPanchayat model, String type);

    void onTagNoRetrived(ArrayList<Datum> tagList);

    void onAnimalTypeSelected(GramPanchayat model, String type);
    String getLettitude();
    String getLongitude();


}
