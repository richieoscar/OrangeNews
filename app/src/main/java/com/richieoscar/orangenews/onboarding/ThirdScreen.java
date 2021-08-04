package com.richieoscar.orangenews.onboarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.FragmentThirdScreenBinding;
import com.richieoscar.orangenews.ui.activities.MainActivity;

public class ThirdScreen extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentThirdScreenBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third_screen, container, false);

        binding.finish.setOnClickListener(v -> {
            onboardingFinish();
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();

        });
        return binding.getRoot();
    }

    private void onboardingFinish(){
        SharedPreferences finPref = getActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = finPref.edit();
        editor.putBoolean("finished", true);
        editor.apply();
    }
}