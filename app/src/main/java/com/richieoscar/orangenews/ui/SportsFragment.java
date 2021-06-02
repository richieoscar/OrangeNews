package com.richieoscar.orangenews.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.PagerAdapter;
import com.richieoscar.orangenews.adapter.SportsPagerAdapter;
import com.richieoscar.orangenews.databinding.FragmentSportsBinding;


public class SportsFragment extends Fragment {
    private FragmentSportsBinding binding;
    private ViewPager viewPager;
    private SportsPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sports, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.sports_feed);
        }

        adapter = new SportsPagerAdapter(getChildFragmentManager());
        viewPager = binding.sportViewpager;
        viewPager.setAdapter(adapter);
        binding.homeTablayout.setupWithViewPager(viewPager);
        return binding.getRoot();
    }
}