package com.tekzee.racp.ui.Form.bakari_vitran;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.Form.bakari_vitran.model.RetrivedDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.CountryAdapter;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.NetworkUtils;
import com.tekzee.racp.utils.PopUpUtils;
import com.tekzee.racp.utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BakriVitranPresenter extends BasePresenter<BakariVitranView> {
    public BakriVitranPresenter(BakriVitranActivity bakriVitranActivity) {
        attachView(bakriVitranActivity);

    }

   /* public void saveForm(JsonObject jsonObject) {

        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.addDetailsGoatsDistributedWidowDisabledWomen(jsonObject), new ApiCallback <FormSubmitResponse>() {

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
    }*/



    public void getFormRecordData(JsonObject jsonObject) {

        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getBakriVitranData(jsonObject), new ApiCallback <RetrivedDataResponse>() {
                @Override
                public void onSuccess(RetrivedDataResponse successResult) throws JSONException {


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

    public void getBenifeciery_type() {

        ArrayList<GramPanchayat> arrayList = new ArrayList <>();
        arrayList.add(new GramPanchayat(3,"विधवा"));
        arrayList.add(new GramPanchayat(4,"विकलांग"));

        openSelector(arrayList, "type", mvpView.getContext().getString(R.string.select_pashu_palak_unit));
    }

    public void getNasl() {

        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {

            mvpView.hideSoftKeyboard();
            mvpView.showProgressDialog("Please wait...", false);

            addSubscription(apiStores.getNasla(), new ApiCallback <JsonObject>() {
                @Override
                public void onSuccess(JsonObject successResult) {

                    Log.e("", String.valueOf(successResult));
                    try {
                        JSONObject jsonObject = new JSONObject(String.valueOf(successResult));
                        jsonObject.getBoolean("success");
                        Log.e("success msg", "" + jsonObject.getBoolean("success"));

                        if (jsonObject.getBoolean("success")) {
                            ArrayList <GramPanchayat> arrayList = new ArrayList <>();
                            JSONArray countryJsonArray = jsonObject.getJSONArray("data");
                            for (int index = 0; index < countryJsonArray.length(); index++) {
                                JSONObject object = countryJsonArray.getJSONObject(index);

                                arrayList.add(new GramPanchayat(
                                        object.getInt("nasla_id"),
                                        object.getString("nasla_Name")
                                ));
                            }
                            PopUpUtils.openSelector(mvpView.getContext(), arrayList, "nasla", mvpView.getContext().getString(R.string.select_nasl), new PopUpUtils.OnSelected() {
                                @Override
                                public void onGramPanchayatSelected(GramPanchayat model, String type) {
                                    mvpView.onGramPanchayatSelected(model,type);
                                }
                            });

                        } else {
                            mvpView.onNoInternetConnectivity(new CommonResult(false, jsonObject.getString("message")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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

    private void openSelector(ArrayList <GramPanchayat> arrayList, final String type ,final String title) {
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
                final CountryAdapter adapter = new CountryAdapter(arrayList, new CountryAdapter.RowSelect() {
                    @Override
                    public void onSelect(GramPanchayat model) {
                        mvpView.hideSoftKeyboard();
                        dialog.dismiss();
                        mvpView.onGramPanchayatSelected(model, type);


                    }
                });
                mvpView.hideSoftKeyboard();
                rv_country.setAdapter(adapter);
                TextView et_country_name = dialog.findViewById(R.id.et_gram_panchayat);
                et_country_name.setText(title);

                et_country_name.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


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

    public void saveForm(byte[] imageBytes, int form_id1, int i, JsonArray jsonArray) {

        if(!NetworkUtils.isNetworkConnected(mvpView.getContext()))
        {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        }else {

            RequestBody user_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.USER_ID)));
            RequestBody form_id = RequestBody.create(MultipartBody.FORM, String.valueOf(form_id1));
            RequestBody mtg_member_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_member_id)));
            RequestBody mtg_group_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_group_id)));
            RequestBody status_receipt = RequestBody.create(MultipartBody.FORM, "1");
            RequestBody data = RequestBody.create(MultipartBody.FORM, String.valueOf(jsonArray));
             RequestBody img_lat = RequestBody.create(MultipartBody.FORM, mvpView.getLatitude());
            RequestBody img_long = RequestBody.create(MultipartBody.FORM, mvpView.getLongitude());

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
            MultipartBody.Part formData = MultipartBody.Part.createFormData("image_upload", "image_" + System.currentTimeMillis() + ".jpg", requestFile);



            mvpView.hideSoftKeyboard();
            mvpView.showProgressDialog("Please wait...",false);


                addSubscription(apiStores.addDetailsGoatsDistributedWidowDisabledWomen1(formData,user_id,form_id,mtg_member_id,mtg_group_id,status_receipt,data,img_lat,img_long), new ApiCallback <FormSubmitResponse>() {
                @Override
                public void onSuccess(FormSubmitResponse successResult) {
                    if(successResult.getSuccess())
                    {
                        mvpView.SuccessfullSave(successResult);
                    }else {
                        mvpView.onNoInternetConnectivity(new CommonResult(false,successResult.getMessage()));
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
}
