package com.example.qualification_js22_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualification_js22_1.DBOpenHelper;
import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.adapter.CartAdapter;
import com.example.qualification_js22_1.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<>();
    TextView totalTv,taxTv;
    RecyclerView productRv;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    int sum = 0;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        getData();

        totalTv = findViewById(R.id.totalTv);
        taxTv = findViewById(R.id.taxTv);
        totalTv.setText("Rp. " + sum);
        taxTv.setText("Rp. "+ (sum / 10));


        productRv = findViewById(R.id.mainRv);
        adapter = new CartAdapter(this,products);
        layoutManager = new LinearLayoutManager(this);
        productRv.setLayoutManager(layoutManager);
        productRv.setAdapter(adapter);

        TextView resetTv = findViewById(R.id.resetTv);

        resetTv.setOnClickListener(view -> {
            DBOpenHelper.getInstance(this).
                    getWritableDatabase().execSQL("DELETE FROM "+DBOpenHelper.CARTS);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_shop);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_shop:
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

        TextView checkTv = findViewById(R.id.checkTv);
        checkTv.setOnClickListener(view -> {
            if(products.size() > 0)
            {
                Intent intent = new Intent(getApplicationContext(),PromptActivity.class);
                intent.putExtra("sum",sum + sum/10 + 10000);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "You have no products in cart!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    void getData()
    {
        products.clear();
        SQLiteDatabase db = DBOpenHelper.getInstance(this).getReadableDatabase();
        Cursor cursor = db.query(
                DBOpenHelper.CARTS,
                new String[]{
                        DBOpenHelper.ID,
                        DBOpenHelper.NAME,
                        DBOpenHelper.PRICE,
                        DBOpenHelper.QTY,
                        DBOpenHelper.IMG,
                },
                null,
                null,
                null,
                null,
                null
        );

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                products.add(new Product(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.NAME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.PRICE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.QTY)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.IMG))
                ));
                sum+= cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.PRICE)) *
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.QTY));
            }while(cursor.moveToNext());
        }
    }
}