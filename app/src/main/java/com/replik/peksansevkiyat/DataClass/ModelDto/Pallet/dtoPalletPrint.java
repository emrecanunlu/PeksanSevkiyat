package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dtoPalletPrint {
    @SerializedName("id")
    Integer id;
    @SerializedName("barkod")
    String barkod;
    @SerializedName("aciklama")
    String aciklama;
    @SerializedName("createStaff")
    String createStaff;
    @SerializedName("createDate")
    String createDate;

        public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) { this.createDate = createDate; }

    public class PalletPrintList {
        @SerializedName("data")
        private List<dtoPalletPrint> PalletPrints;
        @SerializedName("isSuccessfull")
        private Boolean success;
        @SerializedName("message")
        private String message;

        public Boolean getSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public List<dtoPalletPrint> getPalletPrints() {
            return PalletPrints;
        }

        public void setPalletPrints(List<dtoPalletPrint> palletPrints) { PalletPrints = palletPrints; }
    }
}
