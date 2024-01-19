package com.replik.peksansevkiyat.DataClass.ModelDto.Dtos;
import com.google.gson.annotations.SerializedName;

public class getStandartLong {
    @SerializedName("staffId")
    Integer staffId;
    @SerializedName("value")
    Number value;

    public getStandartLong(Integer StaffId, Number Value) {
        this.staffId = StaffId;
        this.value = Value;
    }
}
