package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Sidemenu extends SugarRecord {

    private String menuName;
    private String menuImage;

    /**
     * No args constructor for use in serialization
     */
    public Sidemenu() {
    }

    /**
     * @param menuImage
     * @param menuName
     */
    public Sidemenu(String menuName, String menuImage) {
        super();
        this.menuName = menuName;
        this.menuImage = menuImage;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

}
