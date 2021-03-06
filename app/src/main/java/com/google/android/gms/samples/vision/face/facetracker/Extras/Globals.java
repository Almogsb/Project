package com.google.android.gms.samples.vision.face.facetracker.Extras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.FaceTrackerActivity;
import com.google.android.gms.samples.vision.face.facetracker.R;

/*
   this classs containing GLOBAL variables that used more then one activity (GUI) and not in paraller ,
   and reflect the mode , and the "DB" of the current use with the app.
 */

public class Globals {

    // finals  variables
    public final static int LEFT_INT = 0;
    public final static int LEFT_FLOAT = 1;
    public final static int RIGHT_INT = 2;
    public final static int RIGHT_FLOAT = 3;

    //Global variables
    public static Integer[] eyes = new Integer[4]; // 0 - left int, 1 - left float , 2 - right int , 3 - right float
    public static DisplayMetrics dm = new DisplayMetrics();
    public static String[] number_picker_range = new String[]{"0","5","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90","95"};
    public static Integer frequency = 5;
    public static double distance = 0;
    public static double line_examination_picker = 0;
    public static double line_examination_result = 0;
    public static double scale_image = 0;
    public static double SPH = 0;
    public static double cntFacesApearance = 0;
    public static double APP_MODE = 0;
}
