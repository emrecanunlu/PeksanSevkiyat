package com.replik.peksansevkiyat.DataClass.ModelDto.Stock;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class StockItem {
    @Nullable
    final private Integer id;
    @SerializedName("stokKodu")
    final private String stockCode;
    @SerializedName("stokAdi")
    final private String stockName;
    @SerializedName("miktar")
    final private double amount;
    @Nullable
    @SerializedName("createdDate")
    final private String createdAt;

    public StockItem(int id, String stockCode, String stockName, double amount, @Nullable String createdAt) {
        this.id = id;
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public double getAmount() {
        return amount;
    }

    public String getStockName() {
        return stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    @Nullable
    public String getCreatedAt() {
        return createdAt;
    }
}
