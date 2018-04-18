package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.FaceTrackerActivity;
import com.google.android.gms.samples.vision.face.facetracker.R;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener , NumberPicker.OnValueChangeListener{



    private NumberPicker auto_np ;
    private Button next_btn ;
    private Button manual_btn ;
    private Button auto_btn ;
    private TextView auto_txt;

    public ThirdActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        auto_np = findViewById(R.id.AutoNP);
        auto_np.setOnClickListener(this);
        auto_np.setMaxValue(120);
        auto_np.setMinValue(1);

        next_btn = findViewById(R.id.NextBtn2);
        next_btn.setOnClickListener(this);

        auto_btn = findViewById(R.id.AutoBtn);
        auto_btn.setOnClickListener(this);

        manual_btn = findViewById(R.id.ManualBtn);
        manual_btn.setOnClickListener(this);

        auto_txt = findViewById(R.id.AutoTV2);

//        back_btn = findViewById(R.id.BackBtn);
//        back_btn.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        Intent intent;
//        if( v == back_btn) {
//            intent = new Intent(this, FirstActivity.class);
//            startActivity(intent);
//        }
        if(v == next_btn){
            intent = new Intent(this, FaceTrackerActivity.class);
            startActivity(intent);
        }

        else if(v == auto_btn){
            auto_txt.setVisibility(View.VISIBLE);
            auto_np.setVisibility(View.VISIBLE);
            next_btn.setVisibility(View.VISIBLE);

        }
        else if(v == manual_btn){
            Globals.frequency = 0;
            intent = new Intent(this, FaceTrackerActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if(picker == auto_np){
            Globals.frequency = newVal;
        }
    }
}