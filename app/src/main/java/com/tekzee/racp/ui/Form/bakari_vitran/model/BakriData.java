package com.tekzee.racp.ui.Form.bakari_vitran.model;

import com.orm.SugarRecord;

public class BakriData extends SugarRecord {

//    String dd;
//    String mm;
//    String yy;
//    String tag_no;
//    String beema_detail;
//    String policy_no;
//    String average;
//    String phucal_proof_date;
//    String note;
//
//    public BakriData() {
//    }
//
//    public BakriData(String dd, String mm, String yy, String tag_no, String beema_detail, String policy_no, String average, String phucal_proof_date,  String note) {
//        this.dd = dd;
//        this.mm = mm;
//        this.yy = yy;
//        this.tag_no = tag_no;
//        this.beema_detail = beema_detail;
//        this.policy_no = policy_no;
//        this.average = average;
//        this.phucal_proof_date = phucal_proof_date;
//
//        this.note = note;
//    }
//
//    public String getDd() {
//        return dd;
//    }
//
//    public void setDd(String dd) {
//        this.dd = dd;
//    }
//
//    public String getMm() {
//        return mm;
//    }
//
//    public void setMm(String mm) {
//        this.mm = mm;
//    }
//
//    public String getYy() {
//        return yy;
//    }
//
//    public void setYy(String yy) {
//        this.yy = yy;
//    }
//
//    public String getTag_no() {
//        return tag_no;
//    }
//
//    public void setTag_no(String tag_no) {
//        this.tag_no = tag_no;
//    }
//
//    public String getBeema_detail() {
//        return beema_detail;
//    }
//
//    public void setBeema_detail(String beema_detail) {
//        this.beema_detail = beema_detail;
//    }
//
//    public String getPolicy_no() {
//        return policy_no;
//    }
//
//    public void setPolicy_no(String policy_no) {
//        this.policy_no = policy_no;
//    }
//
//    public String getAverage() {
//        return average;
//    }
//
//    public void setAverage(String average) {
//        this.average = average;
//    }
//
//    public String getPhucal_proof_date() {
//        return phucal_proof_date;
//    }
//
//    public void setPhucal_proof_date(String phucal_proof_date) {
//        this.phucal_proof_date = phucal_proof_date;
//    }
//
//
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }


    String dd;
    String mm;
    String yy;
    int mm_id;
    int yy_id;
    String category;
    int category_id;
    String tag_no;
    String age;
    String weight;
    String nasl;
    int nasl_id;

    String average;


    boolean criminashak;
    int nashak;
    String criminashak_date;
    String phucal_proof_date;
    String note;

    public BakriData() {
    }

    public BakriData(String dd, String mm, String yy, int mm_id, int yy_id, String category, int category_id, String tag_no, String age, String weight, String nasl, int nasl_id, String average, boolean criminashak, int nashak, String criminashak_date, String phucal_proof_date, String note) {
        this.dd = dd;
        this.mm = mm;
        this.yy = yy;
        this.mm_id = mm_id;
        this.yy_id = yy_id;
        this.category = category;
        this.category_id = category_id;
        this.tag_no = tag_no;
        this.age = age;
        this.weight = weight;
        this.nasl = nasl;
        this.nasl_id = nasl_id;
        this.average = average;
        this.criminashak = criminashak;
        this.nashak = nashak;
        this.criminashak_date = criminashak_date;
        this.phucal_proof_date = phucal_proof_date;
        this.note = note;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public int getMm_id() {
        return mm_id;
    }

    public void setMm_id(int mm_id) {
        this.mm_id = mm_id;
    }

    public int getYy_id() {
        return yy_id;
    }

    public void setYy_id(int yy_id) {
        this.yy_id = yy_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTag_no() {
        return tag_no;
    }

    public void setTag_no(String tag_no) {
        this.tag_no = tag_no;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNasl() {
        return nasl;
    }

    public void setNasl(String nasl) {
        this.nasl = nasl;
    }

    public int getNasl_id() {
        return nasl_id;
    }

    public void setNasl_id(int nasl_id) {
        this.nasl_id = nasl_id;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public boolean isCriminashak() {
        return criminashak;
    }

    public void setCriminashak(boolean criminashak) {
        this.criminashak = criminashak;
    }

    public int getNashak() {
        return nashak;
    }

    public void setNashak(int nashak) {
        this.nashak = nashak;
    }

    public String getCriminashak_date() {
        return criminashak_date;
    }

    public void setCriminashak_date(String criminashak_date) {
        this.criminashak_date = criminashak_date;
    }

    public String getPhucal_proof_date() {
        return phucal_proof_date;
    }

    public void setPhucal_proof_date(String phucal_proof_date) {
        this.phucal_proof_date = phucal_proof_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
