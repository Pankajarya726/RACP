package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class LlwMTGMember extends SugarRecord {

    private Integer pashupalakId;
    private String pashupalakName;
    private String pashupalakMobile;
    private String pashupalakAddress;
    private Integer mtggroupId;

    /**
     * No args constructor for use in serialization
     */
    public LlwMTGMember() {
    }

    /**
     * @param pashupalakAddress
     * @param pashupalakId
     * @param pashupalakName
     * @param mtggroupId
     * @param pashupalakMobile
     */
    public LlwMTGMember(Integer pashupalakId, String pashupalakName, String pashupalakMobile, String pashupalakAddress, Integer mtggroupId) {
        super();
        this.pashupalakId = pashupalakId;
        this.pashupalakName = pashupalakName;
        this.pashupalakMobile = pashupalakMobile;
        this.pashupalakAddress = pashupalakAddress;
        this.mtggroupId = mtggroupId;
    }

    public Integer getPashupalakId() {
        return pashupalakId;
    }

    public void setPashupalakId(Integer pashupalakId) {
        this.pashupalakId = pashupalakId;
    }

    public String getPashupalakName() {
        return pashupalakName;
    }

    public void setPashupalakName(String pashupalakName) {
        this.pashupalakName = pashupalakName;
    }

    public String getPashupalakMobile() {
        return pashupalakMobile;
    }

    public void setPashupalakMobile(String pashupalakMobile) {
        this.pashupalakMobile = pashupalakMobile;
    }

    public String getPashupalakAddress() {
        return pashupalakAddress;
    }

    public void setPashupalakAddress(String pashupalakAddress) {
        this.pashupalakAddress = pashupalakAddress;
    }

    public Integer getMtggroupId() {
        return mtggroupId;
    }

    public void setMtggroupId(Integer mtggroupId) {
        this.mtggroupId = mtggroupId;
    }

}