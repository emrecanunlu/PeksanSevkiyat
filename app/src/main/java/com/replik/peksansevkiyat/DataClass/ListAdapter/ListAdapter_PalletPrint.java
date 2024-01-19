package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.replik.peksansevkiyat.DataClass.ModelDto.Pallet.dtoPalletPrint;
import com.replik.peksansevkiyat.R;

import java.util.ArrayList;

public class ListAdapter_PalletPrint extends ArrayAdapter<dtoPalletPrint> {
    private LayoutInflater mInflater;
    private ArrayList<dtoPalletPrint> palletPrints;
    private int mViewResourceId;

    public ListAdapter_PalletPrint(Context context, int textViewResourceId, ArrayList<dtoPalletPrint> palletPrints){
        super(context, textViewResourceId, palletPrints);
        this.palletPrints = palletPrints;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        convertView = mInflater.inflate(mViewResourceId, null);
        dtoPalletPrint p = palletPrints.get(position);

        if(p!=null)
        {
            TextView txtBarcodePP = (TextView)  convertView.findViewById(R.id.txtBarcodePP);
            TextView txtDatePP = (TextView)  convertView.findViewById(R.id.txtDatePP);

            txtBarcodePP.setText(p.getBarkod());
            txtDatePP.setText(p.getCreateDate());
        }
        
        return convertView;
    }
}
