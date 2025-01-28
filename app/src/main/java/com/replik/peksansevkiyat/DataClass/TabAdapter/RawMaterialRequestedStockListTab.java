package com.replik.peksansevkiyat.DataClass.TabAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ListAdapter.ListAdapter_Stock;
import com.replik.peksansevkiyat.DataClass.ListAdapter.ListenerInterface;
import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.StockItem;
import com.replik.peksansevkiyat.Interface.APIClient;
import com.replik.peksansevkiyat.Interface.APIInterface;
import com.replik.peksansevkiyat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RawMaterialRequestedStockListTab extends Fragment implements ListenerInterface.RawMaterialListener {

    APIInterface apiInterface;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ListAdapter_Stock adapter;
    List<StockItem> stockList = new ArrayList<StockItem>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.raw_material_requested_stock_list_tab, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        apiInterface = APIClient.getRetrofit().create(APIInterface.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

        loadStockList();

        return view;
    }

    void loadStockList() {
        progressBar.setVisibility(View.VISIBLE);


        apiInterface.getRequstedStockList().enqueue(new Callback<List<StockItem>>() {
            @Override
            public void onResponse(Call<List<StockItem>> call, Response<List<StockItem>> response) {
                if (response.isSuccessful()) {
                    adapter = new ListAdapter_Stock(response.body(), RawMaterialRequestedStockListTab.this);
                    recyclerView.setAdapter(adapter);
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<StockItem>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(StockItem stockItem) {

    }
}
