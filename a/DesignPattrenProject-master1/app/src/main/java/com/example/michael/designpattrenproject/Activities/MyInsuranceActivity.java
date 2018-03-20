package com.example.michael.designpattrenproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.michael.designpattrenproject.Classes.Iterator.Container;
import com.example.michael.designpattrenproject.Classes.Iterator.Iterator;
import com.example.michael.designpattrenproject.Classes.Singleton.User;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.ApartmentInsurance;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.CarInsurance;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.Insurance;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.LifeInsurance;
import com.example.michael.designpattrenproject.Classes.TemplateDesing.LostOfWorkAbilityInsurance;
import com.example.michael.designpattrenproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyInsuranceActivity extends AppCompatActivity implements Container {

    private ListView myList ;
    private DatabaseReference myRef;
    private ArrayList<Insurance> my_insurances;
    private String[] insurance_names = {"Apartment" ,"Car" ,"Life" ,"LostOfWorkAbility"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_insurance);
        myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(User.getInstance().getName()).child("MyInsurances");
        my_insurances = new ArrayList<Insurance>();
        myList = findViewById(R.id.ourListViewInsurance);
        final MyInsuranceAdapter myInsuranceAdapter = new MyInsuranceAdapter(MyInsuranceActivity.this, R.layout.our_insurance_listview_row, my_insurances);
        myList.setAdapter(myInsuranceAdapter);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (Iterator iter = getIterator(); iter.hasNext(); ) {
                    String name_temp = iter.next().toString();
                    for (DataSnapshot snap : dataSnapshot.child(name_temp).getChildren()) {
                        myInsuranceAdapter.addId(snap.getKey());
                        switch (name_temp) {
                            case "Apartment":
                                my_insurances.add(snap.getValue(ApartmentInsurance.class));
                                break;
                            case "Car":
                                my_insurances.add(snap.getValue(CarInsurance.class));
                                break;
                            case "Life":
                                my_insurances.add(snap.getValue(LifeInsurance.class));
                                break;
                            case "LostOfWorkAbility":
                                my_insurances.add(snap.getValue(LostOfWorkAbilityInsurance.class));
                                break;
                        }
                    }
                }
                myInsuranceAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    @Override
    public Iterator getIterator() {
       return new NameIterator();
     }

    private class NameIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {

            if(index < insurance_names.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return insurance_names[index++];
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        my_insurances.clear();
        super.onBackPressed();
    }
}