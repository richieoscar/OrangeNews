package com.richieoscar.orangenews.onboarding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.FragmentThirdScreenBinding;
import com.richieoscar.orangenews.ui.activities.MainActivity;

public class ThirdScreen extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentThirdScreenBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third_screen, container, false);

        MainActivity activity = (MainActivity) getActivity();
        activity.hideBottomNavigation();
        binding.finish.setOnClickListener(v->{
            Navigation.findNavController(getActivity(),R.id.frag_container).navigate(R.id.action_onboardFragment_to_home);
        });
        return binding.getRoot();
    }
}