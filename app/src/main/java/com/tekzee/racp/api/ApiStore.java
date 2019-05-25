package com.tekzee.racp.api;

import com.google.gson.JsonObject;
import com.tekzee.racp.ui.Form.adoption.model.RetrivedAdoptionResponse;
import com.tekzee.racp.ui.Form.ajola.model.GetAnimalTypeResponse;
import com.tekzee.racp.ui.Form.ajola.model.RetrivedAjolaResponse;
import com.tekzee.racp.ui.Form.bakara_rotation.model.RetrivedBakraRotationResponse;
import com.tekzee.racp.ui.Form.bakari_awas.model.RetrivedBakariAwasResponse;
import com.tekzee.racp.ui.Form.beema_detail.model.RetrivedBeemaDataResponse;
import com.tekzee.racp.ui.Form.clean_milkkit.model.RetrivedMilkKitResponse;
import com.tekzee.racp.ui.Form.dana_pani_bartan.model.RetrivedDanaPaniResponse;
import com.tekzee.racp.ui.Form.feed_suppliment.model.RetrivedFeedSupplimentResponse;
import com.tekzee.racp.ui.Form.form_2.Model.RetrivedDataResponse;
import com.tekzee.racp.ui.Form.kutti_machine.mode.RetrivedKuttiMachineResponse;
import com.tekzee.racp.ui.Form.milk_info_bakari.model.RetrivedMilkInfoResponse;
import com.tekzee.racp.ui.Form.mtg_meeting.model.MtgMemberResponse;
import com.tekzee.racp.ui.Form.mtg_meeting.model.RetrivedMtgMeetingResponse;
import com.tekzee.racp.ui.Form.mtg_prasikshan.model.RetrivedMtgTrainingResponse;
import com.tekzee.racp.ui.Form.pashu_chiktsak_shivir.model.RetrivedPashuChikithsResponse;
import com.tekzee.racp.ui.Form.vipran_talika.model.RetrivedVipranTalikaResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormRecordDataResponse;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.FormSubmitResponse;
import com.tekzee.racp.ui.Form.weighting_machine.model.RetrivedWeightingMachineResponse;
import com.tekzee.racp.ui.addMGTgroup.model.AddMtgResponse;
import com.tekzee.racp.ui.add_animal_owner.AddPashuPalakResponse;
import com.tekzee.racp.ui.dashboard.model.DashboardDataResponse;
import com.tekzee.racp.ui.formdata.model.FormDataResponse;
import com.tekzee.racp.ui.formselection.model.GetAllFormResponse;
import com.tekzee.racp.ui.home.model.SideMenuResponse;
import com.tekzee.racp.ui.login.model.RequestOtpResponse;
import com.tekzee.racp.ui.login.model.VerifyOtpResponse;
import com.tekzee.racp.ui.selectMtgGroup.model.GetMtgResponse;
import com.tekzee.racp.ui.selectMtgPreson.model.GetMtgMemberResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiStore {

    @POST("validateAppVersion")
    Observable <JsonObject> validateAppVersion(@Body JsonObject input);

    @POST("login")
    Observable <RequestOtpResponse> requestOtp(@Body JsonObject input);


    @POST("verifyOTP")
    Observable <VerifyOtpResponse> verifyOtp(@Body JsonObject jsonObject);

    @POST("getSideMenuData")
    Observable <SideMenuResponse> getSideMenuData(@Body JsonObject jsonObject);

    @POST("getDashboardData")
    Observable <DashboardDataResponse> getDashboardData(@Body JsonObject jsonObject);

    @GET("getGramPanchayatByUserId/{userId}")
    Observable <JsonObject> getGramPanchayat(@Path("userId") Integer userId);

    @GET("getGramByGrampanchayatId/{userId}")
    Observable <JsonObject> getGram(@Path("userId") Integer id);

    @GET("getPashuPalakCategory")
    Observable <JsonObject> getPashuPalakCategory();

    @POST("addMTG")
    Observable <AddMtgResponse> addMtg(@Body JsonObject input);

    @POST("getMTGGroupByUserGramId")
    Observable <JsonObject> getMtgGroup(@Body JsonObject jsonObject);

    @GET("getMTGGroupByGramId/{userId}")
    Observable <JsonObject> getMtgGroup(@Path("userId") Integer id);

    @POST("addPashuPalak")
    Observable <AddPashuPalakResponse> addPashuPalak(@Body JsonObject input);


    @GET("getMTGGroupByUserId/{userId}")
    Observable <GetMtgResponse> getMTGGroupByUserId(@Path("userId") Integer id);

    @GET("getMTGGroupByUserId/{userId}")
    Observable <JsonObject> getMTGGroupByuserId(@Path("userId") Integer id);

    @GET("getMTGMemberByMtgGroupId/{userId}")
    Observable <GetMtgMemberResponse> getMTGMemberByMtgGroupId(@Path("userId") Integer id);

    @GET("getMTGMemberByMtgGroupId/{userId}")
    Observable <JsonObject> getMtgMember(@Path("userId") Integer id);

    @GET("getIdentificationType")
    Observable <JsonObject> getIdentificationType();

    @GET("getCastCategory")
    Observable <JsonObject> getCastCategory();

    @GET("getFarmerType")
    Observable <JsonObject> getFarmerType();

    @GET("getVidhaSabhaByUserId/{userId}")
    Observable <JsonObject> getVidhanSabhaKshetra(@Path("userId") Integer id);

    @GET("getTahsilByUserId/{userId}")
    Observable <JsonObject> getTehsil(@Path("userId") Integer id);

    @POST("addDetailsGoatsDistributeds")
    Observable <FormSubmitResponse> addDetailsGoatsDistributeds(@Body JsonObject jsonObject);


    @POST("getAllFormRecordList")
    Observable <FormDataResponse> getAllFormRecordList(@Body JsonObject input);


    @POST("getFormRecordData")
    Observable <FormRecordDataResponse> getFormRecordData(@Body JsonObject jsonObject);

    @POST("addDetailsGoatsDistributedWidowDisabledWomen")
    Observable <FormSubmitResponse> addDetailsGoatsDistributedWidowDisabledWomen(@Body JsonObject jsonObject);

    @GET("getNasla")
    Observable <JsonObject> getNasla();

    @POST("addGoatsAvailableBeneficiaryMTGMember")
    Observable <FormSubmitResponse> addGoatsAvailableBeneficiaryMTGMember(@Body JsonObject jsonObject);

    @POST("addDetailsGoatsDistributedInsurance")
    Observable <FormSubmitResponse> addDetailsGoatsDistributedInsurance(@Body JsonObject jsonObject);


    @POST("addkuttimachine")
    Observable <FormSubmitResponse> addkuttimachine(@Body JsonObject jsonObject);


    @POST("addDanaPaniBartan")
    Observable <FormSubmitResponse> addDanaPaniBartan(@Body JsonObject jsonObject);

    @POST("addCleanMilkKeet")
    Observable <FormSubmitResponse> addCleanMilkKeet(@Body JsonObject jsonObject);


    @POST("addAjola")
    Observable <FormSubmitResponse> addAjola(@Body JsonObject jsonObject);

    @GET("getAnimalTypeForAjola")
    Observable <GetAnimalTypeResponse> getAnimalTypeForAjola();


    @GET("getAnimalCategory")
    Observable <JsonObject> getAnimalCategory();


    @GET("getAnimalTypeByCategory/{userId}")
    Observable <JsonObject> getAnimalTypeByCategory(@Path("userId") Integer id);


    @GET("getAllFormtype")
    Observable <GetAllFormResponse> getAllFormtype();


    @POST("bharTolneKiMachine")
    Observable <FormSubmitResponse> addbharTolneKiMachine(@Body JsonObject jsonObject);

    @POST("addFeedSuppliment")
    Observable <FormSubmitResponse> addFeedSuppliment(@Body JsonObject jsonObject);


    @POST("veterinary_camp")
    Observable <FormSubmitResponse> addVitenary(@Body JsonObject jsonObject);

    @GET("getMTGGroupByUserId/{userId}")
    Observable <JsonObject> getMtgName(@Path("userId") Integer id);


    @POST("veterinary_camp")
    Observable <FormSubmitResponse> veterinary_camp(@Body JsonObject jsonObject);


    @POST("mtg_training")
    Observable <FormSubmitResponse> mtg_training(@Body JsonObject jsonObject);


    @GET("getDistrictList")
    Observable <JsonObject> getDistrictList();

    @GET("getVidhanasabhaByDistrict/{userId}")
    Observable <JsonObject> getVidhanasabhaByDistrict(@Path("userId") Integer id);


    @GET("getTahsilByVidhanasabhaId/{userId}")
    Observable <JsonObject> getTahsilByVidhanasabhaId(@Path("userId") Integer id);


    @GET("getGrampanchayatByTahsilId/{userId}")
    Observable <JsonObject> getGrampanchayatByTahsilId(@Path("userId") Integer id);

    @POST("addAdoption")
    Observable <FormSubmitResponse> addAdoption(@Body JsonObject jsonObject);

    @POST("bakra_rotation")
    Observable <FormSubmitResponse> addBakraRotation(@Body JsonObject jsonObject);

    @GET("getMTGGroupByUserId/{userId}")
    Observable <JsonObject> getMTGGroup1(@Path("userId") Integer id);


    @GET("getMTGMemberByMtgGroupId/{userId}")
    Observable <MtgMemberResponse> getMTGMemberDetailByMtgMemberId(@Path("userId") Integer id);


    @GET("getMTGMemberByMtgGroupId/{userId}")
    Observable <JsonObject> getMTGMemberDetail(@Path("userId") Integer id);


    @POST("mtg_meetings")
    Observable <FormSubmitResponse> mtg_meetings(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedDataResponse> getForm2Data(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <com.tekzee.racp.ui.Form.bakari_vitran.model.RetrivedDataResponse> getBakriVitranData(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedBeemaDataResponse> getBeemaDetailData(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedWeightingMachineResponse> getWeightingMachineDetailData(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedMilkKitResponse> getCleanMilkKitData(@Body JsonObject jsonObject);


    @POST("getFormRecordData")
    Observable <RetrivedKuttiMachineResponse> getKuttiMachineData(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedDanaPaniResponse> getDanaPaniBartanData(@Body JsonObject jsonObject);


    @POST("getFormRecordData")
    Observable <RetrivedAjolaResponse> getAjolaData(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedFeedSupplimentResponse> getFeedSupplimentData1(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedPashuChikithsResponse> getPashuChikitshaData(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedMtgTrainingResponse> getMtgTrainingData(@Body JsonObject jsonObject);


    @POST("getFormRecordData")
    Observable <RetrivedMtgMeetingResponse> getMtgMeetingData(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedAdoptionResponse> getAdopitonData(@Body JsonObject jsonObject);


     @POST("getFormRecordData")
    Observable <RetrivedBakraRotationResponse> getBakraRotationData(@Body JsonObject jsonObject);


    @GET("getMTGMemberDetailByMtgMemberId/{userId}")
    Observable <com.tekzee.racp.ui.Form.vipran_talika.model.MtgMemberResponse> getMTGMemberdetail(@Path("userId") Integer id);


    @POST("addVipananTalika")
    Observable <FormSubmitResponse> addVipananTalika(@Body JsonObject jsonObject);


    @POST("getFormRecordData")
    Observable <RetrivedVipranTalikaResponse> getVipranTalikaData(@Body JsonObject jsonObject);


    @POST("getFormRecordData")
    Observable <RetrivedBakariAwasResponse> getBakariAwasData(@Body JsonObject jsonObject);


    @Multipart
    @POST("addGoatHouse")
    Observable<FormSubmitResponse> uploadImage(@Part MultipartBody.Part formData,
                                       @Part("user_id") RequestBody user_id,
                                       @Part("form_id") RequestBody form_id,
                                       @Part("mtg_member_id") RequestBody mtg_member_id,
                                       @Part("mtg_group_id") RequestBody mtg_group_id,
                                       @Part("status_receipt") RequestBody status_receipt,
                                       @Part("physical_proof") RequestBody physical_proof,
                                       @Part("usability") RequestBody usability,
                                       @Part("note") RequestBody note,
                                       @Part("date_creation_goat_house") RequestBody date_creation_goat_house,
                                       @Part("dd") RequestBody dd,
                                       @Part("mm") RequestBody mm,
                                       @Part("yy") RequestBody yy,
                                       @Part("img_lat") RequestBody img_lat,
                                       @Part("img_long") RequestBody img_long);




    @POST("addGoatMilkInformation")
    Observable <FormSubmitResponse> addMilkInfo(@Body JsonObject jsonObject);

    @POST("getFormRecordData")
    Observable <RetrivedMilkInfoResponse> getMilkInfoData(@Body JsonObject jsonObject);


    @POST("getAllFiledForm")
    Observable <GetAllFormResponse> getAllFormtype1(@Body JsonObject jsonObject);

}
