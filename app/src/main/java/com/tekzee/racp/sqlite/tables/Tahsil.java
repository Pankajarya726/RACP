package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class Tahsil extends SugarRecord {

    private Integer tahsilId;
    private String tahsilName;
    private Integer vidhanasabhaId;

    public Tahsil() {
    }

    /**
     * @param tahsilId
     * @param tahsilName
     * @param vidhanasabhaId
     */
    public Tahsil(Integer tahsilId, String tahsilName, Integer vidhanasabhaId) {
        super();
        this.tahsilId = tahsilId;
        this.tahsilName = tahsilName;
        this.vidhanasabhaId = vidhanasabhaId;
    }

    public Integer getTahsilId() {
        return tahsilId;
    }

    public void setTahsilId(Integer tahsilId) {
        this.tahsilId = tahsilId;
    }

    public String getTahsilName() {
        return tahsilName;
    }

    public void setTahsilName(String tahsilName) {
        this.tahsilName = tahsilName;
    }

    public Integer getVidhanasabhaId() {
        return vidhanasabhaId;
    }

    public void setVidhanasabhaId(Integer vidhanasabhaId) {
        this.vidhanasabhaId = vidhanasabhaId;
    }

}