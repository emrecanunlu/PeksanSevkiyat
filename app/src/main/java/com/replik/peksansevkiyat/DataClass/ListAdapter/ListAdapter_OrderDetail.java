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

import com.replik.peksansevkiyat.DataClass.ModelDto.OrderDetail.OrderDetail;
import com.replik.peksansevkiyat.R;

import java.util.List;

public class ListAdapter_OrderDetail extends RecyclerView.Adapter<ListAdapter_OrderDetail.ViewHolder> {
    ViewHolder holder;

    private List<OrderDetail> orderDetailList;

    public ListAdapter_OrderDetail(List<OrderDetail> lst) {
        orderDetailList = lst;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        TextView txtStokKodu, txtStok, txtYapi, txtPaletTipi, txtSevkSekli, txtMiktar, txtSlash, txtToplanan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStokKodu = itemView.findViewById(R.id.txtStokKodu);
            txtStok = itemView.findViewById(R.id.txtStok);
            txtYapi = itemView.findViewById(R.id.txtYapi);
            txtPaletTipi = itemView.findViewById(R.id.txtPaletTipi);
            txtSevkSekli = itemView.findViewById(R.id.txtSevkSekli);
            txtMiktar = itemView.findViewById(R.id.txtMiktar);
            txtSlash = itemView.findViewById(R.id.txtSlash);
            txtToplanan = itemView.findViewById(R.id.txtToplanan);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_adapter_order_detail,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtStokKodu.setText(orderDetailList.get(position).getStokKodu());
        holder.txtStok.setText(orderDetailList.get(position).getStok());
        holder.txtYapi.setText(orderDetailList.get(position).getGovde() + " / " + orderDetailList.get(position).getUst() + " / " + orderDetailList.get(position).getLogo());
        holder.txtPaletTipi.setText(orderDetailList.get(position).getPaletTip());
        holder.txtSevkSekli.setText(orderDetailList.get(position).getYuklemeTip());
        holder.txtMiktar.setText(orderDetailList.get(position).getMiktar().toString());
        holder.txtToplanan.setText(orderDetailList.get(position).getToplananMiktar().toString());

        if(orderDetailList.get(position).getToplananMiktar().floatValue() > 0) {
            holder.cardView.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(30, 120, 30)));
            holder.txtStokKodu.setTextColor(Color.WHITE);
            holder.txtStok.setTextColor(Color.WHITE);
            holder.txtYapi.setTextColor(Color.WHITE);
            holder.txtPaletTipi.setTextColor(Color.WHITE);
            holder.txtSevkSekli.setTextColor(Color.WHITE);
            holder.txtMiktar.setTextColor(Color.WHITE);
            holder.txtSlash.setTextColor(Color.WHITE);
            holder.txtToplanan.setTextColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public void setData(List<OrderDetail> sets) {  // new list here
        orderDetailList.clear();
        orderDetailList = sets;
        notifyDataSetChanged();
    }
}