package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ModelDto.Order.Order;
import com.replik.peksansevkiyat.R;

import java.util.List;

public class ListAdapter_Order extends RecyclerView.Adapter<ListAdapter_Order.ViewHolder> {
    ViewHolder holder;

    ListenerInterface.OrderListener orderListener;

    private List<Order> orderList;

    public ListAdapter_Order(List<Order> lst, ListenerInterface.OrderListener onClickListener) {
        orderList = lst;
        orderListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        TextView txtSipNo, txtCari, txtTarih, txtNumuneMi, txtSipHemenSevk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSipNo = itemView.findViewById(R.id.txtSipNo);
            txtCari = itemView.findViewById(R.id.txtCari);
            txtTarih = itemView.findViewById(R.id.txtTarih);
            txtNumuneMi = itemView.findViewById(R.id.txtNumuneMi);
            txtSipHemenSevk = itemView.findViewById(R.id.txtSipHemenSevk);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_adapter_order,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtSipNo.setText(orderList.get(position).getSipNo());
        holder.txtCari.setText(orderList.get(position).getCari());
        holder.txtTarih.setText(orderList.get(position).getTarih());
        holder.txtNumuneMi.setText(orderList.get(position).getNumuneSip() ? "NUMUNE SİPARİŞİ" : "");
        holder.txtSipHemenSevk.setText(orderList.get(position).getHemenSevk() ? "HEMEN TESLİM" : "");

        if(orderList.get(position).getNumuneSip()) {
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(30, 120, 30)));
            holder.txtSipNo.setTextColor(Color.WHITE);
            holder.txtCari.setTextColor(Color.WHITE);
            holder.txtTarih.setTextColor(Color.WHITE);
            holder.txtNumuneMi.setTextColor(Color.WHITE);
            holder.txtSipHemenSevk.setTextColor(Color.WHITE);
        }

        if(orderList.get(position).getHemenSevk()) {
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(190, 50, 50)));
            holder.txtSipNo.setTextColor(Color.WHITE);
            holder.txtCari.setTextColor(Color.WHITE);
            holder.txtTarih.setTextColor(Color.WHITE);
            holder.txtNumuneMi.setTextColor(Color.WHITE);
            holder.txtSipHemenSevk.setTextColor(Color.WHITE);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order o = orderList.get(position);
                orderListener.onItemCliked(o);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void setData(List<Order> sets) {  // new list here
        orderList.clear();
        orderList = sets;
        notifyDataSetChanged();
    }
}
