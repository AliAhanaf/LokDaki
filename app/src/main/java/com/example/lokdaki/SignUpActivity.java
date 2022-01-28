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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signupmail, signuppass;
    private TextView logintext;
    private Button buttonsignup;

    private EditText employername, employeraddress, employerphone;
    private Button savebutton;

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up Page");

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        signupmail = findViewById(R.id.SignUpEmailId);
        signuppass = findViewById(R.id.SignUpPasswordId);
        logintext = findViewById(R.id.loginMessagetId);
        buttonsignup = findViewById(R.id.SignUpButtonId);

        logintext.setOnClickListener(this);
        buttonsignup.setOnClickListener(this);

        employername = findViewById(R.id.NameId);
        employeraddress = findViewById(R.id.AddressId);
        employerphone = findViewById(R.id.PhoneId);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.SignUpButtonId:
                employerRegister();
                break;

            case R.id.loginMessagetId:
                Intent loginpage = new Intent (getApplicationContext(),LoginActivity.class);
                startActivity(loginpage);
                break;
        }

    }

    private void employerRegister() {

        String Email = signupmail.getText().toString().trim();
        String Pass = signuppass.getText().toString().trim();

        //checking the validity of the email
        if(Email.isEmpty())
        {
            signupmail.setError("Enter an email address");
            signuppass.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            signupmail.setError("Enter a valid email address");
            signupmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(Pass.isEmpty())
        {
            signuppass.setError("Enter a password");
            signuppass.requestFocus();
            return;
        }

        if(Pass.length()<8)
        {
            signuppass.setError("Minimum 8 Character password");
            signuppass.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    Intent Employerprofile = new Intent(getApplicationContext(),EmployerProfile.class);
                    Employerprofile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Employerprofile);

                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"User is already Registered",Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                            Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        String Name = employername.getText().toString();
        String Address = employeraddress.getText().toString();
        String Phone = employerphone.getText().toString();

        String Key = databaseReference.push().getKey();

        user userinfo = new user(Name,Address,Phone);
        databaseReference.child(Key).setValue(userinfo);

        System.out.println("Name"+Name);

    }


}