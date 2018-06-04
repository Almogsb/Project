package com.google.android.gms.samples.vision.face.facetracker.Extras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.FaceTrackerActivity;
import com.google.android.gms.samples.vision.face.facetracker.R;

public class Globals {

    //Defines variables
    public final static int LEFT_INT = 0;
    public final static int LEFT_FLOAT = 1;
    public final static int RIGHT_INT = 2;
    public final static int RIGHT_FLOAT = 3;



    //Global variables

    public static Integer[] eyes = new Integer[4]; // 0 - left int, 1 - left float , 2 - right int , 3 - right float
    public static Integer frequency = 0;

    public static double distance = 0;
    public static double distance0 = 0;
    public static double scale_image = 0;
    public static double SPH = 0;


    public static double dp_to_pixels = 0;
    public static DisplayMetrics dm = new DisplayMetrics();

    public static String[] number_picker_range = new String[]{"0","25","50","75"};
}
