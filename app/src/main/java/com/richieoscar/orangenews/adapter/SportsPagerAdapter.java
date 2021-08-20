package com.richieoscar.orangenews.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.richieoscar.orangenews.ui.fragments.LocalSportsFragment;
import com.richieoscar.orangenews.ui.fragments.SportUKFragment;
import com.richieoscar.orangenews.ui.fragments.SportUsFragment;
import com.richieoscar.orangenews.ui.fragments.SportsFragment;

public class SportsPagerAdapter extends FragmentStateAdapter {


    public static final int SIZE = 3;

    public SportsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new LocalSportsFragment();
            case 1:
                return new SportUKFragment();
            case 2:
                return new SportUsFragment();

            default:
                return new SportsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return SIZE;
    }
}
