package com.replik.peksansevkiyat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ListAdapter.ListAdapter_OrderShipping;
import com.replik.peksansevkiyat.DataClass.ListAdapter.ListenerInterface;
import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.Customer;
import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.CustomerOrder;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.OrderShipping;
import com.replik.peksansevkiyat.Transection.GlobalVariable;
import com.replik.peksansevkiyat.Transection.Voids;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ShipmentOrderFinish extends AppCompatActivity implements ListenerInterface.OrderShippingListener {

    ImageButton logoImageButton, settingsImageButton;
    TextView staffName, sevkNoTextView, customerNameTextView, sevkTarihTextView;
    Customer customer;
    RecyclerView recyclerView;
    List<OrderShipping> orderShippingList = new ArrayList<>();
    ListAdapter_OrderShipping listAdapter;
    CustomerOrder order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_order_finish);

        if (GlobalVariable.getCustomerOrderDetails() == null) {
            finish();
        }

        Intent intent = getIntent();

        customer = (Customer) intent.getSerializableExtra("customer");
        order = (CustomerOrder) intent.getSerializableExtra("order");

        logoImageButton = (ImageButton) findViewById(R.id.imgLogo);
        settingsImageButton = (ImageButton) findViewById(R.id.imgSettings);
        recyclerView = (RecyclerView) findViewById(R.id.lstData);
        staffName = (TextView) findViewById(R.id.txtUserName);
        sevkNoTextView = (TextView) findViewById(R.id.txtSevkNo);
        sevkTarihTextView = (TextView) findViewById(R.id.txtSevkTarih);
        customerNameTextView = (TextView) findViewById(R.id.txtCari);

        staffName.setText(GlobalVariable.getUserName());

        sevkNoTextView.setText(order.getSevkNo());
        sevkTarihTextView.setText(Voids.formatDate(order.getShipmentDate()));
        customerNameTextView.setText(customer.getFullName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        listAdapter = new ListAdapter_OrderShipping(orderShippingList, this);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(layoutManager);

        settingsImageButton.setOnClickListener(v -> {
            Intent i = new Intent(this, SettingsActivity.class);

            startActivity(i);
        });

        logoImageButton.setOnClickListener(v -> {
            GlobalVariable.setCustomerOrderDetails(null);
            finish();
        });
    }

    @Override
    public void onItemCliked(OrderShipping orderShipping) {

    }
}