package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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


public class SingleTouchImageViewExaminationActivity extends Activity implements View.OnClickListener , NumberPicker.OnValueChangeListener{

    private TouchImageView image;
    private TextView currentZoomTextView;
    private DecimalFormat df;
    private Button buttonLoadImage;

    private Button next_btn;
    private NumberPicker line_np_res;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_touchimageview_examination);
        //
        // DecimalFormat rounds to 2 decimal places.
        //
        df = new DecimalFormat("#.##");

        currentZoomTextView = (TextView) findViewById(R.id.current_zoom);
        image = (TouchImageView) findViewById(R.id.img);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setZoom((float) Globals.scale_image);
        currentZoomTextView.setText(String.valueOf(Globals.scale_image));

        next_btn = findViewById(R.id.NextBtn4);
        next_btn.setOnClickListener(this);

        line_np_res = findViewById(R.id.LinePickerRes);
        line_np_res.setOnClickListener(this);
        line_np_res.setMaxValue(11);
        line_np_res.setMinValue(0);
        line_np_res.setOnValueChangedListener(this);

        image = (TouchImageView) findViewById(R.id.img);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //
        // Set the OnTouchImageViewListener which updates edit texts
        // with zoom and scroll diagnostics.
        //

        //
        //
        buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        //
        image.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onMove() {

                PointF point = image.getScrollPosition();
                RectF rect = image.getZoomedRect();
                float currentZoom = image.getCurrentZoom();
                boolean isZoomed = image.isZoomed();
             /*   scrollPositionTextView.setText("x: " + df.format(point.x) + " y: " + df.format(point.y));
                zoomedRectTextView.setText("left: " + df.format(rect.left) + " top: " + df.format(rect.top)
                        + "\nright: " + df.format(rect.right) + " bottom: " + df.format(rect.bottom));*/
                currentZoomTextView.setText("getCurrentZoom(): " + currentZoom + " isZoomed(): " + isZoomed);
            }
        });

        /*buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });*/
    }
        @Override
        public void onClick(View v) {
            if (v == next_btn) {
                Intent intent = new Intent(this, SingleTouchImageViewExaminationActivity.class);
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



