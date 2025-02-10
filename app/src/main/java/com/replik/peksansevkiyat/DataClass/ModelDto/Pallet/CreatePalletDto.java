package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import androidx.annotation.Nullable;

import java.util.List;

public class CreatePalletDto {
    private List<String> products;
    private Number staffId;

    public CreatePalletDto(List<String> products, Number staffId) {
        this.products = products;
        this.staffId = staffId;
    }
}
