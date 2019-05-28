package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class AnimalCategory extends SugarRecord {

private Integer animalcategoryId;
private String animalcategoryName;

/**
* No args constructor for use in serialization
*
*/
public AnimalCategory() {
}

/**
*
* @param animalcategoryName
* @param animalcategoryId
*/
public AnimalCategory(Integer animalcategoryId, String animalcategoryName) {
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