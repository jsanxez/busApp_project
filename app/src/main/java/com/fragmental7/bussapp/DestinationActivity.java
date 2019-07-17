package com.fragmental7.bussapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DestinationActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference();
    private Button btnCC;
    private Button btnSR;
    private Button btnFF;
    private String destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        // set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCC = findViewById(R.id.btn_cc);
        btnSR = findViewById(R.id.btn_sr);
        btnFF = findViewById(R.id.btn_free);

        //Ccoyahuacho:
        btnCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(DestinationActivity.this, "touched cc", Toast.LENGTH_SHORT).show();
                searchDestinationValue("CC");
            }
        });
        //Santa Rosa:
        btnSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDestinationValue("SR");
            }
        });
        //Libre:
        btnFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDestinationValue("FF");
            }
        });

    }

    private void searchDestinationValue(final String placeValue) {
        // db listener:
        ref.child("usuarios").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean found = false;
                // looking for the destination value:
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    destination = user.getDestination();

                    // if the search value exists then open the map:
                    if (destination.equals(placeValue)) {
                        found = true;
                        Intent intent = new Intent(DestinationActivity.this, MapsActivity.class);
                        intent.putExtra("dni", snapshot.getKey());
                        startActivity(intent);
                    }
                }// END FOR

                // alert! with this code, may appear when the db values changes:
                if (!found) {
                    Toast.makeText(DestinationActivity.this, "No disponible", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DestinationActivity.this, "Error al conectar ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
