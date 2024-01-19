package com.replik.peksansevkiyat.DataClass.ModelDto.Dtos;

import com.google.gson.annotations.SerializedName;

public class dtoPalletDetailAndSeritra_data {
    @SerializedName("data")
    private dtoPalletDetailAndSeritra dtoPalletDetailAndSeritras;
    @SerializedName("isSuccessfull")
    private Boolean success;
    @SerializedName("message")
    private String message;

    public dtoPalletDetailAndSeritra getDtoPalletDetailAndSeritra() { return dtoPalletDetailAndSeritras; }

    public Boolean getSuccess() { return success; }

    public String getMessage() { return message; }
}
