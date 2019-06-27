package com.example.a2992scoutingviewer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.example.a2992scoutingviewer.MainActivity.mDatabase;

public class TeamActivity extends AppCompatActivity {

    //Average variables
    public static Double avgHabStart = 0.0;
    public static Double avgSandHatchCS = 0.0;
    public static Double avgSandHatch1 = 0.0;
    public static Double avgSandHatch2 = 0.0;
    public static Double avgSandHatch3 = 0.0;
    public static Double avgSandCargoCS = 0.0;
    public static Double avgSandCargo1 = 0.0;
    public static Double avgSandCargo2 = 0.0;
    public static Double avgSandCargo3 = 0.0;
    public static Double avgTeleHatchCS = 0.0;
    public static Double avgTeleHatch1 = 0.0;
    public static Double avgTeleHatch2 = 0.0;
    public static Double avgTeleHatch3 = 0.0;
    public static Double avgTeleCargoCS = 0.0;
    public static Double avgTeleCargo1 = 0.0;
    public static Double avgTeleCargo2 = 0.0;
    public static Double avgTeleCargo3 = 0.0;
    public static Double avgTeleCargoDrops = 0.0;
    public static Double avgTeleHatchDrops = 0.0;
    public static Double avgHabEnd = 0.0;
    public static Double avgPoints = 0.0;
    public static String teamName = "";

    public boolean firebaseBoxChecked;

    private final String TAG = "TeamActivity";

    public static HashMap<String, HashMap<String, String>> listsOfStrings= new HashMap<>();

    public List<String> toolbarSpinner;
    private String teamNum;
    public static List<String> matchNums;
    private TextView toolbarTeamText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        toolbarSpinner = new ArrayList<>();
        matchNums = new ArrayList<>();

        //Get intent extras first
        Intent intentExtras = getIntent();
        Bundle bundleExtras = intentExtras.getExtras();

        if (!(bundleExtras == null)){
            teamNum = bundleExtras.getString("teamNum");
            firebaseBoxChecked = Boolean.valueOf(bundleExtras.getString("firebase"));
        } else {
            startActivity(new Intent(TeamActivity.this, MainActivity.class));
        }
        //Continue with code

        fillListStrings();
        firebase();

        toolbarTeamText = findViewById(R.id.teamTextView);

        mDatabase.child("Teams").child(teamNum).child("NickName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teamName = dataSnapshot.getValue().toString();
                toolbarTeamText.setText("    "  + teamNum + "  " + teamName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //Fill toolbarSpinner for toolbar
        toolbarSpinner.add("Overview");
        toolbarSpinner.add("Pit Scouting ");

        ArrayAdapter toolbarAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text_view, toolbarSpinner);
        toolbarAdapter.setDropDownViewResource(android.R.layout.select_dialog_item);


        spinner.setAdapter(toolbarAdapter);
        //spinner.setOutlineSpotShadowColor(getResources().getColor(R.color.common_google_signin_btn_text_light));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                if(position == 0){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, OverviewFragment.newInstance(teamNum))
                            .commit();
                } else if(position == 1){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, PitScoutingFragment.newInstance(teamNum))
                            .commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, InsertMatchFragment.newInstance(teamNum, parent.getSelectedItem().toString().substring(6)))
                            .commit();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    public void updateSpinner(){
        List<Integer> intNums = new ArrayList<>();
        for(String i: matchNums){
            try{
                intNums.add(Integer.valueOf(i));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        Collections.sort(intNums);
        toolbarSpinner.clear();
        toolbarSpinner.add("Overview");
        toolbarSpinner.add("Pit Scouting ");
        for(int i : intNums){
            toolbarSpinner.add("Match "+ String.valueOf(i));
        }





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

            Intent intent = new Intent(TeamActivity.this, MainActivity.class);
            intent.putExtra("firebase", String.valueOf(firebaseBoxChecked));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void fillListStrings(){
        listsOfStrings.put("PitIntegers", new HashMap<String, String>());
        listsOfStrings.put("PitBooleans", new HashMap<String, String>());
        listsOfStrings.put("PitStrings", new HashMap<String, String>());
    }
    public void firebase(){
        final DatabaseReference database = mDatabase;
        ValueEventListener pitScoutingListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, String> listString = new HashMap<>();

                Log.d(TAG, "firebase pit");


                for(DataSnapshot i: dataSnapshot.getChildren()){
                    listString = new HashMap<>();
                    if(i.getKey().equals("Integers")){
                        for(DataSnapshot value: i.getChildren()){
                            listString.put(value.getKey(), value.getValue().toString());
                            Log.d(TAG + " key", value.getKey());
                            Log.d(TAG + " value", value.getValue().toString());

                        }
                        Log.d(TAG, "integer debug start");
                        for(String q: listString.keySet()){
                            Log.d("Key", q);
                            Log.d("Value", listString.get(q).toString());
                        }



                        listsOfStrings.put("PitIntegers", listString);
                    }

                    if(i.getKey().equals("Strings")){
                        for(DataSnapshot value: i.getChildren()){
                            listString.put(value.getKey(), value.getValue().toString());
                            Log.d(TAG + " key", value.getKey());
                            Log.d(TAG + " value", value.getValue().toString());
                        }
                        listsOfStrings.put("PitStrings", listString);
                    }

                    if(i.getKey().equals("Booleans")){
                        for(DataSnapshot value: i.getChildren()){
                            listString.put(value.getKey(), value.getValue().toString());
                            Log.d(TAG + " key", value.getKey());
                            Log.d(TAG + " value", value.getValue().toString());
                        }
                        listsOfStrings.put("PitBooleans", listString);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        database.child("Teams").child(teamNum).child("PitScouting").addValueEventListener(pitScoutingListener);

        ChildEventListener matchScoutingListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                //Adding match numbers to toolbar etc.
                matchNums.add(dataSnapshot.getKey());
                updateSpinner();

                listsOfStrings.put("Match"+ dataSnapshot.getKey(), new HashMap<String, String>());

                Log.d("TeamActivity matchChild", dataSnapshot.getKey());
                HashMap<String, String> listString = new HashMap<>();
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    if(i.getKey().equals("Integers")){
                        for(DataSnapshot value: i.getChildren()){
                            listString.put(value.getKey(), value.getValue().toString());
                            Log.d(TAG + "IntsList", value.getKey() + " " + value.getValue().toString());
                        }
                        //listsOfStrings.put("Match" + dataSnapshot.getKey() + "Integers", listString);


                    }

                    if(i.getKey().equals("String")){
                        for(DataSnapshot value: i.getChildren()){
                            listString.put(value.getKey(), value.getValue().toString());
                        }
                        //listsOfStrings.put("Match" + dataSnapshot.getKey() + "PitStrings", listString);
                    }

                    if(i.getKey().equals("Booleans")){
                        for(DataSnapshot value: i.getChildren()){
                            listString.put(value.getKey(), value.getValue().toString());
                        }
                        //listsOfStrings.put("Match" + dataSnapshot.getKey() + "PitBooleans", listString);
                    }
                }

                listsOfStrings.put("Match" + dataSnapshot.getKey(), listString);

                updateAverages();

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

            }
        };

        database.child("Teams").child(teamNum).child("MatchScouting").addChildEventListener(matchScoutingListener);



    }

    public void updateAverages(){
        double points = 0;
        double total = 0;
        double value;

        for(String i: matchNums){
            try{
                value = Double.valueOf(listsOfStrings.get("Match" + i).get("preStartingHab"));
                points++;
                if(value == 0 || value == 1){
                    total += value + 1;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        avgHabStart = total / points;
        points = 0;
        total = 0;

        avgHabEnd = findAverage("endHabLevel");
        avgSandHatchCS = findAverage("sandcargoCS");
        avgSandHatch1 = findAverage("sandhatch1");
        avgSandHatch2 = findAverage("sandhatch2");
        avgSandHatch3 = findAverage("sandhatch3");
        avgSandCargoCS = findAverage("sandcargoCS");
        avgSandCargo1 = findAverage("sandcargo1");
        avgSandCargo2 = findAverage("sandcargo2");
        avgSandCargo3 = findAverage("sandcargo3");
        avgTeleHatchCS = findAverage("telecargoCS");
        avgTeleHatch1 = findAverage("telehatch1");
        avgTeleHatch2 = findAverage("telehatch2");
        avgTeleHatch3 = findAverage("telehatch3");
        avgTeleCargoCS = findAverage("telecargoCS");
        avgTeleCargo1 = findAverage("telecargo1");
        avgTeleCargo2 = findAverage("telecargo2");
        avgTeleCargo3 = findAverage("telecargo3");

        avgTeleCargoDrops = findAverage("telecargoDrops");
        avgTeleHatchDrops = findAverage("telehatchDrops");
        
        avgPoints = findAverage("totalPoints");

        
        Log.d(TAG, "avgHabEnd " + avgHabEnd);
        Log.d(TAG, "avgHabStart " + avgHabStart);
        Log.d(TAG, "avgSandHatchCS " + avgSandHatchCS);
        Log.d(TAG, "avgSandHatch1 " + avgSandHatch1);
        Log.d(TAG, "avgSandHatch2 " + avgSandHatch2);
        Log.d(TAG, "avgSandHatch3 " + avgSandHatch3);
        Log.d(TAG, "avgSandCargoCS " + avgSandCargoCS);
        Log.d(TAG, "avgSandCargo1 " + avgSandCargo1);
        Log.d(TAG, "avgSandCargo2 " + avgSandCargo2);
        Log.d(TAG, "avgSandCargo3 " + avgSandCargo3);
        Log.d(TAG, "avgTeleHatchCS " + avgTeleHatchCS);
        Log.d(TAG, "avgTeleHatch1 " + avgTeleHatch1);
        Log.d(TAG, "avgTeleHatch2 " + avgTeleHatch2);
        Log.d(TAG, "avgTeleHatch3 " + avgTeleHatch3);
        Log.d(TAG, "avgTeleCargoCS " + avgTeleCargoCS);
        Log.d(TAG, "avgTeleCargo1 " + avgTeleCargo1);
        Log.d(TAG, "avgTeleCargo2 " + avgTeleCargo2);
        Log.d(TAG, "avgTeleCargo3 " + avgTeleCargo3);
        Log.d(TAG, "avgTeleCargoDrops " + avgTeleCargoDrops);
        Log.d(TAG, "avgTeleHatchDrops " + avgTeleHatchDrops);
        Log.d(TAG, "avgPoints " + avgPoints);

    }

    public Double findAverage(String val){
        double points = 0;
        double total =  0;
        int value;

        for(String i: matchNums){
            try{
                value = Integer.valueOf(listsOfStrings.get("Match"+i).get(val));
                points++;
                total += value;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return total/points;
        


    }


}
