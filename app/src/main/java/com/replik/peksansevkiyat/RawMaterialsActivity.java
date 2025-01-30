package com.replik.peksansevkiyat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.replik.peksansevkiyat.Adapter.RawMaterialAdapter;
import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.LotItem;
import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.RawMaterialItem;
import com.replik.peksansevkiyat.Transection.GlobalVariable;

import java.util.ArrayList;

public class RawMaterialsActivity extends AppCompatActivity implements RawMaterialAdapter.OnItemClickListener {
    private ImageButton imgLogo;
    private TextView txtUserName, txtMenuName;
    private MaterialButton btnAddNew;
    private MaterialButton btnTransfer;
    private RecyclerView rvRawMaterials;
    private RawMaterialAdapter adapter;

    private final ActivityResultLauncher<Intent> stockListLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    ArrayList<String> stockNames = data.getStringArrayListExtra("stockNames");
                    ArrayList<String> stockCodes = data.getStringArrayListExtra("stockCodes");
                    double[] amounts = data.getDoubleArrayExtra("amounts");

                    if (stockNames != null && stockCodes != null && amounts != null) {
                        for (int i = 0; i < stockCodes.size(); i++) {
                            RawMaterialItem item = new RawMaterialItem(
                                stockCodes.get(i),
                                stockNames.get(i),
                                amounts[i]
                            );
                            adapter.addItem(item);
                        }
                        checkTransferButton();
                    }
                }
            });

    private final ActivityResultLauncher<Intent> lotListLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String stockCode = result.getData().getStringExtra("stockCode");
                    ArrayList<LotItem> lots = (ArrayList<LotItem>) result.getData().getSerializableExtra("lots");

                    // Stok koduna göre ilgili RawMaterialItem'ı bul
                    RawMaterialItem item = adapter.findItemByStockCode(stockCode);
                    if (item != null && lots != null) {
                        // Önceki lotları temizle ve yeni lotları ekle
                        item.getLots().clear();
                        for (LotItem lot : lots) {
                            item.addLot(lot);
                        }
                        adapter.notifyDataSetChanged();
                        checkTransferButton();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_materials);

        initializeViews();
        setupViews();
    }

    private void initializeViews() {
        imgLogo = findViewById(R.id.iw_logo);
        txtUserName = findViewById(R.id.tw_username);
        txtMenuName = findViewById(R.id.tw_menu);
        btnAddNew = findViewById(R.id.btn_add_new);
        btnTransfer = findViewById(R.id.btn_transfer);
        rvRawMaterials = findViewById(R.id.rv_raw_materials);

        rvRawMaterials.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RawMaterialAdapter();
        adapter.setOnItemClickListener(this);
        rvRawMaterials.setAdapter(adapter);
    }

    private void setupViews() {
        txtUserName.setText(GlobalVariable.getUserName());
        imgLogo.setOnClickListener(v -> onBackPressed());
        btnAddNew.setOnClickListener(v -> {
            Intent intent = new Intent(this, RawMaterialStockListActivity.class);
            intent.putStringArrayListExtra("existingStockCodes", new ArrayList<>(adapter.getStockCodes()));
            stockListLauncher.launch(intent);
        });

        btnTransfer.setOnClickListener(v -> {
            // TODO: Transfer işlemini burada yapacağız
            Toast.makeText(this, "Transfer işlemi başlatılıyor...", Toast.LENGTH_SHORT).show();
        });
    }

    private void checkTransferButton() {
        if (adapter.getItemCount() == 0) {
            btnTransfer.setVisibility(View.GONE);
            return;
        }

        boolean allComplete = true;
        for (RawMaterialItem item : adapter.getItems()) {
            double totalLotAmount = 0;
            for (LotItem lot : item.getLots()) {
                totalLotAmount += lot.getAmount();
            }
            if (Math.abs(totalLotAmount - item.getAmount()) >= 0.001) {
                allComplete = false;
                break;
            }
        }

        btnTransfer.setVisibility(allComplete ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onRawMaterialSelected(RawMaterialItem item) {
        Intent intent = new Intent(this, LotListActivity.class);
        intent.putExtra("stockCode", item.getStockCode());
        intent.putExtra("stockAmount", item.getAmount());
        intent.putExtra("existingLots", new ArrayList<>(item.getLots()));
        lotListLauncher.launch(intent);
    }
} 