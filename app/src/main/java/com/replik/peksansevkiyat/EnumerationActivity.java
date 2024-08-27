package com.replik.peksansevkiyat;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

public class EnumerationActivity extends AppCompatActivity {

    Button buttonYes, buttonNo;

    // ürün ise seri okut
    // ürün değil ise selectbox aç

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enumeration);

        AtomicBoolean isProduct = new AtomicBoolean(true);

        buttonYes = (Button) findViewById(R.id.button_yes);
        buttonNo = (Button) findViewById(R.id.button_no);

        buttonYes.setOnClickListener(view -> {
            isProduct.set(true);

            setButtonDisable(buttonNo);
            setButtonEnable(buttonYes);
        });
        buttonNo.setOnClickListener(view -> {
            isProduct.set(false);

            setButtonDisable(buttonYes);
            setButtonEnable(buttonNo);
        });
    }

    void setButtonDisable(Button button) {
        button.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.muted)));
        button.setTextColor(getColor(R.color.white_light));
    }

    void setButtonEnable(Button button) {
        button.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.replik)));
        button.setTextColor(getColor(R.color.white));
    }
}