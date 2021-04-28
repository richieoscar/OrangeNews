package com.richieoscar.orangenews.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.richieoscar.orangenews.ui.HeadlinesFragment;
import com.richieoscar.orangenews.ui.LatestFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LatestFragment();
            case 1:
                return new HeadlinesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Latest News";
            case 1:
                return "Top Headlines";
            default:
                return super.getPageTitle(position);
        }
    }
}
