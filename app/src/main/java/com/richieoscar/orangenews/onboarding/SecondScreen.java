package com.richieoscar.orangenews.onboarding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.FragmentSecondScreenBinding;
import com.richieoscar.orangenews.ui.activities.MainActivity;

public class SecondScreen extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSecondScreenBinding binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_second_screen, container, false);

        ViewPager2 viewPager2 = getActivity().findViewById(R.id.onboard_viewpager);
        binding.next.setOnClickListener(v->{
            viewPager2.setCurrentItem(2);
        });
        return binding.getRoot();
    }
}