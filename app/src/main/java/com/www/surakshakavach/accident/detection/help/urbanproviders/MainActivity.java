package com.www.surakshakavach.accident.detection.help.urbanproviders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String[] choices = {"Select","Plumber","Carpentar","Driver","Painter","Electrician","Air Conditioner","Pest control","Vehicle repair","RO Serive","Cleaning Service"};

    EditText phonumber,fullname;
    Button register,loginsign;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String number,name,profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,choices);
        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spin.setAdapter(aa);

        phonumber = findViewById(R.id.phonenumber);
        fullname = findViewById(R.id.fullname);
        register = findViewById(R.id.register);
        loginsign = findViewById(R.id.loginsign);

        loginsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginactivity.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!profession.equals("select")) {
                    DatabaseReference myref = database.getReference("requests");
                    if (fullname.getText().toString().length() > 2 && phonumber.getText().toString().length() == 10) {
                        myref.child(phonumber.getText().toString()).child("name").setValue(fullname.getText().toString());
                        myref.child(phonumber.getText().toString()).child("profession").setValue(profession);
                        Toast.makeText(MainActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Please check the details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),choices[position] , Toast.LENGTH_LONG).show();
        profession = choices[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}