package com.tekzee.racp.sqlite.tables.formtable;

import com.orm.SugarRecord;

public class Pashupalak extends SugarRecord {

    String Name;
    String FatherName;
    String addresss;
    String Mobile;
    String IdentificationType;
    int IdentificationTypeId;
    String IdentificationNumber;
    String PashupalakType;
    int PashupalakTypeId;
    String Category;
    int CategoryId;
    String CastCategory;
    int CastCategoryId;
    String MtgGroup;
    int MtgGroupId;

    public Pashupalak() {
    }

    public Pashupalak(String name, String fatherName, String addresss, String mobile, String identificationType, int identificationTypeId, String identificationNumber, String pashupalakType, int pashupalakTypeId, String category, int categoryId, String castCategory, int castCategoryId, String mtgGroup, int mtgGroupId) {
        Name = name;
        FatherName = fatherName;
        this.addresss = addresss;
        Mobile = mobile;
        IdentificationType = identificationType;
        IdentificationTypeId = identificationTypeId;
        IdentificationNumber = identificationNumber;
        PashupalakType = pashupalakType;
        PashupalakTypeId = pashupalakTypeId;
        Category = category;
        CategoryId = categoryId;
        CastCategory = castCategory;
        CastCategoryId = castCategoryId;
        MtgGroup = mtgGroup;
        MtgGroupId = mtgGroupId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getIdentificationType() {
        return IdentificationType;
    }

    public void setIdentificationType(String identificationType) {
        IdentificationType = identificationType;
    }

    public int getIdentificationTypeId() {
        return IdentificationTypeId;
    }

    public void setIdentificationTypeId(int identificationTypeId) {
        IdentificationTypeId = identificationTypeId;
    }

    public String getIdentificationNumber() {
        return IdentificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        IdentificationNumber = identificationNumber;
    }

    public String getPashupalakType() {
        return PashupalakType;
    }

    public void setPashupalakType(String pashupalakType) {
        PashupalakType = pashupalakType;
    }

    public int getPashupalakTypeId() {
        return PashupalakTypeId;
    }

    public void setPashupalakTypeId(int pashupalakTypeId) {
        PashupalakTypeId = pashupalakTypeId;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCastCategory() {
        return CastCategory;
    }

    public void setCastCategory(String castCategory) {
        CastCategory = castCategory;
    }

    public int getCastCategoryId() {
        return CastCategoryId;
    }

    public void setCastCategoryId(int castCategoryId) {
        CastCategoryId = castCategoryId;
    }

    public String getMtgGroup() {
        return MtgGroup;
    }

    public void setMtgGroup(String mtgGroup) {
        MtgGroup = mtgGroup;
    }

    public int getMtgGroupId() {
        return MtgGroupId;
    }

    public void setMtgGroupId(int mtgGroupId) {
        MtgGroupId = mtgGroupId;
    }
}
