package com.richieoscar.orangenews.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.richieoscar.orangenews.ui.GermanyFragment;
import com.richieoscar.orangenews.ui.HeadlinesFragment;
import com.richieoscar.orangenews.ui.LatestFragment;
import com.richieoscar.orangenews.ui.LocalSportsFragment;
import com.richieoscar.orangenews.ui.SpainSportsFragment;
import com.richieoscar.orangenews.ui.SportUKFragment;
import com.richieoscar.orangenews.ui.SportUsFragment;

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
            case 3:
                return new SpainSportsFragment();
            case 4:
                return new GermanyFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
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
            case 3:
                return "Spain";
            case 4:
                return  "Germ";
            default:
                return super.getPageTitle(position);
        }
    }
}
