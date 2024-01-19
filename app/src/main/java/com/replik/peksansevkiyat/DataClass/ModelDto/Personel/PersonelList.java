package com.replik.peksansevkiyat.DataClass.ModelDto.Personel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonelList {
    @SerializedName("data")
    private List<Personel> Personels;
    @SerializedName("isSuccessfull")
    private Boolean success;
    @SerializedName("message")
    private String message;

    public List<Personel> getPersonels() {
        return Personels;
    }

    public Boolean getSuccess() { return success; }

    public String getMessage() { return message; }
}