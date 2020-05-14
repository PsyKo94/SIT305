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

public class Property {
    //Declare Recycler View info
    private int id, image;
    private String nameText;
    //Constructor
    public Property(int id, int image, String nameText){
        this.id = id;
        this.image = image;
        this.nameText = nameText;
    }

    //Get and Set
    public int getId(){
        return id;
    }
    public void setId(){
        this.id = id;
    }

    public int getImage(){
        return image;
    }
    public void setImage(){
        this.image = image;
    }

    public String getNameText(){
        return nameText;
    }
    public void SetNameText(){
        this.nameText = nameText;
    }
}


