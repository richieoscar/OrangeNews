package com.richieoscar.orangenews.onboarding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.FragmentOnboardBinding;
import com.richieoscar.orangenews.ui.activities.MainActivity;

import java.util.ArrayList;

public class OnboardFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentOnboardBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboard, container, false);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FirstScreen());
        fragments.add(new SecondScreen());
        fragments.add(new FourthScreen());
        fragments.add(new ThirdScreen());

        OnboardAdapter adapter = new OnboardAdapter(fragments, getActivity().getSupportFragmentManager(),getLifecycle());
        binding.onboardViewpager.setAdapter(adapter);
        return binding.getRoot();
    }
}