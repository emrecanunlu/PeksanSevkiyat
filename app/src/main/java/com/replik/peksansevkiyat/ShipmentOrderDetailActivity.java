package com.replik.peksansevkiyat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ListAdapter.ListAdapter_Customer_Order_Detail;
import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.Customer;
import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.CustomerOrder;
import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.CustomerOrderDetail;
import com.replik.peksansevkiyat.Interface.APIClient;
import com.replik.peksansevkiyat.Interface.APIInterface;
import com.replik.peksansevkiyat.Transection.GlobalVariable;
import com.replik.peksansevkiyat.Transection.Voids;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipmentOrderDetailActivity extends AppCompatActivity {

    ImageView logoImageView, printImageView;
    APIInterface apiInterface;
    TextView staffNameTextView, sipNoTextView, customerTextView, shippingNameTextView, deliveryNameTextView, deliveryAddressTextView, deliveryDate;
    ListAdapter_Customer_Order_Detail listAdapter;
    RecyclerView recyclerView;
    Context context = ShipmentOrderDetailActivity.this;
    List<CustomerOrderDetail> customerOrderDetailList = new ArrayList<>();
    Customer customer;
    CustomerOrder order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_order_detail);

        Intent intent = getIntent();

        customer = (Customer) intent.getSerializableExtra("customer");
        order = (CustomerOrder) intent.getSerializableExtra("order");

        apiInterface = APIClient.getRetrofit().create(APIInterface.class);

        recyclerView = (RecyclerView) findViewById(R.id.customer_order_detail_list_recycler_view);
        logoImageView = (ImageView) findViewById(R.id.imgLogo);
        printImageView = (ImageView) findViewById(R.id.imgPrint);
        staffNameTextView = (TextView) findViewById(R.id.txtUserName);
        sipNoTextView = (TextView) findViewById(R.id.txtSipNo);
        customerTextView = (TextView) findViewById(R.id.txtCari);
        shippingNameTextView = (TextView) findViewById(R.id.txtNakliye);
        deliveryNameTextView = (TextView) findViewById(R.id.txtTeslimAdi);
        deliveryAddressTextView = (TextView) findViewById(R.id.txtTeslimAdresi);
        deliveryDate = (TextView) findViewById(R.id.txtSevkTarih);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        listAdapter = new ListAdapter_Customer_Order_Detail(customerOrderDetailList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(layoutManager);

        sipNoTextView.setText(order.getSevkNo());
        staffNameTextView.setText(GlobalVariable.getUserName());

        customerTextView.setText(customer.getFullName());

        shippingNameTextView.setText(order.getNakliyeTipi());

        deliveryDate.setText(Voids.formatDate(order.getShipmentDate()));
        deliveryNameTextView.setText(order.getTeslimAdi());
        deliveryAddressTextView.setText(order.getTeslimAdresi());

        printImageView.setOnClickListener(v -> print());

        logoImageView.setOnClickListener(v -> {
            finish();
        });

        fetchOrderDetailList();
    }

    void fetchOrderDetailList() {
        apiInterface.getOrderDetail(order.getSevkNo()).enqueue(
                new Callback<List<CustomerOrderDetail>>() {
                    @Override
                    public void onResponse(Call<List<CustomerOrderDetail>> call, Response<List<CustomerOrderDetail>> response) {
                        if (response.isSuccessful()) {
                            customerOrderDetailList = response.body();

                            listAdapter.setList(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CustomerOrderDetail>> call, Throwable t) {

                    }
                }
        );
    }

    void print() {
        Toast.makeText(context, "Print Callback", Toast.LENGTH_SHORT).show();
    }
}