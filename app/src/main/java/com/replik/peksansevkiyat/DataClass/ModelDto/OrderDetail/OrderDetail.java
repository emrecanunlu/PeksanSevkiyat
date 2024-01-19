package com.replik.peksansevkiyat.DataClass.ModelDto.OrderDetail;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("id")
    Integer Id;
    @SerializedName("sipId")
    Integer sipId;
    @SerializedName("stokKodu")
    String stokKodu;
    @SerializedName("stok")
    String stok;
    @SerializedName("yapKod")
    String yapKod;
    @SerializedName("govde")
    String govde;
    @SerializedName("ust")
    String ust;
    @SerializedName("logo")
    String logo;
    @SerializedName("yuklemeTip")
    String yuklemeTip;
    @SerializedName("paletTip")
    String paletTip;
    @SerializedName("miktar")
    Number miktar;
    @SerializedName("kalanMiktar")
    Number kalanMiktar;
    @SerializedName("toplananMiktar")
    Number toplananMiktar;

    public Integer getId() { return Id; }

    public Integer getSipId() { return sipId; }

    public String getStokKodu() { return stokKodu; }

    public String getStok() { return stok; }

    public String getYapKod() { return yapKod; }

    public String getGovde() { return govde; }

    public String getUst() { return ust; }

    public String getLogo() { return logo; }

    public String getYuklemeTip() { return yuklemeTip; }

    public String getPaletTip() { return paletTip; }

    public Number getMiktar() { return miktar; }

    public Number getKalanMiktar() { return kalanMiktar; }

    public Number getToplananMiktar() { return toplananMiktar; }
}
