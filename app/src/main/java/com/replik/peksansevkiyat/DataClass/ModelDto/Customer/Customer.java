package com.replik.peksansevkiyat.DataClass.ModelDto.Customer;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {
    @SerializedName("cariIsim")
    private String fullName;
    @SerializedName("cariKod")
    private String code;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
