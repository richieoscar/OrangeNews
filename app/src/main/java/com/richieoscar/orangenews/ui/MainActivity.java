package com.richieoscar.orangenews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpNavigationView();
    }

    private void setUpNavigationView() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.frag_container);
        NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.getNavController());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                 openSearch();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSearch() {
        startActivity(new Intent(this, SearchActivity.class));

    }
}