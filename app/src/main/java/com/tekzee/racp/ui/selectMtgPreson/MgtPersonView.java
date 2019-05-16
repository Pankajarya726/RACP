package com.tekzee.racp.ui.selectMtgPreson;

import android.content.Context;

import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.selectMtgPreson.model.Datum;
import com.tekzee.racp.ui.selectMtgPreson.model.GetMtgMemberResponse;

import java.util.List;

public interface MgtPersonView extends BaseView {
    void onItemSelected(int adapterPosition, List <Datum> mgtperson);

    Context getContext();

    void onNoInternetConnectivity(CommonResult commonResult);

    void onSuccess(GetMtgMemberResponse successResult);
}
