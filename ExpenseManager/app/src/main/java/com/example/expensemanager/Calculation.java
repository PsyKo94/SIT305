package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Calculation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        //Grab key
        Bundle key = getIntent().getExtras();
        final int keyValue = key.getInt("key");

        //Declare image
        final ImageView image = (ImageView) findViewById(R.id.image);

        //Load images
        final int rent = getResources().getIdentifier("rent", "drawable", getPackageName());
        final int food = getResources().getIdentifier("food", "drawable", getPackageName());
        final int travel = getResources().getIdentifier("travel", "drawable", getPackageName());
        final int shopping = getResources().getIdentifier("shopping", "drawable", getPackageName());

        //Declare text views
        final TextView text_instructions = (TextView) findViewById(R.id.text_calculationInstructions);

        //Declare input
        final EditText inputNum = (EditText) findViewById(R.id.input_value);

        //Set text instructions and images
        switch(keyValue) {
            case 0:
                text_instructions.setText("Enter your home rent:");
                image.setImageResource(rent);
                break;

            case 1:
                text_instructions.setText("Enter your eating out:");
                image.setImageResource(food);
                break;

            case 2:
                text_instructions.setText("Enter your traveling:");
                image.setImageResource(travel);
                break;

            case 3:
                text_instructions.setText("Enter your shopping:");
                image.setImageResource(shopping);
                break;
        }

        //Create return/add button
        Button btnReturn = (Button) findViewById(R.id.button_add);

        //Initialise return button
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Initialise values
                Global g = (Global)getApplication();
                float rentValue = g.getRent();
                float foodValue = g.getFood();
                float travelValue = g.getTravel();
                float shoppingValue = g.getShopping();

                //Add values and update constants
                switch(keyValue) {
                    case 0:
                        rentValue = Float.parseFloat(inputNum.getText().toString());
                        g.setRent(rentValue);
                        break;

                    case 1:
                        foodValue = Float.parseFloat(inputNum.getText().toString());
                        g.setFood(foodValue);
                        break;

                    case 2:
                        travelValue = Float.parseFloat(inputNum.getText().toString());
                        g.setTravel(travelValue);
                        break;

                    case 3:
                        shoppingValue = Float.parseFloat(inputNum.getText().toString());
                        g.setShopping(shoppingValue);
                        break;
                }

                //Perform calculation
                Float total = rentValue + foodValue + travelValue + shoppingValue;
                Intent intent = new Intent();

                //Return result and finish activity
                intent.putExtra("returnValue", total.toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }



}
