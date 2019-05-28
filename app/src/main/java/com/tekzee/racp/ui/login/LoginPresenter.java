package com.tekzee.racp.ui.login;

import com.google.gson.JsonObject;
import com.tekzee.racp.R;
import com.tekzee.racp.api.ApiCallback;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.sqlite.tables.AnimalCategory;
import com.tekzee.racp.sqlite.tables.AnimalType;
import com.tekzee.racp.sqlite.tables.Castcategory;
import com.tekzee.racp.sqlite.tables.District;
import com.tekzee.racp.sqlite.tables.Farmertype;
import com.tekzee.racp.sqlite.tables.Formtype;
import com.tekzee.racp.sqlite.tables.Gram;
import com.tekzee.racp.sqlite.tables.Grampanchayat;
import com.tekzee.racp.sqlite.tables.Homemenu;
import com.tekzee.racp.sqlite.tables.Identificationtype;
import com.tekzee.racp.sqlite.tables.LlwGram;
import com.tekzee.racp.sqlite.tables.LlwGrampanchayat;
import com.tekzee.racp.sqlite.tables.LlwMTGMember;
import com.tekzee.racp.sqlite.tables.MtgGroup;
import com.tekzee.racp.sqlite.tables.Nasla;
import com.tekzee.racp.sqlite.tables.PashuPalakCategory;
import com.tekzee.racp.sqlite.tables.Sidemenu;
import com.tekzee.racp.sqlite.tables.Tahsil;
import com.tekzee.racp.sqlite.tables.Vidhanasabha;
import com.tekzee.racp.ui.base.BasePresenter;
import com.tekzee.racp.ui.base.model.CommonResult;
import com.tekzee.racp.ui.login.model.RequestOtpResponse;
import com.tekzee.racp.ui.login.model.VerifyOtpResponse;
import com.tekzee.racp.utils.Log;
import com.tekzee.racp.utils.NetworkUtils;
import com.tekzee.racp.utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter extends BasePresenter <LoginView> {

    private static final  String TAG =  LoginPresenter.class.getSimpleName();


    public LoginPresenter(LoginView view) {
        attachView(view);
    }


    public void requestOtp(JsonObject input) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.requestOtp(input), new ApiCallback <RequestOtpResponse>() {
                @Override
                public void onSuccess(RequestOtpResponse successResult) {
                    if (successResult.isSuccess()) {
                        mvpView.onRequestOtpSuccess(successResult);
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


//    public void verifyOtp(JsonObject jsonObject) {
//        mvpView.hideSoftKeyboard();
//        mvpView.showProgressDialog("Please wait...", false);
//        // mvpView.hideSoftKeyboard();
//        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
//            mvpView.hideProgressDialog();
//            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
//        } else {
//            addSubscription(apiStores.verifyOtp(jsonObject), new ApiCallback <VerifyOtpResponse>() {
//                @Override
//                public void onSuccess(VerifyOtpResponse successResult) {
//                    if (successResult.isSuccess()) {
//                        mvpView.onVerifyOtpSuccess(successResult);
//                    } else {
//                        mvpView.onNoInternetConnectivity(new CommonResult(false, successResult.getMessage()));
//                    }
//                }
//
//                @Override
//                public void onFailure(CommonResult commonResult) {
//                    mvpView.onNoInternetConnectivity(commonResult);
//                }
//
//                @Override
//                public void onFinish() {
//                    mvpView.hideProgressDialog();
//                }
//            });
//        }
//
//    }

    public void verifyOtp(JsonObject jsonObject) {
        mvpView.hideSoftKeyboard();
        mvpView.showProgressDialog("Please wait...", false);
        // mvpView.hideSoftKeyboard();
        if (!NetworkUtils.isNetworkConnected(mvpView.getContext())) {
            mvpView.hideProgressDialog();
            mvpView.onNoInternetConnectivity(new CommonResult(false, mvpView.getContext().getResources().getString(R.string.no_internet)));
        } else {
            addSubscription(apiStores.verifyOtp(jsonObject), new ApiCallback <JsonObject>() {
                @Override
                public void onSuccess(JsonObject successResult) {

                    try {
                        JSONObject object = new JSONObject(successResult.toString());
                        if (object.getBoolean("success")) {
                            JSONObject DataObject = new JSONObject(String.valueOf(object.getJSONObject("data")));


                            JSONObject ProfileObject = new JSONObject(String.valueOf(DataObject.getJSONObject("user_profile")));
                            Utility.setIntegerSharedPreference(mvpView.getContext(), Constant.USER_ID, ProfileObject.getInt("user_id"));
                            Utility.setSharedPreference(mvpView.getContext(), Constant.UserName, ProfileObject.getString("user_name"));
                            Utility.setIntegerSharedPreference(mvpView.getContext(), Constant.RoleId, ProfileObject.getInt("role_id"));
                            Utility.setSharedPreference(mvpView.getContext(), Constant.UserRole, ProfileObject.getString("role_name"));
                            Utility.setSharedPreference(mvpView.getContext(), Constant.UserEmail, ProfileObject.getString("email"));
                            Utility.setSharedPreference(mvpView.getContext(), Constant.Mobile, ProfileObject.getString("mobile"));
                            Utility.setSharedPreference(mvpView.getContext(), Constant.image, ProfileObject.getString("profile_image"));

                            JSONArray jsonArray = new JSONArray(String.valueOf(DataObject.getJSONArray("mtg_group")));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject MtgGroupObject = jsonArray.getJSONObject(i);

                                MtgGroup mtgGroup = new MtgGroup(MtgGroupObject.getInt("mtggroup_id"),
                                        MtgGroupObject.getString("mtggroup_name"));
                                mtgGroup.save();
                            }

                            JSONArray llw_MTGMemberArray = new JSONArray(String.valueOf(DataObject.getJSONArray("llw_MTGMember")));
                            for (int i = 0; i < llw_MTGMemberArray.length(); i++) {
                                JSONObject llw_MTGMemberObject = llw_MTGMemberArray.getJSONObject(i);



                                LlwMTGMember mtgMember = new LlwMTGMember(llw_MTGMemberObject.getInt("pashupalak_id"),
                                        llw_MTGMemberObject.getString("pashupalak_name"),
                                        llw_MTGMemberObject.getString("pashupalak_mobile"),
                                        llw_MTGMemberObject.getString("pashupalak_address"),
                                        llw_MTGMemberObject.getInt("mtggroup_id"));
                                mtgMember.save();
                                Log.view(TAG,""+LlwMTGMember.count(LlwMTGMember.class));
                            }

                            JSONArray llw_grampanchayatArray = new JSONArray(String.valueOf(DataObject.getJSONArray("llw_grampanchayat")));
                            for (int i = 0; i < llw_grampanchayatArray.length(); i++) {
                                JSONObject llw_grampanchayatObject = llw_grampanchayatArray.getJSONObject(i);

                                LlwGrampanchayat llwGrampanchayat = new LlwGrampanchayat(llw_grampanchayatObject.getInt("grampanchayat_id"),
                                        llw_grampanchayatObject.getString("grampanchayat_name"));
                                llwGrampanchayat.save();
                                Log.view(TAG,""+LlwGrampanchayat.count(LlwGrampanchayat.class));
                            }

                            JSONArray llw_gramArray = new JSONArray(String.valueOf(DataObject.getJSONArray("llw_gram")));
                            for (int i = 0; i < llw_gramArray.length(); i++) {
                                JSONObject llw_gramObject = llw_gramArray.getJSONObject(i);

                                LlwGram llwGram = new LlwGram(llw_gramObject.getInt("gram_id"),
                                        llw_gramObject.getString("gram_name"),
                                        llw_gramObject.getInt("grampanchayat_id"));
                                llwGram.save();
                                Log.view(TAG,""+LlwGram.count(LlwGram.class));

                            }


                            JSONArray formtypeArray = new JSONArray(String.valueOf(DataObject.getJSONArray("formtype")));
                            for (int i = 0; i < formtypeArray.length(); i++) {
                                JSONObject formObject = formtypeArray.getJSONObject(i);

                                Formtype formtype = new Formtype(formObject.getInt("id"),
                                        formObject.getString("form_name"),
                                        formObject.getString("form_image"));
                                formtype.save();
                                Log.view(TAG,""+Formtype.count(Formtype.class));

                            }


                            JSONArray homemenuArray = new JSONArray(String.valueOf(DataObject.getJSONArray("homemenu")));
                            for (int i = 0; i < homemenuArray.length(); i++) {
                                JSONObject homemenuObject = homemenuArray.getJSONObject(i);

                                Homemenu homemenu = new Homemenu(homemenuObject.getInt("menu_id"),
                                        homemenuObject.getString("menu_name"),
                                        homemenuObject.getString("menu_image"));
                                homemenu.save();
                                Log.view(TAG,""+Homemenu.count(Homemenu.class));

                            }


                            JSONArray sidemenu = new JSONArray(String.valueOf(DataObject.getJSONArray("sidemenu")));
                            for (int i = 0; i < sidemenu.length(); i++) {
                                JSONObject sidemenuObject = sidemenu.getJSONObject(i);

                                Sidemenu sidemenu1= new Sidemenu(
                                        sidemenuObject.getString("menu_name"),
                                        sidemenuObject.getString("menu_image"));
                                sidemenu1.save();
                                Log.view(TAG,""+Sidemenu.count(Sidemenu.class));

                            }

                            JSONArray animal_category = new JSONArray(String.valueOf(DataObject.getJSONArray("animal_category")));
                            for (int i = 0; i < animal_category.length(); i++) {
                                JSONObject animal_categoryObject = animal_category.getJSONObject(i);

                                AnimalCategory animalCategory = new AnimalCategory(
                                        animal_categoryObject.getInt("animalcategory_id"),
                                        animal_categoryObject.getString("animalcategory_Name"));
                                animalCategory.save();
                                Log.view(TAG,""+AnimalCategory.count(AnimalCategory.class));

                            }

                            JSONArray animal_type = new JSONArray(String.valueOf(DataObject.getJSONArray("animal_type")));
                            for (int i = 0; i < animal_type.length(); i++) {
                                JSONObject animal_typeObject = animal_type.getJSONObject(i);

                                AnimalType animalType = new AnimalType(animal_typeObject.getInt("animaltype_id"),
                                        animal_typeObject.getString("animaltype_Name"),
                                        animal_typeObject.getString("animal_category_name"));
                                animalType.save();
                                Log.view(TAG,""+AnimalType.count(AnimalType.class));

                            }


                            JSONArray pashuPalakCategories = new JSONArray(String.valueOf(DataObject.getJSONArray("PashuPalakCategories")));
                            for (int i = 0; i < pashuPalakCategories.length(); i++) {
                                JSONObject categoryObject = pashuPalakCategories.getJSONObject(i);

                                PashuPalakCategory pashuPalakCategory = new PashuPalakCategory(categoryObject.getInt("pashuPalakCategoryID"),
                                        categoryObject.getString("pashuPalakCategoryName"));
                                pashuPalakCategory.save();
                                Log.view(TAG,""+PashuPalakCategory.count(PashuPalakCategory.class));

                            }



                            JSONArray farmertype = new JSONArray(String.valueOf(DataObject.getJSONArray("farmertype")));
                            for (int i = 0; i < farmertype.length(); i++) {
                                JSONObject farmertypeObject = farmertype.getJSONObject(i);

                                Farmertype farmertype1 = new Farmertype(farmertypeObject.getInt("farmertype_id"),
                                        farmertypeObject.getString("farmertype_Name"));
                                farmertype1.save();
                                Log.view(TAG,""+Farmertype.count(Farmertype.class));

                            }




                            JSONArray identificationtype = new JSONArray(String.valueOf(DataObject.getJSONArray("identificationtype")));
                            for (int i = 0; i < identificationtype.length(); i++) {
                                JSONObject identificationObject = identificationtype.getJSONObject(i);

                                Identificationtype identificationtype1 = new Identificationtype(identificationObject.getInt("identificationtype_id"),
                                        identificationObject.getString("identificationtype_Name"));
                                identificationtype1.save();
                                Log.view(TAG,""+Identificationtype.count(Identificationtype.class));

                            }


                            JSONArray castcategories = new JSONArray(String.valueOf(DataObject.getJSONArray("castcategories")));
                            for (int i = 0; i < castcategories.length(); i++) {
                                JSONObject castcategoriesObject = castcategories.getJSONObject(i);

                                Castcategory castcategories1 = new Castcategory(castcategoriesObject.getInt("castCategory_id"),
                                        castcategoriesObject.getString("castCategory_Name"));
                                castcategories1.save();
                                Log.view(TAG,""+Castcategory.count(Castcategory.class));

                            }


                            JSONArray nasla = new JSONArray(String.valueOf(DataObject.getJSONArray("nasla")));
                            for (int i = 0; i < nasla.length(); i++) {
                                JSONObject naslaObject = nasla.getJSONObject(i);

                                Nasla nasla1 = new Nasla(naslaObject.getInt("nasla_id"),
                                        naslaObject.getString("nasla_Name"));
                                nasla1.save();
                                Log.view(TAG,""+Nasla.count(Nasla.class));

                            }


                            JSONArray district = new JSONArray(String.valueOf(DataObject.getJSONArray("district")));
                            for (int i = 0; i < district.length(); i++) {
                                JSONObject districtObject = district.getJSONObject(i);

                                District district1 = new District(districtObject.getInt("district_id"),
                                        districtObject.getString("district_Name"));
                                district1.save();
                                Log.view(TAG,""+District.count(District.class));

                            }

                            JSONArray vidhanasabha = new JSONArray(String.valueOf(DataObject.getJSONArray("vidhanasabha")));
                            for (int i = 0; i < vidhanasabha.length(); i++) {
                                JSONObject vidhanasabhaObject = vidhanasabha.getJSONObject(i);

                                Vidhanasabha vidhanasabha1 = new Vidhanasabha(vidhanasabhaObject.getInt("vidhanasabha_id"),
                                        vidhanasabhaObject.getString("vidhanasabha_Name"),
                                        vidhanasabhaObject.getInt("district_id"));
                                vidhanasabha1.save();
                                Log.view(TAG,""+Vidhanasabha.count(Vidhanasabha.class));

                            }



                            JSONArray tahsil = new JSONArray(String.valueOf(DataObject.getJSONArray("tahsil")));
                            for (int i = 0; i < tahsil.length(); i++) {
                                JSONObject tahsilObject = tahsil.getJSONObject(i);

                                Tahsil tahsil1 = new Tahsil(tahsilObject.getInt("tahsil_id"),
                                        tahsilObject.getString("tahsil_Name"),
                                        tahsilObject.getInt("vidhanasabha_id"));
                                tahsil1.save();
                                Log.view(TAG,""+Tahsil.count(Tahsil.class));

                            }


                            JSONArray grampanchayat = new JSONArray(String.valueOf(DataObject.getJSONArray("grampanchayat")));
                            for (int i = 0; i < grampanchayat.length(); i++) {
                                JSONObject grampanchayatObject = grampanchayat.getJSONObject(i);

                                Grampanchayat grampanchayat1 = new Grampanchayat(grampanchayatObject.getInt("grampanchayat_id"),
                                        grampanchayatObject.getString("grampanchayat_name"),
                                        grampanchayatObject.getInt("tahsil_id"));
                                grampanchayat1.save();
                                Log.view(TAG,""+Grampanchayat.count(Grampanchayat.class));

                            }



                            JSONArray gram = new JSONArray(String.valueOf(DataObject.getJSONArray("gram")));
                            Log.view(TAG,""+gram.length());
                            for (int i = 0; i < gram.length(); i++) {
                                Log.view(TAG,""+i);

                                JSONObject gramObject = gram.getJSONObject(i);

                                Gram gram1 = new Gram(gramObject.getInt("gram_id"),
                                        gramObject.getString("gram_name"),
                                        gramObject.getInt("grampanchayat_id"));
                                gram1.save();
                                Log.view(TAG,""+Gram.count(Gram.class));

                            }


                            mvpView.onVerifyOtpSuccess(new VerifyOtpResponse(true,object.getString("message")));


                        }
                        else {
                            mvpView.onNoInternetConnectivity(new CommonResult(false, object.getString("message")));
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                   /* if (successResult.isSuccess()) {
                        mvpView.onVerifyOtpSuccess(successResult);

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


}
