package com.ebookfrenzy.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class reportActivity extends AppCompatActivity {


    TextView dname , dreport, age, phonenumber, contry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        Intent next = getIntent();
        String n = next.getStringExtra("NAME");
        String report =  next.getStringExtra("report");
        String no = next.getStringExtra("PNumber");
        String co1 = next.getStringExtra("country");
        String age1 = next.getStringExtra("Age");



        age = findViewById(R.id.textViewage);
        phonenumber = findViewById(R.id.textViewnumber);
        contry = findViewById(R.id.textViewcountry);
        dname = findViewById(R.id.textViewName);
        dreport = findViewById(R.id.report);

        age.setText(age1);
        contry.setText(co1);
        phonenumber.setText(no);
        dname.setText(n);
        dreport.setText(report);




    }



}