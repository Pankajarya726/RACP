package com.tekzee.racp.ui.formselection.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("form_name")
    @Expose
    private String formName;
    @SerializedName("form_image")
    @Expose
    private String formImage;

    /**
     * No args constructor for use in serialization
     *
     */
    public FormData() {
    }

    /**
     *
     * @param id
     * @param formName
     * @param formImage
     */
    public FormData(Integer id, String formName, String formImage) {
        super();
        this.id = id;
        this.formName = formName;
        this.formImage = formImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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