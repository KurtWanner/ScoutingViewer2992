package com.example.a2992matchupdate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.a2992matchupdate.MainActivity.mDatabase;

public class LaunchActivity extends AppCompatActivity {

    private TextView r1;
    private TextView r2;
    private TextView r3;
    private TextView b1;
    private TextView b2;
    private TextView b3;
    
    private Button r1B;
    private Button r2B;
    private Button r3B;
    private Button b1B;
    private Button b2B;
    private Button b3B;




    public static ArrayAdapter<String> teamAdapter;
    public static List<String> allTeams;
    private String matchNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        r1 = findViewById(R.id.red1);
        r2 = findViewById(R.id.red2);
        r3 = findViewById(R.id.red3);
        b1 = findViewById(R.id.blue1);
        b2 = findViewById(R.id.blue2);
        b3 = findViewById(R.id.blue3);

        r1B = findViewById(R.id.red1Btn);
        r2B = findViewById(R.id.red2Btn);
        r3B = findViewById(R.id.red3Btn);
        b1B = findViewById(R.id.blue1Btn);
        b2B = findViewById(R.id.blue2Btn);
        b3B = findViewById(R.id.blue3Btn);
        
        
        
        allTeams = new ArrayList<>();

        Intent intentExtras = getIntent();
        Bundle bundleExtras = intentExtras.getExtras();

        if(bundleExtras != null){
            matchNum = bundleExtras.getString("matchNum");
        }


        getFirebaseTeams();

        r1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r1.getText().toString().equals("Loading...")){
                    return;
                }
                Intent intent = new Intent(LaunchActivity.this, TeamActivity.class);
                intent.putExtra("teamNum", r1.getText().toString());
                intent.putExtra("matchNum", matchNum);
                Log.d("Started Activity with", r1.getText().toString());
                startActivity(intent);
            }
        });

        r2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r2.getText().toString().equals("Loading...")){
                    return;
                }
                Intent intent = new Intent(LaunchActivity.this, TeamActivity.class);
                intent.putExtra("teamNum", r2.getText().toString());
                intent.putExtra("matchNum", matchNum);
                Log.d("Started Activity with", r2.getText().toString());
                startActivity(intent);
            }
        });

        r3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r3.getText().toString().equals("Loading...")){
                    return;
                }
                Intent intent = new Intent(LaunchActivity.this, TeamActivity.class);
                intent.putExtra("teamNum", r3.getText().toString());
                intent.putExtra("matchNum", matchNum);
                Log.d("Started Activity with", r3.getText().toString());
                startActivity(intent);
            }
        });

        b1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b1.getText().toString().equals("Loading...")){
                    return;
                }
                Intent intent = new Intent(LaunchActivity.this, TeamActivity.class);
                intent.putExtra("teamNum", b1.getText().toString());
                intent.putExtra("matchNum", matchNum);
                Log.d("Started Activity with", b1.getText().toString());
                startActivity(intent);
            }
        });

        b2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b2.getText().toString().equals("Loading...")){
                    return;
                }
                Intent intent = new Intent(LaunchActivity.this, TeamActivity.class);
                intent.putExtra("teamNum", b2.getText().toString());
                intent.putExtra("matchNum", matchNum);
                Log.d("Started Activity with", b2.getText().toString());
                startActivity(intent);
            }
        });

        b3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b3.getText().toString().equals("Loading...")){
                    return;
                }
                Intent intent = new Intent(LaunchActivity.this, TeamActivity.class);
                intent.putExtra("teamNum", b3.getText().toString());
                intent.putExtra("matchNum", matchNum);
                Log.d("Started Activity with", b3.getText().toString());
                startActivity(intent);
            }
        });
        
        
        
        
        /*
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, TeamActivity.class);
                intent.putExtra("teamNum", teamSpinner.getSelectedItem().toString());
                intent.putExtra("matchNum", matchNum);
                Log.d("Started Activity with", teamSpinner.getSelectedItem().toString());

                startActivity(intent);
            }
        });
        */



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(LaunchActivity.this, MainActivity.class);

            intent.putExtra("Stuff", "Stuff");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                
                if(dataSnapshot.getKey().equals("r1")){
                    r1.setText(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().equals("r2")){
                    r2.setText(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().equals("r3")){
                    r3.setText(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().equals("b1")){
                    b1.setText(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().equals("b2")){
                    b2.setText(dataSnapshot.getValue().toString());
                }
                if(dataSnapshot.getKey().equals("b3")){
                    b3.setText(dataSnapshot.getValue().toString());
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
        database.child("Matches").child(matchNum).addChildEventListener(valueEventListener);
        Log.d("Size of allteams", Integer.toString(allTeams.size()));
        Log.d("Database team reference", database.child("Teams").toString());
    }
}
