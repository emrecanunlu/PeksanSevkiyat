package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.replik.peksansevkiyat.DataClass.ModelDto.Pallet.PalletContent;
import com.replik.peksansevkiyat.DataClass.ModelDto.Pallet.PalletContentResponse;
import com.replik.peksansevkiyat.R;

import java.util.List;

public class ListAdapter_PalletContent extends ArrayAdapter<PalletContent> {

    final private List<PalletContent> products;
    final private int resouce;
    final private Context context;
    final private LayoutInflater inflater;

    public ListAdapter_PalletContent(@NonNull Context context, int resource, @NonNull List<PalletContent> objects) {
        super(context, resource, objects);
        this.products = objects;
        this.resouce = resource;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resouce, parent, false);
        PalletContent product = products.get(position);

        if (product != null) {
            TextView seriNo = convertView.findViewById(R.id.txtSeriNo);
            TextView stok = convertView.findViewById(R.id.txtStok);
            TextView miktar = convertView.findViewById(R.id.txtMiktar);

            seriNo.setText(product.getSerialNo());
            stok.setText(product.getStockCode() + " | " + (product.getYapKod() == null ? "-" : product.getYapKod()));
            miktar.setText(product.getAmount().toString());
        }

        return convertView;
    }
}
