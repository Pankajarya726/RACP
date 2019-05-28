package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Formtype extends SugarRecord {

    private Integer formId;
    private String formName;
    private String formImage;

    public Formtype() {
    }


    public Formtype(Integer formId, String formName, String formImage) {
        super();
        this.formId = formId;
        this.formName = formName;
        this.formImage = formImage;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormImage() {
        return formImage;
    }

    public void setFormImage(String formImage) {
        this.formImage = formImage;
    }

}