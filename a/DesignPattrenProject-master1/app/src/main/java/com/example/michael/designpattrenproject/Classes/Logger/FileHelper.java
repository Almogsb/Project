package com.example.michael.designpattrenproject.Classes.Logger;

/**
 * Created by almog on 23/02/2018.
 */

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tan on 2/18/2016.
 */
public class FileHelper {
    final static String fileName = setDate() + "-log.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/logger/" ;
    final static String TAG = FileHelper.class.getName();
    private String date;

    public static boolean saveToFile( String data){
        try {
            new File(path).mkdir();
            File file = new File( path + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write((setDate() +": "+ data + System.getProperty("line.separator")).getBytes());

            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;


    }
    private static String setDate() {
        Calendar cal=Calendar.getInstance();
        Date today = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        return formatter.format(today);

    }

}