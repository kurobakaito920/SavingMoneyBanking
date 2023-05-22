package com.example.savingmoneybanking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.savingmoneybanking.fragment.HomeFragment;
import com.example.savingmoneybanking.fragment.SettingFragment;
import com.example.savingmoneybanking.fragment.SpendingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView mnBottom = findViewById(R.id.navBottomView);
        loadFragment(new HomeFragment());
        mnBottom.setOnItemSelectedListener(getListener());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return item->{
            Fragment fragment;
            switch (item.getItemId()){
                case R.id.home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.spending_management:
                    fragment = new SpendingFragment();
                    loadFragment(fragment);
                    return true;
                case  R.id.setting:
                    fragment = new SettingFragment();
                    loadFragment(fragment);
                    return true;
                default:
                    return true;
            }
        };
    }


    void loadFragment(Fragment fmNew){
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
}