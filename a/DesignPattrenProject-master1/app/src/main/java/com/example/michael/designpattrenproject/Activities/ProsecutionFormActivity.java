package com.example.michael.designpattrenproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.michael.designpattrenproject.Classes.Logger.FileHelper;
import com.example.michael.designpattrenproject.Classes.Singleton.User;
import com.example.michael.designpattrenproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProsecutionFormActivity extends AppCompatActivity {

    EditText prosecutionText;
    Button claimBtn;
    String claim_id;
    String insurance_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prosecution_form);
        prosecutionText = findViewById(R.id.claimText);
        claimBtn = findViewById(R.id.claimBtn);
        claimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                claim_id = getIntent().getStringExtra("claim_id");
                insurance_name = getIntent().getStringExtra("insurance_name");
                FirebaseDatabase.getInstance().getReference().
                        child("Users").child(User.getInstance().getName()).child("MyInsurances")
                        .child(insurance_name.substring(0,insurance_name.indexOf(" ")).trim()).child(claim_id).child("Claims").push().setValue(prosecutionText.getText().toString());
                Toast.makeText(ProsecutionFormActivity.this,"Submited successfully!",Toast.LENGTH_SHORT).show();
                FileHelper.saveToFile("Submit button pressed for " + insurance_name);
                finish();
            }
        });
    }
}
