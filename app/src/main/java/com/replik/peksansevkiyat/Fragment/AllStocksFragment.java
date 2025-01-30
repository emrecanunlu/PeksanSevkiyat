package com.replik.peksansevkiyat.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.replik.peksansevkiyat.Adapter.StockAdapter;
import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.StockItem;
import com.replik.peksansevkiyat.Interface.APIClient;
import com.replik.peksansevkiyat.Interface.APIInterface;
import com.replik.peksansevkiyat.R;
import com.replik.peksansevkiyat.RawMaterialStockListActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllStocksFragment extends Fragment implements StockAdapter.OnStockSelectedListener {
    private RecyclerView rvStockItems;
    private StockAdapter adapter;
    private APIInterface apiInterface;
    private LinearLayout layoutAmountInput;
    private EditText etAmount;
    private MaterialButton btnAdd;
    private StockItem selectedStock;
    private ArrayList<String> existingStockCodes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_list, container, false);
        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        
        if (getActivity() != null && getActivity().getIntent() != null) {
            existingStockCodes = getActivity().getIntent().getStringArrayListExtra("existingStockCodes");
        }
        if (existingStockCodes == null) {
            existingStockCodes = new ArrayList<>();
        }

        initializeViews(view);
        setupViews();
        fetchStocks();
        return view;
    }

    private void initializeViews(View view) {
        rvStockItems = view.findViewById(R.id.rv_stock_items);
        layoutAmountInput = view.findViewById(R.id.layout_amount_input);
        etAmount = view.findViewById(R.id.et_amount);
        btnAdd = view.findViewById(R.id.btn_add);

        rvStockItems.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StockAdapter(existingStockCodes);
        rvStockItems.setAdapter(adapter);
    }

    private void setupViews() {
        adapter.setOnStockSelectedListener(this);
        
        etAmount.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard();
                return true;
            }
            return false;
        });

        btnAdd.setOnClickListener(v -> {
            if (selectedStock != null && etAmount.getText() != null) {
                String amountStr = etAmount.getText().toString();
                if (!amountStr.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);
                    hideKeyboard();
                    returnResult(amount);
                }
            }
        });
    }

    private void returnResult(double amount) {
        if (getActivity() != null && selectedStock != null) {
            Intent intent = new Intent();
            intent.putExtra("stockName", selectedStock.getStockName());
            intent.putExtra("stockCode", selectedStock.getStockCode());
            intent.putExtra("amount", amount);
            
            // Sonucu aktiviteye ekle
            ((RawMaterialStockListActivity) getActivity()).addResult(intent);
            
            // Eklenen stok kodunu listeye ekle
            existingStockCodes.add(selectedStock.getStockCode());
            
            // Seçimi temizle ve formu gizle
            selectedStock = null;
            layoutAmountInput.setVisibility(View.GONE);
            etAmount.setText("");
            adapter.clearSelection();
            
            // Adapter'ı güncelle
            adapter.setExistingStockCodes(existingStockCodes);
        }
    }

    private void hideKeyboard() {
        if (getContext() != null && etAmount != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etAmount.getWindowToken(), 0);
            etAmount.clearFocus();
        }
    }

    private void showKeyboard() {
        if (getContext() != null && etAmount != null) {
            etAmount.requestFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(etAmount, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void fetchStocks() {
        apiInterface.getStockList().enqueue(new Callback<List<StockItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<StockItem>> call, @NonNull Response<List<StockItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setStocks(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<StockItem>> call, @NonNull Throwable t) {
                // Hata durumunda yapılacak işlemler
            }
        });
    }

    @Override
    public void onStockSelected(StockItem stock) {
        selectedStock = stock;
        layoutAmountInput.setVisibility(View.VISIBLE);
        etAmount.setText("");
        showKeyboard();
    }
} 
