package com.tekzee.racp.ui.Form.weighting_machine;

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

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.weighting_machine.model.GetAnimalCetegoryResponse;
import com.tekzee.racp.ui.Form.weighting_machine.model.RetrivedWeightingMachineResponse;
import com.tekzee.racp.ui.addMGTgroup.CountryAdapter;
import com.tekzee.racp.ui.addMGTgroup.model.GramPanchayat;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeightingMachinePresenter extends BasePresenter<WeightinhMachineView> {
    public WeightingMachinePresenter(WeightinhMachineView weightMachine) {
        attachView(weightMachine);
    }


    public void getAnimelCetegory() {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);

        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getAnimalCategory(), new ApiCallback <JsonObject>() {
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
                                        object.getInt("animalcategory_id"),
                                        object.getString("animalcategory_Name")
                                ));
                            }
                            openSelector(arrayList, "animalcategory");

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


    public void getAnimalTypeByCategory(int id) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);

        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getAnimalTypeByCategory(id), new ApiCallback <JsonObject>() {
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
                                        object.getInt("animaltype_id"),
                                        object.getString("animaltype_Name")
                                ));
                            }
                            openSelector(arrayList, "animaltype");

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


    private void openSelector(ArrayList <GramPanchayat> arrayList, final String type) {
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
                EditText et_country_name = dialog.findViewById(R.id.et_gram_panchayat);
                et_country_name.setVisibility(View.GONE);

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

    public void saveForm(JsonObject json) {

        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getString(com.tekzee.racp.R.string.no_internet)));
        } else {
            addSubscription(apiStores.addbharTolneKiMachine(json), new ApiCallback <FormSubmitResponse>() {

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
            addSubscription(apiStores.getWeightingMachineDetailData(jsonObject), new ApiCallback <RetrivedWeightingMachineResponse>() {
                @Override
                public void onSuccess(RetrivedWeightingMachineResponse successResult) {


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
}
