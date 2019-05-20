package com.tekzee.racp.ui.Form.milk_info_bakari;

import android.content.Context;

import com.tekzee.racp.ui.Form.milk_info_bakari.model.RetrivedMilkInfoResponse;
import com.tekzee.racp.ui.Form.vipran_talika.model.MtgMemberResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface MilkInfoView extends BaseView {
    Context getContext();
    int getMtgGroupId();
    int getMtgMemberId();
    String getPashuPalakName();
    int getGramId();
    int getGramPanchayatId();
    String getAddress();
    String getMobile();
    Float getTotleMilkProduction();
    Float getUpbhogMatra();
    Float getUplabdhMatra();

    void onNoInternetConnectivity(CommonResult message);

    void onGramPanchayatSelected(GramPanchayat model, String type);

    void onGetMemberDetail(MtgMemberResponse successResult);

    void SuccessfullSave(FormSubmitResponse successResult);

    void onSuccessfullyRetrived(RetrivedMilkInfoResponse successResult);
}
