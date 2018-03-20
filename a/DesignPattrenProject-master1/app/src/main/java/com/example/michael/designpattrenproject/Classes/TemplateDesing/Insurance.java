package com.example.michael.designpattrenproject.Classes.TemplateDesing;

import com.example.michael.designpattrenproject.Classes.Singleton.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Michael on 13/01/2018.
 */

public abstract class Insurance {


    abstract void InsuranceDetails(DatabaseReference myRef, String extraDetails);

    protected String description;
    protected String purchaseDate;
    protected String expirationDate;

    public  void BuyInsurance(String extraDetails){
        setDate();
        //get the reference to user->myInsurance in DB
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().
                child("Users").child(User.getInstance().getName()).child("MyInsurances");
        InsuranceDetails(myRef,extraDetails);

    }

    private void setDate() {
        Calendar cal=Calendar.getInstance();

        Date today = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        purchaseDate = formatter.format(today);

        cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+1);
        Date nextYear = cal.getTime();
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        expirationDate = formatter.format(nextYear);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
