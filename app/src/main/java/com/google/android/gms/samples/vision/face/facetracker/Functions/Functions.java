package com.google.android.gms.samples.vision.face.facetracker.Functions;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/*
    this class contain global static function that take part from our ALGORITHM that we present.
 */
public class Functions {

    public static double CreateFloatNumber(int first_number, int second_number){
        return first_number + (0.01*second_number);
    }
    public static double CmToInch(double number){
        return number/2.54;
    }
    public static double CalculateFarPoint(double sph) {
        return 1/(sph)*100; // return in CM
    }
    // this function calculate the optimal diagonal size By the formula that we present in our algorithm
    public static double OptimalDiagonalSize (double distance){
        double pow = Math.pow(Globals.dm.heightPixels/(double)Globals.dm.widthPixels,2);
        double square = Math.sqrt(pow+1);
        double tan = Math.tan(1.0/60*PI/180);
        double d = distance*square*Globals.dm.widthPixels*tan;
        return d;
    }
}
