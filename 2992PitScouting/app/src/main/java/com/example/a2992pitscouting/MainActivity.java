package com.example.a2992pitscouting;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<String> allTeams = new ArrayList<>();
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    public static String teamNumber;


    public static HashMap<String, String> stringsMap = new HashMap<>();
    public static HashMap<String, Boolean> booleansMap = new HashMap<>();
    public static HashMap<String, Integer> integersMap = new HashMap<>();



    private SectionsPageAdapter mSectionsPageAdapter;
    public static DatabaseReference mDatabase;
    //Spinner spinner;
    public static ArrayAdapter<String> teamAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        fillValues(new HashMap<String, String>(),new HashMap<String, Boolean>(),new HashMap<String, Integer>());
        getFirebaseTeams();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Spinner centSpinner = (Spinner) findViewById(R.id.central_spinner);
        teamAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, MainActivity.allTeams);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //centSpinner.setAdapter(teamAdapter);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



        //spinner = (Spinner) findViewById(R.id.team_spinner);
        Log.d("Context", String.valueOf(this));

        //Log.d("Spinner object", spinner.toString());
        Log.d("Size right before", Integer.toString(allTeams.size()));
        //Log.d("Only item", allTeams.get(0));
        Log.d("Layout", String.valueOf(android.R.layout.simple_spinner_item));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
      A placeholder fragment containing a simple view.


    public static class PlaceholderFragment extends Fragment {

         The fragment argument representing the section number for this
         fragment.

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


         Returns a new instance of this fragment for the given section
         number.

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
        */

    public void getFirebaseTeams(){
        final DatabaseReference database = mDatabase;
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
    public static void fillValues(HashMap<String, String> strings, HashMap<String, Boolean> booleans, HashMap<String, Integer> integers){
            Log.d("FillValues", "Has run");

            integersMap.put("sandGamePiecesScored", 0);
            booleansMap.put("sandHatchCS", false);
            booleansMap.put("sandHatch1", false);
            booleansMap.put("sandHatch2", false);
            booleansMap.put("sandHatch3", false);
            booleansMap.put("sandCargoCS", false);
            booleansMap.put("sandCargo1", false);
            booleansMap.put("sandCargo2", false);
            booleansMap.put("sandCargo3", false);
            integersMap.put("sandStartingLocationIndex", 0);
            integersMap.put("sandOperationIndex", 0);
            integersMap.put("sandStartingPieceIndex", 0);

            booleansMap.put("teleHatchCS", false);
            booleansMap.put("teleHatch1", false);
            booleansMap.put("teleHatch2", false);
            booleansMap.put("teleHatch3", false);
            booleansMap.put("teleCargoCS", false);
            booleansMap.put("teleCargo1", false);
            booleansMap.put("teleCargo2", false);
            booleansMap.put("teleCargo3", false);
            integersMap.put("teleGamePiece", 0);
            integersMap.put("teleScoringLocation", 0);

            booleansMap.put("endSoloYes", false);
            booleansMap.put("endSolo2", false);
            booleansMap.put("endSolo3", false);
            booleansMap.put("endBuddyYes", false);
            booleansMap.put("endBuddy2", false);
            booleansMap.put("endBuddy3", false);
            integersMap.put("endSolo2", null);
            integersMap.put("endSolo3", null);
            integersMap.put("endBuddy2", null);
            integersMap.put("endBuddy3", null);
            stringsMap.put("endHow", "");

            stringsMap.put("drivetrain", "");
            stringsMap.put("motors", "");
            stringsMap.put("wheels", "");
            stringsMap.put("weight", "");

            stringsMap.put("competitions", "");
            stringsMap.put("driverExperience", "");
            stringsMap.put("driveCoach", "");
            stringsMap.put("hours", "");
            integersMap.put("studentCoach", 0);

        if(!strings.isEmpty()){
            stringsMap.clear();


            for(String i: strings.keySet()){
                stringsMap.put(i, strings.get(i));
                Log.d("Strings log " + i, stringsMap.get(i));
            }
        }
        if(!booleans.isEmpty()){
            booleansMap.clear();

            for(String i: booleans.keySet()){
                booleansMap.put(i, booleans.get(i));
                Log.d("Booleans log " + i, String.valueOf(booleansMap.get(i)));
            }
        }
        if(!integers.isEmpty()){
            integersMap.clear();
            for(String i: integers.keySet()){
                integersMap.put(i, integers.get(i));
                Log.d("Integers log " + i, String.valueOf(integersMap.get(i)));
            }
        }

        for(String i: stringsMap.keySet()){
            Log.d("Final " + i, String.valueOf(stringsMap.get(i)));
        }
        for(String i: booleansMap.keySet()){
            Log.d("Final " + i, String.valueOf(booleansMap.get(i)));
        }
        for(String i: integersMap.keySet()){
            Log.d("Final " + i, String.valueOf(integersMap.get(i)));
        }

    }

}


