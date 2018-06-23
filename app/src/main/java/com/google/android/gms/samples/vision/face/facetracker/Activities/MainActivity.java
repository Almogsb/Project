package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.R;

import static java.lang.Math.sqrt;

/*
 ********************* MAIN ACTIVITY ********************
 */
/***
 MainActivity class include variables  the functionality and listeners like "OnValueChange" ,"onClick", "onBackPressed","setOnClickListener"
 Depending on the corresponding XML file
 ***/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private variables
    private Button get_started_btn;
    private Button eye_examination_btn;
    private Button help_btn;

    // "Constructor" ,initial the gui functionality called when the corresponding XML file load
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_started_btn = findViewById(R.id.GetStartedBtn);
        get_started_btn.setOnClickListener(this);

        eye_examination_btn = findViewById(R.id.eyeExaminationBtn);
        eye_examination_btn.setOnClickListener(this);

        help_btn = findViewById(R.id.HelpBtn);
        help_btn.setOnClickListener(this);

        //Calculate the inch of the screen
        getWindowManager().getDefaultDisplay().getMetrics(Globals.dm);
        double x = Math.pow(Globals.dm.widthPixels/Globals.dm.xdpi, 2);
        double y = Math.pow(Globals.dm.heightPixels/Globals.dm.ydpi, 2);
        double screenInches = sqrt(x+y);
        Log.d("debug", "Screen Inches:"+screenInches);
        Log.d("debug", "Screen Inches w:"+Globals.dm.widthPixels);
        Log.d("debug", "Screen Inches h:"+Globals.dm.heightPixels);

        Log.d("INCHES ! ", String.format("%.2f", screenInches)+" In");

    }
    @Override
    public void onClick(View v) {
        Intent intent;
        if(v == get_started_btn){
            intent=new Intent(this,AcuityNumberActivity.class);
            startActivity(intent);
            Globals.APP_MODE = 1;
        } else if(v == eye_examination_btn) {
            intent = new Intent(this, AcuityNumberActivity.class);
            startActivity(intent);
            Globals.APP_MODE = 0;
        }
        else if(v == help_btn) {
            intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        }
    }
}
