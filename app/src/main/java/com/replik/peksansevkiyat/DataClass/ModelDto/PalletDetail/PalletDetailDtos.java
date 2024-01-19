package com.replik.peksansevkiyat.DataClass.ModelDto.PalletDetail;

import com.google.gson.annotations.SerializedName;

public class PalletDetailDtos {
    public static class setPalletDetailColumn {
        @SerializedName("palletId")
        Integer palletId;
        @SerializedName("aciklama")
        String aciklama;
        @SerializedName("staffId")
        Integer staffId;
        @SerializedName("seriNo")
        String seriNo;
        @SerializedName("stokKodu")
        String stokKodu;
        @SerializedName("miktar")
        Number miktar;
        @SerializedName("miktar2")
        Number miktar2;

        public setPalletDetailColumn(Integer palletId, String aciklama, Integer staffId, String seriNo, String stokKodu, Number miktar, Number miktar2) {
            this.palletId = palletId;
            this.aciklama = aciklama;
            this.staffId = staffId;
            this.seriNo = seriNo;
            this.stokKodu = stokKodu;
            this.miktar = miktar;
            this.miktar2 = miktar2;
        }
    }

    /// eski yapıda kullanılıyordu iptal edildi
    public static class delPalletDetailColumn {
        @SerializedName("palletDetailId")
        Integer palletDetailId;
        @SerializedName("staffId")
        Integer staffId;

        public delPalletDetailColumn(Integer palletDetailId, Integer staffId) {
            this.palletDetailId = palletDetailId;
            this.staffId = staffId;
        }
    }
}
