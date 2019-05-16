package com.tekzee.racp.ui.Form.mtg_meeting;

import android.content.Context;

import com.tekzee.racp.ui.Form.mtg_meeting.model.MtgMemberResponse;
import com.tekzee.racp.ui.Form.mtg_meeting.model.RetrivedMtgMeetingResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface MtgMeetingView extends BaseView {

    Context getContext();

    void onNoInternetConnectivity(CommonResult commonResult);

    void onGramPanchayatSelected(GramPanchayat model, String type);

    void onGetMemberList(MtgMemberResponse successResult);

    void SuccessfullSave(FormSubmitResponse successResult);

    void onSuccessfullyRetrived(RetrivedMtgMeetingResponse successResult);
}
