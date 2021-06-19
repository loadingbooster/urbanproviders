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

public class ac extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText service,install,repair;
    Button save;
    String phoneno="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);

        service = findViewById(R.id.servicechargeac);
        install = findViewById(R.id.installchargeac);
        repair = findViewById(R.id.repairchargeac);
        save = findViewById(R.id.savebuttonac);
        DatabaseReference ref = database.getReference("providers/ac");

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
                    service.setText(snapshot.child("service").getValue().toString());
                    install.setText(snapshot.child("install").getValue().toString());
                    repair.setText(snapshot.child("repair").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(phoneno).child("service").setValue(service.getText().toString());
                ref.child(phoneno).child("install").setValue(install.getText().toString());
                ref.child(phoneno).child("repair").setValue(repair.getText().toString());
                Toast.makeText(ac.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}