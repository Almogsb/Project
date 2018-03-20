package com.example.michael.designpattrenproject.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.designpattrenproject.Classes.Logger.FileHelper;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.CarInsurance;
import com.example.michael.designpattrenproject.Classes.Singleton.User;
import com.example.michael.designpattrenproject.R;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView welcomeText;
    private Button buyBtn;
    private Button myBtn;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buyBtn=findViewById(R.id.buyInsuranceBtn);
        buyBtn.setOnClickListener(this);
        myBtn=findViewById(R.id.myInsuranceBtn);
        myBtn.setOnClickListener(this);
        welcomeText = findViewById(R.id.nameText);
        welcomeText.setText("Hello " + User.getInstance().getName());

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                FileHelper.saveToFile("Permission is granted");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                FileHelper.saveToFile("Request for Permission");
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            FileHelper.saveToFile("Permission is already exist");
        }
    }

    @Override
    public void onClick(View view) {
        if(view==buyBtn)
        {
            FileHelper.saveToFile("Buy insurence button pressed");
            Intent intent=new Intent(this,BuyInsuranceActivity.class);
            startActivity(intent);
        }
        if(view==myBtn)
        {
            FileHelper.saveToFile("My insurences button pressed");
            Intent intent=new Intent(this,MyInsuranceActivity.class);
            startActivity(intent);
        }

    }
}

