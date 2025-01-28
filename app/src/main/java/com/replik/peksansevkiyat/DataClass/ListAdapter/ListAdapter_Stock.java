package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.StockItem;
import com.replik.peksansevkiyat.R;

import java.util.List;

public class ListAdapter_Stock extends RecyclerView.Adapter<ListAdapter_Stock.ViewHolder> {

    final List<StockItem> stockList;
    final ListenerInterface.RawMaterialListener listener;

    public ListAdapter_Stock(List<StockItem> stockList, ListenerInterface.RawMaterialListener listener) {
        this.stockList = stockList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_adapter_stock_item, parent, false);


        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final StockItem item = stockList.get(position);

        holder.stockNameTextView.setText(item.getStockName());
        holder.stockCodeTextView.setText(item.getStockCode());
        holder.stockAmountTextView.setText(item.getAmount() + " Kg");

        holder.cardView.setOnClickListener(v -> {
            final StockItem stockItem = stockList.get(position);
            listener.onClick(stockItem);
        });
    }


    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView stockCodeTextView, stockNameTextView, stockAmountTextView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            stockAmountTextView = (TextView) itemView.findViewById(R.id.stock_amount);
            stockCodeTextView = (TextView) itemView.findViewById(R.id.stock_code);
            stockNameTextView = (TextView) itemView.findViewById(R.id.stock_name);
        }
    }
}
