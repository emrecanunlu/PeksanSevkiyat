package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import androidx.annotation.Nullable;

import java.util.List;

public class PalletContentDto {
    private List<String> products;
    @Nullable
    private String stockCode;
    @Nullable
    private String yapkod;
    private Number staffId;

    public PalletContentDto(List<String> products, Number staffId, @Nullable String stockCode, @Nullable String yapkod) {
        this.products = products;
        this.staffId = staffId;
        this.stockCode = stockCode;
        this.yapkod = yapkod;
    }
}
