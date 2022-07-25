package com.example.qualification_js22_1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qualification_js22_1.DBOpenHelper;
import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView addBtn,titleTv,priceTv,qtyTv;
    private TextView minusTv, plusTv;
    private ImageView image;
    private Product curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        init();
        getProduct();
    }

    public void getProduct()
    {
        curr = (Product) getIntent().getExtras().getSerializable("object");
        image.setImageResource(curr.getImage());
        titleTv.setText(curr.getName());
        String price = "Rp. "+ curr.getPrice();
        priceTv.setText(price);
    }

    public void init()
    {
        addBtn = findViewById(R.id.addTv);
        titleTv = findViewById(R.id.titleTv);
        priceTv = findViewById(R.id.priceTv);
        qtyTv = findViewById(R.id.qtyTv);
        minusTv = findViewById(R.id.minusTv);
        plusTv = findViewById(R.id.plusTv);
        image = findViewById(R.id.image);

        minusTv.setOnClickListener(view -> {
            int qty = Integer.parseInt((String) qtyTv.getText());
            if(qty != 0)
            {
                qty -= 1;
                qtyTv.setText(""+qty);
            }
        });

        plusTv.setOnClickListener(view -> {
            int qty = Integer.parseInt((String) qtyTv.getText());
            qty += 1;
            qtyTv.setText(""+qty);
        });

        addBtn.setOnClickListener(view -> {
            SQLiteDatabase db = DBOpenHelper.getInstance(this).getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DBOpenHelper.NAME,titleTv.getText().toString());

            String newPrice = priceTv.getText().toString().substring(4);

            cv.put(DBOpenHelper.PRICE,newPrice);
            cv.put(DBOpenHelper.QTY,qtyTv.getText().toString());
            cv.put(DBOpenHelper.IMG,curr.getImage());
            db.insert(DBOpenHelper.CARTS, null,cv);

            Toast.makeText(this, "Successfully added to cart !", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}