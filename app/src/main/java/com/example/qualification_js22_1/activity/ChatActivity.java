package com.example.qualification_js22_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.qualification_js22_1.DBOpenHelper;
import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.adapter.CartAdapter;
import com.example.qualification_js22_1.adapter.TransactionAdapter;
import com.example.qualification_js22_1.model.Product;
import com.example.qualification_js22_1.model.Transaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    ArrayList<Transaction> transactions = new ArrayList<Transaction>();


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_chat);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_chat:
                    return true;
                case R.id.nav_shop:
                    startActivity(new Intent(getApplicationContext(),ShopActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_maps:
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        getData();

        RecyclerView transactionRv = findViewById(R.id.rv);
        RecyclerView.Adapter  adapter = new TransactionAdapter(this,transactions);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        transactionRv.setLayoutManager(layoutManager);
        transactionRv.setAdapter(adapter);

    }

    void getData()
    {
        transactions.clear();
        SQLiteDatabase db = DBOpenHelper.getInstance(this).getReadableDatabase();
        Cursor cursor = db.query(
                DBOpenHelper.TRANSACTIONS,
                new String[]{
                        DBOpenHelper.T_ID,
                        DBOpenHelper.T_TOTAL,
                        DBOpenHelper.T_TIME,
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
                transactions.add(new Transaction(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.T_ID)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.T_TOTAL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.T_TIME))
                ));
            }while(cursor.moveToNext());
        }
    }
}