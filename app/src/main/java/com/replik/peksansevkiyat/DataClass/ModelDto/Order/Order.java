package com.replik.peksansevkiyat.DataClass.ModelDto.Order;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("id")
    Integer id;
    @SerializedName("sipNo")
    String sipNo;
    @SerializedName("cari")
    String cari;
    @SerializedName("cariKodu")
    String cariKodu;
    @SerializedName("tarih")
    String tarih;
    @SerializedName("numuneSip")
    Boolean numuneSip;
    @SerializedName("hemenSevk")
    Boolean hemenSevk;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSipNo() { return sipNo; }

    public String getCari() { return cari; }

    public String getCariKodu() { return cariKodu; }

    public String getTarih() { return tarih; }

    public Boolean getNumuneSip() { return numuneSip; }

    public Boolean getHemenSevk() { return hemenSevk; }
}
