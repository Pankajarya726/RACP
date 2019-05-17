package com.tekzee.racp.ui.Form.vipran_talika;

import android.content.Context;
import android.widget.TextView;

import com.tekzee.racp.ui.Form.vipran_talika.model.MtgMemberResponse;
import com.tekzee.racp.ui.Form.vipran_talika.model.RetrivedVipranTalikaResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

import java.util.List;

public interface VipranView extends BaseView {

    Context getContext();
    void onDateSelected(int adapterPosition, List <Immunization> immunizations, TextView tv_date);

    void onGramPanchayatSelected(GramPanchayat model, String type);

    void onNoInternetConnectivity(CommonResult commonResult);

    void onGetMemberDetail(MtgMemberResponse successResult);

    void SuccessfullSave(FormSubmitResponse successResult);

    void onSuccessfullyRetrived(RetrivedVipranTalikaResponse successResult);
}
