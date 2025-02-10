package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import java.util.List;

public class PalletContentResponse {
    private String message;
    private Boolean isSuccessfull;

    private List<PalletContent> data;

    public List<PalletContent> getData() {
        return data;
    }

    public void setData(List<PalletContent> data) {
        this.data = data;
    }

    public Boolean getSuccessfull() {
        return isSuccessfull;
    }

    public void setSuccessfull(Boolean successfull) {
        isSuccessfull = successfull;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
