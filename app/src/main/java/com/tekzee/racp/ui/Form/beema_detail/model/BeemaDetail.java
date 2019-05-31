package com.tekzee.racp.ui.Form.beema_detail.model;

import com.orm.SugarRecord;

public class BeemaDetail extends SugarRecord {



    String animal_type;
    int animal_id;
    String tagno;
    String receipt_date;
    String company_name;
    String policy_no;
    String date_from;
    String date_to;
    String date_conditoin;
    String death_date;
    String claim_condition;



    public BeemaDetail() {
    }

    public BeemaDetail(String animal_type, int animal_id, String tagno, String receipt_date, String company_name, String policy_no, String date_from, String date_to, String date_conditoin, String death_date, String claim_condition) {
        this.animal_type = animal_type;
        this.animal_id = animal_id;
        this.tagno = tagno;
        this.receipt_date = receipt_date;
        this.company_name = company_name;
        this.policy_no = policy_no;
        this.date_from = date_from;
        this.date_to = date_to;
        this.date_conditoin = date_conditoin;
        this.death_date = death_date;
        this.claim_condition = claim_condition;
    }

    public String getAnimal_type() {
        return animal_type;
    }

    public void setAnimal_type(String animal_type) {
        this.animal_type = animal_type;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getReceipt_date() {
        return receipt_date;
    }

    public void setReceipt_date(String receipt_date) {
        this.receipt_date = receipt_date;
    }

    public String getTagno() {
        return tagno;
    }

    public void setTagno(String tagno) {
        this.tagno = tagno;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPolicy_no() {
        return policy_no;
    }

    public void setPolicy_no(String policy_no) {
        this.policy_no = policy_no;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_conditoin() {
        return date_conditoin;
    }

    public void setDate_conditoin(String date_conditoin) {
        this.date_conditoin = date_conditoin;
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
