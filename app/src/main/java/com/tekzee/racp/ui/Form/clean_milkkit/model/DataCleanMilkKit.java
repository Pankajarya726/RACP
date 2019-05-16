package com.tekzee.racp.ui.Form.clean_milkkit.model;

import com.orm.SugarRecord;

public class DataCleanMilkKit extends SugarRecord {


    String dd;
    String mm;
    int mm_id;
    String yy;
    int yy_id;

    String phy_proof;
    boolean Proof;
    String usability;
    boolean Use;
    String available;
    boolean availability;

    String visiting_date;
    String test_date;
    String mresult;
    boolean Result;

    String before_use;
    String after_use;
    String note;

    public DataCleanMilkKit() {
    }

    public DataCleanMilkKit(String dd, String mm, int mm_id, String yy, int yy_id, String phy_proof, boolean proof, String usability, boolean use, String available, boolean availability, String visiting_date, String test_date, String mresult, boolean result, String before_use, String after_use, String note) {
        this.dd = dd;
        this.mm = mm;
        this.mm_id = mm_id;
        this.yy = yy;
        this.yy_id = yy_id;
        this.phy_proof = phy_proof;
        Proof = proof;
        this.usability = usability;
        Use = use;
        this.available = available;
        this.availability = availability;
        this.visiting_date = visiting_date;
        this.test_date = test_date;
        this.mresult = mresult;
        Result = result;
        this.before_use = before_use;
        this.after_use = after_use;
        this.note = note;
    }

    public String getDd() {
        return dd;
    }

    public String getMm() {
        return mm;
    }

    public int getMm_id() {
        return mm_id;
    }

    public String getYy() {
        return yy;
    }

    public int getYy_id() {
        return yy_id;
    }

    public String getPhy_proof() {
        return phy_proof;
    }

    public boolean isProof() {
        return Proof;
    }

    public String getUsability() {
        return usability;
    }

    public boolean isUse() {
        return Use;
    }

    public String getAvailable() {
        return available;
    }

    public boolean isAvailability() {
        return availability;
    }

    public String getVisiting_date() {
        return visiting_date;
    }

    public String getTest_date() {
        return test_date;
    }

    public String getMresult() {
        return mresult;
    }

    public boolean isResult() {
        return Result;
    }

    public String getBefore_use() {
        return before_use;
    }

    public String getAfter_use() {
        return after_use;
    }

    public String getNote() {
        return note;
    }
}
