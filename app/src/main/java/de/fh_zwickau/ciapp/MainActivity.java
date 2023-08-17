package de.fh_zwickau.ciapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.fh_zwickau.ciapp.databinding.ActivityMainBinding;
import de.fh_zwickau.ciapp.fragments.CIListFragment;
import de.fh_zwickau.ciapp.fragments.HomeFragment;
import de.fh_zwickau.ciapp.fragments.NewCIFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = MainViewModel.getViewModel();
        mViewModel.initialiseDB(getApplication());
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);
        FloatingActionButton floatingBtn = findViewById(R.id.floating_button);
        mViewModel.initializeBottomNavBar(bottomNav);
        mViewModel.replaceFragment(new HomeFragment(), getSupportFragmentManager(), false);

        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    if (bottomNav.getSelectedItemId() != item.getItemId()) {
                        mViewModel.replaceFragment(new HomeFragment(), getSupportFragmentManager(), false);
                    }
                    break;
                case R.id.favorite:
                    if (bottomNav.getSelectedItemId() != item.getItemId()) {
                        mViewModel.replaceFragment(CIListFragment.newInstance("true"), getSupportFragmentManager(), false);
                    }
                    break;

                case R.id.list:
                    if (bottomNav.getSelectedItemId() != item.getItemId()) {
                        mViewModel.replaceFragment(CIListFragment.newInstance("false"), getSupportFragmentManager(), false);
                    }
                    break;
            }
            return true;
        });

        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.replaceFragment(new NewCIFragment(), getSupportFragmentManager(), true);
            }
        });


    }
}