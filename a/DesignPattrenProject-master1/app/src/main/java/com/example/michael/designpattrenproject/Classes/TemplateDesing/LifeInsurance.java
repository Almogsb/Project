package com.example.michael.designpattrenproject.Classes.TemplateDesing;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by Michael on 13/01/2018.
 */

public class LifeInsurance extends Insurance {

    private String ID;

    public LifeInsurance() {

    }

    @Override
    void InsuranceDetails(DatabaseReference myRef, String extraDetails) {
        this.ID=extraDetails;
        this.description = "Life Insurance";
        myRef.child("Life").push().setValue(this);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
