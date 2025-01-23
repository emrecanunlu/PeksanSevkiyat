package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import java.util.Date;

public class PalletLabel {
    private Number id;
    private String barkod;
    private String aciklama;
    private String createStaff;
    private String createDate;

    public Number getId() {
        return id;
    }

    public String getAciklama() {
        return aciklama;
    }

    public String getBarkod() {
        return barkod;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public String getCreateDate() {
        return createDate;
    }
}
