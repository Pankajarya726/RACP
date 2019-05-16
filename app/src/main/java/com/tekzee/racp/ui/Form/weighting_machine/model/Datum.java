package com.tekzee.racp.ui.Form.weighting_machine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("animalcategory_id")
    @Expose
    private Integer animalcategoryId;
    @SerializedName("animalcategory_Name")
    @Expose
    private String animalcategoryName;

    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }

    /**
     * @param animalcategoryName
     * @param animalcategoryId
     */
    public Datum(Integer animalcategoryId, String animalcategoryName) {
        super();
        this.animalcategoryId = animalcategoryId;
        this.animalcategoryName = animalcategoryName;
    }

    public Integer getAnimalcategoryId() {
        return animalcategoryId;
    }

    public void setAnimalcategoryId(Integer animalcategoryId) {
        this.animalcategoryId = animalcategoryId;
    }

    public String getAnimalcategoryName() {
        return animalcategoryName;
    }

    public void setAnimalcategoryName(String animalcategoryName) {
        this.animalcategoryName = animalcategoryName;
    }

}