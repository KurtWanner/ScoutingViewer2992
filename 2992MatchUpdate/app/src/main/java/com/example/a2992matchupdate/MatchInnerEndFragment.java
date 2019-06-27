package com.example.a2992matchupdate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;

import static com.example.a2992matchupdate.InsertMatchFragment.matchNum;
import static com.example.a2992matchupdate.TeamActivity.listsOfStrings;

public class MatchInnerEndFragment extends Fragment {

    private final String TAG = "MatchInnerEnd";

    private TextView endHab;
    private TextView assistTeam;
    private TextView timerText;
    private CheckBox buddyYesCheck;
    private TextView howMany;
    private TextView team1Text;
    private TextView team2Text;
    private TextView hab1Text;
    private TextView hab2Text;
    private CheckBox robo1Check;
    private CheckBox robo2Check;
    private TextView team1Spinner;
    private TextView hab1Spinner;
    private TextView team2Spinner;
    private TextView hab2Spinner;

    public MatchInnerEndFragment(){

    }

    public static MatchInnerEndFragment newInstance() {
        MatchInnerEndFragment fragment = new MatchInnerEndFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.match_inne_end, container, false);

        endHab = rootView.findViewById(R.id.endHabSpinner);
        timerText = rootView.findViewById(R.id.timeText);
        buddyYesCheck = rootView.findViewById(R.id.endCheckBox);
        assistTeam = rootView.findViewById(R.id.endAssistSpinner);
        team1Text = rootView.findViewById(R.id.end1TeamText);
        team2Text = rootView.findViewById(R.id.end2TeamText);
        howMany = rootView.findViewById(R.id.endHowManyText);
        hab1Text = rootView.findViewById(R.id.end1HabText);
        hab2Text = rootView.findViewById(R.id.end2HabText);
        robo1Check = rootView.findViewById(R.id.end1RoboCheck);
        robo2Check = rootView.findViewById(R.id.end2RoboCheck);
        team1Spinner = rootView.findViewById(R.id.end1TeamSpinner);
        hab1Spinner = rootView.findViewById(R.id.end1HabSpinner);
        team2Spinner = rootView.findViewById(R.id.end2TeamSpinner);
        hab2Spinner = rootView.findViewById(R.id.end2HabSpinner);


        try{
            if(listsOfStrings.get("Match" + matchNum) != null){
                insertStrings(listsOfStrings.get("Match" + matchNum));
                insertBooleans(listsOfStrings.get("Match" + matchNum));
                insertIntegers(listsOfStrings.get("Match" + matchNum));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        robo1Check.setClickable(false);
        robo2Check.setClickable(false);
        buddyYesCheck.setClickable(false);
        
        
        
        
        
        return rootView;
    }


    //TODO Set variables
    public void insertBooleans(HashMap<String, String> boolHash){
        Log.d(TAG, "insertBooleans");
        robo1Check.setChecked(Boolean.valueOf(boolHash.get("end1Robo")));
        robo2Check.setChecked(Boolean.valueOf(boolHash.get("end2Robo")));
        buddyYesCheck.setChecked(Boolean.valueOf(boolHash.get("endBuddyAssist")));

        if(buddyYesCheck.isChecked()){
            howMany.setVisibility(View.VISIBLE);
            robo1Check.setVisibility(View.VISIBLE);
            robo2Check.setVisibility(View.VISIBLE);
        }
        if(robo1Check.isChecked()){
            team1Text.setVisibility(View.VISIBLE);
            hab1Text.setVisibility(View.VISIBLE);
            team1Spinner.setVisibility(View.VISIBLE);
            hab1Spinner.setVisibility(View.VISIBLE);
            team2Text.setVisibility(View.INVISIBLE);
            hab2Text.setVisibility(View.INVISIBLE);
            team2Spinner.setVisibility(View.INVISIBLE);
            hab2Spinner.setVisibility(View.INVISIBLE);
        }
        if(robo2Check.isChecked()){
            team1Text.setVisibility(View.VISIBLE);
            team2Text.setVisibility(View.VISIBLE);
            hab1Text.setVisibility(View.VISIBLE);
            hab2Text.setVisibility(View.VISIBLE);
            team1Spinner.setVisibility(View.VISIBLE);
            hab1Spinner.setVisibility(View.VISIBLE);
            team2Spinner.setVisibility(View.VISIBLE);
            hab2Spinner.setVisibility(View.VISIBLE);
        }



    }

    public void insertStrings(HashMap<String, String> stringHash){
        Log.d(TAG, "insertStrings");

        try{
            if(stringHash.get("end1TeamNum") == null){
                team1Spinner.setText("N/A");
            }
            team1Spinner.setText(stringHash.get("end1TeamNum"));
        } catch (Exception e){
            e.printStackTrace();
        }
        
        try{
            if(stringHash.get("end2TeamNum") == null){
                team2Spinner.setText("N/A");
            }
            team2Spinner.setText(stringHash.get("end2TeamNum"));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("endAssisted") == null){
                assistTeam.setText("N/A");
            }
            assistTeam.setText(stringHash.get("endAssisted"));
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
    }

    public void insertIntegers(HashMap<String, String> intHash){
        Log.d(TAG, "insertIntegers");

        try{
            if(intHash.get("endTime") == null){
                timerText.setText("N/A");
            }
            timerText.setText(intHash.get("endTime"));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("end1Hab") == null){
                hab1Spinner.setText("N/A");
            }
            int sandOp = Integer.valueOf(intHash.get("end1Hab"));
            if(sandOp == 0){
                hab1Spinner.setText("Level 1");
            } else if(sandOp == 1) {
                hab1Spinner.setText("Level 2");
            } else if(sandOp == 2){
                hab1Spinner.setText("Level 3");
            } else {
                Log.d(TAG, "operationScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("end2Hab") == null){
                hab2Spinner.setText("N/A");
            }
            int sandOp = Integer.valueOf(intHash.get("end2Hab"));
            if(sandOp == 0){
                hab2Spinner.setText("Level 1");
            } else if(sandOp == 1) {
                hab2Spinner.setText("Level 2");
            } else if(sandOp == 2){
                hab2Spinner.setText("Level 3");
            } else {
                Log.d(TAG, "operationScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            if (intHash.get("endHabLevel") == null) {
                endHab.setText("N/A");
            }
            int sandOp = Integer.valueOf(intHash.get("endHabLevel"));

            if(sandOp == 0){
                endHab.setText("Not On Hab");
            }else if(sandOp == 1){
                endHab.setText("Level 1");
            } else if(sandOp == 2) {
                endHab.setText("Level 2");
            } else if(sandOp == 3){
                endHab.setText("Level 3");
            } else {
                Log.d(TAG, "operationScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
