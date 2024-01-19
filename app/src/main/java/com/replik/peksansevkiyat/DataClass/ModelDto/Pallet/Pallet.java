package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import com.google.gson.annotations.SerializedName;

public class Pallet {
    @SerializedName("id")
    Integer id;
    @SerializedName("barkod")
    String barkod;
    @SerializedName("aciklama")
    String aciklama;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
