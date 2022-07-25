package com.example.qualification_js22_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.activity.ProductDetailActivity;
import com.example.qualification_js22_1.model.Product;

import java.util.ArrayList;

class CartHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView nameTv, priceTv,qtyTv,totalTv;


    public CartHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.item_img);
        nameTv = itemView.findViewById(R.id.item_name);
        priceTv = itemView.findViewById(R.id.item_price);
        qtyTv = itemView.findViewById(R.id.qtyTv);
        totalTv = itemView.findViewById(R.id.totalTv);

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartHolder> {
    Context context;
    ArrayList<Product> transactionList;

    public CartAdapter(Context context, ArrayList<Product> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.cart_product,parent,false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        Product product = transactionList.get(position);
        holder.img.setImageResource(product.getImage());
        holder.nameTv.setText(product.getName());
        holder.priceTv.setText("Rp. "+ product.getPrice());
        holder.qtyTv.setText(" "+ product.getQty());
        holder.totalTv.setText("Rp. "+ (product.getPrice() * product.getQty()));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}

