package com.www.surakshakavach.accident.detection.help.urbanproviders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class loginactivity extends AppCompatActivity {

    EditText phonelogin,phonepass;
    Button login;
    String prof;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        phonelogin = findViewById(R.id.phonelogin);
        phonepass = findViewById(R.id.passlogin);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phonepass.getText().toString().toString().charAt(0) == '1'){
                    prof = "driver";
                    intent = new Intent(loginactivity.this,driver.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == '2'){
                    prof = "maid";
                    intent = new Intent(loginactivity.this,maid.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == '3'){
                    prof = "waterpurifier";
                    intent = new Intent(loginactivity.this,ro.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == '4'){
                    prof = "ac";
                    intent = new Intent(loginactivity.this,ac.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == '5'){
                    prof = "electrician";
                    intent = new Intent(loginactivity.this, electrician.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == '6'){
                    prof = "plumber";
                    intent = new Intent(loginactivity.this, plumber.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == '7'){
                    prof = "carpentar";
                    intent = new Intent(loginactivity.this, carpentar.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == '8'){
                    prof = "men";
                }
                if(phonepass.getText().toString().toString().charAt(0) == '9'){
                    prof = "women";
                }
                if(phonepass.getText().toString().toString().charAt(0) == 'a'){
                    prof = "painter";
                    intent = new Intent(loginactivity.this,painter.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == 'b'){
                    prof = "repair";
                    intent = new Intent(loginactivity.this,repair.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == 'c'){
                    prof = "pest";
                    intent = new Intent(loginactivity.this,pest.class);
                }
                if(phonepass.getText().toString().toString().charAt(0) == 'd'){
                    prof = "yoga";
                }
                DatabaseReference myref = database.getReference("providers/"+prof);
                    myref.child(phonelogin.getText().toString()).child("pass").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.getValue() != null){
                                if (snapshot.getValue().toString().equals(phonepass.getText().toString())) {
                                    FileOutputStream fos;
                                    try {
                                        String filename = "userphone";
                                        String data = phonelogin.getText().toString();
                                        fos = openFileOutput(filename, Context.MODE_PRIVATE);
                                        //default mode is PRIVATE, can be APPEND etc.
                                        fos.write(data.getBytes());
                                        fos.close();
                                        Toast.makeText(getApplicationContext(), filename + "saved",
                                                Toast.LENGTH_LONG).show();
                                       // Intent intent = new Intent(loginactivity.this, driver.class);
                                        startActivity(intent);

                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(loginactivity.this, "wrong user", Toast.LENGTH_SHORT).show();
                                }
                        }else{
                                Toast.makeText(loginactivity.this, "Wrong User", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            }
        });
    }
}