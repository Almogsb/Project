package com.example.michael.designpattrenproject.Classes.TemplateDesing;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by Michael on 13/01/2018.
 */

public class ApartmentInsurance extends Insurance {

    private String address;

    public ApartmentInsurance() {

    }

    @Override
    void InsuranceDetails(DatabaseReference myRef, String extraDetails) {
        this.description = "Apartment Insurance";
        this.address=extraDetails;
        myRef.child("Apartment").push().setValue(this);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
