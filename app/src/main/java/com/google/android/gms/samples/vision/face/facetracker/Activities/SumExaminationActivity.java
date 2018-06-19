package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//blalalalal
import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.R;

import static java.lang.Math.sqrt;

public class SumExaminationActivity extends AppCompatActivity implements View.OnClickListener {


    private Button next_btn;
    private TextView final_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_examination);
        next_btn = findViewById(R.id.SumExaminationFinishBtn);
        next_btn.setOnClickListener(this);
        final_txt = findViewById(R.id.SumExaminationTxt);
        final_txt.setOnClickListener(this);

        if(Globals.line_examination_result > Globals.line_examination_picker)
            final_txt.setText("Greate!\nNow you see better.");
        else
            final_txt.setText("Sorry!\nWe could not sucsses this time.\nPlease try again.");

    }
    @Override
    public void onClick(View v) {
        if (v == next_btn) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(SumExaminationActivity.this,"You can't go back until you will complete the test.", Toast.LENGTH_SHORT).show();

    }

}
