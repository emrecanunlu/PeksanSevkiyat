package com.replik.peksansevkiyat.DataClass.ModelDto;

import com.google.gson.annotations.SerializedName;

public class ApkVersion {
    @SerializedName("message")
    String version;
    @SerializedName("data")
    String url;

    public String getVersion() {
        return version;
    }

    public String getUrl() {
        return url;
    }
}
