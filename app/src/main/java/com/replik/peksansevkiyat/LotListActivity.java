package com.replik.peksansevkiyat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.replik.peksansevkiyat.Adapter.LotAdapter;
import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.LotItem;
import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.RawMaterialItem;
import com.replik.peksansevkiyat.Interface.APIClient;
import com.replik.peksansevkiyat.Interface.APIInterface;
import com.replik.peksansevkiyat.Transection.GlobalVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LotListActivity extends AppCompatActivity implements LotAdapter.OnLotSelectedListener {
    private ImageButton imgLogo;
    private TextView txtUserName;
    private TextView txtStockCode;
    private EditText etSearch;
    private LinearLayout layoutSearch;
    private ProgressBar progressBar;
    private RecyclerView rvLots;
    private LotAdapter adapter;
    private APIInterface apiInterface;
    private String stockCode;
    private double stockAmount;
    private RawMaterialItem rawMaterialItem;

    private LinearLayout layoutAmountInput;
    private EditText etAmount;
    private MaterialButton btnAdd;
    private LotItem selectedLot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_list);

        stockCode = getIntent().getStringExtra("stockCode");
        stockAmount = getIntent().getDoubleExtra("stockAmount", 0.0);
        rawMaterialItem = new RawMaterialItem(stockCode, "", stockAmount);

        // Mevcut lotları al ve rawMaterialItem'a ekle
        ArrayList<LotItem> existingLots = (ArrayList<LotItem>) getIntent().getSerializableExtra("existingLots");
        if (existingLots != null) {
            for (LotItem lot : existingLots) {
                rawMaterialItem.addLot(lot);
            }
        }

        if (stockCode == null) {
            finish();
            return;
        }

        initializeViews();
        setupViews();

        // Mevcut lotların seri numaralarını adapter'a gönder
        if (existingLots != null) {
            List<String> existingSerialNumbers = new ArrayList<>();
            for (LotItem lot : existingLots) {
                existingSerialNumbers.add(lot.getSerialNumber());
            }
            adapter.setExistingSerialNumbers(existingSerialNumbers);
        }

        fetchLots("");
    }

    @Override
    public void onBackPressed() {
        // Activity kapanırken tüm lotları gönder
        Intent intent = new Intent();
        intent.putExtra("stockCode", stockCode);
        intent.putExtra("lots", new ArrayList<>(rawMaterialItem.getLots()));
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    private void initializeViews() {
        imgLogo = findViewById(R.id.iw_logo);
        txtUserName = findViewById(R.id.tw_username);
        txtStockCode = findViewById(R.id.tw_stock_code);
        etSearch = findViewById(R.id.et_search);
        layoutSearch = findViewById(R.id.layout_search);
        progressBar = findViewById(R.id.progress_bar);
        rvLots = findViewById(R.id.rv_lot_items);

        layoutAmountInput = findViewById(R.id.layout_amount_input);
        etAmount = findViewById(R.id.et_amount);
        btnAdd = findViewById(R.id.btn_add);

        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        
        rvLots.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LotAdapter();
        adapter.setOnLotSelectedListener(this);
        rvLots.setAdapter(adapter);
    }

    private void setupViews() {
        txtUserName.setText(GlobalVariable.getUserName());
        txtStockCode.setText(stockCode);
        imgLogo.setOnClickListener(v -> onBackPressed());

        if (etSearch != null) {
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    fetchLots(s.toString());
                }
            });

            etSearch.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard();
                    return true;
                }
                return false;
            });
        }

        etAmount.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard();
                return true;
            }
            return false;
        });

        btnAdd.setOnClickListener(v -> {
            if (selectedLot == null) {
                Toast.makeText(this, "Lütfen bir lot seçiniz", Toast.LENGTH_SHORT).show();
                return;
            }

            String amountStr = etAmount.getText().toString();
            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Lütfen miktar giriniz", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Geçersiz miktar formatı", Toast.LENGTH_SHORT).show();
                return;
            }

            if (amount <= 0) {
                Toast.makeText(this, "Miktar 0'dan büyük olmalıdır", Toast.LENGTH_SHORT).show();
                return;
            }

            // Girilen miktar lot'un miktarından büyük olamaz kontrolü
            if (amount > selectedLot.getAmount()) {
                Toast.makeText(this, String.format("En fazla %.2f Kg ekleyebilirsiniz (Lot miktarı)", selectedLot.getAmount()), Toast.LENGTH_SHORT).show();
                return;
            }

            // Kalan miktar kontrolü
            double remainingAmount = rawMaterialItem.getRemainingAmount();
            if (amount > remainingAmount) {
                Toast.makeText(this, String.format("En fazla %.2f Kg ekleyebilirsiniz (Kalan miktar)", remainingAmount), Toast.LENGTH_SHORT).show();
                return;
            }

            // Yeni lot oluştur (mevcut lot'un kopyası)
            LotItem newLot = new LotItem(selectedLot.getSerialNumber(), selectedLot.getLotNumber(), amount);
            rawMaterialItem.addLot(newLot);

            // Lot eklendikten sonra
            List<String> existingSerialNumbers = new ArrayList<>();
            for (LotItem lot : rawMaterialItem.getLots()) {
                existingSerialNumbers.add(lot.getSerialNumber());
            }
            adapter.setExistingSerialNumbers(existingSerialNumbers);

            // Debug için log ekleyelim
            double totalAmount = 0;
            for (LotItem lot : rawMaterialItem.getLots()) {
                totalAmount += lot.getAmount();
            }
            android.util.Log.d("LotListActivity", String.format("StockCode: %s, NewLotAmount: %.2f, TotalAmount: %.2f, StockAmount: %.2f, RemainingAmount: %.2f",
                stockCode, amount, totalAmount, stockAmount, rawMaterialItem.getRemainingAmount()));

            // Kalan miktar 0 ise sayfayı kapat
            if (Math.abs(rawMaterialItem.getRemainingAmount()) < 0.001) {
                Toast.makeText(this, "Stok miktarı tamamlandı", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                // Form'u kapat ve search'i göster
                hideKeyboard();
                selectedLot = null;
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            layoutSearch.setVisibility(View.VISIBLE);
            layoutAmountInput.setVisibility(View.GONE);
        }
    }

    private void showLoading(boolean show) {
        if (progressBar != null) {
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        if (rvLots != null) {
            rvLots.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void fetchLots(String search) {
        showLoading(true);
        apiInterface.getLotList(stockCode, search).enqueue(new Callback<List<LotItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<LotItem>> call, @NonNull Response<List<LotItem>> response) {
                showLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setLots(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<LotItem>> call, @NonNull Throwable t) {
                showLoading(false);
            }
        });
    }

    @Override
    public void onLotSelected(LotItem lot) {
        selectedLot = lot;
        layoutAmountInput.setVisibility(View.VISIBLE);
        layoutSearch.setVisibility(View.GONE);
        etAmount.setText(String.format(Locale.US, "%.2f", rawMaterialItem.getRemainingAmount()));
    }
} 