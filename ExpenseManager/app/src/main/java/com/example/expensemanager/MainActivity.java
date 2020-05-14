package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declare persistent data
    ListView listExpenseItems;
    String[] expenseItems;
    public static final int REQUEST_CODE_GETMESSAGE = 101;
    float totalValue = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declare resources and assign list/items
        Resources res = getResources();
        listExpenseItems = (ListView) findViewById(R.id.list_expenseItems);
        expenseItems = res.getStringArray(R.array.ExpenseList);

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, expenseItems){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,100);
                return view;
            }
        };
        listExpenseItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Initialise new intent
                Intent intent = new Intent(MainActivity.this, Calculation.class);

                //Add key parameter for calculation
                Bundle key = new Bundle();
                key.putInt("key", position);
                intent.putExtras(key);

                //Start new activity
                startActivityForResult(intent, REQUEST_CODE_GETMESSAGE);
                //startActivity(intent);
            }
        });

        TextView totalSpend = (TextView) findViewById(R.id.text_totalSpend);
        totalSpend.setText("Total Spend: $" + totalValue);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Get result from Calculation
        String results = data.getStringExtra("returnValue");

        //Update spend
        TextView totalSpend = (TextView) findViewById(R.id.text_totalSpend);
        totalSpend.setText("Total Spend: $" + results);
    }
}

