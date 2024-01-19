package com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping;

import com.google.gson.annotations.SerializedName;

public class OrderShipping {
    @SerializedName("id")
    Integer Id;
    //@SerializedName("cariKodu")
    //String CariKodu;
    @SerializedName("plaka")
    String Plaka;
    @SerializedName("tasiyiciVKN")
    String TasiyiciVKN;
    @SerializedName("tasiyiciAdi")
    String TasiyiciAdi;
    @SerializedName("ilce")
    String Ilce;
    @SerializedName("il")
    String Il;
    @SerializedName("ulke")
    String Ulke;
    @SerializedName("postaKodu")
    String PostaKodu;
    @SerializedName("soforAd")
    String SoforAd;
    @SerializedName("soforSoyAd")
    String SoforSoyAd;
    @SerializedName("soforAciklama")
    String SoforAciklama;
    @SerializedName("soforTc")
    String SoforTc;
    @SerializedName("dorsePlaka1")
    String DorsePlaka;
    //@SerializedName("dorsePlaka2")
    //String DorsePlaka2;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    /*public String getCariKodu() {
        return CariKodu;
    }*/
    /*public void setCariKodu(String cariKodu) {
        CariKodu = cariKodu;
    }*/

    public String getPlaka() {
        return Plaka;
    }

    public void setPlaka(String plaka) {
        Plaka = plaka;
    }

    public String getTasiyiciVKN() {
        return TasiyiciVKN;
    }

    public void setTasiyiciVKN(String tasiyiciVKN) {
        TasiyiciVKN = tasiyiciVKN;
    }

    public String getTasiyiciAdi() {
        return TasiyiciAdi;
    }

    public void setTasiyiciAdi(String tasiyiciAdi) {
        TasiyiciAdi = tasiyiciAdi;
    }

    public String getIlce() {
        return Ilce;
    }

    public void setIlce(String ilce) {
        Ilce = ilce;
    }

    public String getIl() {
        return Il;
    }

    public void setIl(String il) {
        Il = il;
    }

    public String getUlke() {
        return Ulke;
    }

    public void setUlke(String ulke) {
        Ulke = ulke;
    }

    public String getPostaKodu() {
        return PostaKodu;
    }

    public void setPostaKodu(String postaKodu) {
        PostaKodu = postaKodu;
    }

    public String getSoforAd() {
        return SoforAd;
    }

    public void setSoforAd(String soforAd) {
        SoforAd = soforAd;
    }

    public String getSoforSoyAd() {
        return SoforSoyAd;
    }

    public void setSoforSoyAd(String soforSoyAd) {
        SoforSoyAd = soforSoyAd;
    }

    public String getSoforAciklama() {
        return SoforAciklama;
    }

    public void setSoforAciklama(String soforAciklama) {
        SoforAciklama = soforAciklama;
    }

    public String getSoforTc() {
        return SoforTc;
    }

    public void setSoforTc(String soforTc) {
        SoforTc = soforTc;
    }

    public String getDorsePlaka() {
        return DorsePlaka;
    }

    public void setDorsePlaka(String dorsePlaka) {
        DorsePlaka = dorsePlaka;
    }

    /*public String getDorsePlaka2() {
        return DorsePlaka2;
    }

    public void setDorsePlaka2(String dorsePlaka2) {
        DorsePlaka2 = dorsePlaka2;
    }*/

    public void setOrderShipping(
            String _Plaka,
            String _TasiyiciVKN,
            String _TasiyiciAdi,
            String _Ilce,
            String _Il,
            String _Ulke,
            String _PostaKodu,
            String _SoforAd,
            String _SoforSoyAd,
            String _SoforAciklama,
            String _SoforTc,
            String _DorsePlaka) {
        Plaka = _Plaka;
        TasiyiciVKN = _TasiyiciVKN;
        TasiyiciAdi = _TasiyiciAdi;
        Ilce = _Ilce;
        Il = _Il;
        Ulke = _Ulke;
        PostaKodu = _PostaKodu;
        SoforAd = _SoforAd;
        SoforSoyAd = _SoforSoyAd;
        SoforTc = _SoforTc;
        SoforAciklama = _SoforAciklama;
        DorsePlaka = _DorsePlaka;
    }
}
