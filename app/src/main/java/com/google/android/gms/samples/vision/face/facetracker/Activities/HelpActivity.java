package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.R;

import static java.lang.Math.sqrt;

/*
 ********************* HELP ACTIVITY ********************
 */

/***
 MainActivity class include variables  the functionality and listeners like "OnValueChange" ,"onClick", "onBackPressed","setOnClickListener"
 Depending on the corresponding XML file
 ***/
public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    //private variables
    private Button back_btn;

    // "Constructor" ,initial the gui functionality called when the corresponding XML file load
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        back_btn = findViewById(R.id.HelpBackBtn);
        back_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == back_btn) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
