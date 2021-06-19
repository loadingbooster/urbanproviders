package com.www.surakshakavach.accident.detection.help.urbanproviders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class plumber extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    EditText visitingcharge;
    Button saveplumber;

    String phoneno="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber);

        visitingcharge = findViewById(R.id.plumbervisit);
        saveplumber = findViewById(R.id.saveplumber);

        DatabaseReference ref = database.getReference("providers/plumber");

        int c;
        try {
            FileInputStream fis = openFileInput("userphone");
            while ((c = fis.read()) != -1) {
                phoneno = phoneno + (char) c;
            }
            fis.close();
        }catch (Exception e){

        }

        ref.child(phoneno).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("snapshot", String.valueOf(snapshot));
                if(snapshot.child("oneday").getValue() !=null) {
                    visitingcharge.setText(snapshot.child("service").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        saveplumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(phoneno).child("visitcharge").setValue(visitingcharge.getText().toString());
                Toast.makeText(plumber.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }
}