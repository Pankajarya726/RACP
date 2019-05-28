package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Castcategory extends SugarRecord {

    private Integer castCategoryId;
    private String castCategoryName;

    /**
     * No args constructor for use in serialization
     */
    public Castcategory() {
    }

    /**
     * @param castCategoryId
     * @param castCategoryName
     */
    public Castcategory(Integer castCategoryId, String castCategoryName) {
        super();
        this.castCategoryId = castCategoryId;
        this.castCategoryName = castCategoryName;
    }

    public Integer getCastCategoryId() {
        return castCategoryId;
    }

    public void setCastCategoryId(Integer castCategoryId) {
        this.castCategoryId = castCategoryId;
    }

    public String getCastCategoryName() {
        return castCategoryName;
    }

    public void setCastCategoryName(String castCategoryName) {
        this.castCategoryName = castCategoryName;
    }

}