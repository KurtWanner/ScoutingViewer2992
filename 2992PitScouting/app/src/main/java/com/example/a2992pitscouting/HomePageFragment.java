package com.example.a2992pitscouting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.a2992pitscouting.MainActivity.allTeams;
import static com.example.a2992pitscouting.MainActivity.integersMap;
import static com.example.a2992pitscouting.MainActivity.teamAdapter;

public class HomePageFragment extends Fragment {
    //The fragment argument representing the section number for this
    //fragment.
    private Spinner teamSpinner;
    private Button select;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public HomePageFragment() {

    }


    //Returns a new instance of this fragment for the given section
    //number.

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_page, container, false);
        this.fillOptions();
        Log.d("HomePage fragement","Has run");

        teamSpinner = (Spinner) rootView.findViewById(R.id.teamSpinner);
        teamSpinner.setAdapter(teamAdapter);
        select = (Button) rootView.findViewById(R.id.edit_button);

        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.teamNumber = teamSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                MainActivity.teamNumber = teamSpinner.getSelectedItem().toString();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importFirebaseInfo();
                Toast.makeText(getActivity(), "Loaded Data",
                        Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    public void fillOptions(){

    }

    public void returnTeamNum(){
        MainActivity.teamNumber = teamSpinner.getSelectedItem().toString();
    }

    public void importFirebaseInfo(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String teamNum = MainActivity.teamNumber;
        Log.d("TeamNum to firebase", teamNum);
        DatabaseReference pitScoutingLists = mDatabase.child("Teams").child(teamNum).child("PitScouting");
        ValueEventListener pitListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, String> strings = new HashMap<>();
                HashMap<String, Boolean> booleans = new HashMap<>();
                HashMap<String, Integer> ints = new HashMap<>();

                strings.clear();
                booleans.clear();
                ints.clear();



                Log.d("Datasnapshot name is ", dataSnapshot.getKey());

                for(DataSnapshot list: dataSnapshot.getChildren()){

                    Log.d("List name is ", list.getKey());
                    if(list.getKey().equals("Strings")){

                        for(DataSnapshot item: list.getChildren()){
                            strings.put(item.getKey(), item.getValue().toString());
                        }
                    }
                    if(list.getKey().equals("Booleans")){
                        Log.d("Input booleans", "has run");

                        for(DataSnapshot item: list.getChildren()){
                            Log.d(item.getKey(), item.getValue().toString());
                            if(item.getValue().toString().equals("true")){
                                booleans.put(item.getKey(), true);
                                Log.d("True", "True");
                            } else {
                                booleans.put(item.getKey(), false);
                            }
                        }
                    }
                    if(list.getKey().equals("Integers")){
                        for(DataSnapshot item: list.getChildren()){
                            ints.put(item.getKey(), Integer.valueOf(item.getValue().toString()));
                        }
                    }

                }
                MainActivity.fillValues(new HashMap<String, String>(),new HashMap<String, Boolean>(),new HashMap<String, Integer>());
                MainActivity.fillValues(strings, booleans, ints);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        pitScoutingLists.addValueEventListener(pitListener);
    }


}
