package com.replik.peksansevkiyat.Interface;

import com.replik.peksansevkiyat.Transection.GlobalVariable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GlobalVariable.getApiUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
