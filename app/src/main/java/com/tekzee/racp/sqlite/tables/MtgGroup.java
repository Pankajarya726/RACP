package com.tekzee.racp.sqlite.tables;

import com.orm.SugarRecord;

public class MtgGroup extends SugarRecord{

    private Integer mtggroupId;
    private String mtggroupName;

    /**
     * No args constructor for use in serialization
     */
    public MtgGroup() {
    }

    /**
     * @param mtggroupName
     * @param mtggroupId
     */
    public MtgGroup(Integer mtggroupId, String mtggroupName) {
        super();
        this.mtggroupId = mtggroupId;
        this.mtggroupName = mtggroupName;
    }

    public Integer getMtggroupId() {
        return mtggroupId;
    }

    public void setMtggroupId(Integer mtggroupId) {
        this.mtggroupId = mtggroupId;
    }

    public String getMtggroupName() {
        return mtggroupName;
    }

    public void setMtggroupName(String mtggroupName) {
        this.mtggroupName = mtggroupName;
    }

}