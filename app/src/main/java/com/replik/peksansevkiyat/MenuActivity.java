package com.replik.peksansevkiyat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.replik.peksansevkiyat.DataClass.ModelDto.Label.ZarfLabel;
import com.replik.peksansevkiyat.DataClass.ModelDto.Label.ZarfProducts;
import com.replik.peksansevkiyat.Transection.GlobalVariable;
import com.replik.peksansevkiyat.Transection.PrintBluetooth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    Button btnMenuPalette, btnMenuOrder, btnShipment;
    ImageButton imgLogo, imgSettings;
    TextView txtUserName;
    Context context = MenuActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtUserName = (TextView) findViewById(R.id.txtUserName);
        txtUserName.setText(GlobalVariable.getUserName());

        imgSettings = findViewById(R.id.imgSettings);
        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SettingsActivity.class);
                startActivity(i);
            }
        });
        btnMenuPalette = (Button) findViewById(R.id.btnPaletteAdd);
        btnMenuPalette.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(context, PaletteActivity.class);
                startActivity(i);
            }
        });

        btnMenuOrder = (Button) findViewById(R.id.btnPaletteEdit);
        btnMenuOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(context, OrderActivity.class);
                startActivity(i);
            }
        });

        btnShipment = (Button) findViewById(R.id.btnShipment);
        btnShipment.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShipmentCustomerListActivity.class);
            startActivity(intent);

            /*try {
                PrintBluetooth printBluetooth = new PrintBluetooth();
                PrintBluetooth.printer_id = GlobalVariable.printerName;

                ZarfProducts product1 = new ZarfProducts("STK001", "Kırmızı", "5");
                ZarfProducts product2 = new ZarfProducts("STK002", "Mavi", "10");
                ZarfProducts product3 = new ZarfProducts("STK003", "Yeşil", "7");

                // Ürün listesini oluşturma
                List<ZarfProducts> products = new ArrayList<>();
                products.add(product1);
                products.add(product2);
                products.add(product3);

                // ZarfLabel örneği oluşturma
                ZarfLabel zarfLabel = new ZarfLabel(
                        "Mehmet Ali Demir",
                        "Barbaros Mahallesi, 23. Sokak, No: 12, Beşiktaş, İstanbul",
                        "Adana | Express Kargo",
                        products
                );

                printBluetooth.findBT();
                printBluetooth.openBT();
                printBluetooth.printTestTable(zarfLabel);
                printBluetooth.closeBT();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        });

        imgLogo = (ImageButton) findViewById(R.id.imgLogo);
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}