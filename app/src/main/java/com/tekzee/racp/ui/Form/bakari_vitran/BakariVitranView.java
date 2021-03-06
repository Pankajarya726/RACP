package com.tekzee.racp.ui.Form.bakari_vitran;

import android.app.Activity;
import android.widget.TextView;

import com.tekzee.racp.ui.Form.bakari_vitran.model.RetrivedDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.Dialogs;

import org.json.JSONException;

import java.util.List;

public interface BakariVitranView extends BaseView , Dialogs.okClickListner {
    Activity getContext();



    void onNoInternetConnectivity(CommonResult commonResult);

    void SuccessfullSave(FormSubmitResponse successResult);

    void onDateSelected(int adapterPosition, List<Immunization> immunizations, TextView tv_date);

    void onSuccessfullyRetrived(RetrivedDataResponse successResult) throws JSONException;

    void onGramPanchayatSelected(GramPanchayat model, String type);

    String getLatitude();
    String getLongitude();
}
