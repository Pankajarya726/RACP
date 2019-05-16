package com.tekzee.racp.ui.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpResponse{


    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;



    public VerifyOtpResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

        public boolean isSuccess(){
                return success;
        }

        public boolean getSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public Data getData() {
            return data;
        }

    public void setData(Data data) {
        this.data = data;
    }

}