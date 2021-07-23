package com.richieoscar.orangenews.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.richieoscar.orangenews.ui.fragments.LocalSportsFragment;
import com.richieoscar.orangenews.ui.fragments.SportUKFragment;
import com.richieoscar.orangenews.ui.fragments.SportUsFragment;

import java.util.ArrayList;

public class SportsPagerAdapter extends FragmentPagerAdapter {

    public SportsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LocalSportsFragment();
            case 1:
                return new SportUKFragment();
            case 2:
                return new SportUsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Local";
            case 1:
                return "UK";
            case 2:
                return "USA";
            default:
                return super.getPageTitle(position);
        }
    }
}
