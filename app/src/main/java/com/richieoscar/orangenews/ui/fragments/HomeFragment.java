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
import com.richieoscar.orangenews.adapter.PagerAdapter;
import com.richieoscar.orangenews.databinding.FragmentHomeBinding;
import com.richieoscar.orangenews.ui.activities.MainActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPager2 viewPager;
    private FragmentStateAdapter adapter;
    private String[] titles = {"Latest News", "Top Headlines"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().show();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        adapter = new PagerAdapter(getActivity());
        viewPager = binding.viewpager2;
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(binding.homeTablayout, viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        activity.showBottomNavigation();
    }
}