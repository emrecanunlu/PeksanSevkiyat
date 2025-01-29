package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.StockLot;
import com.replik.peksansevkiyat.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter_RawMaterial_Lot extends RecyclerView.Adapter<ListAdapter_RawMaterial_Lot.ViewHolder> {

    final List<StockLot> stockLotList;

    public ListAdapter_RawMaterial_Lot(List<StockLot> stockLotList) {
        this.stockLotList = stockLotList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent. getContext())
                .inflate(R.layout.list_adapter_raw_material_serial_item, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final StockLot lotItem = stockLotList.get(position);

        Log.i("Serial Number", lotItem.getSerialNo());

        holder.serialNumberTextView.setText(lotItem.getSerialNo());
        holder.lotNumberTextView.setText(lotItem.getLotNo().isEmpty() ? "---" : lotItem.getLotNo());
        holder.serialNumberAmountTextView.setText(lotItem.getAmount() + " Kg");
    }

    @Override
    public int getItemCount() {
        return stockLotList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView serialNumberTextView, lotNumberTextView, serialNumberAmountTextView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            serialNumberTextView = (TextView) itemView.findViewById(R.id.serial_number);
            lotNumberTextView = (TextView) itemView.findViewById(R.id.lot_number);
            serialNumberAmountTextView = (TextView) itemView.findViewById(R.id.serial_amount);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}
