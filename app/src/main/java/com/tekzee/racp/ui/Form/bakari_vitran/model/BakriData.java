package com.tekzee.racp.ui.Form.bakari_vitran.model;

import android.provider.ContactsContract;

import com.orm.SugarRecord;
import com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model.Immunization;

import java.util.List;

public class BakriData extends SugarRecord {

    String dd;
    String mm;
    String yy;
    String tag_no;
    String beema_detail;
    String policy_no;
    String average;
    String phucal_proof_date;
    String note;

    public BakriData() {
    }

    public BakriData(String dd, String mm, String yy, String tag_no, String beema_detail, String policy_no, String average, String phucal_proof_date,  String note) {
        this.dd = dd;
        this.mm = mm;
        this.yy = yy;
        this.tag_no = tag_no;
        this.beema_detail = beema_detail;
        this.policy_no = policy_no;
        this.average = average;
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

    public String getTag_no() {
        return tag_no;
    }

    public void setTag_no(String tag_no) {
        this.tag_no = tag_no;
    }

    public String getBeema_detail() {
        return beema_detail;
    }

    public void setBeema_detail(String beema_detail) {
        this.beema_detail = beema_detail;
    }

    public String getPolicy_no() {
        return policy_no;
    }

    public void setPolicy_no(String policy_no) {
        this.policy_no = policy_no;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
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
