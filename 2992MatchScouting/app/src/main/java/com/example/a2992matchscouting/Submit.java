package com.example.a2992matchscouting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static com.example.a2992matchscouting.HomePage.mDatabase;
import static com.example.a2992matchscouting.MainActivity.b1;
import static com.example.a2992matchscouting.MainActivity.b2;
import static com.example.a2992matchscouting.MainActivity.b3;
import static com.example.a2992matchscouting.MainActivity.booleanHashMap;
import static com.example.a2992matchscouting.MainActivity.integerHashMap;
import static com.example.a2992matchscouting.MainActivity.matchNum;
import static com.example.a2992matchscouting.MainActivity.r1;
import static com.example.a2992matchscouting.MainActivity.r2;
import static com.example.a2992matchscouting.MainActivity.r3;
import static com.example.a2992matchscouting.MainActivity.scoutName;
import static com.example.a2992matchscouting.MainActivity.stringHashMap;
import static com.example.a2992matchscouting.MainActivity.teamNum;


public class Submit extends Fragment {

    private Button submitButton;
    public Submit() {
    }

    public static Submit newInstance() {
        Submit fragment = new Submit();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_submit, container, false);

        MainActivity.canLoadGuess = false;

        submitButton = rootview.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTotalPoints();
                sendDataToFirebase();
                Intent intent = new Intent(getActivity(), HomePage.class);
                intent.putExtra("User", scoutName);
                intent.putExtra("MatchNum", matchNum);
                if(teamNum.equals(r1)){
                    intent.putExtra("Position", "r1");
                } else if(teamNum.equals(r2)){
                    intent.putExtra("Position", "r2");
                } else if(teamNum.equals(r3)){
                    intent.putExtra("Position", "r3");
                } else if(teamNum.equals(b1)){
                    intent.putExtra("Position", "b1");
                } else if(teamNum.equals(b2)){
                    intent.putExtra("Position", "b2");
                } else if(teamNum.equals(b3)){
                    intent.putExtra("Position", "b3");
                }



                getActivity().startActivity(intent);

            }
        });






        return rootview;
    }

    public void sendDataToFirebase(){
        DatabaseReference Database = mDatabase;
        String teamNum = MainActivity.teamNum;
        Log.d("TeamNum to firebase", teamNum);
        Database.child("Teams").child(teamNum).child("MatchScouting").child(MainActivity.matchNum).child("Integers").setValue(MainActivity.integerHashMap);
        Database.child("Teams").child(teamNum).child("MatchScouting").child(MainActivity.matchNum).child("String").setValue(MainActivity.stringHashMap);
        Database.child("Teams").child(teamNum).child("MatchScouting").child(MainActivity.matchNum).child("Booleans").setValue(booleanHashMap);
        Log.d("Boolean preRed", String.valueOf(booleanHashMap.get("preRedCheck")));
        Log.d("Boolean preBlue", String.valueOf(booleanHashMap.get("preBlueCheck")));
        Log.d("Boolean preTie", String.valueOf(booleanHashMap.get("preTieCheck")));
        if (booleanHashMap.get("preRedCheck") || booleanHashMap.get("preBlueCheck") || booleanHashMap.get("preTieCheck")){
            HashMap<String, String> userGuess = new HashMap<>();
            if (booleanHashMap.get("preRedCheck")){
                userGuess.put("Guess", "Red");
            } else if(booleanHashMap.get("preBlueCheck")){
                userGuess.put("Guess", "Blue");
            } else {
                userGuess.put("Guess", "Tie");
            }
            userGuess.put("Points", stringHashMap.get("preUserGuess"));
            Database.child("MatchGuesses").child(matchNum).child(scoutName).setValue(userGuess);
        }


    }
    public void addTotalPoints(){
        int points = 0;

        if(booleanHashMap.get("sandCrossHab")){
            if(integerHashMap.get("preStartingHab").equals(0)){
                points += 3;
            } else if(integerHashMap.get("preStartingHab").equals(1)){
                points += 6;
            }
        }
        points += 2 * (integerHashMap.get("sandhatchCS") + integerHashMap.get("sandhatch1") + integerHashMap.get("sandhatch2") + integerHashMap.get("sandhatch3")
            + integerHashMap.get("telehatchCS") + integerHashMap.get("telehatch1") + integerHashMap.get("telehatch2") + integerHashMap.get("telehatch3"));

        points += 3 * (integerHashMap.get("sandcargoCS") + integerHashMap.get("sandcargo1") + integerHashMap.get("sandcargo2") + integerHashMap.get("sandcargo3")
                + integerHashMap.get("telecargoCS") + integerHashMap.get("telecargo1") + integerHashMap.get("telecargo2") + integerHashMap.get("telecargo3"));

        if(stringHashMap.get("endAssisted").equals("No")){
            if(integerHashMap.get("endHabLevel").equals(1)){
                points += 3;
            } else if(integerHashMap.get("endHabLevel").equals(2)){
                points += 6;
            } else if(integerHashMap.get("endHabLevel").equals(3)){
                points += 12;
            }
        }

        if(booleanHashMap.get("end1Robo") || booleanHashMap.get("end2Robo")){
            if(integerHashMap.get("end1Hab").equals(0)){
                points += 3;
            } else if(integerHashMap.get("end1Hab").equals(1)){
                points += 6;
            } else if(integerHashMap.get("end1Hab").equals(2)){
                points += 12;
            }
        }
        if(booleanHashMap.get("end2Robo")){
            if(integerHashMap.get("end2Hab").equals(Integer.valueOf(0))){
                points += 3;
            } else if(integerHashMap.get("end2Hab").equals(1)){
                points += 6;
            } else if(integerHashMap.get("end2Hab").equals(2)){
                points += 12;
            }
        }

        integerHashMap.put("totalPoints", points);
        




    }



}
