package com.richieoscar.orangenews.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.ui.activities.MainActivity;


public class FirstScreen extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        activity.hideBottomNavigation();
        return inflater.inflate(R.layout.fragment_first_screen, container, false);
    }
}