package com.tekzee.racp.sqlite.tables.formtable;

import com.orm.SugarRecord;

public class BakariVitran extends SugarRecord {

    String user_id;
    String mtg_group_id;
    String mtg_member_id;
    String tag_no;
    String category_id;
    String milk_production;
    String physical_proof_date;
    String crimi_nashak;
    String crimi_nashak_date;
    String note;
    String image_path;
    String teekakarn;
    String timeStamp;
    String dd;
    String mm;
    String yy;

    public BakariVitran() {
    }

    public BakariVitran(String user_id, String mtg_group_id, String mtg_member_id, String tag_no, String category_id, String milk_production, String physical_proof_date, String crimi_nashak, String crimi_nashak_date, String note, String image_path, String teekakarn, String timeStamp) {
        this.user_id = user_id;
        this.mtg_group_id = mtg_group_id;
        this.mtg_member_id = mtg_member_id;
        this.tag_no = tag_no;
        this.category_id = category_id;
        this.milk_production = milk_production;
        this.physical_proof_date = physical_proof_date;
        this.crimi_nashak = crimi_nashak;
        this.crimi_nashak_date = crimi_nashak_date;
        this.note = note;
        this.image_path = image_path;
        this.teekakarn = teekakarn;
        this.timeStamp = timeStamp;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMtg_group_id() {
        return mtg_group_id;
    }

    public void setMtg_group_id(String mtg_group_id) {
        this.mtg_group_id = mtg_group_id;
    }

    public String getMtg_member_id() {
        return mtg_member_id;
    }

    public void setMtg_member_id(String mtg_member_id) {
        this.mtg_member_id = mtg_member_id;
    }

    public String getTag_no() {
        return tag_no;
    }

    public void setTag_no(String tag_no) {
        this.tag_no = tag_no;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getMilk_production() {
        return milk_production;
    }

    public void setMilk_production(String milk_production) {
        this.milk_production = milk_production;
    }

    public String getPhysical_proof_date() {
        return physical_proof_date;
    }

    public void setPhysical_proof_date(String physical_proof_date) {
        this.physical_proof_date = physical_proof_date;
    }

    public String getCrimi_nashak() {
        return crimi_nashak;
    }

    public void setCrimi_nashak(String crimi_nashak) {
        this.crimi_nashak = crimi_nashak;
    }

    public String getCrimi_nashak_date() {
        return crimi_nashak_date;
    }

    public void setCrimi_nashak_date(String crimi_nashak_date) {
        this.crimi_nashak_date = crimi_nashak_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getTeekakarn() {
        return teekakarn;
    }

    public void setTeekakarn(String teekakarn) {
        this.teekakarn = teekakarn;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
