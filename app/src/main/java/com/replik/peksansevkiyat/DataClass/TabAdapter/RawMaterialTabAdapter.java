package com.replik.peksansevkiyat.DataClass.TabAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class RawMaterialTabAdapter extends FragmentStateAdapter {

    public RawMaterialTabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new RawMaterialRequestedStockListTab();
        }
        return new RawMaterialStockListTab();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
