package com.example.lokdaki;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class load_employer_data  extends AppCompatActivity {

    TextView fName;
    FirebaseAuth fireAuth;
    DatabaseReference dbRef;

    String userID;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_profile);

        fName = findViewById(R.id.full_name);

        fireAuth = FirebaseAuth.getInstance();
    }
}
