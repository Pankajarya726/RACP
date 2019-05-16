package com.tekzee.racp.ui.Form.ajola.model;

import com.orm.SugarRecord;

public class DataAjola extends SugarRecord {

    String dd;
    String mm;
    int mm_id;
    String yy;
    int yy_id;
    String proof;
    boolean phisical_proof;
    String animal_type;
    int animal_id;
    String Average_milk_production;
    String milk_amount_before;
    String milk_amount_after;
    String faitpercent;
    boolean fait_check;
    String fait_before;
    String fait_after;

    public DataAjola() {
    }

    public DataAjola(String dd, String mm, int mm_id, String yy, int yy_id, String proof, boolean phisical_proof, String animal_type, int animal_id, String average_milk_production, String milk_amount_before, String milk_amount_after, String faitpercent, boolean fait_check, String fait_before, String fait_after) {
        this.dd = dd;
        this.mm = mm;
        this.mm_id = mm_id;
        this.yy = yy;
        this.yy_id = yy_id;
        this.proof = proof;
        this.phisical_proof = phisical_proof;
        this.animal_type = animal_type;
        this.animal_id = animal_id;
        Average_milk_production = average_milk_production;
        this.milk_amount_before = milk_amount_before;
        this.milk_amount_after = milk_amount_after;
        this.faitpercent = faitpercent;
        this.fait_check = fait_check;
        this.fait_before = fait_before;
        this.fait_after = fait_after;
    }

    public String getDd() {
        return dd;
    }

    public int getMm_id() {
        return mm_id;
    }

    public int getYy_id() {
        return yy_id;
    }

    public String getMm() {
        return mm;
    }

    public String getYy() {
        return yy;
    }

    public String getAnimal_type() {
        return animal_type;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public String getProof() {
        return proof;
    }

    public boolean isPhisical_proof() {
        return phisical_proof;
    }

    public String getAverage_milk_production() {
        return Average_milk_production;
    }

    public String getMilk_amount_before() {
        return milk_amount_before;
    }

    public String getMilk_amount_after() {
        return milk_amount_after;
    }

    public String getFaitpercent() {
        return faitpercent;
    }

    public boolean isFait_check() {
        return fait_check;
    }

    public String getFait_before() {
        return fait_before;
    }

    public String getFait_after() {
        return fait_after;
    }
}
