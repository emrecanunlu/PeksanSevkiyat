package com.replik.peksansevkiyat.DataClass.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.replik.peksansevkiyat.DataClass.ModelDto.PalletDetail.PalletDetail;
import com.replik.peksansevkiyat.R;

import java.util.ArrayList;

public class ListAdapter_PalletDetail extends ArrayAdapter<PalletDetail> {
    private LayoutInflater mInflater;
    private ArrayList<PalletDetail> palletDetails;
    private int mViewResourceId;

    public ListAdapter_PalletDetail(Context context, int textViewResourceId, ArrayList<PalletDetail> palletDetails){
        super(context, textViewResourceId, palletDetails);
        this.palletDetails = palletDetails;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        convertView = mInflater.inflate(mViewResourceId, null);
        PalletDetail p = palletDetails.get(position);

        if(p != null)
        {
            TextView seriNo = (TextView)  convertView.findViewById(R.id.txtSeriNo);
            TextView stok = (TextView)  convertView.findViewById(R.id.txtStok);
            TextView miktar = (TextView)  convertView.findViewById(R.id.txtMiktar);
            TextView miktar2 = (TextView)  convertView.findViewById(R.id.txtMiktar2);

            seriNo.setText(p.getSeriNo());
            stok.setText(p.getStokKodu() + " " + p.getYapKod());
            miktar.setText(p.getMiktar().toString());
            miktar2.setText(p.getMiktar2().toString());

            /*if(p.getSeriNo().equals("321"))
                convertView.setBackgroundColor(Color.RED);*/
        }

        return convertView;
    }
}
