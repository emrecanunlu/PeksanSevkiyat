package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

public class PalletLabelResponse {
    private String message;
    private Boolean isSuccessfull;
    private PalletLabel data;

    public PalletLabel getData() {
        return data;
    }

    public Boolean getSuccessfull() {
        return isSuccessfull;
    }

    public String getMessage() {
        return message;
    }
}
