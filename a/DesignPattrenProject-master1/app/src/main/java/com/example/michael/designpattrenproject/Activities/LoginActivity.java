package com.example.michael.designpattrenproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michael.designpattrenproject.Classes.Logger.FileHelper;
import com.example.michael.designpattrenproject.Classes.Singleton.User;
import com.example.michael.designpattrenproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private DatabaseReference myRef;
    private Button login;
    private EditText userName;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myRef = FirebaseDatabase.getInstance().getReference().child("Users");
        login = findViewById(R.id.loginBtn);
        userName = findViewById(R.id.nameText);
        password = findViewById(R.id.passText);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(userName.getText().toString()))
                        {
                            if(password.getText().toString().equals(dataSnapshot.
                                    child(userName.getText().
                                            toString()).
                                    child("password").getValue()
                                    .toString()))
                            {
                                User.getInstance().setName(userName.getText().toString());
                                //Logger
                                FileHelper.saveToFile("Login successfull");
                                Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                //Logger
                                Toast.makeText(LoginActivity.this,"Incorrect password",Toast.LENGTH_SHORT).show();
                                FileHelper.saveToFile("Incorrect password");
                            }
                        }
                        else {
                            //Logger
                            Toast.makeText(LoginActivity.this,"User not exist",Toast.LENGTH_SHORT).show();
                            FileHelper.saveToFile("User not exist");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
