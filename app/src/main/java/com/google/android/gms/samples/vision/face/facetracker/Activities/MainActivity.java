package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.Functions.Functions;
import com.google.android.gms.samples.vision.face.facetracker.R;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private Button get_started_btn;
  //  private TextView hello_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_started_btn = findViewById(R.id.GetStartedBtn);
        get_started_btn.setOnClickListener(this);

        //Calculate the inch of the screen
        getWindowManager().getDefaultDisplay().getMetrics(Globals.dm);
        double x = Math.pow(Globals.dm.widthPixels/Globals.dm.xdpi, 2);
        double y = Math.pow(Globals.dm.heightPixels/Globals.dm.ydpi, 2);
        double screenInches = sqrt(x+y);
        Log.d("debug", "Screen Inches:"+screenInches);
        Log.d("debug", "Screen Inches w:"+Globals.dm.widthPixels);
        Log.d("debug", "Screen Inches h:"+Globals.dm.heightPixels);

        Log.d("INCHES ! ", String.format("%.2f", screenInches)+" In");
   /*     float Density = (float) sqrt(((Globals.dm.widthPixels * Globals.dm.heightPixels) + (Globals.dm.heightPixels * Globals.dm.heightPixels)) / screenInches);
        Log.d("DENSITY ! ", String.format("%.2f",Density)+" In");*/
        //End calculate the inch of the screen

        // /Convert dp to pixels of the screen
        //Globals.dp_to_pixels = Functions.convertDpToPixel(480 , this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,FirstActivity.class);
        startActivity(intent);
    }
}
