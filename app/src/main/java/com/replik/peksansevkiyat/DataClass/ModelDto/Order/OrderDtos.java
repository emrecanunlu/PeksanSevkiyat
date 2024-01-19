package com.replik.peksansevkiyat.DataClass.ModelDto.Order;

import com.google.gson.annotations.SerializedName;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.OrderShipping;

public class OrderDtos {
    public static class setPickingItem {
        @SerializedName("sipNo")
        String sipNo;
        @SerializedName("staffId")
        Integer staffId;
        @SerializedName("barcode")
        String barcode;
        @SerializedName("quantity")
        Double quantity;
        @SerializedName("isNumune")
        Boolean isNumune;

        public setPickingItem(String SipNo, Integer StaffId, String Barcode, Double Quantity, Boolean IsNumune) {
            this.sipNo = SipNo;
            this.staffId = StaffId;
            this.barcode = Barcode;
            this.quantity = Quantity;
            this.isNumune = IsNumune;
        }
    }

    public static class delPickingItem {
        @SerializedName("sipNo")
        String sipNo;
        @SerializedName("staffId")
        Integer staffId;
        @SerializedName("barcode")
        String barcode;

        public delPickingItem(String SipNo, Integer StaffId, String Barcode) {
            this.sipNo = SipNo;
            this.staffId = StaffId;
            this.barcode = Barcode;
        }
    }
    
    public static class setOrderStatus {
        @SerializedName("sipNo")
        String sipNo;
        @SerializedName("orderShipping")
        OrderShipping orderShipping;

        public setOrderStatus(String sipNo, OrderShipping orderShipping) {
            this.sipNo = sipNo;
            this.orderShipping = orderShipping;
        }
    }
}
