package com.richieoscar.orangenews.ui.fragments;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.FragmentAboutBinding;
import com.richieoscar.orangenews.ui.activities.MainActivity;

import java.util.Calendar;


public class AboutFragment extends Fragment {

   FragmentAboutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().hide();
        activity.hideBottomNavigation();

        Calendar calendar = Calendar.getInstance();
       int year = calendar.get(Calendar.YEAR);
       binding.date.setText(String.valueOf(year));
        PackageManager mgr= getContext().getPackageManager();
        try {
            PackageInfo info = mgr.getPackageInfo(getContext().getPackageName(), 0);
            binding.version.setText("Version: " +info.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return binding.getRoot();
    }
}