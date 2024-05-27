package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.CustomerOrder;
import com.replik.peksansevkiyat.R;
import com.replik.peksansevkiyat.Transection.Voids;

import org.w3c.dom.Text;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListAdapter_Customer_Order extends RecyclerView.Adapter<ListAdapter_Customer_Order.ViewHolder> {

    private List<CustomerOrder> customerOrderList;
    private ListenerInterface.ShipmentCustomerOrderListener shipmentCustomerOrderListener;

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView sevkNoTextView, receiverTextView, shippingTextView, dateTextView, productQuantityTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sevkNoTextView = (TextView) itemView.findViewById(R.id.txtSevkNo);
            receiverTextView = (TextView) itemView.findViewById(R.id.txtAlici);
            shippingTextView = (TextView) itemView.findViewById(R.id.txtNakliye);
            dateTextView = (TextView) itemView.findViewById(R.id.txtTarih);
            productQuantityTextView = (TextView) itemView.findViewById(R.id.txtAdet);

            itemView.setOnClickListener(v -> {
                if (shipmentCustomerOrderListener != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        shipmentCustomerOrderListener.onItemClicked(customerOrderList.get(pos));
                    }
                }
            });
        }
    }

    public ListAdapter_Customer_Order(List<CustomerOrder> customerOrderList, ListenerInterface.ShipmentCustomerOrderListener shipmentCustomerOrderListener) {
        this.customerOrderList = customerOrderList;
        this.shipmentCustomerOrderListener = shipmentCustomerOrderListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter_shipment_order, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerOrder customerOrder = customerOrderList.get(position);

        holder.sevkNoTextView.setText(customerOrder.getSevkNo());
        holder.receiverTextView.setText("Alıcı: " + customerOrder.getTeslimAdi());
        holder.shippingTextView.setText("Nakliye Tipi: " + customerOrder.getNakliyeTipi());
        holder.dateTextView.setText(Voids.formatDate(customerOrder.getShipmentDate()));
        holder.productQuantityTextView.setText(customerOrder.getKoliAdet() + " Adet");
    }

    @Override
    public int getItemCount() {
        return this.customerOrderList.size();
    }

    public void setList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList.clear();
        this.customerOrderList = customerOrderList;

        notifyDataSetChanged();
    }

}
