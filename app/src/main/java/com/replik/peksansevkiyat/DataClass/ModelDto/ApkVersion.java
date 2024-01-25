package com.replik.peksansevkiyat.DataClass.ModelDto;

import com.google.gson.annotations.SerializedName;

public class ApkVersion {
    @SerializedName("data")
    setVersion data;
    @SerializedName("message")
    String message;
    @SerializedName("isSuccessfull")
    private Boolean success;

    public String getVersion() { return data.version; }
    public String getUrl() { return data.url; }
    public String getDetail() { return data.detail; }

   public class setVersion {
       @SerializedName("url")
       String url;
       @SerializedName("version")
       String version;
       @SerializedName("detail")
       String detail;
    }
}
