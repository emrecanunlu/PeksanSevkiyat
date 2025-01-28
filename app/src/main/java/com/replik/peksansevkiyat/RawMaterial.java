package com.replik.peksansevkiyat;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.replik.peksansevkiyat.DataClass.TabAdapter.RawMaterialTabAdapter;
import com.replik.peksansevkiyat.Transection.GlobalVariable;

public class RawMaterial extends AppCompatActivity {

    TextView userNameTextView;
    ImageButton headerBrandLogo;
    ViewPager2 viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_material);

        userNameTextView = (TextView) findViewById(R.id.txtUserName);
        viewPager = (ViewPager2) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        headerBrandLogo = (ImageButton) findViewById(R.id.imgLogo);

        RawMaterialTabAdapter tabAdapter = new RawMaterialTabAdapter(this);
        viewPager.setAdapter(tabAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("S. Kodları");
                    break;
                case 1:
                    tab.setText("Talep Edilen S. Kodları");
                    break;
            }
        }).attach();

        userNameTextView.setText(GlobalVariable.getUserName());

        headerBrandLogo.setOnClickListener(v -> {
            finish();
        });
    }
}