package com.richieoscar.orangenews.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.ui.activities.MainActivity;
import com.richieoscar.orangenews.ui.activities.SplashActivity;


public class SplashFragment extends Fragment {

    private static final long DELAY = 5000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().hide();
        activity.hideBottomNavigation();
        loadSplashScreen();
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    private void loadSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigation.findNavController(getActivity(),R.id.frag_container).navigate(R.id.action_splashFragment_to_onboardFragment);
            }
        }, DELAY);
    }
}