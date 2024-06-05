package com.replik.peksansevkiyat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ListAdapter.ListAdapter_Customer_Order_Detail;
import com.replik.peksansevkiyat.DataClass.ListAdapter.ListenerInterface;
import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.Customer;
import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.CustomerOrder;
import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.CustomerOrderDetail;
import com.replik.peksansevkiyat.DataClass.ModelDto.Order.OrderDtos;
import com.replik.peksansevkiyat.DataClass.ModelDto.Order.OrderProduct;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.OrderShippingTransport;
import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.UpdateOrderShippingTransportDto;
import com.replik.peksansevkiyat.DataClass.ModelDto.Pallet.PalletDetail;
import com.replik.peksansevkiyat.DataClass.ModelDto.Result;
import com.replik.peksansevkiyat.Interface.APIClient;
import com.replik.peksansevkiyat.Interface.APIInterface;
import com.replik.peksansevkiyat.Transection.Alert;
import com.replik.peksansevkiyat.Transection.Dialog;
import com.replik.peksansevkiyat.Transection.GlobalVariable;
import com.replik.peksansevkiyat.Transection.Voids;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipmentOrderDetailActivity extends AppCompatActivity implements ListenerInterface.UpdateTransportDialogListener {
    ImageView logoImageView, printImageView;
    Button finishOrderButton;
    EditText barcodeEditText;
    ProgressDialog loader;
    AlertDialog alert;
    CardView transportCard;
    APIInterface apiInterface;
    ConstraintLayout progressBar;
    TextView staffNameTextView, sipNoTextView, customerTextView, shippingNameTextView, deliveryNameTextView, deliveryAddressTextView, deliveryDate, txtKoliPalet;
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

        loader = Dialog.getDialog(context, getString(R.string.loading));

        txtKoliPalet = (TextView) findViewById(R.id.txtKoliPalet);
        progressBar = (ConstraintLayout) findViewById(R.id.pnlProgressBar);
        barcodeEditText = (EditText) findViewById(R.id.txtBarcode);
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
        transportCard = (CardView) findViewById(R.id.transportCard);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        listAdapter = new ListAdapter_Customer_Order_Detail(customerOrderDetailList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(layoutManager);

        sipNoTextView.setText(order.getSevkNo());
        staffNameTextView.setText(GlobalVariable.getUserName());

        customerTextView.setText(customer.getFullName());

        shippingNameTextView.setText(order.getNakliyeTipi());

        txtKoliPalet.setText(order.getKoliPalet());

        deliveryDate.setText(Voids.formatDate(order.getShipmentDate()));
        deliveryNameTextView.setText(order.getTeslimAdi());
        deliveryAddressTextView.setText(order.getTeslimAdresi());

        printImageView.setOnClickListener(v -> print());

        logoImageView.setOnClickListener(v -> {
            finish();
        });

        transportCard.setOnClickListener(v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            new UpdateTransportDialogFragment(order.getNakliyeId(), this).show(fragmentManager, "UPDATE_TRANSPORT_DIALOG");
        });

        finishOrderButton = (Button) findViewById(R.id.btnFinishOrder);
        finishOrderButton.setVisibility(View.GONE);
        finishOrderButton.setOnClickListener(v -> {
            Intent i = new Intent(this, ShipmentOrderFinish.class);

            i.putExtra("customer", customer);
            i.putExtra("order", order);

            GlobalVariable.setCustomerOrderDetails(customerOrderDetailList);

            startActivity(i);
        });

        barcodeEditText.setInputType(InputType.TYPE_NULL);
        barcodeEditText.requestFocus();
        barcodeEditText.setOnKeyListener(
                (v, keyCode, event) -> {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        if (keyCode == KeyEvent.KEYCODE_ENTER) {
                            barcodeEntry(barcodeEditText.getText().toString());

                            return true;
                        }
                    }
                    return false;
                }
        );

        fetchOrderDetailList();
    }

    void fetchOrderDetailList() {
        progressBar.setVisibility(View.VISIBLE);

        apiInterface.getOrderDetail(order.getSevkNo()).enqueue(
                new Callback<List<CustomerOrderDetail>>() {
                    @Override
                    public void onResponse(Call<List<CustomerOrderDetail>> call, Response<List<CustomerOrderDetail>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            customerOrderDetailList = response.body();
                            listAdapter.setList(customerOrderDetailList);

                            if (response.body().stream().allMatch(x -> x.getSevkMiktar() == x.getGonderilenMiktar())) {
                                finishOrderButton.setVisibility(View.VISIBLE);
                            }
                        }

                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<List<CustomerOrderDetail>> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );
    }

    void barcodeEntry(String barcode) {
        barcodeEditText.setText("");
        loader.show();

        apiInterface.getPalletDetail(barcode.trim()).enqueue(
                new Callback<List<PalletDetail>>() {
                    @Override
                    public void onResponse(Call<List<PalletDetail>> call, Response<List<PalletDetail>> response) {
                        loader.dismiss();

                        if (response.isSuccessful()) {
                            List<CustomerOrderDetail> list = customerOrderDetailList.stream().filter(x -> x.getSevkMiktar() != x.getGonderilenMiktar()).collect(Collectors.toList());

                            if (response.body().stream().anyMatch(x -> x.getProducts().isEmpty())) {
                                alert = Alert.getAlert(context, getString(R.string.error), "Hatalı Palet Serisi!");

                                alert.show();
                                return;
                            }

                            if (response.body().size() != list.size()) {
                                alert = Alert.getAlert(context, getString(R.string.error), "Hatalı Palet Serisi!");

                                alert.show();
                                return;
                            }

                            boolean hasEquals = true;

                            for (CustomerOrderDetail order : list) {
                                if (!response.body().stream().allMatch(x -> x.getStokKod().equals(order.getStokKodu()) && x.getYapkod().equals(order.getUrunYapkod()))) {
                                    hasEquals = false;
                                    break;
                                }
                            }

                            if (!hasEquals) {
                                alert = Alert.getAlert(context, getString(R.string.error), "Yapı Kodu veya Stok Kodu Uyuşmuyor!");

                                alert.show();
                                return;
                            }

                            for (int i = 0; i < list.size(); i++) {
                                final CustomerOrderDetail customerOrderDetail = list.get(i);

                                /**/
                                final PalletDetail palletDetail = response.body().get(i);
                                /**/

                                final OrderDtos.createOrderByProductsDto orderByProductsDto =
                                        new OrderDtos.createOrderByProductsDto(
                                                order.getSevkNo(),
                                                customerOrderDetail.getSipNo(),
                                                customer.getCode(),
                                                GlobalVariable.getUserId(),
                                                palletDetail.getProducts().stream().map(x -> new OrderProduct(x, palletDetail.getStokKod(), palletDetail.getYapkod())).collect(Collectors.toList())
                                        );

                                /* TO DO */
                                // Her product için isteği sıraya al.

                                loader.show();
                                apiInterface.createOrderByProducts(orderByProductsDto).enqueue(
                                        new Callback<Result>() {
                                            @Override
                                            public void onResponse(Call<Result> call, Response<Result> response) {
                                                loader.dismiss();

                                                if (response.body() != null) {
                                                    if (response.body().getSuccess()) {
                                                        fetchOrderDetailList();
                                                    } else {
                                                        alert = Alert.getAlert(context, getString(R.string.error), response.body().getMessage());
                                                        alert.show();
                                                    }
                                                } else {
                                                    alert = Alert.getAlert(context, getString(R.string.error), response.message());
                                                    alert.show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Result> call, Throwable t) {
                                                loader.dismiss();

                                                alert = Alert.getAlert(context, getString(R.string.error), t.getMessage());
                                                alert.show();
                                            }
                                        }
                                );
                            }
                        } else {
                            alert = Alert.getAlert(context, getString(R.string.error), getString(R.string.danger));
                            alert.show();
                        }

                        loader.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<PalletDetail>> call, Throwable t) {
                        loader.dismiss();
                    }
                }
        );
    }

    void print() {
        Toast.makeText(context, "Print Callback", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransportSelected(OrderShippingTransport transport) {
        loader.show();
        apiInterface.updateTransportType(new UpdateOrderShippingTransportDto(order.getSevkNo(), transport.getId())).enqueue(
                new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        loader.dismiss();
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getSuccess()) {
                                order.setNakliyeId(transport.getId());
                                order.setNakliyeTipi(transport.getDesc());

                                shippingNameTextView.setText(transport.getDesc());
                            } else {
                                alert = Alert.getAlert(context, getString(R.string.error), getString(R.string.error));
                                alert.show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        loader.dismiss();

                        alert = Alert.getAlert(context, getString(R.string.error), t.getMessage());
                        alert.show();
                    }
                }
        );
    }
}