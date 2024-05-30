package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ModelDto.Customer.CustomerOrderDetail;
import com.replik.peksansevkiyat.R;

import java.util.List;

public class ListAdapter_Customer_Order_Detail extends RecyclerView.Adapter<ListAdapter_Customer_Order_Detail.ViewHolder> {

    private List<CustomerOrderDetail> customerOrderDetailList;

    public ListAdapter_Customer_Order_Detail(List<CustomerOrderDetail> customerOrderDetailList) {
        this.customerOrderDetailList = customerOrderDetailList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView stockNameTextView, stockCodeTextView, colorTextView, totalAmountTextView, amountTextView;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stockNameTextView = (TextView) itemView.findViewById(R.id.txtStokAdi);
            stockCodeTextView = (TextView) itemView.findViewById(R.id.txtStokKodu);
            colorTextView = (TextView) itemView.findViewById(R.id.txtRenk);
            totalAmountTextView = (TextView) itemView.findViewById(R.id.txtToplamMiktar);
            amountTextView = (TextView) itemView.findViewById(R.id.txtMiktar);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter_customer_order_detail, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerOrderDetail customerOrderDetail = customerOrderDetailList.get(position);

        holder.stockNameTextView.setText(customerOrderDetail.getStokAdi());
        holder.stockCodeTextView.setText(customerOrderDetail.getStokKodu());
        holder.colorTextView.setText(customerOrderDetail.getColor());
        holder.totalAmountTextView.setText(String.valueOf(customerOrderDetail.getSevkMiktar()));
        holder.amountTextView.setText(String.valueOf(customerOrderDetail.getGonderilenMiktar()));
    }

    @Override
    public int getItemCount() {
        return customerOrderDetailList.size();
    }

    public void setList(List<CustomerOrderDetail> customerOrderDetailList) {
        this.customerOrderDetailList.clear();
        this.customerOrderDetailList = customerOrderDetailList;

        notifyDataSetChanged();
    }
}
