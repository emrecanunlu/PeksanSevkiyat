package com.replik.peksansevkiyat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ListAdapter.ListAdapter_Order;
import com.replik.peksansevkiyat.DataClass.ListAdapter.ListenerInterface;
import com.replik.peksansevkiyat.DataClass.ModelDto.Order.Order;
import com.replik.peksansevkiyat.DataClass.ModelDto.Order.OrderList;
import com.replik.peksansevkiyat.Interface.APIClient;
import com.replik.peksansevkiyat.Interface.APIInterface;
import com.replik.peksansevkiyat.Transection.Alert;
import com.replik.peksansevkiyat.Transection.Dialog;
import com.replik.peksansevkiyat.Transection.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity implements ListenerInterface.OrderListener {
    APIInterface apiInterface;
    ProgressDialog nDialog;
    AlertDialog alert;

    Context context = OrderActivity.this;

    View pBar;

    ImageButton imgLogo, imgSettings;
    TextView txtUserName, txtSearch;
    RecyclerView lstData;

    List<Order> lst = new ArrayList<>();

    LinearLayoutManager layoutManager;
    ListAdapter_Order adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        nDialog = Dialog.getDialog(context, getString(R.string.loading));

        pBar = findViewById(R.id.pnlProgressBar);
        txtUserName = findViewById(R.id.txtUserName);
        txtUserName.setText(GlobalVariable.getUserName());
        lstData = findViewById(R.id.lstData);
        layoutManager = new LinearLayoutManager(context);
        lstData.setLayoutManager(layoutManager);
        adapter = new ListAdapter_Order(lst, this);
        lstData.setAdapter(adapter);

        fnGetData("");

        imgSettings = findViewById(R.id.imgSettings);
        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnGetData(txtSearch.getText().toString());
                /*Intent i = new Intent(context, SettingsActivity.class);
                startActivity(i);*/
            }
        });

        imgLogo = findViewById(R.id.imgLogo);
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtSearch = findViewById(R.id.txtSearch);
        txtSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP) {
                    lst = new ArrayList<>();
                    fnGetData(txtSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    void fnGetData(String search) {
        pBar.setVisibility(View.VISIBLE);
        apiInterface.getOrderList(GlobalVariable.getUserId(), search).enqueue(new Callback<OrderList>() {
            @Override
            public void onResponse(Call<OrderList> call, Response<OrderList> response) {
                pBar.setVisibility(View.GONE);
                if (response.body().getSuccess())
                    adapter.setData(response.body().getOrder());
                else {
                    alert = Alert.getAlert(context, getString(R.string.error), response.body().getMessage());
                    alert.show();
                }
            }

            @Override
            public void onFailure(Call<OrderList> call, Throwable t) {
                pBar.setVisibility(View.GONE);
                alert = Alert.getAlert(context, getString(R.string.error), t.getMessage());
                alert.show();
            }
        });
    }

    @Override
    public void onItemCliked(Order order) {
        GlobalVariable.setSelectedOrder(order);

        Intent i = new Intent(context, OrderDetailActivity.class);
        startActivity(i);
    }
}