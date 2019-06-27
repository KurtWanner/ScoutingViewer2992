package com.example.a2992matchscouting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Helper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePage extends AppCompatActivity {
    private static final String TAG = "home_page";

    public static DatabaseReference mDatabase;
    public Boolean runSnap = false;

    public String homePageTeamNum = null;

    private List<String> userNames = new ArrayList<>();
    private List<String> matches = new ArrayList<>();

    String numOfMatches;

    private HashMap<String, String> matchTeamNums = new HashMap();

    private ArrayAdapter<String> usernameAda;
    private ArrayAdapter<String> matchAda;
    
    private Spinner nameSpinner;
    private Spinner matchSpinner;
    private Button signUpBtn;
    private Button startMatchBtn;

    private CheckBox red1;
    private CheckBox red2;
    private CheckBox red3;
    private CheckBox blue1;
    private CheckBox blue2;
    private CheckBox blue3;
    Timer timer;
    private Boolean importData = false;


    private TextView scoutingTeam;

    public HomePage(){
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        importData = true;

        Intent intentExtras = getIntent();
        Bundle bundleExtras = intentExtras.getExtras();


        if(bundleExtras == null){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            Log.d(TAG, "bundleExtras has run");
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();


        userNames.add("Please Select Your Name");
        matchTeamNums.put("r1", "0");
        matchTeamNums.put("r2", "0");
        matchTeamNums.put("r3", "0");
        matchTeamNums.put("b1", "0");
        matchTeamNums.put("b2", "0");
        matchTeamNums.put("b3", "0");

        red1 = (CheckBox) findViewById(R.id.red1);
        red2 = (CheckBox) findViewById(R.id.red2);
        red3 = (CheckBox) findViewById(R.id.red3);
        blue1 = (CheckBox) findViewById(R.id.blue1);
        blue2 = (CheckBox) findViewById(R.id.blue2);
        blue3 = (CheckBox) findViewById(R.id.blue3);
        nameSpinner = (Spinner) findViewById(R.id.userNameSpinner);
        matchSpinner = (Spinner) findViewById(R.id.matchNumSpinner);
        signUpBtn = (Button) findViewById(R.id.signUpButton);
        startMatchBtn = (Button) findViewById(R.id.startMatchBtn);

        scoutingTeam = (TextView) findViewById(R.id.scoutingTeam);

        usernameAda = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, userNames);
        usernameAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpinner.setAdapter(usernameAda);

        matchAda = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, matches);
        matchAda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matchSpinner.setAdapter(matchAda);

        getFirebaseUsers();
        fillMatches();



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, SignUpPage.class));
            }
        });

        startMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scoutingTeam.getText().toString().equals("0")){
                    Toast.makeText(HomePage.this, "Not Loading Teams", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(red1.isChecked() || red2.isChecked() || red3.isChecked() ||
                        blue1.isChecked() || blue2.isChecked() || blue3.isChecked())){
                    Toast.makeText(HomePage.this, "Please Select A Position", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(nameSpinner.getSelectedItemPosition() == 0){
                    Toast.makeText(HomePage.this, "Please Select Your Name", Toast.LENGTH_SHORT).show();
                    return;
                }


                    Intent intent  = new Intent(HomePage.this, MainActivity.class);
                    intent.putExtra("teamNum", scoutingTeam.getText().toString());
                    intent.putExtra("matchNum", matchSpinner.getSelectedItem().toString());
                    intent.putExtra("scoutName", nameSpinner.getSelectedItem().toString());
                    intent.putExtra("r1", matchTeamNums.get("r1"));
                    intent.putExtra("r2", matchTeamNums.get("r2"));
                    intent.putExtra("r3", matchTeamNums.get("r3"));

                    intent.putExtra("b1", matchTeamNums.get("b1"));
                    intent.putExtra("b2", matchTeamNums.get("b2"));
                    intent.putExtra("b3", matchTeamNums.get("b3"));





                startActivity(intent);
            }
        });

        matchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("r1", String.valueOf(red1.isChecked()));
                Log.d("r2", String.valueOf(red2.isChecked()));
                Log.d("r3", String.valueOf(red3.isChecked()));
                Log.d("b1", String.valueOf(blue1.isChecked()));
                Log.d("b2", String.valueOf(blue2.isChecked()));
                Log.d("b3", String.valueOf(blue2.isChecked()));

                fillMatchNums(String.valueOf(matchSpinner.getItemAtPosition(position)));
                /*
                if(!(red1.isChecked() || red2.isChecked() || red3.isChecked() ||
                blue1.isChecked() || blue2.isChecked() || blue3.isChecked())){
                    Toast.makeText(HomePage.this, "Please Select A Position", Toast.LENGTH_SHORT).show();
                    return;
                }
                */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        red1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    red2.setChecked(false);
                    red3.setChecked(false);
                    blue1.setChecked(false);
                    blue2.setChecked(false);
                    blue3.setChecked(false);

                    scoutingTeam.setText(matchTeamNums.get("r1"));
                }else {
                    scoutingTeam.setText("Please fill in details.");
                }
            }
        });

        red2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.d("red2", "has run");
                if(isChecked){
                    red1.setChecked(false);
                    red3.setChecked(false);
                    blue1.setChecked(false);
                    blue2.setChecked(false);
                    blue3.setChecked(false);
                    scoutingTeam.setText(matchTeamNums.get("r2"));

                }else {
                    scoutingTeam.setText("Please fill in details.");
                }
            }
        });
        red3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    red1.setChecked(false);
                    red2.setChecked(false);
                    blue1.setChecked(false);
                    blue2.setChecked(false);
                    blue3.setChecked(false);
                    scoutingTeam.setText(matchTeamNums.get("r3"));

                }else {
                    scoutingTeam.setText("Please fill in details.");
                }

            }
        });
        blue1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    red1.setChecked(false);
                    red2.setChecked(false);
                    red3.setChecked(false);
                    blue2.setChecked(false);
                    blue3.setChecked(false);
                    scoutingTeam.setText(matchTeamNums.get("b1"));

                }else {
                    scoutingTeam.setText("Please fill in details.");
                }

            }
        });
        blue2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    red1.setChecked(false);
                    red2.setChecked(false);
                    red3.setChecked(false);
                    blue1.setChecked(false);
                    blue3.setChecked(false);
                    scoutingTeam.setText(matchTeamNums.get("b2"));

                }else {
                    scoutingTeam.setText("Please fill in details.");
                }

            }
        });
        blue3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    red1.setChecked(false);
                    red2.setChecked(false);
                    red3.setChecked(false);
                    blue1.setChecked(false);
                    blue2.setChecked(false);
                    scoutingTeam.setText(matchTeamNums.get("b3"));
                } else {
                    scoutingTeam.setText("Please fill in details.");
                }

            }
        });





    }
    
    public void buttonVisibility(){
        
    }

    public void getFirebaseUsers(){
        final DatabaseReference database = mDatabase;
        //allTeams.add("0");
        ChildEventListener valueEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("DataSnapshot read ", dataSnapshot.getKey());
                if (!userNames.contains(dataSnapshot.getKey())){
                    userNames.add(dataSnapshot.getKey());
                    Log.d("Current size userNames", Integer.toString(userNames.size()));
                    usernameAda.notifyDataSetChanged();
                    if(importData){
                        Intent intentExtras = getIntent();
                        Bundle bundleExtras = intentExtras.getExtras();
                        if(!(bundleExtras == null)){
                            Log.d("Homepage user", String.valueOf(bundleExtras.getString("User")));
                            nameSpinner.setSelection(usernameAda.getPosition(bundleExtras.getString("User")));
                        }
                    }



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
                Toast.makeText(HomePage.this, "Not Connected", Toast.LENGTH_SHORT).show();

                Log.d("CancelledLog", databaseError.getMessage());
            }
        };
        database.child("Users").addChildEventListener(valueEventListener);
        //Log.d("Size of allteams", Integer.toString(allTeams.size()));
        //Log.d("Database team reference", database.child("Teams").toString());
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
                        matches.add(String.valueOf(i + 1));
                        Log.d("onDataChange list", String.valueOf(i +1));
                    }
                    matchAda.notifyDataSetChanged();



                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intentExtras = getIntent();
                Bundle bundleExtras = intentExtras.getExtras();
                if(!(bundleExtras == null)){
                    Log.d("Homepage matchnum", String.valueOf(bundleExtras.getString("MatchNum")));
                    matchSpinner.setSelection(matchAda.getPosition(bundleExtras.getString("MatchNum")) + 1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(HomePage.this, "Not Connected", Toast.LENGTH_SHORT).show();

            }
        };
        database.child("TotalMatch").addListenerForSingleValueEvent((ValueEventListener) matchListener);
    }

    public void fillMatchNums(String match){
        DatabaseReference Database = mDatabase;
        DatabaseReference matchNum = Database.child("Matches").child(match);


        class Helper extends TimerTask
        {
            public int i = -1;
            public void run()
            {
                Intent intentExtras = getIntent();
                Bundle bundleExtras = intentExtras.getExtras();
                if(!(bundleExtras == null)){
                    Log.d("Homepage user", bundleExtras.getString("User"));
                    nameSpinner.setSelection(usernameAda.getPosition(bundleExtras.getString("User")));
                    if (bundleExtras.getString("Position").equals("r1")){
                        red1.setChecked(true);
                    } else if (bundleExtras.getString("Position").equals("r2")){
                        red2.setChecked(true);
                    } else if (bundleExtras.getString("Position").equals("r3")){
                        red3.setChecked(true);
                    } else if (bundleExtras.getString("Position").equals("b1")){
                        blue1.setChecked(true);
                    } else if (bundleExtras.getString("Position").equals("b2")){
                        blue2.setChecked(true);
                    } else if (bundleExtras.getString("Position").equals("b3")){
                        blue3.setChecked(true);
                    }
                }
            }
        }

        ValueEventListener matchTeams = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("matchTeams listener run", "true");
                for (DataSnapshot i: dataSnapshot.getChildren()){
                    matchTeamNums.put(i.getKey(), i.getValue().toString());
                    runSnap = true;

                }

                if (!runSnap){
                    matchTeamNums.put("r1", "0");
                    matchTeamNums.put("r2", "0");
                    matchTeamNums.put("r3", "0");
                    matchTeamNums.put("b1", "0");
                    matchTeamNums.put("b2", "0");
                    matchTeamNums.put("b3", "0");
                } else {
                    runSnap = false;
                    Intent intentExtras = getIntent();
                    Bundle bundleExtras = intentExtras.getExtras();
                    
                    if(red1.isChecked()){
                        scoutingTeam.setText(matchTeamNums.get("r1"));
                    }
                    if(red2.isChecked()){
                        scoutingTeam.setText(matchTeamNums.get("r2"));
                    }
                    if(red3.isChecked()){
                        scoutingTeam.setText(matchTeamNums.get("r3"));
                    }
                    if(blue1.isChecked()){
                        scoutingTeam.setText(matchTeamNums.get("b1"));
                    }
                    if(blue2.isChecked()){
                        scoutingTeam.setText(matchTeamNums.get("b2"));
                    }
                    if(blue3.isChecked()){
                        scoutingTeam.setText(matchTeamNums.get("b3"));
                    }
                    
                    /*
                    red1.setChecked(red1.isChecked());
                    red2.setChecked(red2.isChecked());
                    red3.setChecked(red3.isChecked());
                    blue1.setChecked(blue1.isChecked());
                    blue2.setChecked(blue2.isChecked());
                    blue3.setChecked(blue3.isChecked());
                    */
                    
                    if(importData){
                        if(!(bundleExtras == null)){
                            try{
                                if (bundleExtras.getString("Position").equals("r1")){
                                    red1.setChecked(true);
                                } else if (bundleExtras.getString("Position").equals("r2")){
                                    red2.setChecked(true);
                                } else if (bundleExtras.getString("Position").equals("r3")){
                                    red3.setChecked(true);
                                } else if (bundleExtras.getString("Position").equals("b1")){
                                    blue1.setChecked(true);
                                } else if (bundleExtras.getString("Position").equals("b2")){
                                    blue2.setChecked(true);
                                } else if (bundleExtras.getString("Position").equals("b3")){
                                    blue3.setChecked(true);
                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                        importData = false;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(HomePage.this, "Not Connected", Toast.LENGTH_SHORT).show();

            }
        };

        matchNum.addListenerForSingleValueEvent(matchTeams);



    }


}
