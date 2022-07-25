package com.example.qualification_js22_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qualification_js22_1.R;
import com.example.qualification_js22_1.model.Product;
import com.example.qualification_js22_1.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class TransactionHolder extends RecyclerView.ViewHolder {
    TextView noTv,timeTv,totalTv;


    public TransactionHolder(@NonNull View itemView) {
        super(itemView);
        noTv = itemView.findViewById(R.id.noTv);
        timeTv = itemView.findViewById(R.id.timeTv);
        totalTv = itemView.findViewById(R.id.totalTv);

    }
}

public class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {
    Context context;
    ArrayList<Transaction> transactionList;

    public TransactionAdapter(Context context, ArrayList<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.transaction,parent,false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.noTv.setText("No. "+transaction.getId());
        holder.totalTv.setText("Rp. "+transaction.getTotal());

//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date(((long)transaction.getTime())*1000L);
//        holder.timeTv.setText("" + formatter.format(date));
        holder.timeTv.setText(""+transaction.getTime());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}

