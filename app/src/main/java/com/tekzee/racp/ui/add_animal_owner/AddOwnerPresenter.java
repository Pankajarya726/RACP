package com.tekzee.racp.ui.add_animal_owner;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
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

public class AddOwnerPresenter extends BasePresenter <AddOwnerView> {
    public AddOwnerPresenter(AddOwnerActivity add_ownerActivity) {
        attachView(add_ownerActivity);
    }


    public void getGramPanchayat(int tehsil_id) {

        mvpView.showProgressDialog("Please wait...", false);
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideSoftKeyboard();
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getGrampanchayatByTahsilId(tehsil_id), new ApiCallback <JsonObject>() {
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
                            openSelector(arrayList, "Gram_panchayat",mvpView.getContext().getString(R.string.gram_panchayat));

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
                            openSelector(arrayList, "Gram",mvpView.getContext().getString(R.string.village));

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

    public void getPashuPalakCategory() {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getPashuPalakCategory(), new ApiCallback <JsonObject>() {
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
                                        object.getInt("pashuPalakCategoryID"),
                                        object.getString("pashuPalakCategoryName")
                                ));
                            }
                            openSelector(arrayList, "PashuPalakCategory",mvpView.getContext().getString(R.string.pashu_palak_unit));

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

    public void getMtgGroup(int user_id) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);


        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getMTGGroupByuserId(user_id), new ApiCallback <JsonObject>() {
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
                            openSelector(arrayList, "MtgGroup",mvpView.getContext().getString(R.string.mtg_group));

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

    public void getIdentificationType() {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);

        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getIdentificationType(), new ApiCallback <JsonObject>() {
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
                                        object.getInt("identificationtype_id"),
                                        object.getString("identificationtype_Name")
                                ));
                            }
                            openSelector(arrayList, "identificationtype",mvpView.getContext().getString(R.string.select_identification_type));

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


    public void getCastCategory() {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);

        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getCastCategory(), new ApiCallback <JsonObject>() {
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
                                        object.getInt("castCategory_id"),
                                        object.getString("castCategory_Name")
                                ));
                            }
                            openSelector(arrayList, "castCategory",mvpView.getContext().getResources().getString(R.string.select_farmer_category));

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

    public void getFarmerType() {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);

        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getFarmerType(), new ApiCallback <JsonObject>() {
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
                                        object.getInt("farmertype_id"),
                                        object.getString("farmertype_Name")
                                ));
                            }
                            openSelector(arrayList, "farmertype",mvpView.getContext().getString(R.string.select_farmer_type));

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

    public void getVidhanSabhaKshetra(int id) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getVidhanasabhaByDistrict(id), new ApiCallback <JsonObject>() {
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
                                        object.getInt("vidhanasabha_id"),
                                        object.getString("vidhanasabha_Name")
                                ));
                            }
                            openSelector(arrayList, "vidhanasabha",mvpView.getContext().getString(R.string.select_vidhansabha));

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

    public void getTehsil(int id) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getTahsilByVidhanasabhaId(id), new ApiCallback <JsonObject>() {
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
                                        object.getInt("tahsil_id"),
                                        object.getString("tahsil_Name")
                                ));
                            }
                            openSelector(arrayList, "tahsil",mvpView.getContext().getString(R.string.select_tehsil));

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

    public void addPashuPalak(JsonObject input) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.addPashuPalak(input), new ApiCallback <AddPashuPalakResponse>() {
                @Override
                public void onSuccess(AddPashuPalakResponse successResult) {


                    if (successResult.getSuccess()) {
                        mvpView.onAddPashuPalakSuccess(successResult);


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


    public void getDistrict() {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getDistrictList(), new ApiCallback <JsonObject>() {
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
                                        object.getInt("district_id"),
                                        object.getString("district_Name")
                                ));
                            }
                            openSelector(arrayList, "district",mvpView.getContext().getString(R.string.select_district));

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
}
