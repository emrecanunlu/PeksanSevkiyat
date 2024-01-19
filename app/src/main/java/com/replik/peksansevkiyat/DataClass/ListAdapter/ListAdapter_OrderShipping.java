package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ModelDto.OrderShipping.OrderShipping;
import com.replik.peksansevkiyat.R;

import java.util.List;

public class ListAdapter_OrderShipping extends RecyclerView.Adapter<ListAdapter_OrderShipping.ViewHolder> {
    ViewHolder holder;

    ListenerInterface.OrderShippingListener orderShippingListener;

    private List<OrderShipping> orderShippingList;

    public ListAdapter_OrderShipping(List<OrderShipping> lst, ListenerInterface.OrderShippingListener onClickListener) {
        orderShippingList = lst;
        orderShippingListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        TextView txtPlaka, txtTasiyiciAdi, txtSofor, txtIlIlce, txtUlke;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPlaka = itemView.findViewById(R.id.txtPlaka);
            txtTasiyiciAdi = itemView.findViewById(R.id.txtTasiyiciAdi);
            txtSofor = itemView.findViewById(R.id.txtSofor);
            txtIlIlce = itemView.findViewById(R.id.txtIlIlce);
            txtUlke = itemView.findViewById(R.id.txtUlke);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_adapter_order_shipping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtPlaka.setText(orderShippingList.get(position).getPlaka());
        holder.txtTasiyiciAdi.setText(orderShippingList.get(position).getTasiyiciAdi());
        holder.txtSofor.setText(orderShippingList.get(position).getSoforAd() + " " + orderShippingList.get(position).getSoforSoyAd());
        holder.txtIlIlce.setText(orderShippingList.get(position).getIl() + "/" + orderShippingList.get(position).getIlce());
        holder.txtUlke.setText(orderShippingList.get(position).getUlke());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderShipping o = orderShippingList.get(position);
                orderShippingListener.onItemCliked(o);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderShippingList.size();
    }

    public void setData(List<OrderShipping> sets) {  // new list here
        orderShippingList.clear();
        orderShippingList = sets;
        notifyDataSetChanged();
    }
}
