package com.richieoscar.orangenews.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.FragmentMusicBinding;


public class MusicFragment extends Fragment {
    FragmentMusicBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding = DataBindingUtil.inflate(inflater,R.layout.fragment_music, container, false);
      return binding.getRoot();
    }
}