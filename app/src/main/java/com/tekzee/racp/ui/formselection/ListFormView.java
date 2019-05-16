package com.tekzee.racp.ui.formselection;

import android.content.Context;

import com.tekzee.racp.ui.base.BaseView;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.formselection.model.FormData;
import com.tekzee.racp.ui.formselection.model.GetAllFormResponse;

import java.util.List;

public interface ListFormView extends BaseView {
    void onItemSelected(int adapterPosition, List <FormData> formData);

    Context getContext();

    void onGetFormSuccess(GetAllFormResponse successResult);

    void onNoInternetConnectivity(CommonResult commonResult);
}
