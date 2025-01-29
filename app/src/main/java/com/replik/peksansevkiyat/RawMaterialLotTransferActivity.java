package com.replik.peksansevkiyat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ListAdapter.ListAdapter_RawMaterial_Lot;
import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.StockItem;
import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.StockLot;
import com.replik.peksansevkiyat.Interface.APIClient;
import com.replik.peksansevkiyat.Interface.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RawMaterialLotTransferActivity extends AppCompatActivity {

    TextView stockNameTextView, stockCodeTextView;
    ImageView closeButton;
    APIInterface apiInterface;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<StockLot> stockLotList = new ArrayList<>();
    ListAdapter_RawMaterial_Lot adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_material_lot_transfer);

        Intent intent = getIntent();

        String stockCode = intent.getStringExtra("stockCode");
        String stockName = intent.getStringExtra("stockName");

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        stockNameTextView = (TextView) findViewById(R.id.stock_name);
        stockCodeTextView = (TextView) findViewById(R.id.stock_code);
        closeButton = (ImageButton) findViewById(R.id.close_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stockNameTextView.setText(stockName);
        stockCodeTextView.setText(stockCode);

        loadLotList(stockCode);

        closeButton.setOnClickListener(v -> finish());
    }

    void loadLotList(String stockCode) {
        progressBar.setVisibility(View.VISIBLE);

        apiInterface.getStockLotList(stockCode)
                .enqueue(new Callback<List<StockLot>>() {
                    @Override
                    public void onResponse(Call<List<StockLot>> call, Response<List<StockLot>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            stockLotList.clear();
                            stockLotList.addAll(response.body());

                            adapter = new ListAdapter_RawMaterial_Lot(stockLotList);
                            recyclerView.setAdapter(adapter);
                        }

                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<List<StockLot>> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }
}