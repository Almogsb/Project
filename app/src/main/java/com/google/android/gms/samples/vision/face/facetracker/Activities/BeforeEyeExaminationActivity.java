package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.R;
import com.google.android.gms.samples.vision.face.facetracker.TouchImageView;

//import com.example.touch.R;
//import com.ortiz.touch.TouchImageView.OnTouchImageViewListener;


public class BeforeEyeExaminationActivity extends Activity implements View.OnClickListener , NumberPicker.OnValueChangeListener {

    private TouchImageView image;

    private Button next_btn;
    private NumberPicker line_np;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_eye_examination);

        next_btn = findViewById(R.id.BeforeEyeExamNextBtn);
        next_btn.setOnClickListener(this);

        line_np = findViewById(R.id.LinePicker);
        line_np.setOnClickListener(this);
        line_np.setMaxValue(11);
        line_np.setMinValue(0);
        line_np.setOnValueChangedListener(this);

        image = (TouchImageView) findViewById(R.id.img);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

    }


    @Override
    public void onClick(View v) {
        if (v == next_btn) {
            Intent intent = new Intent(this, AfterRemoveGlassActivity.class);
            startActivity(intent);
            finish();
        }
    }

        @Override
        public void onValueChange (NumberPicker picker,int oldVal, int newVal){
            if (picker == line_np) {
                Globals.line_examination_picker = newVal;
                if(newVal != 0)
                    next_btn.setVisibility(View.VISIBLE);
                else
                    next_btn.setVisibility(View.INVISIBLE);

            }
        }

}



