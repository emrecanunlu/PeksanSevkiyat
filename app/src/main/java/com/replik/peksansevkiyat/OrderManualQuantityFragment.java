package com.replik.peksansevkiyat;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.replik.peksansevkiyat.Interface.Interfaces;
import com.replik.peksansevkiyat.Transection.GlobalVariable;

import java.text.DecimalFormat;

public class OrderManualQuantityFragment extends DialogFragment {

    View view;
    EditText txtManuelQuantity;
    Button btnSave, btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_manuel_quantity, null);

        setCancelable(false);

        txtManuelQuantity = view.findViewById(R.id.txtManuelQuantity);
        btnSave = view.findViewById(R.id.btnManualQuantitySave);
        btnCancel = view.findViewById(R.id.btnManualQuantityCancel);

        txtManuelQuantity.setHint((new DecimalFormat("#")).format(GlobalVariable.getManuelQuantityVal()));
        int position = txtManuelQuantity.length();
        Editable etext = txtManuelQuantity.getText();
        Selection.setSelection(etext, position);

        txtManuelQuantity.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtManuelQuantity.getText().toString().equals("")) {
                    Interfaces.OrderManuelQuantityInterface intFace = (Interfaces.OrderManuelQuantityInterface) getActivity();
                    intFace.modalTick(true, Double.valueOf(txtManuelQuantity.getText().toString()));
                    dismiss();
                }
                else {
                    txtManuelQuantity.setBackgroundColor(Color.RED);
                }
            }
        });

        return view;
    }

    @Override
    public void dismiss() {
        Interfaces.OrderManuelQuantityInterface intFace = (Interfaces.OrderManuelQuantityInterface) getActivity();
        intFace.modalTick(false, 0.0);
        super.dismiss();
    }
}
