package com.tekzee.racp.ui.Form.milk_info_bakari;

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

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.Form.milk_info_bakari.model.RetrivedMilkInfoResponse;
import com.tekzee.racp.ui.Form.vipran_talika.model.MtgMemberResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.addMGTgroup.CountryAdapter;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.NetworkUtils;
import com.tekzee.racp.utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MilkInfoPresenter extends BasePresenter<MilkInfoView> {

    public MilkInfoPresenter(MilkInfoView view) {
        attachView(view);
    }

    public void getMtgName() {

        Log.e("tag","getmtgname");
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getMTGGroup1(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.USER_ID)), new ApiCallback <JsonObject>() {
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
                                        object.getInt("mtggroup_id"),
                                        object.getString("mtggroup_name")
                                ));
                            }
                            openSelector(arrayList, "mtggroup",mvpView.getContext().getString(R.string.select_mtg_name));

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


    public void getMtgMemberList(int mtg_id) {

        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getMTGMemberDetail(mtg_id), new ApiCallback <JsonObject>() {
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
                                        object.getInt("pashupalak_id"),
                                        object.getString("pashupalak_name")
                                ));
                            }
                            openSelector(arrayList, "pashupalak",mvpView.getContext().getString(R.string.selcet_mtg_person));

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

    public void getGramPanchayat() {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getGramPanchayat(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.USER_ID)), new ApiCallback <JsonObject>() {
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
                                        object.getInt("grampanchayat_id"),
                                        object.getString("grampanchayat_name")
                                ));
                            }
                            openSelector(arrayList, "Gram_panchayat",mvpView.getContext().getString(R.string.select_gramPanchayat));

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


    public void getGram(int id) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getGram(id), new ApiCallback <JsonObject>() {
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
                                        object.getInt("gram_id"),
                                        object.getString("gram_name")
                                ));
                            }
                            openSelector(arrayList, "Gram",mvpView.getContext().getString(R.string.select_village));

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

    private void openSelector(ArrayList <GramPanchayat> arrayList, final String type, final String title) {
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


    public void getPashupalakDetail(int mtg_member_id) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getMTGMemberdetail(mtg_member_id), new ApiCallback <com.tekzee.racp.ui.Form.vipran_talika.model.MtgMemberResponse>() {
                @Override
                public void onSuccess(MtgMemberResponse successResult) throws JSONException {

                    Log.e(getClass().getSimpleName(),successResult.toString());
                    if (successResult.getSuccess()){
                        mvpView.hideSoftKeyboard();
                        mvpView.onGetMemberDetail(successResult);
                    }
                    else {
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

    public void saveForm(JsonObject jsonObject) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getString(com.tekzee.racp.R.string.no_internet)));
        } else {
            addSubscription(apiStores.addMilkInfo(jsonObject), new ApiCallback <FormSubmitResponse>() {

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

    public void getFormRecordData(JsonObject jsonObject) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getMilkInfoData(jsonObject), new ApiCallback <RetrivedMilkInfoResponse>() {
                @Override
                public void onSuccess(RetrivedMilkInfoResponse successResult) {


                    if (successResult.getSuccess()) {
                        mvpView.hideSoftKeyboard();
                        mvpView.onSuccessfullyRetrived(successResult);


                    } else {
                        mvpView.hideSoftKeyboard();
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
}
