package com.richieoscar.orangenews.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.PagerAdapter;
import com.richieoscar.orangenews.databinding.FragmentHomeBinding;
import com.richieoscar.orangenews.ui.activities.MainActivity;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    ViewPager viewPager;
    PagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().show();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        adapter = new PagerAdapter(getChildFragmentManager());
        viewPager = binding.homeViewpager;
        viewPager.setAdapter(adapter);
        binding.homeTablayout.setupWithViewPager(viewPager);
        return binding.getRoot();
    }

}