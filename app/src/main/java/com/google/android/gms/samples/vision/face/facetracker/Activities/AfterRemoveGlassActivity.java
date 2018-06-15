package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.R;

import static java.lang.Math.sqrt;

public class AfterRemoveGlassActivity extends AppCompatActivity implements View.OnClickListener {



    private Button next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_remove_glasses);
        next_btn = findViewById(R.id.AfterRemoveNextBtn);
        next_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == next_btn) {
            Intent intent = new Intent(this, AfterEyeExaminationActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(AfterRemoveGlassActivity.this,"You can't go back until you will complete the test.", Toast.LENGTH_SHORT).show();

    }


}
