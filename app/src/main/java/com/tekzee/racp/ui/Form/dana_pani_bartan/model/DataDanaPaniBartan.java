package com.tekzee.racp.ui.Form.dana_pani_bartan.model;

import com.orm.SugarRecord;

public class DataDanaPaniBartan extends SugarRecord {

    String dd;
    String mm;
    int mm_id;
    String yy;
    int yy_id;
     String use;
    boolean isUse;
    String proof;
    boolean isProof;
    String note;

    public DataDanaPaniBartan() {
    }


    public DataDanaPaniBartan(String dd, String mm, int mm_id, String yy, int yy_id, String use, boolean isUse, String proof, boolean isProof, String note) {
        this.dd = dd;
        this.mm = mm;
        this.mm_id = mm_id;
        this.yy = yy;
        this.yy_id = yy_id;
        this.use = use;
        this.isUse = isUse;
        this.proof = proof;
        this.isProof = isProof;
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

    public String getUse() {
        return use;
    }

    public boolean isUse() {
        return isUse;
    }

    public String getProof() {
        return proof;
    }

    public boolean isProof() {
        return isProof;
    }

    public String getNote() {
        return note;
    }
}
