package com.example.bmiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void calculatebmi(View v){

        double height=0;
        double weight=0;
        double bmi=0;
        String msg="";

        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);

        Button button2 = (Button) findViewById(R.id.button2);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);

        weight = Double.valueOf(editText1.getText().toString());
        height = Double.valueOf(editText2.getText().toString());

        bmi = weight / (height * height);

        textView1.setText(String.valueOf(bmi));
        if(bmi<18.5){
            msg = "Underweight";
        }
        else if(bmi>18.5 && bmi<25){
            msg = "Normal Weight";
        }
        else if(bmi>25){
            msg = "Overweight";
        }
        textView2.setText(msg);

    }
}