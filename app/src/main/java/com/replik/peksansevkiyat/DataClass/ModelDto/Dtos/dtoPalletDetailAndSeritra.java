package com.replik.peksansevkiyat.DataClass.ModelDto.Dtos;

import com.google.gson.annotations.SerializedName;
import com.replik.peksansevkiyat.DataClass.ModelDto.PalletDetail.PalletDetail;
import com.replik.peksansevkiyat.DataClass.ModelDto.Seritra.spSeritra;

public class dtoPalletDetailAndSeritra {
    @SerializedName("seritra")
    spSeritra seritras;
    @SerializedName("palletDetail")
    PalletDetail palletDetails;

    public spSeritra getSeritra() { return seritras; }

    public PalletDetail getPalletDetail() { return palletDetails; }
}
