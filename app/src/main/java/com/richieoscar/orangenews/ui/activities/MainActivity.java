package com.richieoscar.orangenews.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
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
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
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
            case R.id.share:
            case R.id.add_to_bookmark:
            case R.id.web_reload:
            case R.id.web_share:
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
        int currentFrag = navHostFragment.getNavController().getCurrentDestination().getId();
        switch (currentFrag) {
            case R.id.home:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_home_to_searchFragment);
                break;
            case R.id.sports:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_sports_to_searchFragment);
                break;
            case R.id.entertainment:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_entertainment_to_searchFragment);
                break;
            case R.id.business:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_business_to_searchFragment);
                break;
            case R.id.tech:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_tech_to_searchFragment);
                break;
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.frag_container), drawerLayout);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_search:
                openSearch();
                item.setChecked(true);
                break;
            case R.id.likes:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.likesFragment);
                item.setChecked(true);
                break;
            case R.id.saved:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.savedFragment);
                item.setChecked(true);
                break;
            case R.id.sources:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.sourcesFragment);
                item.setChecked(true);
                break;
            case R.id.share:
                shareApp();
                break;
            case R.id.about:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.aboutFragment);
                item.setChecked(true);
                break;
        }
        // item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void navigateToSourceDetail(Bundle args) {
        Navigation.findNavController(this, R.id.frag_container).navigate(R.id.sourceDetailFragment, args);
    }

    public void openDetail(Bundle args) {
        int currentFrag = navHostFragment.getNavController().getCurrentDestination().getId();
        switch (currentFrag) {
            case R.id.home:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_home_to_detailsFragment, args);
                break;
            case R.id.sports:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_sports_to_detailsFragment, args);
                break;
            case R.id.entertainment:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_entertainment_to_detailsFragment, args);
                break;
            case R.id.business:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_business_to_detailsFragment, args);
                break;
            case R.id.tech:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_tech_to_detailsFragment, args);
                break;
            case R.id.likesFragment:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_likesFragment_to_detailsFragment, args);
                break;
            case R.id.savedFragment:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_savedFragment_to_detailsFragment, args);
                break;
            case R.id.searchFragment:
                Navigation.findNavController(this, R.id.frag_container).navigate(R.id.action_searchFragment_to_detailsFragment, args);

        }
    }

    public void openWebView(Bundle args) {
        Navigation.findNavController(this, R.id.frag_container).navigate(R.id.webFragment, args);
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

    private void shareApp() {
        String appUrl = "https://play.google.com/store/apps/details?id=com.richieoscar.orangenews";
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle(R.string.share_with)
                .setText("OrangeNews App\nDownload From PlayStore Here \n" + appUrl)
                .startChooser();
    }
}