package com.richieoscar.orangenews.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;
import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.SportsPagerAdapter;
import com.richieoscar.orangenews.databinding.FragmentSportsBinding;
import com.richieoscar.orangenews.ui.activities.MainActivity;


public class SportsFragment extends Fragment {
    private FragmentSportsBinding binding;
    private ViewPager2 viewPager;
    private FragmentStateAdapter adapter;
    private String[] titles = {"Local Sport", "UK Sports", "US Sports"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().show();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sports, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.sports_feed);
        }

        adapter = new SportsPagerAdapter(getActivity());
        viewPager = binding.viewpager2;
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(binding.homeTablayout, viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        MainActivity activity = (MainActivity) getActivity();
        activity.showBottomNavigation();
        super.onResume();
    }
}