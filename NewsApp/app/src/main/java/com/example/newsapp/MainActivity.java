package com.example.newsapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PropertyAdapter.OnItemListener {

    //Populate list items
    Integer[] imageList = {R.drawable.abc_news, R.drawable.nine_news, R.drawable.the_age_news, R.drawable.sydney_morning_herald};
    String[] textList = {"ABC News", "9News", "The Age", "Sydney Morning Herald"};

    //Implement Recycler View, Property Adapter and List
    RecyclerView listRecyclerView;
    PropertyAdapter propertyAdapter;
    List<Property> propertyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Find RecyclerView and assign
        listRecyclerView = findViewById(R.id.recyclerNewsItems);

        //Create adapter and set layout
        propertyAdapter = new PropertyAdapter(propertyList, MainActivity.this, this);
        listRecyclerView.setAdapter(propertyAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listRecyclerView.setLayoutManager(layoutManager);

        //Filling in Recycler View List
        for (int i = 0; i < imageList.length; i++) {
            int image = imageList[i];
            String nameText = textList[i];
            com.example.newsapp.Property property = new com.example.newsapp.Property(i, image, nameText);
            propertyList.add(property);
        }
    }

    @Override
    public void onItemClick(int position) {
        //get item click
        propertyList.get(position);

        //Call Fragment
        ChangeFragment(position);
    }

    //Create Fragment
    public void ChangeFragment(int position){
        Fragment fragment;
        if (position == 0){
            fragment = new FragmentABCNews();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentMain, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        if (position == 1){
            fragment = new FragmentNine();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentMain, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        if (position == 2){
            fragment = new FragmentTheAge();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentMain, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        if (position == 3){
            fragment = new FragmentSydney();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentMain, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}

