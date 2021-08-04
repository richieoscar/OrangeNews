package com.richieoscar.orangenews.onboarding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.FragmentFourthScreenBinding;
import com.richieoscar.orangenews.databinding.FragmentSecondScreenBinding;


public class FourthScreen extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentFourthScreenBinding binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_fourth_screen, container, false);

        ViewPager2 viewPager2 = getActivity().findViewById(R.id.onboard_viewpager);
        binding.next.setOnClickListener(v->{
            viewPager2.setCurrentItem(3);
        });
        return binding.getRoot();
    }
}