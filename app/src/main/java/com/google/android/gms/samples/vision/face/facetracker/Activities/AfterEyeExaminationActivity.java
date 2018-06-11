package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.R;
import com.google.android.gms.samples.vision.face.facetracker.TouchImageView;

import java.text.DecimalFormat;

//import com.example.touch.R;
//import com.ortiz.touch.TouchImageView.OnTouchImageViewListener;


public class AfterEyeExaminationActivity extends Activity implements View.OnClickListener , NumberPicker.OnValueChangeListener{

    private TouchImageView image;
    private TextView currentZoomTextView;
    private DecimalFormat df;
    private Button next_btn;
    private NumberPicker line_np_res;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_eye_examination);

        // DecimalFormat rounds to 2 decimal places.
        df = new DecimalFormat("##.##");

        currentZoomTextView = (TextView) findViewById(R.id.current_zoom);
        image = (TouchImageView) findViewById(R.id.img);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setZoom((float) Globals.scale_image);
        currentZoomTextView.setText(String.valueOf("ZOOM: " + df.format(Globals.scale_image)));

        next_btn = findViewById(R.id.AfterEyeExamNextBtn);
        next_btn.setOnClickListener(this);
        line_np_res = findViewById(R.id.LinePickerRes);
        line_np_res.setOnClickListener(this);
        line_np_res.setMaxValue(11);
        line_np_res.setMinValue(0);
        line_np_res.setOnValueChangedListener(this);

        image = (TouchImageView) findViewById(R.id.img);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        image.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onMove() {

                float currentZoom = image.getCurrentZoom();
                currentZoomTextView.setText("Zoom Image: " + df.format(currentZoom));
            }
        });
    }
        @Override
        public void onClick(View v) {
            if (v == next_btn) {
                Intent intent = new Intent(this, SumExaminationActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onValueChange (NumberPicker picker,int oldVal, int newVal){
            if (picker == line_np_res) {
                Globals.line_examination_result = newVal;
                if(newVal != 0)
                    next_btn.setVisibility(View.VISIBLE);
                else
                    next_btn.setVisibility(View.INVISIBLE);

            }
        }
}



