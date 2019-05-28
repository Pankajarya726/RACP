package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class PashuPalakCategory extends SugarRecord {

    private Integer pashuPalakCategoryID;
    private String pashuPalakCategoryName;

    /**
     * No args constructor for use in serialization
     */
    public PashuPalakCategory() {
    }

    /**
     * @param pashuPalakCategoryID
     * @param pashuPalakCategoryName
     */
    public PashuPalakCategory(Integer pashuPalakCategoryID, String pashuPalakCategoryName) {
        super();
        this.pashuPalakCategoryID = pashuPalakCategoryID;
        this.pashuPalakCategoryName = pashuPalakCategoryName;
    }

    public Integer getPashuPalakCategoryID() {
        return pashuPalakCategoryID;
    }

    public void setPashuPalakCategoryID(Integer pashuPalakCategoryID) {
        this.pashuPalakCategoryID = pashuPalakCategoryID;
    }

    public String getPashuPalakCategoryName() {
        return pashuPalakCategoryName;
    }

    public void setPashuPalakCategoryName(String pashuPalakCategoryName) {
        this.pashuPalakCategoryName = pashuPalakCategoryName;
    }

}