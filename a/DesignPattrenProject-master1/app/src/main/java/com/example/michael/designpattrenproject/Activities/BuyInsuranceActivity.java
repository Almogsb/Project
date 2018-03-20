package com.example.michael.designpattrenproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.michael.designpattrenproject.R;

public class BuyInsuranceActivity extends AppCompatActivity {


    private ListView myList;
    private String[] insurances;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_insurance);
        insurances = new String[]{"Lost of work ability", "Life", "Apartment", "Car"};
        myList = findViewById(R.id.ourListViewInsurance);
        OurInsuranceAdapter ourInsuranceAdapter = new OurInsuranceAdapter(this, R.layout.our_insurance_listview_row, insurances);
        myList.setAdapter(ourInsuranceAdapter);
    }
}
