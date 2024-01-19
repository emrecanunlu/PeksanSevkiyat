package com.replik.peksansevkiyat.DataClass.ModelDto.Seritra;

import com.google.gson.annotations.SerializedName;

public class spSeritra {

    @SerializedName("seriNo")
    String seriNo;
    @SerializedName("stokKodu")
    String stokKodu;
    @SerializedName("govde")
    String govde;
    @SerializedName("logo")
    String logo;
    @SerializedName("ust")
    String ust;
    @SerializedName("yapKod")
    String yapKod;
    @SerializedName("miktar")
    Number miktar;
    @SerializedName("miktar2")
    Number miktar2;
    @SerializedName("subeKodu")
    Integer subeKodu;
    @SerializedName("depoKodu")
    Integer depokod;

    public String getSeriNo() {
        return seriNo;
    }

    public String getStokKodu() {
        return stokKodu;
    }

    public String getGovde() {
        return govde;
    }

    public String getLogo() {
        return logo;
    }

    public String getUst() {
        return ust;
    }

    public String getYapKod() {
        return yapKod;
    }

    public Number getMiktar() {
        return miktar;
    }

    public Number getMiktar2() {
        return miktar2;
    }

    public Integer getSubeKodu() {
        return subeKodu;
    }

    public Integer getDepokod() {
        return depokod;
    }
}
