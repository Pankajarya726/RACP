package com.tekzee.racp.ui.selectMtgGroup;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.sqlite.SqliteDB;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.selectMtgGroup.model.GetMtgResponse;
import com.tekzee.racp.utils.Log;
import com.tekzee.racp.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectMtgPresenter extends BasePresenter<SelectMtgView> {

    private static final String TAG = SelectMtgPresenter.class.getSimpleName();

    public SelectMtgPresenter(SelectMtgActivity selectMtgActivity) {
        attachView(selectMtgActivity);
    }

    public void getMtgGroup(int id) {

      /*  List <MtgGroup> mtgGroupList = new ArrayList <>();
        mtgGroupList = MtgGroup.listAll(MtgGroup.class);

        if (mtgGroupList.size()>0) {
            mvpView.onGetMtgGroup(mtgGroupList);

        }else {
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getString(R.string.identification_type_not_available)));
        }
*/



        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...",false);
        // mvpView.hideSoftKeyboard();
        if(!NetworkUtils.isNetworkConnected(mvpView.getContext()))
        {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        }else {
            addSubscription(apiStores.getMTGGroupByUserId(id), new ApiCallback <GetMtgResponse>() {
                @Override
                public void onSuccess(GetMtgResponse successResult) {
                    Log.view(TAG,successResult.getMessage());
                    if(successResult.getSuccess())
                    {
                        mvpView.onSuccess(successResult);

                    }else {
                        Log.view("tag",successResult.getMessage());
                        mvpView.onNoInternetConnectivity(new CommonResult(false,successResult.getMessage()));
                    }
                }

                @Override
                public void onFailure(CommonResult commonResult) {

                    mvpView.onNoInternetConnectivity(new CommonResult(false,commonResult.getMessage()));
                }

                @Override
                public void onFinish() {
                    mvpView.hideProgressDialog();
                }
            });
        }
    }
  public void getMtgMember(final Integer mtggroupId) {
        mvpView.hideSoftKeyboard();
       // mvpView.showProgressDialog("Please wait...",false);
        // mvpView.hideSoftKeyboard();
        if(!NetworkUtils.isNetworkConnected(mvpView.getContext()))
        {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        }else {
            addSubscription(apiStores.getMtgMember(mtggroupId), new ApiCallback <JsonObject>() {
                @Override
                public void onSuccess(JsonObject jsonObject) throws JSONException {

                    JSONObject obj = new JSONObject(String.valueOf(jsonObject));
                    if(obj.getBoolean("success"))
                    {
                        JSONArray jsonArray = obj.getJSONArray("data");
                        for (int index = 0; index < jsonArray.length(); index++) {
                            JSONObject object = jsonArray.getJSONObject(index);
                            SqliteDB sqliteDB =new SqliteDB(mvpView.getContext());
                            sqliteDB.addMtgMember(mtggroupId,object.getInt("pashupalak_id"),object.getString("pashupalak_name"));
                        }
                    }
                }
                @Override
                public void onFailure(CommonResult commonResult) {
                    //mvpView.onNoInternetConnectivity(commonResult);
                    //mvpView.hideProgressDialog();
                }

                @Override
                public void onFinish() {
                   // mvpView.hideProgressDialog();
                }
            });
        }
    }

}
