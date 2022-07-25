package com.example.qualification_js22_1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.qualification_js22_1.fragment.MapsFragment;
import com.example.qualification_js22_1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapsActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_maps);

        MapsFragment mapsFragment = new MapsFragment();

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.flMaps,mapsFragment).
                commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_maps:
                    return true;
                case R.id.nav_shop:
                    startActivity(new Intent(getApplicationContext(),ShopActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_chat:
                    startActivity(new Intent(getApplicationContext(),ChatActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });


    }
}