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
    private PackageInfo info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);
        PackageManager mgr = getContext().getPackageManager();
        try {
            info = mgr.getPackageInfo(getContext().getPackageName(), 0);
            if (info != null) {
                binding.version.setText("Version: " + info.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().hide();
        activity.hideBottomNavigation();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        binding.date.setText(String.valueOf(year));


        return binding.getRoot();
    }
}