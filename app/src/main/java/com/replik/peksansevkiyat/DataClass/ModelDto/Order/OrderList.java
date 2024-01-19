package com.replik.peksansevkiyat.DataClass.ModelDto.Order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderList {
    @SerializedName("data")
    private List<Order> Orders;
    @SerializedName("isSuccessfull")
    private Boolean success;
    @SerializedName("message")
    private String message;

    public List<Order> getOrder() { return Orders; }
    public void setOrders(List<Order> orders) { Orders = orders; }

    public Boolean getSuccess() { return success; }

    public String getMessage() { return message; }


}
