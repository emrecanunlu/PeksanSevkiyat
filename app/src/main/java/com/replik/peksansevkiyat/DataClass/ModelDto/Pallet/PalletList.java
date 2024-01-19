package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PalletList {
    @SerializedName("data")
    private List<Pallet> Pallets;

    public List<Pallet> getPallet() {
        return Pallets;
    }

    public void setPallets(List<Pallet> pallets) {
        Pallets = pallets;
    }
}
