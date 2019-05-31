package com.tekzee.racp.ui.Form.beema_detail;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.ui.Form.beema_detail.model.Datum;
import com.tekzee.racp.ui.Form.beema_detail.model.RetrivedBeemaDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
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

public class BeemaDetailPresenter extends BasePresenter <BeemaDetailView> {


    public BeemaDetailPresenter(BeemaDetailView view) {
        attachView(view);
    }

    public void saveForm(JsonObject jsonObject) {

        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getString(com.tekzee.racp.R.string.no_internet)));
        } else {
            addSubscription(apiStores.addDetailsGoatsDistributedInsurance(jsonObject), new ApiCallback <FormSubmitResponse>() {

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
                addSubscription(apiStores.getBeemaDetailData(jsonObject), new ApiCallback <RetrivedBeemaDataResponse>() {
                    @Override
                    public void onSuccess(RetrivedBeemaDataResponse successResult) {


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


    public void getTagNo(int id, int i) {

        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.getTagNo(id,i), new ApiCallback <JsonObject>() {
                @Override
                public void onSuccess(JsonObject jsonObject) {

                    try {
                        JSONObject mobject = new JSONObject(jsonObject.toString());
                        if (mobject.getBoolean("success")){
                            ArrayList<Datum> tagList = new ArrayList <>();
                            ArrayList<GramPanchayat> arrayList = new ArrayList <>();
                            JSONArray marray = mobject.getJSONArray("data");
                            for (int i=0;i<marray.length();i++){
                                JSONObject object= marray.getJSONObject(i);


                                tagList.add(new Datum(object.getInt("id"),object.getString("tag_no"),object.getString("date_receipt")));
                                arrayList.add(new GramPanchayat(object.getInt("id"),object.getString("tag_no")));


                            }
                            mvpView.onTagNoRetrived(tagList);

                            PopUpUtils.openSelector(mvpView.getContext(), arrayList, "tag", mvpView.getContext().getString(R.string.select_tag_no), new PopUpUtils.OnSelected() {
                                @Override
                                public void onGramPanchayatSelected(GramPanchayat model, String type) {
                                    mvpView.onTagNoSelected(model,type);
                                }
                            });


                        }else {
                           // mvpView.onNoInternetConnectivity(new CommonResult(false, mobject.getString("message")));
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                 /*   if (successResult.getSuccess()) {
                        mvpView.onSuccessfullyRetrived(successResult);


                    } else {
                        mvpView.onNoInternetConnectivity(new CommonResult(false, successResult.getMessage()));
                    }*/

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

    public void getAnimalType() {
        ArrayList<GramPanchayat> arrayList = new ArrayList <>();
        arrayList.add(new GramPanchayat(1,mvpView.getContext().getString(R.string.racp_bakra)));
        arrayList.add(new GramPanchayat(2,mvpView.getContext().getString(R.string.racp_bakari)));
        arrayList.add(new GramPanchayat(3,mvpView.getContext().getString(R.string.self_bakra)));
        arrayList.add(new GramPanchayat(4,mvpView.getContext().getString(R.string.self_bakri)));


        PopUpUtils.openSelector(mvpView.getContext(), arrayList, "animaltype", mvpView.getContext().getString(R.string.select_animal_type), new PopUpUtils.OnSelected() {
            @Override
            public void onGramPanchayatSelected(GramPanchayat model, String type) {
                mvpView.onAnimalTypeSelected(model,type);
            }
        });

    }

    public void saveForm(JsonArray jsonArray, byte[] imageBytes) {

        if(!NetworkUtils.isNetworkConnected(mvpView.getContext()))
        {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        }else {

            RequestBody mtg_group_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_group_id)));
            RequestBody user_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.USER_ID)));
            RequestBody mtg_member_id = RequestBody.create(MultipartBody.FORM, String.valueOf(Utility.getIngerSharedPreferences(mvpView.getContext(), Constant.mtg_member_id)));
            RequestBody form_id = RequestBody.create(MultipartBody.FORM, "4");
            RequestBody status_receipt = RequestBody.create(MultipartBody.FORM, "1");

            RequestBody data = RequestBody.create(MultipartBody.FORM,jsonArray.toString());
            RequestBody img_lat = RequestBody.create(MultipartBody.FORM, mvpView.getLettitude());
            RequestBody img_long = RequestBody.create(MultipartBody.FORM, mvpView.getLongitude());

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
            MultipartBody.Part formData = MultipartBody.Part.createFormData("image_upload", "image_" + System.currentTimeMillis() + ".jpg", requestFile);



            mvpView.hideSoftKeyboard();
            mvpView.showProgressDialog("Please wait...",false);


            addSubscription(apiStores.addDetailsGoatsDistributedInsurance1(formData,mtg_group_id,user_id,mtg_member_id,form_id,status_receipt,data,img_lat,img_long), new ApiCallback <FormSubmitResponse>() {
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
