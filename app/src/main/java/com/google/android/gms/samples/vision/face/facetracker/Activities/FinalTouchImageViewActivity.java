package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.FaceTrackerActivity;
import com.google.android.gms.samples.vision.face.facetracker.R;


import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.widget.Toast;

//import com.example.touch.R;
import com.google.android.gms.samples.vision.face.facetracker.TouchImageView;
//import com.ortiz.touch.TouchImageView.OnTouchImageViewListener;

import java.text.DecimalFormat;

/***
 FinalTouchImageViewActivity class include variables  the functionality and listeners like "OnValueChange" ,"onClick", "onBackPressed","setOnClickListener"
 Depending on the corresponding XML file
 ***/
public class FinalTouchImageViewActivity extends Activity implements View.OnClickListener {
    //private variables
    private TouchImageView image;
    private TextView currentZoomTextView;
    private DecimalFormat df;
    private Button LoadImageBtn;
    private Button back_btn;
    private static final int PICK_FROM_GALLERY = 1;

    // "Constructor" ,initial the gui functionality called when the corresponding XML file load
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_touchimageview);
        //
        // DecimalFormat rounds to 2 decimal places.
        //
        df = new DecimalFormat("##.##");
        currentZoomTextView = (TextView) findViewById(R.id.current_zoom);
        image = (TouchImageView) findViewById(R.id.img);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setZoom((float) Globals.scale_image);
        currentZoomTextView.setText(String.valueOf("ZOOM: " + df.format(Globals.scale_image)));
        LoadImageBtn = (Button) findViewById(R.id.buttonLoadPicture);

        back_btn = (Button)findViewById(R.id.FinalTouchImageBackBtn);
        back_btn.setOnClickListener(FinalTouchImageViewActivity.this);

        image.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onMove() {

                float currentZoom = image.getCurrentZoom();
                currentZoomTextView.setText("ZOOM: " + df.format(currentZoom));
            }
        });
         LoadImageBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                try {
                    if (ActivityCompat.checkSelfPermission(FinalTouchImageViewActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(FinalTouchImageViewActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                        Toast.makeText(FinalTouchImageViewActivity.this,"Access to the storage is needed for enter the gallery.", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

            //    TouchImageView imageView = (TouchImageView) findViewById(R.id.img);
                image.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            }
        }


    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == back_btn) {
            intent = new Intent(FinalTouchImageViewActivity.this, FrequencyActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, FaceTrackerActivity.class);
        startActivity(intent);
        Globals.cntFacesApearance = 0;
    }

}



