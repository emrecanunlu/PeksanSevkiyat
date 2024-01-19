package com.replik.peksansevkiyat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.replik.peksansevkiyat.Transection.GlobalVariable;

public class PaletteActivity extends AppCompatActivity {
    ImageButton imgLogo, imgSettings;
    TextView txtUserName;
    Button btnPaletteAdd, btnPaletteEdit, btnPalettePrint;

    Context context = PaletteActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

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

        imgLogo = (ImageButton) findViewById(R.id.imgLogo);
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnPaletteAdd = findViewById(R.id.btnPaletteAdd);
        btnPaletteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PaletteActivity.this, PaletteAdd.class);
                startActivity(i);
            }
        });

        btnPaletteEdit = findViewById(R.id.btnPaletteEdit);
        btnPaletteEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PaletteActivity.this, PaletteEdit.class);
                startActivity(i);
            }
        });

        btnPalettePrint = findViewById(R.id.btnPalettePrint);
        btnPalettePrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PaletteActivity.this, PalettePrint.class);
                startActivity(i);
            }
        });
    }
}