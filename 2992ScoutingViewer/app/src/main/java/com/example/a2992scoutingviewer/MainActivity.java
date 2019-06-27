package com.example.a2992scoutingviewer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button selectButton;
    private CheckBox firebaseBox;
    private Spinner teamSpinner;
    public static ArrayAdapter<String> teamAdapter;
    public static List<String> allTeams = new ArrayList<>();
    public static DatabaseReference mDatabase;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseBox = findViewById(R.id.firebaseBox);
        selectButton = findViewById(R.id.selectButton);
        teamSpinner = findViewById(R.id.teamSpinner);

        Log.d(TAG, String.valueOf(firebaseBox.isChecked()));

        Intent intentExtras = getIntent();
        Bundle bundleExtras = intentExtras.getExtras();

        if(bundleExtras == null){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            mDatabase = FirebaseDatabase.getInstance().getReference();
            Log.d(TAG, "bundleExtras has run");
        } else {
            firebaseBox.setChecked(Boolean.valueOf(bundleExtras.getString("firebase")));
        }

        teamAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, allTeams);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSpinner.setAdapter(teamAdapter);

        getFirebaseTeams();


        firebaseBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    mDatabase.keepSynced(false);
                    Log.d(TAG, "firebase not syncing");
                } else {
                    mDatabase.keepSynced(true);
                    Log.d(TAG, "firebase syncing");
                }
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TeamActivity.class);
                intent.putExtra("teamNum", teamSpinner.getSelectedItem().toString());
                intent.putExtra("firebase", String.valueOf(firebaseBox.isChecked()));
                Log.d("Started Activity with", teamSpinner.getSelectedItem().toString());

                startActivity(intent);
            }
        });




    }

    public void sortTeamNums(){
        List<Integer> tempList = new ArrayList<>();
        for(String i: allTeams){
            tempList.add(Integer.valueOf(i));
        }
        Collections.sort(tempList);
        for(int i=0; i < tempList.size(); i++){
            allTeams.set(i, String.valueOf(tempList.get(i)));
        }
    }

    public void getFirebaseTeams(){
        DatabaseReference database = mDatabase;
        //allTeams.add("0");
        ChildEventListener valueEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("DataSnapshot read ", dataSnapshot.getKey());
                if (!allTeams.contains(dataSnapshot.getKey())){
                    allTeams.add(dataSnapshot.getKey());
                    Log.d("Current size allTeams", Integer.toString(MainActivity.allTeams.size()));
                    sortTeamNums();
                    teamAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("CancelledLog", databaseError.getMessage());
            }
        };
        database.child("Teams").addChildEventListener(valueEventListener);
        Log.d("Size of allteams", Integer.toString(allTeams.size()));
        Log.d("Database team reference", database.child("Teams").toString());
    }
}
