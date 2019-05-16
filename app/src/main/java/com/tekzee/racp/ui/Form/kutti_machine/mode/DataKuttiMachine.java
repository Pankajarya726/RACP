package com.tekzee.racp.ui.Form.kutti_machine.mode;

import com.orm.SugarRecord;

public class DataKuttiMachine extends SugarRecord {

    String dd;
    String mm;
    int mm_id;
    String yy;
    int yy_id;
    String machine_type;
    int machine_id;
    String use;
    boolean isUse;
    String proof;
    boolean isProof;
    String note;

    public DataKuttiMachine() {
    }

    public DataKuttiMachine(String dd, String mm, int mm_id, String yy, int yy_id, String machine_type, int machine_id, String use, boolean isUse, String proof, boolean isProof, String note) {
        this.dd = dd;
        this.mm = mm;
        this.mm_id = mm_id;
        this.yy = yy;
        this.yy_id = yy_id;
        this.machine_type = machine_type;
        this.machine_id = machine_id;
        this.use = use;
        this.isUse = isUse;
        this.proof = proof;
        this.isProof = isProof;
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

    public int getMm_id() {
        return mm_id;
    }

    public void setMm_id(int mm_id) {
        this.mm_id = mm_id;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public int getYy_id() {
        return yy_id;
    }

    public void setYy_id(int yy_id) {
        this.yy_id = yy_id;
    }

    public String getMachine_type() {
        return machine_type;
    }

    public void setMachine_type(String machine_type) {
        this.machine_type = machine_type;
    }

    public int getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(int machine_id) {
        this.machine_id = machine_id;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public boolean isProof() {
        return isProof;
    }

    public void setProof(boolean proof) {
        isProof = proof;
    }
}
