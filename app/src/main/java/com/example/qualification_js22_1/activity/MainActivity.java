package com.example.qualification_js22_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.adapter.Adapter;
import com.example.qualification_js22_1.adapter.SliderAdapter;
import com.example.qualification_js22_1.model.Product;
import com.example.qualification_js22_1.model.Slider;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView productRv;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product>  productList;

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;

    public void insert()
    {
        productList = new ArrayList<>();
        productList.add(new Product(1,"Es Kopi Test",25000, R.drawable.img));
        productList.add(new Product(2,"Es Kopi Manja",3000,R.drawable.img1));
        productList.add(new Product(3,"Es Kopi Mantan",15000,R.drawable.img2));

        productRv = findViewById(R.id.mainRv);
        adapter = new Adapter(this,productList);
        layoutManager = new LinearLayoutManager(this);
        productRv.setLayoutManager(layoutManager);
        productRv.setAdapter(adapter);
    }

    public void carouselInit(View view)
    {
        ArrayList<Slider> sliders = new ArrayList<>();
        SliderView sliderView = view.findViewById(R.id.popslider);
        sliders.add(new Slider(R.drawable.img));
        sliders.add(new Slider(R.drawable.img1));
        sliders.add(new Slider(R.drawable.img2));

        SliderAdapter adapter = new SliderAdapter(this, sliders);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        view.findViewById(R.id.close).setOnClickListener(view1 -> {
            dialog.hide();
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.nav_shop:
                    startActivity(new Intent(getApplicationContext(),ShopActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_home:
                    return true;
                case R.id.nav_maps:
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_chat:
                    startActivity(new Intent(getApplicationContext(),ChatActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        insert();


        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup,null);
        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();

        carouselInit(popupView);
        dialog.show();
    }
}