package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>{
    //Create and set Property List and Context
    private List<Property> propertyList;
    private Context context;
    private OnItemListener onItemListener;

    public PropertyAdapter(List<Property> propertyList, MainActivity context, MainActivity onItemListener){
        this.propertyList = propertyList;
        this.context = context;
        this.onItemListener = onItemListener;
    }

    //Create, find and set image and text
    public class PropertyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public TextView textView;
        OnItemListener onItemListener;

        public PropertyViewHolder (View view, OnItemListener onItemListener){
            super(view);
            imageView = view.findViewById(R.id.imageMain);
            textView = view.findViewById(R.id.textMain);
            this.onItemListener = onItemListener;

            //Set on click listener
            itemView.setOnClickListener(this);
        }


        //On click sends result back to activity
        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public PropertyAdapter.PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //Create new view
        View itemView = LayoutInflater.from(context).inflate(R.layout.main_list_item, parent, false);
        return new PropertyAdapter.PropertyViewHolder(itemView, onItemListener);
    }

    //Override view
    @Override
    public void onBindViewHolder (PropertyViewHolder holder, int position){
        holder.imageView.setImageResource(propertyList.get(position).getImage());
        holder.textView.setText(propertyList.get(position).getNameText());
    }

    //return size of item list
    @Override
    public int getItemCount(){
        return propertyList.size();
    }

    //Method to get and return clicked item in list
    public interface OnItemListener{
        void onItemClick(int position);
    }
}
