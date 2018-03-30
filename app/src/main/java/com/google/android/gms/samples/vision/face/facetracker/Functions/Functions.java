package com.google.android.gms.samples.vision.face.facetracker.Functions;

import android.util.DisplayMetrics;
import android.util.Log;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by almog on 28/03/2018.
 */

public class Functions {

    public static double CreateFloatNumber(int first_number, int second_number){
        return first_number+(0.1*second_number);
    }
    public static double Average(int first_number, int second_number) {
        return (first_number+second_number)/2;
    }
    public static void CalculateGlassNumber(int left_eye, int right_eye) {
        double eyes_acuity_avg = Average(left_eye,right_eye);
    }
    public static float CalculateFarPoint(float acuity_number) {
        return 1/abs(acuity_number);
    }
    public static double OptimalDiagonalSize (double distance){
        double pow = Math.pow(Globals.dm.widthPixels/Globals.dm.heightPixels,2);
        double square = Math.sqrt(pow+1);
        double tan = Math.tan(1.0/60*PI/180);
        return distance/square*Globals.dm.widthPixels*tan;
    }



}
