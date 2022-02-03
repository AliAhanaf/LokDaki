package com.example.lokdaki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<WorkerData> list;

    public MyAdapter(Context context, ArrayList<WorkerData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View nView = LayoutInflater.from(context).inflate(R.layout.list, parent, false);

        return new MyViewHolder(nView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        WorkerData wData = list.get(position);
        holder.fullName.setText(WorkerData.getFullName());
        holder.phone.setText(WorkerData.getPhoneNumber());
        holder.age.setText(WorkerData.getWorkerAge());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fullName, phone, age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.worker_name);
            phone = itemView.findViewById(R.id.worker_phn);
            age = itemView.findViewById(R.id.worker_age);
        }
    }
}
