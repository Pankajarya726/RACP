package com.tekzee.racp.ui.Form.feed_suppliment;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.ui.Form.feed_suppliment.model.RetrivedFeedSupplimentResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.NetworkUtils;

import org.json.JSONException;

import java.util.ArrayList;

public class FeedSupplimentPresenter extends BasePresenter<FeedSupplimentView> {
    public FeedSupplimentPresenter(FeedSupplimentView feedSuppliment) {
        attachView(feedSuppliment);
    }

    public void saveForm(JsonObject json) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getString(com.tekzee.racp.R.string.no_internet)));
        } else {
            addSubscription(apiStores.addFeedSuppliment(json), new ApiCallback <FormSubmitResponse>() {

                @Override
                public void onSuccess(FormSubmitResponse successResult) throws JSONException {
                    if (successResult.getSuccess()) {
                        mvpView.SuccessfullSave(successResult);


                    } else {
                        mvpView.onNoInternetConnectivity(new CommonResult(false, successResult.getMessage()));
                    }
                }

                @Override
                public void onFailure(CommonResult commonResult) {
                    mvpView.onNoInternetConnectivity(commonResult);
                }

                @Override
                public void onFinish() {
                    mvpView.hideProgressDialog();
                }
            });
        }
    }

    public void getFormRecordData1(JsonObject jsonObject) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getFeedSupplimentData1(jsonObject), new ApiCallback <RetrivedFeedSupplimentResponse>() {
                @Override
                public void onSuccess(RetrivedFeedSupplimentResponse successResult) {


                    if (successResult.getSuccess()) {
                        mvpView.onSuccessfullyRetrived(successResult);


                    } else {
                        mvpView.onNoInternetConnectivity(new CommonResult(false, successResult.getMessage()));
                    }

                }

                @Override
                public void onFailure(CommonResult commonResult) {
                    mvpView.onNoInternetConnectivity(commonResult);
                }

                @Override
                public void onFinish() {
                    mvpView.hideProgressDialog();
                }
            });
        }
    }


    public void openSelector(ArrayList <String> arrayList, final String type , final String title) {
        if (arrayList.size() > 0) {

            final Dialog dialog = new Dialog(mvpView.getContext());
            try {
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.popup_select_grampanchayat);
                dialog.getWindow().setLayout(-1, -2);
                dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);

                RecyclerView rv_country = dialog.findViewById(R.id.rv_grampanchayat);

                rv_country.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
                final EffectAdapter adapter = new EffectAdapter(arrayList, new EffectAdapter.RowSelect() {
                    @Override
                    public void onSelect(String model) {
                        mvpView.hideSoftKeyboard();
                        dialog.dismiss();
                        mvpView.onEffectSelect(model, type,title);


                    }
                });
                mvpView.hideSoftKeyboard();
                rv_country.setAdapter(adapter);
                TextView et_country_name = dialog.findViewById(R.id.et_gram_panchayat);
                et_country_name.setText(title);




                dialog.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mvpView.hideSoftKeyboard();
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mvpView.hideSoftKeyboard();
                        dialog.dismiss();
                    }
                });

                dialog.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // mvpView.showInPopup(mvpView.getContext().getResources().getString(R.string.gram_panchayat));
        }

    }

}
