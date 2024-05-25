package com.replik.peksansevkiyat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ListAdapter.ListAdapter_Customer;
import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.Customer;
import com.replik.peksansevkiyat.Interface.APIInterface;
import com.replik.peksansevkiyat.Transection.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

public class ShipmentOrderListActivity extends AppCompatActivity {

    Customer customer;
    TextView staffNameTextView, customerNameTextView, customerCodeTextView;
    ImageView logoImageView, settingsImageView;
    Context context = ShipmentOrderListActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_order_list);
        Intent intent = getIntent();

        customer = (Customer) intent.getSerializableExtra("customer");

        settingsImageView = (ImageView) findViewById(R.id.imgSettings);
        logoImageView = (ImageView) findViewById(R.id.imgLogo);
        staffNameTextView = (TextView) findViewById(R.id.txtUserName);
        customerNameTextView = (TextView) findViewById(R.id.customer_fullname);
        customerCodeTextView = (TextView) findViewById(R.id.customer_code);

        staffNameTextView.setText(GlobalVariable.getUserName());

        customerNameTextView.setText(customer.getFullName());
        customerCodeTextView.setText(customer.getCode().toString());

        logoImageView.setOnClickListener(v -> {
            finish();
        });

        settingsImageView.setOnClickListener(v -> {
            Intent i = new Intent(context, SettingsActivity.class);
            startActivity(i);
        });
    }
}