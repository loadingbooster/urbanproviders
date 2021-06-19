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

public class maid extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText cookonce,cooktwice,dishonce,dishtwice,moponce,moptwice,shopclean;
    Button save;
    String phoneno="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid);

        cookonce = findViewById(R.id.cookonce);
        cooktwice = findViewById(R.id.cooktwice);
        dishonce = findViewById(R.id.dishonce);
        dishtwice = findViewById(R.id.dishtwice);
        moponce = findViewById(R.id.moponce);
        moptwice = findViewById(R.id.moptwice);
        shopclean = findViewById(R.id.shopclean);
        save = findViewById(R.id.savebuttonmaid);

        DatabaseReference ref = database.getReference("providers/maid");

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
                    cookonce.setText(snapshot.child("cookonce").getValue().toString());
                    cooktwice.setText(snapshot.child("cooktwice").getValue().toString());
                    dishonce.setText(snapshot.child("dishonce").getValue().toString());
                    dishtwice.setText(snapshot.child("dishtwice").getValue().toString());
                    moponce.setText(snapshot.child("moponce").getValue().toString());
                    moptwice.setText(snapshot.child("moptwice").getValue().toString());
                    shopclean.setText(snapshot.child("shopclean").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(phoneno).child("cookonce").setValue(cookonce.getText().toString());
                ref.child(phoneno).child("cooktwice").setValue(cooktwice.getText().toString());
                ref.child(phoneno).child("dishonce").setValue(dishonce.getText().toString());
                ref.child(phoneno).child("dishtwice").setValue(dishtwice.getText().toString());
                ref.child(phoneno).child("moponce").setValue(moponce.getText().toString());
                ref.child(phoneno).child("moptwice").setValue(moptwice.getText().toString());
                ref.child(phoneno).child("shopclean").setValue(shopclean.getText().toString());
            }
        });

    }
}