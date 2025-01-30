package com.replik.peksansevkiyat.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.replik.peksansevkiyat.DataClass.ModelDto.Stock.LotItem;
import com.replik.peksansevkiyat.R;

import java.util.ArrayList;
import java.util.List;

public class LotAdapter extends RecyclerView.Adapter<LotAdapter.ViewHolder> {
    private List<LotItem> lots = new ArrayList<>();
    private List<String> existingSerialNumbers = new ArrayList<>();
    private OnLotSelectedListener listener;
    private int selectedPosition = -1;

    public void setLots(List<LotItem> lots) {
        this.lots = lots;
        notifyDataSetChanged();
    }

    public void setExistingSerialNumbers(List<String> serialNumbers) {
        this.existingSerialNumbers = serialNumbers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LotItem lot = lots.get(position);
        boolean isExisting = existingSerialNumbers.contains(lot.getSerialNumber());
        boolean isSelected = position == selectedPosition;

        holder.tvLotNumber.setText(lot.getLotNumber());
        holder.tvSerialNumber.setText(lot.getSerialNumber());
        holder.tvAmount.setText(String.format("%.2f Kg", lot.getAmount()));

        if (isExisting) {
            holder.tvLotNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.darker_gray));
            holder.tvSerialNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.darker_gray));
            holder.tvAmount.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.darker_gray));
            holder.itemView.setEnabled(false);
            holder.itemView.setClickable(false);
            holder.itemView.setAlpha(0.5f);
        } else {
            if (isSelected) {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.replik));
                holder.tvLotNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
                holder.tvSerialNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
                holder.tvAmount.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
            } else {
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
                holder.tvLotNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.black));
                holder.tvSerialNumber.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.black));
                holder.tvAmount.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.replik));
            }
            holder.itemView.setEnabled(true);
            holder.itemView.setClickable(true);
            holder.itemView.setAlpha(1f);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null && !isExisting) {
                int oldPosition = selectedPosition;
                selectedPosition = position;
                notifyItemChanged(oldPosition);
                notifyItemChanged(selectedPosition);
                listener.onLotSelected(lot);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lots.size();
    }

    public void setOnLotSelectedListener(OnLotSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnLotSelectedListener {
        void onLotSelected(LotItem lot);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLotNumber;
        TextView tvSerialNumber;
        TextView tvAmount;
        MaterialCardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLotNumber = itemView.findViewById(R.id.tv_lot_number);
            tvSerialNumber = itemView.findViewById(R.id.tv_serial_number);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
} 