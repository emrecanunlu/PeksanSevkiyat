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
            /*Intent intent = new Intent(context, ShipmentCustomerListActivity.class);
            startActivity(intent);*/

            try {
                PrintBluetooth printBluetooth = new PrintBluetooth();
                PrintBluetooth.printer_id = GlobalVariable.printerName;

                List<ZarfProducts> products = new ArrayList<>();

                products.add(new ZarfProducts("029.020CSP42B", "Beyaz01/Sari01/-", "25"));
                products.add(new ZarfProducts("030.010ABC123", "Kirmizi02/Mavi01/Siyah03", "30"));
                products.add(new ZarfProducts("031.005XYZ789", "Yesil01/-/-", "15"));
                products.add(new ZarfProducts("032.015PQR456", "Mor01/Beyaz02/-", "18"));
                products.add(new ZarfProducts("033.008DEF321", "Sari01/Siyah02/-", "22"));
                products.add(new ZarfProducts("034.025GHI654", "Beyaz01/Kahverengi01/-", "27"));
                products.add(new ZarfProducts("035.012JKL987", "Mavi02/-/-", "19"));
                products.add(new ZarfProducts("036.018MNO852", "Kahverengi01/Siyah01/-", "23"));
                products.add(new ZarfProducts("037.022UVW369", "Sari02/Mor01/-", "21"));
                products.add(new ZarfProducts("038.011RST246", "Beyaz01/-/-", "16"));
                products.add(new ZarfProducts("039.007OPQ135", "Kirmizi01/Mavi02/-", "20"));
                products.add(new ZarfProducts("040.019FGH753", "Yesil01/Beyaz01/-", "24"));
                products.add(new ZarfProducts("041.016IJK582", "Siyah01/Kirmizi01/-", "17"));
                products.add(new ZarfProducts("042.024LMN951", "Beyaz02/Siyah02/-", "28"));
                products.add(new ZarfProducts("043.013XYZ468", "Mavi01/Kirmizi01/-", "14"));
                products.add(new ZarfProducts("044.017OPQ279", "Mor01/Beyaz01/-", "26"));
                products.add(new ZarfProducts("045.021UVW864", "Sari01/Kahverengi01/-", "29"));
                products.add(new ZarfProducts("046.023RST573", "Beyaz01/Siyah01/-", "31"));
                products.add(new ZarfProducts("047.014FGH186", "Kahverengi01/Mavi01/-", "32"));
                products.add(new ZarfProducts("048.006IJK952", "Siyah01/Mor01/-", "33"));

                // ZarfLabel örneği oluşturma
                ZarfLabel zarfLabel = new ZarfLabel(
                        "Johnathan Smith",
                        "1234 Elm Street, Apartment 56, Springfield, IL 62704, United States",
                        "Standard",
                        "Standard delivery to Johnathan Smith's residence at 1234 Elm St, Apt 56, Springfield, IL 62704, USA.",
                        "0000000001",
                        products
                );

                printBluetooth.findBT();
                printBluetooth.openBT();
                printBluetooth.printZarfHeader(zarfLabel);
                printBluetooth.printZarfTable(zarfLabel.getProducts());
                printBluetooth.closeBT();
            } catch (IOException e) {
                e.printStackTrace();
            }
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