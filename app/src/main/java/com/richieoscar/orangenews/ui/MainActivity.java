package com.richieoscar.orangenews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        drawerLayout = binding.drawer;
        navigationView = binding.navView;
        getSupportActionBar().setElevation(0);
        setUpDrawerNavigationView();
        setUpBottomNavigationView();
        showBottomNavigation();
    }

    private void setUpBottomNavigationView() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.frag_container);
        NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.getNavController());
    }

    private void setUpDrawerNavigationView() {
        NavController navController = Navigation.findNavController(this, R.id.frag_container);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                openSearch();
                return true;
            case R.id.settings:
            case R.id.clear_likes:
            case R.id.clear_saved:
                return false;
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSearch() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.frag_container), drawerLayout);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.likes:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.likesFragment);
                break;
            case R.id.saved:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.savedFragment);
                break;
            case R.id.sources:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.sourcesFragment);
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void navigateToSourceDetail(Bundle args) {
        Navigation.findNavController(this, R.id.frag_container).navigate(R.id.sourceDetailFragment, args);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    public void hideBottomNavigation() {
        binding.bottomNav.setVisibility(View.GONE);
    }

    public void showBottomNavigation() {
        binding.bottomNav.setVisibility(View.VISIBLE);
    }
}