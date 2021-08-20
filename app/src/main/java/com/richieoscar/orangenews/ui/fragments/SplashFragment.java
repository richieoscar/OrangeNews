package com.richieoscar.orangenews.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.ui.activities.MainActivity;


public class SplashFragment extends Fragment {

    private static final long DELAY = 3000;
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pref = getActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE);
        loadSplashScreen();
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    private void loadSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isOnboardingFinished()) gotoHome();
                else gotoOnboarding();
            }
        }, DELAY);
    }
    public void gotoHome(){
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }

    private void gotoOnboarding(){
        Navigation.findNavController(getActivity(),R.id.splash_container).navigate(R.id.action_splashFragment_to_onboardFragment);
    }

    private boolean isOnboardingFinished(){
        // SharedPreferences pref = getActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE);
       return pref.getBoolean("finished", false);
    }
}