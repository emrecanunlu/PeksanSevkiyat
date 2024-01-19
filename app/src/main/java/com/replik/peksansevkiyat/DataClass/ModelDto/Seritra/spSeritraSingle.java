package com.replik.peksansevkiyat.DataClass.ModelDto.Seritra;

import com.google.gson.annotations.SerializedName;

public class spSeritraSingle {
    @SerializedName("data")
    private spSeritra spSeritra;
    @SerializedName("isSuccessfull")
    private Boolean success;
    @SerializedName("message")
    private String message;

    public spSeritra getSpSeritra() {
        return spSeritra;
    }

    public Boolean getSuccess() { return success; }

    public String getMessage() { return message; }
}
