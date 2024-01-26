package com.replik.peksansevkiyat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ListAdapter.ListAdapter_OrderShipping;
import com.replik.peksansevkiyat.DataClass.ListAdapter.ListenerInterface;
import com.replik.peksansevkiyat.DataClass.ModelDto.Order.OrderDtos;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.OrderShipping;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.OrderShippingList;
import com.replik.peksansevkiyat.DataClass.ModelDto.Result;
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

public class OrderFinishActivity extends AppCompatActivity implements ListenerInterface.OrderShippingListener {
    APIInterface apiInterface;
    ProgressDialog nDialog;
    AlertDialog alert;

    Context context = OrderFinishActivity.this;

    View pBar;
    ConstraintLayout pnlSearch,pnlAddShipping;
    ScrollView pnlManualEntry;

    ImageButton imgLogo, imgSettings;
    TextView txtUserName, txtSearch, txtSipNo, txtSipCari, txtSipTarih;
    TextView txtOrderPlaka, txtOrderVergiNo, txtOrderIsim, txtOrderIlce, txtOrderIl, txtOrderUlke, txtOrderPosta, txtOrderSoforAd, txtOrderSoforSoyad, txtOrderSoforTc, txtOrderAciklama, txtOrderDorse;
    Button btnAddShipping, btnOrderFinish;

    RecyclerView lstData;
    List<OrderShipping> lst = new ArrayList<>();
    LinearLayoutManager layoutManager;
    ListAdapter_OrderShipping adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_finish);

        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        nDialog = Dialog.getDialog(context, getString(R.string.loading));

        pBar = findViewById(R.id.pnlProgressBar);
        pnlAddShipping = findViewById(R.id.pnlAddShipping);
        pnlSearch = findViewById(R.id.pnlSearch);
        pnlManualEntry = findViewById(R.id.pnlManualEntry);

        txtUserName = findViewById(R.id.txtUserName);
        txtUserName.setText(GlobalVariable.getUserName());

        lstData = findViewById(R.id.lstData);
        layoutManager = new LinearLayoutManager(context);
        lstData.setLayoutManager(layoutManager);
        adapter = new ListAdapter_OrderShipping(lst, this);
        lstData.setAdapter(adapter);

        txtSipNo = findViewById(R.id.txtSipNo);
        txtSipNo.setText(GlobalVariable.getSelectedOrder().getSipNo());
        txtSipCari = findViewById(R.id.txtSipCari);
        txtSipCari.setText(GlobalVariable.getSelectedOrder().getCari());
        txtSipTarih = findViewById(R.id.txtSipTarih);
        txtSipTarih.setText(GlobalVariable.getSelectedOrder().getTarih());

        txtOrderPlaka = findViewById(R.id.txtOrderPlaka);
        txtOrderVergiNo = findViewById(R.id.txtOrderVergiNo);
        txtOrderIsim = findViewById(R.id.txtOrderIsim);
        txtOrderIlce = findViewById(R.id.txtOrderIlce);
        txtOrderIl = findViewById(R.id.txtOrderIl);
        txtOrderUlke = findViewById(R.id.txtOrderUlke);
        txtOrderPosta = findViewById(R.id.txtOrderPosta);
        txtOrderSoforAd = findViewById(R.id.txtOrderSoforAd);
        txtOrderSoforSoyad = findViewById(R.id.txtOrderSoforSoyad);
        txtOrderSoforTc = findViewById(R.id.txtOrderSoforTc);
        txtOrderAciklama = findViewById(R.id.txtOrderAciklama);
        txtOrderDorse = findViewById(R.id.txtOrderDorse1);

        pnlAddShipping.setVisibility(View.GONE);
        fnGetShippingList("");

        imgSettings = findViewById(R.id.imgSettings);
        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SettingsActivity.class);
                startActivity(i);
            }
        });

        imgLogo = findViewById(R.id.imgLogo);
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { fnBackDetail(); }
        });

        txtSearch = findViewById(R.id.txtSearch);
        txtSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_UP){
                    lst = new ArrayList<>();
                    fnGetShippingList(txtSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });

        btnAddShipping = findViewById(R.id.btnAddShipping);
        btnAddShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nDialog.show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(getString(R.string.sure));
                builder.setMessage(getString(R.string.question_order_shipping_add));
                builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nDialog.dismiss();
                    }
                });
                builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nDialog.dismiss();
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        pnlManualEntry.setVisibility(View.VISIBLE);
                        pnlSearch.setVisibility(View.GONE);
                    }
                });
                builder.show();
            }
        });

        btnOrderFinish = findViewById(R.id.btnOrderFinish);
        btnOrderFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ErrorMessage = "";
                int renkE = Color.rgb(240, 80, 80);
                if(txtOrderPlaka.getText().toString().isEmpty()) {
                    txtOrderPlaka.setHintTextColor(renkE);
                    ErrorMessage += getString(R.string.order_plaka);
                }

                if(txtOrderVergiNo.getText().toString().isEmpty()) {
                    txtOrderVergiNo.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_vergino);
                }

                if(txtOrderIsim.getText().toString().isEmpty()) {
                    txtOrderIsim.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_isim);
                }

                if(txtOrderIlce.getText().toString().isEmpty()) {
                    txtOrderIlce.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_ilce);
                }

                if(txtOrderIl.getText().toString().isEmpty()) {
                    txtOrderIl.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_il);
                }

                if(txtOrderIl.getText().toString().isEmpty()) {
                    txtOrderIl.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_il);
                }

                if(txtOrderUlke.getText().toString().isEmpty()) {
                    txtOrderUlke.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_ulke);
                }

                if(txtOrderPosta.getText().toString().isEmpty()) {
                    txtOrderPosta.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_posta);
                }

                if(txtOrderSoforAd.getText().toString().isEmpty()) {
                    txtOrderSoforAd.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_sofor_ad);
                }

                if(txtOrderSoforSoyad.getText().toString().isEmpty()) {
                    txtOrderSoforSoyad.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_sofor_soyad);
                }

                if(txtOrderSoforTc.getText().toString().isEmpty()) {
                    txtOrderSoforTc.setHintTextColor(renkE);
                    ErrorMessage += (!ErrorMessage.isEmpty() ? "\n" : "") + getString(R.string.order_sofor_tc);
                }

                if(ErrorMessage.isEmpty()) {
                    OrderShipping os = new OrderShipping();
                    os.setOrderShipping(
                            txtOrderPlaka.getText().toString(),
                            txtOrderVergiNo.getText().toString(),
                            txtOrderIsim.getText().toString(),
                            txtOrderIlce.getText().toString(),
                            txtOrderIl.getText().toString(),
                            txtOrderUlke.getText().toString(),
                            txtOrderPosta.getText().toString(),
                            txtOrderSoforAd.getText().toString(),
                            txtOrderSoforAd.getText().toString(),
                            txtOrderAciklama.getText().toString(),
                            txtOrderSoforTc.getText().toString(),
                            txtOrderDorse.getText().toString());
                    fnOrderFinish(new OrderDtos.setOrderStatus(GlobalVariable.getSelectedOrder().getSipNo(), os));
                }
                else {
                    alert = Alert.getAlert(context,getString(R.string.error), ErrorMessage + "\n" + getString(R.string.not_empty));
                    alert.show();
                }
            }
        });
    }

    void fnOrderFinish(OrderDtos.setOrderStatus data) {
        nDialog.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(getString(R.string.sure));
        builder.setMessage(getString(R.string.question_order_finish));
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nDialog.dismiss();
            }
        });
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                apiInterface.setOrderStatus(data).enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        nDialog.dismiss();

                        if(response.body().getSuccess()) {

                            String IrsNo = response.body().getMessage().replace("|","-").split("-")[0].toString();
                            String GibIrsNo = response.body().getMessage().replace("|","-").split("-")[1].toString();

                            //HT: işlemler bölündü o yüzden bu işlemin ardından taslak ve print işlemlerine ayrı ayrı post at
                            // ----- TASLAK OLUSTUR
                            nDialog.show();
                            apiInterface.setNetsisEIrsPacked(IrsNo).enqueue(new Callback<Result>() {
                                @Override
                                public void onResponse(Call<Result> call, Response<Result> response) {
                                    nDialog.dismiss();

                                    if(response.body().getSuccess()) {
                                        // ---- YAZDIRMA GÖNDER
                                        nDialog.show();
                                        apiInterface.setNetsisPrint(GibIrsNo).enqueue(new Callback<Result>() {
                                            @Override
                                            public void onResponse(Call<Result> call, Response<Result> response) {
                                                nDialog.dismiss();

                                                finish();

                                                Intent ii = new Intent(context, OrderActivity.class);
                                                startActivity(ii);

                                                if(response.body().getSuccess()) {
                                                    alert = Alert.getAlert(context,getString(R.string.info), response.body().getMessage());
                                                    alert.show();
                                                }
                                                else {
                                                    alert = Alert.getAlert(context,getString(R.string.error),response.body().getMessage());
                                                    alert.show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Result> call, Throwable t) {
                                                nDialog.dismiss();
                                                alert = Alert.getAlert(context, getString(R.string.error), t.getMessage());
                                                alert.show();
                                            }
                                        });
                                    }
                                    else {
                                        alert = Alert.getAlert(context,getString(R.string.error),response.body().getMessage());
                                        alert.show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Result> call, Throwable t) {
                                    /// HT:TimeOut sorunu çözülene kadar geçici olarak timeout yazsa bile sistemi yazdırmaya yönlendir yapıyor
                                    // ---- YAZDIRMA GÖNDER
                                    nDialog.show();
                                    apiInterface.setNetsisPrint(GibIrsNo).enqueue(new Callback<Result>() {
                                        @Override
                                        public void onResponse(Call<Result> call, Response<Result> response) {
                                            nDialog.dismiss();

                                            finish();

                                            Intent ii = new Intent(context, OrderActivity.class);
                                            startActivity(ii);

                                            if(response.body().getSuccess()) {
                                                alert = Alert.getAlert(context,getString(R.string.info), response.body().getMessage());
                                                alert.show();
                                            }
                                            else {
                                                alert = Alert.getAlert(context,getString(R.string.error),response.body().getMessage());
                                                alert.show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Result> call, Throwable t) {
                                            nDialog.dismiss();
                                            alert = Alert.getAlert(context, getString(R.string.error), t.getMessage());
                                            alert.show();
                                        }
                                    });
                                }
                            });
                        }
                        else {
                            alert = Alert.getAlert(context,getString(R.string.error),response.body().getMessage());
                            alert.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        nDialog.dismiss();
                        alert = Alert.getAlert(context, getString(R.string.error), t.getMessage());
                        alert.show();
                    }
                });
            }
        });
        builder.show();
    }

    private void fnGetShippingList(String search) {
        pBar.setVisibility(View.VISIBLE);
        apiInterface.getOrderShippingList(GlobalVariable.getUserId(), search).enqueue(new Callback<OrderShippingList>() {
            @Override
            public void onResponse(Call<OrderShippingList> call, Response<OrderShippingList> response) {
                pBar.setVisibility(View.GONE);
                if(response.body().getSuccess()) {
                    adapter.setData(response.body().getOrderShipping());

                    if(response.body().getOrderShipping().size() == 0)
                        pnlAddShipping.setVisibility(View.VISIBLE);
                    else
                        pnlAddShipping.setVisibility(View.GONE);
                }
                else {
                    alert = Alert.getAlert(context,getString(R.string.error),response.body().getMessage());
                    alert.show();
                }
            }

            @Override
            public void onFailure(Call<OrderShippingList> call, Throwable t) {
                pBar.setVisibility(View.GONE);
                alert = Alert.getAlert(context, getString(R.string.error), t.getMessage());
                alert.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        fnBackDetail();
    }

    void fnBackDetail() {
        nDialog.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(getString(R.string.sure));
        builder.setMessage(getString(R.string.question_order_detail_back));
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nDialog.dismiss();
            }
        });
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent ii = new Intent(context, OrderDetailActivity.class);
                startActivity(ii);
                finish();
                nDialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onItemCliked(OrderShipping orderShipping) {
        fnOrderFinish(new OrderDtos.setOrderStatus(GlobalVariable.getSelectedOrder().getSipNo(), orderShipping));
    }
}