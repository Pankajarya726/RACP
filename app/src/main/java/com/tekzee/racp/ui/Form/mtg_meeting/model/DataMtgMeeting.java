package com.tekzee.racp.ui.Form.mtg_meeting.model;

import com.orm.SugarRecord;

public class DataMtgMeeting extends SugarRecord {

    int mtggroupid;
    int mtgmemberid;
    String date_mtg_meeting;
    String mtgname;
    String namemember;
    String note;

    public DataMtgMeeting(int mtggroupid, int mtgmemberid, String date_mtg_meeting, String mtgname, String namemember, String note) {
        this.mtggroupid = mtggroupid;
        this.mtgmemberid = mtgmemberid;
        this.date_mtg_meeting = date_mtg_meeting;
        this.mtgname = mtgname;
        this.namemember = namemember;
        this.note = note;

    }

    public DataMtgMeeting() {
    }

    public int getMtggroupid() {
        return mtggroupid;
    }

    public void setMtggroupid(int mtggroupid) {
        this.mtggroupid = mtggroupid;
    }

    public int getMtgmemberid() {
        return mtgmemberid;
    }

    public void setMtgmemberid(int mtgmemberid) {
        this.mtgmemberid = mtgmemberid;
    }

    public String getDate_mtg_meeting() {
        return date_mtg_meeting;
    }

    public void setDate_mtg_meeting(String date_mtg_meeting) {
        this.date_mtg_meeting = date_mtg_meeting;
    }

    public String getMtgname() {
        return mtgname;
    }

    public void setMtgname(String mtgname) {
        this.mtgname = mtgname;
    }

    public String getNamemember() {
        return namemember;
    }

    public void setNamemember(String namemember) {
        this.namemember = namemember;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

