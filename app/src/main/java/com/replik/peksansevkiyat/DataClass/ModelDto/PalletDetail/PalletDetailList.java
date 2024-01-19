package com.replik.peksansevkiyat.DataClass.ModelDto.PalletDetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PalletDetailList {
    @SerializedName("data")
    private List<PalletDetail> PalletDetails;
    @SerializedName("isSuccessfull")
    private Boolean success;
    @SerializedName("message")
    private String message;

    public List<PalletDetail> getPalletDetails() { return PalletDetails; }

    public Boolean getSuccess() { return success; }

    public String getMessage() { return message; }
}
