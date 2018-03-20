package com.example.michael.designpattrenproject.Classes.TemplateDesing;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by Michael on 13/01/2018.
 */

public class LostOfWorkAbilityInsurance extends Insurance {

    private String ID;

    public LostOfWorkAbilityInsurance() {

    }

    @Override
    void InsuranceDetails(DatabaseReference myRef, String extraDetails) {
        this.ID=extraDetails;
        this.description = "Lost Of Work Ability Insurance";
        myRef.child("LostOfWorkAbility").push().setValue(this);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
