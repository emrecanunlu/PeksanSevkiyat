package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.Customer;
import com.replik.peksansevkiyat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListAdapter_Customer extends RecyclerView.Adapter<ListAdapter_Customer.ViewHolder> {

    private ListenerInterface.ShipmentCustomerListener shipmentCustomerListener;
    private List<Customer> customerList;
    private List<Customer> searchedList = new ArrayList<>();

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

                    if (pos != RecyclerView.NO_POSITION) {
                        shipmentCustomerListener.onItemClicked(searchedList.get(pos));
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
        holder.fullNameTextView.setText(searchedList.get(position).getFullName());
        holder.codeTextView.setText(searchedList.get(position).getCode());
    }

    @Override
    public int getItemCount() {
        return searchedList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Customer> customerList) {
        this.customerList.clear();
        this.searchedList.clear();

        this.customerList = customerList;
        this.searchedList = new ArrayList<>(customerList);

        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void search(String term) {
        searchedList.clear();

        if (term.isEmpty()) searchedList.addAll(customerList);
        else {
            for (Customer customer : customerList) {
                if (customer.getFullName().toLowerCase().contains(term) || customer.getCode().contains(term)) {
                    searchedList.add(customer);
                }
            }
        }

        notifyDataSetChanged();
    }
}