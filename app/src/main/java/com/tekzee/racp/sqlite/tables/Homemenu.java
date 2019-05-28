package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Homemenu extends SugarRecord {


    private Integer menuId;

    private String menuName;

    private String menuImage;

    public Homemenu() {
    }
    public Homemenu(Integer menuId, String menuName, String menuImage) {
        super();
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuImage = menuImage;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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