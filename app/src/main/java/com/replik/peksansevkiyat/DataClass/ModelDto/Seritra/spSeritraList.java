package com.replik.peksansevkiyat.DataClass.ModelDto.Seritra;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class spSeritraList {
    @SerializedName("data")
    private List<spSeritra> spSeritras;

    public List<spSeritra> getSpSeritra() {
        return spSeritras;
    }

    public void setSpSeritra(List<spSeritra> spSeritras) {
        spSeritras = spSeritras;
    }
}

