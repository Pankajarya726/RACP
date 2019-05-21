package com.tekzee.racp.ui.Form.bakari_vitran;

import android.content.Context;
import android.widget.TextView;

import com.tekzee.racp.ui.Form.bakari_vitran.model.BakriData;
import com.tekzee.racp.ui.Form.bakari_vitran.model.RetrivedDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.dashboard.DashboardAdapter;
import com.tekzee.racp.utils.Dialogs;

import java.util.List;

public interface BakariVitranView extends BaseView , Dialogs.okClickListner {
    Context getContext();



    void onNoInternetConnectivity(CommonResult commonResult);

    void SuccessfullSave(FormSubmitResponse successResult);

    void onDateSelected(int adapterPosition, List<Immunization> immunizations, TextView tv_date);

    void onSuccessfullyRetrived(RetrivedDataResponse successResult);
}
