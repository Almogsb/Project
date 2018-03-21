package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.R;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {



    private Button far ;
    private Button near ;
    private TextView hello_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        near = findViewById(R.id.NearBtn);
        near.setOnClickListener(this);
        far = findViewById(R.id.FarBtn);
        far.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v==near) {
            Globals.mode = Globals.NEAR;
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
            finish();
        }
        else if (v==far)
        {
            Globals.mode = Globals.FAR;
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
