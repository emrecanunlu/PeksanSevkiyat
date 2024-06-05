package com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderShippingList {
    @SerializedName("data")
    private List<OrderShipping> OrderShippings;
    @SerializedName("isSuccessfull")
    private Boolean success;
    @SerializedName("message")
    private String message;

    public List<OrderShipping> getOrderShipping() {
        return OrderShippings;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
