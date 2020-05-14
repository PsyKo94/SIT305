package com.example.converterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Declare unit of measure
    private String unitOfMeasure;

    public String getSomeVariable() {
        return unitOfMeasure;
    }

    public void setSomeVariable(String someVariable) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declare conversion values
        final double centimeter = 100;
        final double foot = 3.28084;
        final double inch = 39.3701;
        final double gram = 1000;
        final double ounce = 35.274;
        final double pound = 2.20462;

        //Declare input
        final EditText inputNum = (EditText) findViewById(R.id.edit_num);

        //Declare dropdown menu
        Spinner spinner_menu = (Spinner) findViewById(R.id.list_unitMeasurement);
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_measurements, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        spinner_menu.setAdapter(adapter);
        spinner_menu.setOnItemSelectedListener(this);

        //Declare image buttons
        ImageButton img_button_distance = (ImageButton) findViewById(R.id.image_distance);
        ImageButton img_button_temp = (ImageButton) findViewById(R.id.image_temperature);
        ImageButton img_button_weight = (ImageButton) findViewById(R.id.image_weight);

        //Declare text views
        final TextView text_results1 = (TextView) findViewById(R.id.text_results1);
        final TextView text_results2 = (TextView) findViewById(R.id.text_results2);
        final TextView text_results3 = (TextView) findViewById(R.id.text_results3);

        //Convert distance
        img_button_distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unitOfMeasure.equals("Meter")){
                    //Check if input = null
                    double inputValue = 0;
                    try {
                        inputValue = Double.parseDouble(inputNum.getText().toString());
                    } catch(NumberFormatException nfe) {

                    }
                    //Calculate measurements
                    double resultCentimeter = centimeter * inputValue;
                    double resultFoot = foot * inputValue;
                    double resultInch = inch * inputValue;

                    //Round results
                    resultCentimeter = Math.round(resultCentimeter * 100)/100.0;
                    String resultCentimeterString = Double.toString(resultCentimeter);

                    resultFoot = Math.round(resultFoot * 100)/100.0;
                    String resultFootString = Double.toString(resultFoot);

                    resultInch = Math.round(resultInch * 100)/100.0;
                    String resultInchString = Double.toString(resultInch);

                    //Check if result has decimal
                    if((resultCentimeter-(int)resultCentimeter)!=0){
                        resultCentimeterString = Double.toString(resultCentimeter);
                    }
                    else
                    {
                        resultCentimeterString = String.format("%.0f", resultCentimeter);
                    }

                    if((resultFoot-(int)resultFoot)!=0){
                        resultFootString = Double.toString(resultFoot);
                    }
                    else
                    {
                        resultFootString = String.format("%.0f", resultFoot);
                    }

                    if((resultInch-(int)resultInch)!=0){
                        resultInchString = Double.toString(resultInch);
                    }
                    else
                    {
                        resultInchString = String.format("%.0f", resultInch);
                    }

                    //Display measurements
                    SpannableString ss1 = new SpannableString(resultCentimeterString + " Centimeter");
                    ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, resultCentimeterString.length(), 0);
                    ss1.setSpan(new RelativeSizeSpan(2f), 0, resultCentimeterString.length(), 0);
                    ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, resultCentimeterString.length(), 0);
                    text_results1.setText(ss1);

                    SpannableString ss2 = new SpannableString(resultFootString + " Foot");
                    ss2.setSpan(new StyleSpan(Typeface.BOLD), 0, resultFootString.length(), 0);
                    ss2.setSpan(new RelativeSizeSpan(2f), 0, resultFootString.length(), 0);
                    ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, resultFootString.length(), 0);
                    text_results2.setText(ss2);

                    SpannableString ss3 = new SpannableString(resultInchString + " Inch");
                    ss3.setSpan(new StyleSpan(Typeface.BOLD), 0, resultInchString.length(), 0);
                    ss3.setSpan(new RelativeSizeSpan(2f), 0, resultInchString.length(), 0);
                    ss3.setSpan(new ForegroundColorSpan(Color.RED), 0, resultInchString.length(), 0);
                    text_results3.setText(ss3);
                }
                //Display error
                else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Can't convert " + unitOfMeasure + " to a distance",Toast. LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //Convert length
        img_button_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unitOfMeasure.equals("Celsius")){
                    //Check if input = null
                    double inputValue = 0;
                    try {
                        inputValue = Double.parseDouble(inputNum.getText().toString());
                    } catch(NumberFormatException nfe) {

                    }
                    //Calculate measurements
                    double resultFahrenheit = (inputValue * (9/5)) + 32;
                    double resultKelvin = inputValue + 273.15;

                    //Round results
                    resultFahrenheit = Math.round(resultFahrenheit * 100)/100.0;
                    String resultFahrenheitString = Double.toString(resultFahrenheit);

                    resultKelvin = Math.round(resultKelvin * 100)/100.0;
                    String resultKelvinString = Double.toString(resultKelvin);

                    //Check if result has decimal
                    if((resultFahrenheit-(int)resultFahrenheit)!=0){
                        resultFahrenheitString = Double.toString(resultFahrenheit);
                    }
                    else
                    {
                        resultFahrenheitString = String.format("%.0f", resultFahrenheit);
                    }

                    if((resultKelvin-(int)resultKelvin)!=0){
                        resultKelvinString = Double.toString(resultKelvin);
                    }
                    else
                    {
                        resultKelvinString = String.format("%.0f", resultKelvin);
                    }

                    //Display measurements
                    SpannableString ss1 = new SpannableString(resultFahrenheitString + " Fahrenheit");
                    ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, resultFahrenheitString.length(), 0);
                    ss1.setSpan(new RelativeSizeSpan(2f), 0, resultFahrenheitString.length(), 0);
                    ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, resultFahrenheitString.length(), 0);
                    text_results1.setText(ss1);

                    SpannableString ss2 = new SpannableString(resultKelvinString + " Kelvin");
                    ss2.setSpan(new StyleSpan(Typeface.BOLD), 0, resultKelvinString.length(), 0);
                    ss2.setSpan(new RelativeSizeSpan(2f), 0, resultKelvinString.length(), 0);
                    ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, resultKelvinString.length(), 0);
                    text_results2.setText(ss2);

                    text_results3.setText("");
                }
                else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Can't convert " + unitOfMeasure + " to a temperature",Toast. LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //Convert weight
        img_button_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unitOfMeasure.equals("Kilograms")){
                    //Check if input = null
                    double inputValue = 0;
                    try {
                        inputValue = Double.parseDouble(inputNum.getText().toString());
                    } catch(NumberFormatException nfe) {

                    }
                    //Calculate measurements
                    double resultGram = gram * inputValue;
                    double resultOunce = ounce * inputValue;
                    double resultPound = pound * inputValue;

                    //Round results
                    resultGram = Math.round(resultGram * 100)/100.0;
                    String resultGramString = Double.toString(resultGram);

                    resultOunce = Math.round(resultOunce * 100)/100.0;
                    String resultOunceString = Double.toString(resultOunce);

                    resultPound = Math.round(resultPound * 100)/100.0;
                    String resultPoundString = Double.toString(resultPound);

                    //Check if result has decimal
                    if((resultGram-(int)resultGram)!=0){
                        resultGramString = Double.toString(resultGram);
                    }
                    else
                    {
                        resultGramString = String.format("%.0f", resultGram);
                    }

                    if((resultOunce-(int)resultOunce)!=0){
                        resultOunceString = Double.toString(resultOunce);
                    }
                    else
                    {
                        resultOunceString = String.format("%.0f", resultOunce);
                    }

                    if((resultPound-(int)resultPound)!=0){
                        resultPoundString = Double.toString(resultPound);
                    }
                    else
                    {
                        resultPoundString = String.format("%.0f", resultPound);
                    }

                    //Display measurements
                    SpannableString ss1 = new SpannableString(resultGramString + " Gram");
                    ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, resultGramString.length(), 0);
                    ss1.setSpan(new RelativeSizeSpan(2f), 0, resultGramString.length(), 0);
                    ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, resultGramString.length(), 0);
                    text_results1.setText(ss1);

                    SpannableString ss2 = new SpannableString(resultOunceString + " Ounce(Oz)");
                    ss2.setSpan(new StyleSpan(Typeface.BOLD), 0, resultOunceString.length(), 0);
                    ss2.setSpan(new RelativeSizeSpan(2f), 0, resultOunceString.length(), 0);
                    ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, resultOunceString.length(), 0);
                    text_results2.setText(ss2);

                    SpannableString ss3 = new SpannableString(resultPoundString + " Pound(lb)");
                    ss3.setSpan(new StyleSpan(Typeface.BOLD), 0, resultPoundString.length(), 0);
                    ss3.setSpan(new RelativeSizeSpan(2f), 0, resultPoundString.length(), 0);
                    ss3.setSpan(new ForegroundColorSpan(Color.RED), 0, resultPoundString.length(), 0);
                    text_results3.setText(ss3);
                }
                else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Can't convert " + unitOfMeasure + " to a weight",Toast. LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Assign UOM to string
        unitOfMeasure = parent.getItemAtPosition(position).toString();
        ////Toast.makeText(parent.getContext(), unitOfMeasure, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
