package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.Customer;
import com.replik.peksansevkiyat.R;

import java.util.List;

public class ListAdapter_Customer extends RecyclerView.Adapter<ListAdapter_Customer.ViewHolder> {

    private ListenerInterface.ShipmentCustomerListener shipmentCustomerListener;
    private List<Customer> customerList;

    public ListAdapter_Customer(List<Customer> customerList, ListenerInterface.ShipmentCustomerListener shipmentCustomerListener) {
        this.customerList = customerList;
        this.shipmentCustomerListener = shipmentCustomerListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameTextView, codeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullNameTextView = (TextView) itemView.findViewById(R.id.customer_fullname);
            codeTextView = (TextView) itemView.findViewById(R.id.customer_code);

            itemView.setOnClickListener(v -> {
                if (shipmentCustomerListener != null) {
                    int pos = getAdapterPosition();

                    if (pos !=  RecyclerView.NO_POSITION) {
                        shipmentCustomerListener.onItemClicked(customerList.get(pos));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter_customer, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fullNameTextView.setText(customerList.get(position).getFullName());
        holder.codeTextView.setText(customerList.get(position).getCode());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public void setList(List<Customer> customerList) {
        this.customerList.clear();
        this.customerList = customerList;

        notifyDataSetChanged();
    }
}