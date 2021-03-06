package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran;

import android.app.Activity;
import android.widget.TextView;

import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormRecordDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.ResponseDataRetrived;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

import java.util.List;

public interface FormView extends BaseView {
    void onNoInternetConnectivity(CommonResult commonResult);

  

    Activity getContext();

    void SuccessfullSave(FormSubmitResponse successResult);

    void onSuccess(FormRecordDataResponse successResult);


    void onDateSelected(int adapterPosition, List <Immunization> immunizations, TextView tv_date);

    void onGramPanchayatSelected(GramPanchayat model, String type);

    String getLatitude();
    String getLongitude();

    void onRetrived(ResponseDataRetrived successResult);
}
