package com.google.android.gms.samples.vision.face.facetracker.Functions;

import android.content.Context;
import android.content.res.Resources;
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

    public static double CreateFloatNumber(int first_number, int second_number){return first_number + (0.01*second_number);}
    public static double CmToInch(double number){
        return number/2.54;
    }
    public static double CalculateFarPoint(double sph) {
       return 1/(sph)*100; // return in CM
    }

   /* public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }*/
    public static double OptimalDiagonalSize (double distance){
        double pow = Math.pow(Globals.dm.heightPixels/(double)Globals.dm.widthPixels,2);
        double square = Math.sqrt(pow+1);
        double tan = Math.tan(1.0/60*PI/180);
        double a = distance*square*Globals.dm.widthPixels*tan;
        return a;
    }



}
