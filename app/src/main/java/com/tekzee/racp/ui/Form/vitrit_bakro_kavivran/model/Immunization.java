package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.orm.SugarRecord;

public class Immunization extends SugarRecord {

    String teekaname;
    String teekadate;

    public Immunization() {
    }

    public Immunization(String teekaname, String teekadate) {
        this.teekaname = teekaname;
        this.teekadate = teekadate;
    }

    public String getTeekaname() {
        return teekaname;
    }

    public void setTeekaname(String teekaname) {
        this.teekaname = teekaname;
    }

    public String getTeekadate() {
        return teekadate;
    }

    public void setTeekadate(String teekadate) {
        this.teekadate = teekadate;
    }
}
