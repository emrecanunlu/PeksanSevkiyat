package com.replik.peksansevkiyat.DataClass.ModelDto;

import com.google.gson.annotations.SerializedName;

public class ApkVersion {
    @SerializedName("data")
    String version;
    @SerializedName("message")
    String url;

    public String getVersion() {
        return version;
    }

    public String getUrl() {
        return url;
    }
}
