package com.tekzee.racp.ui.Form.beema_detail.model;

import com.orm.SugarRecord;

public class BeemaDetail extends SugarRecord {

    String dd;
    String mm;
    String yy;
    String animalType;
    int animal_id;
    String  tagNo;
    String policy_no;
    String dateFrom;
    String dateTo;
    String deathCondition;
    String death_date;
    String claim_condition;

    public BeemaDetail() {
    }


    public BeemaDetail(String dd, String mm, String yy, String animalType, int animal_id, String tagNo, String policy_no, String dateFrom, String dateTo, String deathCondition, String death_date, String claim_condition) {
        this.dd = dd;
        this.mm = mm;
        this.yy = yy;
        this.animalType = animalType;
        this.animal_id = animal_id;
        this.tagNo = tagNo;
        this.policy_no = policy_no;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.deathCondition = deathCondition;
        this.death_date = death_date;
        this.claim_condition = claim_condition;
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

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getTagNo() {
        return tagNo;
    }

    public void setTagNo(String tagNo) {
        this.tagNo = tagNo;
    }

    public String getPolicy_no() {
        return policy_no;
    }

    public void setPolicy_no(String policy_no) {
        this.policy_no = policy_no;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDeathCondition() {
        return deathCondition;
    }

    public void setDeathCondition(String deathCondition) {
        this.deathCondition = deathCondition;
    }

    public String getDeath_date() {
        return death_date;
    }

    public void setDeath_date(String death_date) {
        this.death_date = death_date;
    }

    public String getClaim_condition() {
        return claim_condition;
    }

    public void setClaim_condition(String claim_condition) {
        this.claim_condition = claim_condition;
    }
}
