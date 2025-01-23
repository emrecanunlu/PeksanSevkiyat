package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class PalletContent {
    private String serialNo;
    private String stockCode;
    @SerializedName("yapkod")
    private String yapKod;
    private Number amount;

    public Number getAmount() {
        return amount;
    }

    public String getYapKod() {
        return yapKod;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getSerialNo() {
        return serialNo;
    }
}
