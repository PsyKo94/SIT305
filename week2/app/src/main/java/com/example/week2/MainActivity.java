package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declare numbers
        final EditText firstNum = (EditText) findViewById(R.id.firstNum);
        final EditText secondNum = (EditText) findViewById(R.id.secondNum);

        //Declare buttons
        Button add = (Button) findViewById(R.id.addButton);
        Button subtract = (Button) findViewById(R.id.subtractButton);
        Button multiply = (Button) findViewById(R.id.multiplyButton);
        Button divide = (Button) findViewById(R.id.divideButton);

        //Declare result
        final TextView resultText = (TextView) findViewById(R.id.result);

        //Execute add function
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(firstNum.getText().toString());
                int num2 = Integer.parseInt(secondNum.getText().toString());
                int result = num1 + num2;
                // result + "" to concatenate result onto empty string
                resultText.setText(result + "");
            }
        });

        //Execute subtract function
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(firstNum.getText().toString());
                int num2 = Integer.parseInt(secondNum.getText().toString());
                int result = num1 - num2;
                // result + "" to concatenate result onto empty string
                resultText.setText(result + "");
            }
        });

        //Execute multiply function
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(firstNum.getText().toString());
                int num2 = Integer.parseInt(secondNum.getText().toString());
                int result = num1 * num2;
                // result + "" to concatenate result onto empty string
                resultText.setText(result + "");
            }
        });

        //Execute divide function
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(firstNum.getText().toString());
                int num2 = Integer.parseInt(secondNum.getText().toString());
                int result = num1 / num2;
                // result + "" to concatenate result onto empty string
                resultText.setText(result + "");
            }
        });
    }
}
