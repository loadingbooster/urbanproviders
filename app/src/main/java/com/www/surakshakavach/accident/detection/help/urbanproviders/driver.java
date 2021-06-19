package com.www.surakshakavach.accident.detection.help.urbanproviders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class driver extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText daily,weekly,monthly;
    Button save;
    String phoneno="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        daily = findViewById(R.id.pricedailydriver);
        weekly = findViewById(R.id.priceweeklydriver);
        monthly = findViewById(R.id.pricemonthlydriver);
        save = findViewById(R.id.savebuttondriver);
        DatabaseReference ref = database.getReference("providers/driver");

        int c;
        try {
            FileInputStream fis = openFileInput("userphone");
            while ((c = fis.read()) != -1) {
                phoneno = phoneno + (char) c;
            }
            fis.close();
        }catch (Exception e){

        }
        Toast.makeText(this, phoneno, Toast.LENGTH_SHORT).show();

        ref.child(phoneno).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("snapshot", String.valueOf(snapshot));
                if(snapshot.child("oneday").getValue() !=null) {
                    daily.setText(snapshot.child("oneday").getValue().toString());
                    weekly.setText(snapshot.child("week").getValue().toString());
                    monthly.setText(snapshot.child("month").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(phoneno).child("oneday").setValue(daily.getText().toString());
                ref.child(phoneno).child("week").setValue(weekly.getText().toString());
                ref.child(phoneno).child("month").setValue(monthly.getText().toString());
                Toast.makeText(driver.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}