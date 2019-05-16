package com.tekzee.racp.ui.Form.mtg_meeting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MtgMember {

    @SerializedName("pashupalak_id")
    @Expose
    private Integer pashupalakId;
    @SerializedName("pashupalak_name")
    @Expose
    private String pashupalakName;


    private boolean check;

    /**
     * No args constructor for use in serialization
     */
    public MtgMember() {
    }

    public MtgMember(Integer pashupalakId, String pashupalakName) {
        this.pashupalakId = pashupalakId;
        this.pashupalakName = pashupalakName;

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


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}