package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.R;


import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.touch.R;
import com.google.android.gms.samples.vision.face.facetracker.TouchImageView;
//import com.ortiz.touch.TouchImageView.OnTouchImageViewListener;

import java.text.DecimalFormat;


public class SingleTouchImageViewActivity extends Activity {

    private TouchImageView image;
    private TextView scrollPositionTextView;
    private TextView zoomedRectTextView;
    private TextView currentZoomTextView;
    private DecimalFormat df;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_touchimageview);
        //
        // DecimalFormat rounds to 2 decimal places.
        //
        df = new DecimalFormat("#.##");
        scrollPositionTextView = (TextView) findViewById(R.id.scroll_position);
        zoomedRectTextView = (TextView) findViewById(R.id.zoomed_rect);
        currentZoomTextView = (TextView) findViewById(R.id.current_zoom);
        image = (TouchImageView) findViewById(R.id.img);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setZoom((float) 2);
        //
        // Set the OnTouchImageViewListener which updates edit texts
        // with zoom and scroll diagnostics.
        //
        image.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onMove() {

                PointF point = image.getScrollPosition();
                RectF rect = image.getZoomedRect();
                float currentZoom = image.getCurrentZoom();
                boolean isZoomed = image.isZoomed();
                scrollPositionTextView.setText("x: " + df.format(point.x) + " y: " + df.format(point.y));
                zoomedRectTextView.setText("left: " + df.format(rect.left) + " top: " + df.format(rect.top)
                        + "\nright: " + df.format(rect.right) + " bottom: " + df.format(rect.bottom));
                currentZoomTextView.setText("getCurrentZoom(): " + currentZoom + " isZoomed(): " + isZoomed);
            }
        });
    }
}
