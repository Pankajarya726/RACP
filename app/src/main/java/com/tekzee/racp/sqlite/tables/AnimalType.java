package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class AnimalType extends SugarRecord {

    private Integer animaltypeId;
    private String animaltypeName;
    private String animalCategoryName;

    /**
     * No args constructor for use in serialization
     */
    public AnimalType() {
    }

    /**
     * @param animalCategoryName
     * @param animaltypeId
     * @param animaltypeName
     */
    public AnimalType(Integer animaltypeId, String animaltypeName, String animalCategoryName) {
        super();
        this.animaltypeId = animaltypeId;
        this.animaltypeName = animaltypeName;
        this.animalCategoryName = animalCategoryName;
    }

    public Integer getAnimaltypeId() {
        return animaltypeId;
    }

    public void setAnimaltypeId(Integer animaltypeId) {
        this.animaltypeId = animaltypeId;
    }

    public String getAnimaltypeName() {
        return animaltypeName;
    }

    public void setAnimaltypeName(String animaltypeName) {
        this.animaltypeName = animaltypeName;
    }

    public String getAnimalCategoryName() {
        return animalCategoryName;
    }

    public void setAnimalCategoryName(String animalCategoryName) {
        this.animalCategoryName = animalCategoryName;
    }

}