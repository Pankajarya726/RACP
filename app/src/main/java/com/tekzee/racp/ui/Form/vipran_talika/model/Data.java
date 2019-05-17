package com.tekzee.racp.ui.Form.vipran_talika.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("table_id")
    @Expose
    private Integer tableId;
    @SerializedName("is_mtg_member")
    @Expose
    private Integer isMtgMember;
    @SerializedName("bechan_yogya")
    @Expose
    private Integer bechanYogya;
    @SerializedName("animaltype_id")
    @Expose
    private Integer animaltypeId;
    @SerializedName("pashupalak_name")
    @Expose
    private String pashupalakName;
    @SerializedName("pashupalak_mobile")
    @Expose
    private String pashupalakMobile;
    @SerializedName("pashupalak_address")
    @Expose
    private String pashupalakAddress;
    @SerializedName("tag_no")
    @Expose
    private Integer tagNo;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("form_id")
    @Expose
    private Integer formId;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("date_ppr")
    @Expose
    private String datePpr;
    @SerializedName("date_et")
    @Expose
    private String dateEt;
    @SerializedName("date_fmd")
    @Expose
    private String dateFmd;
    @SerializedName("date_hs")
    @Expose
    private String dateHs;
    @SerializedName("mtgmember_name")
    @Expose
    private String mtgmemberName;
    @SerializedName("mtggroup_name")
    @Expose
    private String mtggroupName;
    @SerializedName("grampanchayat_name")
    @Expose
    private String grampanchayatName;
    @SerializedName("gram_name")
    @Expose
    private String gramName;
    @SerializedName("animal_type")
    @Expose
    private String animalType;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }
    /**
     *
     * @param mtggroupName
     * @param pashupalakAddress
     * @param isMtgMember
     * @param weight
     * @param dateHs
     * @param tagNo
     * @param grampanchayatName
     * @param animalType
     * @param bechanYogya
     * @param tableId
     * @param dateEt
     * @param formId
     * @param datePpr
     * @param age
     * @param animaltypeId
     * @param pashupalakName
     * @param gramName
     * @param pashupalakMobile
     * @param dateFmd
     * @param mtgmemberName
     */

    public Data(Integer tableId, Integer isMtgMember, Integer bechanYogya, Integer animaltypeId, String pashupalakName, String pashupalakMobile, String pashupalakAddress, Integer tagNo, Integer age, Integer formId, String weight, String datePpr, String dateEt, String dateFmd, String dateHs, String mtgmemberName, String mtggroupName, String grampanchayatName, String gramName, String animalType) {
        this.tableId = tableId;
        this.isMtgMember = isMtgMember;
        this.bechanYogya = bechanYogya;
        this.animaltypeId = animaltypeId;
        this.pashupalakName = pashupalakName;
        this.pashupalakMobile = pashupalakMobile;
        this.pashupalakAddress = pashupalakAddress;
        this.tagNo = tagNo;
        this.age = age;
        this.formId = formId;
        this.weight = weight;
        this.datePpr = datePpr;
        this.dateEt = dateEt;
        this.dateFmd = dateFmd;
        this.dateHs = dateHs;
        this.mtgmemberName = mtgmemberName;
        this.mtggroupName = mtggroupName;
        this.grampanchayatName = grampanchayatName;
        this.gramName = gramName;
        this.animalType = animalType;
    }




    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getIsMtgMember() {
        return isMtgMember;
    }

    public void setIsMtgMember(Integer isMtgMember) {
        this.isMtgMember = isMtgMember;
    }

    public Integer getBechanYogya() {
        return bechanYogya;
    }

    public void setBechanYogya(Integer bechanYogya) {
        this.bechanYogya = bechanYogya;
    }

    public Integer getAnimaltypeId() {
        return animaltypeId;
    }

    public void setAnimaltypeId(Integer animaltypeId) {
        this.animaltypeId = animaltypeId;
    }

    public String getPashupalakName() {
        return pashupalakName;
    }

    public void setPashupalakName(String pashupalakName) {
        this.pashupalakName = pashupalakName;
    }

    public String getPashupalakMobile() {
        return pashupalakMobile;
    }

    public void setPashupalakMobile(String pashupalakMobile) {
        this.pashupalakMobile = pashupalakMobile;
    }

    public String getPashupalakAddress() {
        return pashupalakAddress;
    }

    public void setPashupalakAddress(String pashupalakAddress) {
        this.pashupalakAddress = pashupalakAddress;
    }

    public Object getTagNo() {
        return tagNo;
    }

    public void setTagNo(Integer tagNo) {
        this.tagNo = tagNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String  getWeight() {
        return weight;
    }

    public void setWeight(String  weight) {
        this.weight = weight;
    }

    public String getDatePpr() {
        return datePpr;
    }

    public void setDatePpr(String datePpr) {
        this.datePpr = datePpr;
    }

    public String getDateEt() {
        return dateEt;
    }

    public void setDateEt(String dateEt) {
        this.dateEt = dateEt;
    }

    public String getDateFmd() {
        return dateFmd;
    }

    public void setDateFmd(String dateFmd) {
        this.dateFmd = dateFmd;
    }

    public String getDateHs() {
        return dateHs;
    }

    public void setDateHs(String dateHs) {
        this.dateHs = dateHs;
    }

    public Object getMtgmemberName() {
        return mtgmemberName;
    }

    public String getMtggroupName() {
        return mtggroupName;
    }

    public String getGrampanchayatName() {
        return grampanchayatName;
    }

    public void setGrampanchayatName(String grampanchayatName) {
        this.grampanchayatName = grampanchayatName;
    }

    public String getGramName() {
        return gramName;
    }

    public void setGramName(String gramName) {
        this.gramName = gramName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

}