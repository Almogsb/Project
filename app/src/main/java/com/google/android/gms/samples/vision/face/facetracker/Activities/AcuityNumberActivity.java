package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.FaceTrackerActivity;
import com.google.android.gms.samples.vision.face.facetracker.R;

import java.util.Arrays;
/***
    AcuityNumberActivity class include variables  the functionality and listeners like "OnValueChange" ,"onClick",
    Depending on the corresponding XML file
 ***/
public class AcuityNumberActivity extends AppCompatActivity implements View.OnClickListener , NumberPicker.OnValueChangeListener{

    //private variables
    private NumberPicker left_int_np ;
    private NumberPicker left_float_np ;
    private NumberPicker right_int_np ;
    private NumberPicker right_float_np ;
    private Button next_btn ;
    private Button back_btn ;


    // "Constructor" ,initial the gui functionality called when the corresponding XML file load
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acuity_number);

        right_float_np = findViewById(R.id.RightFloatNP);
        right_float_np.setOnClickListener(this);
        right_float_np.setDisplayedValues(Globals.number_picker_range);
        right_float_np.setMaxValue(19);
        right_float_np.setMinValue(0);
        right_float_np.setOnValueChangedListener(this);

        left_float_np = findViewById(R.id.LeftFloatNP);
        left_float_np.setOnClickListener(this);
        left_float_np.setDisplayedValues(Globals.number_picker_range);
        left_float_np.setMaxValue(19);
        left_float_np.setMinValue(0);
        left_float_np.setOnValueChangedListener(this);

        left_int_np = findViewById(R.id.LeftIntNP);
        left_int_np.setOnClickListener(this);
        left_int_np.setMaxValue(100);
        left_int_np.setMinValue(0);
        left_int_np.setOnValueChangedListener(this);

        right_int_np = findViewById(R.id.RightIntNP);
        right_int_np.setOnClickListener(this);
        right_int_np.setMaxValue(100);
        right_int_np.setMinValue(0);
        right_int_np.setOnValueChangedListener(this);

        next_btn = findViewById(R.id.AcuityNumberNextBtn);
        next_btn.setOnClickListener(this);

        back_btn = findViewById(R.id.AcuityNumberBackBtn);
        back_btn.setOnClickListener(this);

        //initial array values with 0
        Arrays.fill(Globals.eyes, 0);

    }
    @Override
    public void onClick(View v) {
         if( v == back_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else if(v == next_btn){
             int i;
             for(i=0;i<4;i++){
                 if(Globals.eyes[i] != 0)
                    break;
             }
             if(i==4)
                 Toast.makeText(AcuityNumberActivity.this,"Probably your vision is PERFECT :)", Toast.LENGTH_SHORT).show();
             else {
                 if (Globals.APP_MODE == 1) {
                     Intent intent = new Intent(this, FrequencyActivity.class);
                     startActivity(intent);
                     finish();
                 } else {//Globals.APP_MODE == 0
                     Intent intent = new Intent(this, FaceTrackerActivity.class);
                     startActivity(intent);
                     finish();
                 }
             }
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if(picker == left_int_np){
            Globals.eyes[Globals.LEFT_INT] = newVal;
        }
        else if(picker == left_float_np){
            Globals.eyes[Globals.LEFT_FLOAT] = Integer.valueOf(Globals.number_picker_range[newVal]);
        }
        else if(picker == right_int_np){
            Globals.eyes[Globals.RIGHT_INT] = newVal;
        }
        else if(picker == right_float_np){
            Globals.eyes[Globals.RIGHT_FLOAT] = Integer.valueOf(Globals.number_picker_range[newVal]);
        }
    }
}
