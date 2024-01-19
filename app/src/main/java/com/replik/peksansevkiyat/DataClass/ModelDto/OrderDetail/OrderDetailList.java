package com.replik.peksansevkiyat.DataClass.ModelDto.OrderDetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailList {
    @SerializedName("data")
    private List<OrderDetail> OrderDetails;
    @SerializedName("isSuccessfull")
    private Boolean success;
    @SerializedName("message")
    private String message;

    public List<OrderDetail> getOrderDetail() { return OrderDetails; }

    public Boolean getSuccess() { return success; }

    public String getMessage() { return message; }
}
