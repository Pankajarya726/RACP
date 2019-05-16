package com.tekzee.racp.ui.formdata;

import android.content.Context;

import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formdata.model.Datum;
import com.tekzee.racp.ui.formdata.model.FormDataResponse;

import java.util.List;

public interface FormDataView extends BaseView {
    Context getContext();

    void onNoInternetConnectivity(CommonResult commonResult);

    void onSuccess(FormDataResponse successResult);

    void onItemSelected(int adapterPosition, List<Datum> formData);
}
