package com.tekzee.racp.ui.add_animal_owner;

import android.content.Context;

import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface AddOwnerView extends BaseView {
    Context getContext();

    void onGramPanchayatSelected(GramPanchayat model, String type);

    void onNoInternetConnectivity(CommonResult commonResult);

    void onAddPashuPalakSuccess(AddPashuPalakResponse successResult);
}
