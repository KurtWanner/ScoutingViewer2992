package com.example.a2992matchscouting;

import android.content.Intent;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPageAdapter mSectionsPagerAdapter;

    public static HashMap<String, String> stringHashMap = new HashMap<>();
    public static HashMap<String, Boolean> booleanHashMap = new HashMap<>();
    public static HashMap<String, Integer> integerHashMap = new HashMap<>();

    public static String scoutName;
    public static String matchNum = "0";
    public static String teamNum = "0";
    public static String r1;
    public static String r2;
    public static String r3;
    public static String b1;
    public static String b2;
    public static String b3;

    public static Boolean canLoadGuess = true;

    public HashMap<String, String> teamList = new HashMap<>();
    public List<String> teamMates = new ArrayList<>();
    public List<String> assistTeamMates = new ArrayList<>();
    public static ArrayAdapter teamAdapter;
    public static ArrayAdapter assistTeamAdapter;

    private TextView teamNumber;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillHashMaps();

        canLoadGuess = true;

        Intent intentExtras = getIntent();
        Bundle bundleExtras = intentExtras.getExtras();
        if(!bundleExtras.isEmpty()){
            matchNum = String.valueOf(bundleExtras.get("matchNum"));
            teamNum = String.valueOf(bundleExtras.get("teamNum"));
            scoutName = String.valueOf(bundleExtras.get("scoutName"));
            r1 = String.valueOf(bundleExtras.get("r1"));
            r2 = String.valueOf(bundleExtras.get("r2"));
            r3 = String.valueOf(bundleExtras.get("r3"));
            b1 = String.valueOf(bundleExtras.get("b1"));
            b2 = String.valueOf(bundleExtras.get("b2"));
            b3 = String.valueOf(bundleExtras.get("b3"));

            Log.d("matchNum", matchNum);
            Log.d("teamNum", teamNum);

        } else {
            startActivity(new Intent(MainActivity.this, HomePage.class));
        }

        if(r1.equals(teamNum) || r2.equals(teamNum) || r3.equals(teamNum)){
            teamMates.add(r1);
            teamMates.add(r2);
            teamMates.add(r3);
        } else {
            teamMates.add(b1);
            teamMates.add(b2);
            teamMates.add(b3);
        }
        teamMates.remove(teamNum);

        teamAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, teamMates);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        assistTeamMates.add("No");
        assistTeamMates.addAll(teamMates);
        assistTeamMates.remove(teamNum);

        assistTeamAdapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, assistTeamMates);
        assistTeamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        teamNumber = findViewById(R.id.teamNumber);
        teamNumber.setText("You are Scouting: " + teamNum);
        if(teamNum.equals(r1) || teamNum.equals(r2) || teamNum.equals(r3)){
            teamNumber.setTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            teamNumber.setTextColor(getResources().getColor(R.color.design_default_color_primary));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


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
            startActivity(new Intent(MainActivity.this, HomePage.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void fillHashMaps(){

        integerHashMap.put("totalPoints", 0);
        stringHashMap.put("scoutName", scoutName);
        integerHashMap.put("matchNumber", Integer.valueOf(matchNum));
        integerHashMap.put("disqualifyMatch", 0);

        integerHashMap.put("preLoadedGamePiece", 0);
        integerHashMap.put("preStartingHab", 0);
        stringHashMap.put("preUserGuess", "");
        booleanHashMap.put("preRedCheck", false);
        booleanHashMap.put("preBlueCheck", false);
        booleanHashMap.put("preTieCheck", false);

        integerHashMap.put("sandhatchCS", 0);
        integerHashMap.put("sandhatch1", 0);
        integerHashMap.put("sandhatch2", 0);
        integerHashMap.put("sandhatch3", 0);
        integerHashMap.put("sandcargoCS", 0);
        integerHashMap.put("sandcargo1", 0);
        integerHashMap.put("sandcargo2", 0);
        integerHashMap.put("sandcargo3", 0);
        booleanHashMap.put("sandCrossHab", false);

        integerHashMap.put("telehatchCS", 0);
        integerHashMap.put("telehatch1", 0);
        integerHashMap.put("telehatch2", 0);
        integerHashMap.put("telehatch3", 0);
        integerHashMap.put("telecargoCS", 0);
        integerHashMap.put("telecargo1", 0);
        integerHashMap.put("telecargo2", 0);
        integerHashMap.put("telecargo3", 0);
        integerHashMap.put("telecargoDrops", 0);
        integerHashMap.put("telehatchDrops", 0);


        integerHashMap.put("endHabLevel", 0);
        stringHashMap.put("endAssisted", "No");
        integerHashMap.put("endTime", 0);
        booleanHashMap.put("endBuddyAssist", false);
        booleanHashMap.put("end1Robo", false);
        booleanHashMap.put("end2Robo", false);
        integerHashMap.put("end1Hab", 0);
        integerHashMap.put("end2Hab", 0);
        stringHashMap.put("end1TeamNum", "");
        stringHashMap.put("end2TeamNum", "");

        booleanHashMap.put("comDefCheck", false);
        booleanHashMap.put("comBrokeCheck", false);
        stringHashMap.put("comGenNotes", "");
        stringHashMap.put("comDefNotes", "");
        stringHashMap.put("comBrokeNotes", "");



    }

    /**
     * A placeholder fragment containing a simple view.
     */

    /*
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

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
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

}
