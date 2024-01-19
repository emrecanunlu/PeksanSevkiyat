package com.replik.peksansevkiyat.DataClass.ModelDto;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("isSuccessfull")
    private Boolean success;
    @SerializedName("message")
    private String message;

    public Boolean getSuccess() { return success; }

    public String getMessage() { return message; }
}
