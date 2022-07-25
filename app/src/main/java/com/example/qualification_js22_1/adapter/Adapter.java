package com.example.qualification_js22_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.activity.ProductDetailActivity;
import com.example.qualification_js22_1.activity.ShopActivity;
import com.example.qualification_js22_1.model.Product;

import java.util.ArrayList;

class Holder extends RecyclerView.ViewHolder{
    ImageView img;
    TextView nameTv,priceTv,buyTv;

    public Holder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.item_img);
        nameTv = itemView.findViewById(R.id.item_name);
        priceTv = itemView.findViewById(R.id.item_price);
        buyTv = itemView.findViewById(R.id.buyTv);
    }
}

public class Adapter extends RecyclerView.Adapter<Holder> {
    Context context;
    ArrayList<Product> productList;

    public Adapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Product product = productList.get(position);
        holder.img.setImageResource(product.getImage());
        holder.nameTv.setText(product.getName());
        holder.priceTv.setText("Rp. "+ product.getPrice());

        Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
        intent.putExtra("object",product);
        holder.buyTv.setOnClickListener(view -> context.startActivity(intent));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
