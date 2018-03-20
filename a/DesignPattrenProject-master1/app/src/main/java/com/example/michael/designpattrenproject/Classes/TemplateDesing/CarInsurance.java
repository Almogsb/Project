package com.example.michael.designpattrenproject.Classes.TemplateDesing;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by Michael on 13/01/2018.
 */

public class CarInsurance extends Insurance {

    private String carNum;

    public CarInsurance() {

    }

    @Override
    void InsuranceDetails(DatabaseReference myRef, String extraDetails) {
        this.carNum=extraDetails;
        this.description = "Car Insurance";
        myRef.child("Car").push().setValue(this);
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
