package com.tekzee.racp.ui.selectMtgPreson;

import android.util.Log;

import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.selectMtgPreson.model.GetMtgMemberResponse;
import com.tekzee.racp.utils.NetworkUtils;

public class MgtPersonPresenter extends BasePresenter <MgtPersonView> {
    public MgtPersonPresenter(SelectMgtPerson selectMgtPerson) {
        attachView(selectMgtPerson);
    }

    public void getMtgPerson(int id) {

      /*  List <LlwMTGMember> llwMTGMemberList = new ArrayList <>();

        LlwMTGMember.executeQuery("VACUUM");
        llwMTGMemberList = LlwMTGMember.findWithQuery(LlwMTGMember.class, "Select * from LLW_MTGMEMBER where MTGGROUP_ID = ?", String.valueOf(id));
        // llwGramList =LlwGram.listAll(LlwGram.class);
        if (llwMTGMemberList.size()>0) {

            mvpView.onGetMtgMember(llwMTGMemberList);

        }else {
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getString(R.string.gram_not_available)));
        }
*/


        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getMTGMemberByMtgGroupId(id), new ApiCallback <GetMtgMemberResponse>() {
                @Override
                public void onSuccess(GetMtgMemberResponse successResult) {
                    //Log.e("", String.valueOf(successResult));

                    if (successResult.getSuccess()) {

                        mvpView.onSuccess(successResult);

                    }
                    else {
                        Log.e("", String.valueOf(successResult.getMessage()));
                        mvpView.onNoInternetConnectivity(new CommonResult(false, successResult.getMessage()));
                    }
                }


                @Override
                public void onFailure(CommonResult commonResult) {
                    Log.e("", String.valueOf(commonResult.getMessage()));
                    mvpView.onNoInternetConnectivity(new CommonResult(false, commonResult.getMessage()));
                }

                @Override
                public void onFinish() {
                    mvpView.hideProgressDialog();
                }
            });
        }


    }
}
