package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.R;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {



    private NumberPicker left_int ;
    private Button near ;
    private TextView hello_text;

    public SecondActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        left_int = findViewById(R.id.numberPicker);
        left_int.setOnClickListener(this);
        left_int.setDisplayedValues(new String[]{"0","25","50","75"});
//        far = findViewById(R.id.FarBtn);
//        far.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
