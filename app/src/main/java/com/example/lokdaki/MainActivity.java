package com.example.lokdaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginbutton;
    private Button signupbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbutton = findViewById(R.id.mainloginButtonId);
        signupbutton = findViewById(R.id.mainSignUpButtonId);

        loginbutton.setOnClickListener(this);
        signupbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.mainSignUpButtonId:
                Intent signuppage = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(signuppage);
                break;

            case R.id.mainloginButtonId:
                Intent loginpage = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginpage);
                break;
        }

    }
}