package com.example.uts_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.myViewHolder> {
    String data1[], data2[];
    int images[];
    Context context;

    public SimpleAdapter( Context context,String[] data1, String[] data2, int[] images) {
        this.data1 = data1;
        this.data2 = data2;
        this.images = images;
        this.context = context;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.row_list,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        holder.mytext1.setText(data1[position]);
        holder.mytext2.setText(data2[position]);
        holder.myimages.setImageResource(images[position]);
        //setting Click Listener
        //TODO : ERROR !

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2",data2[position]);
                intent.putExtra("myimage",images[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (images != null) {
            return images.length;
        } else {
            return 0;
        }
    }

    public class myViewHolder extends  RecyclerView.ViewHolder
    {
        TextView mytext1,mytext2;
        ImageView myimages;
        ConstraintLayout mainLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            mytext1 = itemView.findViewById(R.id.textView_title);
            mytext2 = itemView.findViewById(R.id.textView_description);
            myimages = itemView.findViewById(R.id.imageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
