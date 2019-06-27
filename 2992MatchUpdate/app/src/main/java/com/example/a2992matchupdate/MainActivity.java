package com.example.a2992matchupdate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private List<String> matchNumbers;
    private Button start;
    private Spinner matchNumbersSpinner;
    private ArrayAdapter<String> matchAdapter;
    private List<String> allMatches;
    private String numOfMatches;
    public static DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentExtras = getIntent();
        Bundle bundleExtras = intentExtras.getExtras();

        if(bundleExtras == null){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }

        matchNumbers = new ArrayList<>();
        allMatches = new ArrayList<>();
        
        matchNumbersSpinner = findViewById(R.id.matchTeamSpinner);
        start = findViewById(R.id.matchSelectButton);

        matchAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, allMatches);
        matchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matchNumbersSpinner.setAdapter(matchAdapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LaunchActivity.class);
                intent.putExtra("matchNum", matchNumbersSpinner.getSelectedItem().toString());

                startActivity(intent);
            }
        });


        fillMatches();
        
        
    }

    public void fillMatches(){

        final DatabaseReference database = mDatabase;
        ValueEventListener matchListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                numOfMatches = dataSnapshot.getValue().toString();
                if(numOfMatches.isEmpty()){
                    Log.d("onDataChange", "returned");
                    return;
                }
                try{
                    Log.d("numOfMatches", numOfMatches);
                    for(int i = 0; i < Integer.valueOf(numOfMatches); i ++){
                        allMatches.add(String.valueOf(i + 1));
                        Log.d("onDataChange list", String.valueOf(i +1));
                    }
                    matchAdapter.notifyDataSetChanged();



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        database.child("TotalMatch").addListenerForSingleValueEvent((ValueEventListener) matchListener);
    }
}
