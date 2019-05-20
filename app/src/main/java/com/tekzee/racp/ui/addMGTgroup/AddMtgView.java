package com.tekzee.racp.ui.addMGTgroup;

import android.content.Context;

import com.tekzee.racp.ui.addMGTgroup.model.AddMtgResponse;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayatResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface AddMtgView extends BaseView {
    Context getContext();
    void onNoInternetConnectivity(CommonResult result);

    void onGramPanchayatSelected(GramPanchayat model, String type);

    void onAddMtgSuccess(AddMtgResponse successResult);

    String getName();
    int getGramPachayatId();
    int GramId();
}
