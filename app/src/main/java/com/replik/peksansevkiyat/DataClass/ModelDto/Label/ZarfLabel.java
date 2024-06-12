package com.replik.peksansevkiyat.DataClass.ModelDto.Label;

import java.util.List;

public class ZarfLabel {
    private String teslimAdi;
    private String teslimAdresi;
    private String nakliyeTipi;
    private List<ZarfProducts> products;

    public ZarfLabel(String teslimAdi, String teslimAdresi, String nakliyeTipi, List<ZarfProducts> products) {
        this.teslimAdi = teslimAdi;
        this.teslimAdresi = teslimAdresi;
        this.nakliyeTipi = nakliyeTipi;
        this.products = products;
    }
    
    public String getTeslimAdi() {
        return teslimAdi;
    }

    public void setTeslimAdi(String teslimAdi) {
        this.teslimAdi = teslimAdi;
    }

    public String getTeslimAdresi() {
        return teslimAdresi;
    }

    public void setTeslimAdresi(String teslimAdresi) {
        this.teslimAdresi = teslimAdresi;
    }

    public String getNakliyeTipi() {
        return nakliyeTipi;
    }

    public void setNakliyeTipi(String nakliyeTipi) {
        this.nakliyeTipi = nakliyeTipi;
    }

    public List<ZarfProducts> getProducts() {
        return products;
    }

    public void setProducts(List<ZarfProducts> products) {
        this.products = products;
    }
}
