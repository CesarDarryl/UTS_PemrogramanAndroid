package com.example.uts_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleAdapter extends RecyclerView.Adapter {
    String data1[], data2[];
    int images[];
    Context context;

    public SimpleAdapter(String[] data1, String[] data2, int[] images, Context context) {
        this.data1 = data1;
        this.data2 = data2;
        this.images = images;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class myViewHolder extends  RecyclerView.ViewHolder
    {
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
