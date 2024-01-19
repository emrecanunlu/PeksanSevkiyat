package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import com.google.gson.annotations.SerializedName;

public class PalletSingle {
    @SerializedName("data")
    private Pallet Pallet;
    @SerializedName("isSuccessfull")
    private Boolean success;
    @SerializedName("message")
    private String message;

    public Pallet getPallet() {
        return Pallet;
    }

    public void setPallet(Pallet pallet) {
        Pallet = pallet;
    }

    public Boolean getSuccess() { return success; }

    public String getMessage() { return message; }
}
