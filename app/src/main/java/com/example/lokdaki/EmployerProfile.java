package com.example.lokdaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployerProfile extends AppCompatActivity {

    private EditText employername, employeraddress, employerphone;
    private Button savebutton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference("Employers");

        employername = findViewById(R.id.NameId);
        employeraddress = findViewById(R.id.AddressId);
        employerphone = findViewById(R.id.PhoneId);
        savebutton = findViewById(R.id.SavebuttonId);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEmployerInfo();
            }
        });
    }

    private void SaveEmployerInfo()
    {
        String Name = employername.getText().toString().trim();
        String Address = employeraddress.getText().toString().trim();
        String Phone = employerphone.getText().toString().trim();

        String key = databaseReference.push().getKey();

        user userinfo = new user(Name,Address,Phone);
        databaseReference.child(key).setValue(userinfo);
        Toast.makeText(getApplicationContext(), "Information Saved", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.logoutId)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent mainpage = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mainpage);
        }

        return super.onOptionsItemSelected(item);
    }
}