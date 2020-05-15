package com.example.dataviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Report1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report1);

        //Get List from Global
        Global g = (Global)getApplication();
        ArrayList<String> materialList = (ArrayList<String>) g.getMaterialList();

        //Display List
        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, materialList);
        ListView theListView = (ListView) findViewById(R.id.list_material);
        theListView.setAdapter(theAdapter);
    }


}
