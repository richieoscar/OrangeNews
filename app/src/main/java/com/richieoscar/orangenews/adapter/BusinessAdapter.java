package com.richieoscar.orangenews.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.richieoscar.orangenews.ui.fragments.BusinessFragment;
import com.richieoscar.orangenews.ui.fragments.HomeFragment;

public class BusinessAdapter extends FragmentStateAdapter {


    public BusinessAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BusinessFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
