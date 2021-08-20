package com.richieoscar.orangenews.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.richieoscar.orangenews.ui.fragments.HeadlinesFragment;
import com.richieoscar.orangenews.ui.fragments.HomeFragment;
import com.richieoscar.orangenews.ui.fragments.LatestFragment;

public class PagerAdapter extends FragmentStateAdapter {


    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LatestFragment();
            case 1:
                return new HeadlinesFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
