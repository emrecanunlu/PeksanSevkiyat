package com.replik.peksansevkiyat.DataClass.ModelDto.Pallet;

import java.util.List;

public class PalletDetail {
    private String stokKod;
    private String yapkod;
    private List<String> products;

    public String getStokKod() {
        return stokKod;
    }

    public void setStokKod(String stokKod) {
        this.stokKod = stokKod;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String getYapkod() {
        return yapkod;
    }

    public void setYapkod(String yapkod) {
        this.yapkod = yapkod;
    }
}
