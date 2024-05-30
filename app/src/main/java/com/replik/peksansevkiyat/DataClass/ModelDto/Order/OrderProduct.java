package com.replik.peksansevkiyat.DataClass.ModelDto.Order;

public class OrderProduct {
    private String serialNo;
    private String stokkodu;
    private String yapkod;

    public OrderProduct(String serialNo, String stokkodu, String yapkod) {
        this.serialNo = serialNo;
        this.stokkodu = stokkodu;
        this.yapkod = yapkod;
    }

    public String getYapkod() {
        return yapkod;
    }

    public void setYapkod(String yapkod) {
        this.yapkod = yapkod;
    }

    public String getStokkodu() {
        return stokkodu;
    }

    public void setStokkodu(String stokkodu) {
        this.stokkodu = stokkodu;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
}
