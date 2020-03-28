package com.example.try_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>{
    private  ArrayList<ExampleItem> mExampleList;//getting information into adapter
    private  onItemClickListener mListener;

    public interface onItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(onItemClickListener listener)
    {
        mListener = listener;
    }

    public static class ExampleViewHolder extends  RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            //REFERENCES TO LAYOUT
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView1);
            mTextView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //To pass the Static class is , Add in Constructor of the class

                    //call Interface
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    //TODO : Get DATA out of array list INTO Adapter. is to passit into constructor
    public ExampleAdapter(ArrayList<ExampleItem> exampleItems)
    {
        mExampleList = exampleItems; // passing item to to get information item to adapter
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //GETTING VIEW HOLDER
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder exampleviewholder = new ExampleViewHolder(view, mListener);
        return exampleviewholder;


    }

    //TODO : PASSIT INFORMATION INSIDE This class
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        //instance
        //Get item same as the Position
        ExampleItem currentItem = mExampleList.get(position);

        //get information
        holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mTextView1.setText(currentItem.getmText1());
        holder.mTextView2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
