package com.tekzee.racp.ui.Form.bakara_rotation;

import android.content.Context;

import com.tekzee.racp.ui.Form.bakara_rotation.model.RetrivedBakraRotationResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;

public interface RotationView extends BaseView {
    Context getContext();

    void SuccessfullSave(FormSubmitResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);

    void onSuccessfullyRetrived(RetrivedBakraRotationResponse successResult);

    int getTagNo();
    String getPashupalakNameBefore();
    String getMtgNameBefore();
    String getAddressBefore();
    String getMobileBefore();
    String getVidhanSabhaBefore();
    String getGramPanchayatBefore();
    String getTehsilBefore();
    String getGramBefore();
    String getPashupalakNameAfter();
    String getMtgNameAfter();
    String getAddressAfter();
    String getMobileAfter();
    String getVidhanSabhaAfter();
    String getGramPanchayatAfter();
    String getTehsilAfter();
    String getGramAfter();

 }
