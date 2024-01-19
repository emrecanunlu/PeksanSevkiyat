package com.replik.peksansevkiyat.Interface;

import com.replik.peksansevkiyat.DataClass.ModelDto.ApkVersion;
import com.replik.peksansevkiyat.DataClass.ModelDto.Dtos.dtoPalletDetailAndSeritra_data;
import com.replik.peksansevkiyat.DataClass.ModelDto.Dtos.getStandartLong;
import com.replik.peksansevkiyat.DataClass.ModelDto.Order.OrderDtos;
import com.replik.peksansevkiyat.DataClass.ModelDto.Order.OrderList;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderDetail.OrderDetailList;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.OrderShippingList;
import com.replik.peksansevkiyat.DataClass.ModelDto.Pallet.PalletSingle;
import com.replik.peksansevkiyat.DataClass.ModelDto.Pallet.dtoPalletPrint;
import com.replik.peksansevkiyat.DataClass.ModelDto.PalletDetail.PalletDetailDtos;
import com.replik.peksansevkiyat.DataClass.ModelDto.PalletDetail.PalletDetailList;
import com.replik.peksansevkiyat.DataClass.ModelDto.Personel.PersonelList;
import com.replik.peksansevkiyat.DataClass.ModelDto.Result;
import com.replik.peksansevkiyat.DataClass.ModelDto.Seritra.spSeritraSingle;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("api/getApkVersion")
    Call<ApkVersion> getApkVersion();

    // --------------- GET
    @GET("api/getPersonels")
    Call<PersonelList> getUserList();

    @GET("api/getSeriControl/{staffId}/{seri}")
    Call<spSeritraSingle> getSeriControl(@Path("staffId") Number staffId, @Path("seri") String barcode);

    @GET("api/getSeriControlFromPallet/{staffId}/{seri}")
    Call<dtoPalletDetailAndSeritra_data> getSeriControlFromPallet(@Path("staffId") Number staffId, @Path("seri") String barcode);

    @GET("api/getPalletControlByID/{staffId}/{id}")
    Call<PalletSingle> getPalletControl(@Path("staffId") Number staffId, @Path("id") Integer Id);

    @GET("api/getPalletControl/{staffId}/{barcode}")
    Call<PalletSingle> getPalletControl(@Path("staffId") Number staffId, @Path("barcode") String barcode);

    @GET("api/getPalletDetail/{staffId}/{palletId}")
    Call<PalletDetailList> getPalletDetail(@Path("staffId") Number staffId, @Path("palletId") Number palletId);

    @GET("api/getOrderList/{staffId}")
    Call<OrderList> getOrderList(@Path("staffId") Number staffId, @Query("search") String search);

    @GET("api/getOrderDetailList/{staffId}/{sipNo}")
    Call<OrderDetailList> getOrderDetailList(@Path("staffId") Number staffId, @Path("sipNo") String sipNo);

    @GET("api/getPalletPrint/{staffId}")
    Call<dtoPalletPrint.PalletPrintList> getPalletPrint(@Path("staffId") Number staffId);

    @GET("api/getOrderShippingList/{staffId}")
    Call<OrderShippingList> getOrderShippingList(@Path("staffId") Number staffId, @Query("search") String search);

    // --------------- SET
    @POST("api/setPalletDetail")
    Call<PalletDetailList> setPalletDetail(@Body PalletDetailDtos.setPalletDetailColumn palletDetailColumn);

    @POST("api/setPalletPrint")
    Call<Result> setPalletPrint(@Body getStandartLong getStandartLong);

    @POST("api/setOrderCollectedByBarcode")
    Call<Result> setOrderCollectedByBarcode(@Body OrderDtos.setPickingItem pickingData);

    @POST("api/setOrderStatus")
    Call<Result> setOrderStatus(@Body OrderDtos.setOrderStatus data);

    // --------------- DELETE
    @HTTP(method = "DELETE", path = "api/delPalletDetail", hasBody = true)
    Call<PalletDetailList> delPalletDetail(@Body PalletDetailDtos.delPalletDetailColumn delPalletDetailColumn);

    @HTTP(method = "DELETE", path = "api/delPallet", hasBody = true)
    Call<Result> delPallet(@Body getStandartLong delStandartColumn);

    @HTTP(method = "DELETE", path = "api/delOrderPicking", hasBody = true)
    Call<Result> delOrderPicking(@Body OrderDtos.delPickingItem delPickingItem);
}
