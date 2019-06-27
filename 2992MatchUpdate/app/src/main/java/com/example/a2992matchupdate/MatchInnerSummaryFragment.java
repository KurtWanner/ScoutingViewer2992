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

public class MatchInnerSummaryFragment extends Fragment {

    private final String TAG = "MatchInnerSum";

    private TextView startingPlat;
    private CheckBox habCrossed;
    private TextView sandHatch;
    private TextView sandCargo;
    private TextView teleHatch;
    private TextView teleCargo;
    private TextView endPlat;
    private CheckBox endAssist;
    private CheckBox endHelp;
    private TextView genCom;

    public MatchInnerSummaryFragment(){

    }

    public static MatchInnerSummaryFragment newInstance() {
        MatchInnerSummaryFragment fragment = new MatchInnerSummaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.match_inner_summary, container, false);

        startingPlat = rootView.findViewById(R.id.summaryStartingPlat);
        habCrossed = rootView.findViewById(R.id.sumHabCrossed);
        sandHatch = rootView.findViewById(R.id.sumSandHatch);
        sandCargo = rootView.findViewById(R.id.sumSandCargo);
        teleHatch = rootView.findViewById(R.id.sumTeleHatch);
        teleCargo = rootView.findViewById(R.id.sumTeleCargo);
        endPlat = rootView.findViewById(R.id.sumEndPlat);
        endAssist = rootView.findViewById(R.id.sumEndAssistBox);
        endHelp = rootView.findViewById(R.id.sumEndHelpBox);
        genCom = rootView.findViewById(R.id.sumGenCom);


        try{
            if(listsOfStrings.get("Match" + matchNum) != null){
                insertStrings(listsOfStrings.get("Match" + matchNum));
                insertBooleans(listsOfStrings.get("Match" + matchNum));
                insertIntegers(listsOfStrings.get("Match" + matchNum));
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        habCrossed.setClickable(false);
        endAssist.setClickable(false);
        endHelp.setClickable(false);

        return rootView;
    }


    //TODO Set variables
    public void insertBooleans(HashMap<String, String> boolHash){
        Log.d(TAG, "insertBooleans");

        habCrossed.setChecked(Boolean.valueOf(boolHash.get("sandCrossHab")));

        endHelp.setChecked(Boolean.valueOf(boolHash.get("endBuddyAssist")));
        
        



    }

    public void insertStrings(HashMap<String, String> stringHash){
        Log.d(TAG, "insertStrings");


        try{
            if(stringHash.get("endAssisted") == null){
                endAssist.setChecked(false);
            }
            endAssist.setChecked(Boolean.valueOf(stringHash.get("endAssisted")));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("comGenNotes") == null){
                genCom.setText("N/A");
            }
            genCom.setText(stringHash.get("comGenNotes"));
            if(stringHash.get("comGenNotes").isEmpty()){
                genCom.setText("N/A");
            }


        } catch (Exception e){
            e.printStackTrace();
        }
        
        
    }

    public void insertIntegers(HashMap<String, String> intHash){
        Log.d(TAG, "insertIntegers");

        try{
            if(intHash.get("preStartingHab") == null){
                startingPlat.setText("N/A");
            }
            int sandOp = Integer.valueOf(intHash.get("preStartingHab"));
            if(sandOp == 0){
                startingPlat.setText("Level 1");
            } else if(sandOp == 1) {
                startingPlat.setText("Level 2");
            } else if(sandOp == 2){
                startingPlat.setText("Was a No Show");
            } else {
                Log.d(TAG, "startScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            int sandOp = Integer.valueOf(intHash.get("sandhatch1")) + Integer.valueOf(intHash.get("sandhatch2")) 
                    + Integer.valueOf(intHash.get("sandhatch3")) + Integer.valueOf(intHash.get("sandhatchCS"));
            sandHatch.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            int sandOp = Integer.valueOf(intHash.get("sandcargo1")) + Integer.valueOf(intHash.get("sandcargo2"))
                    + Integer.valueOf(intHash.get("sandcargo3")) + Integer.valueOf(intHash.get("sandcargoCS"));
            sandCargo.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            int sandOp = Integer.valueOf(intHash.get("telecargo1")) + Integer.valueOf(intHash.get("telecargo2"))
                    + Integer.valueOf(intHash.get("telecargo3")) + Integer.valueOf(intHash.get("telecargoCS"));
            teleCargo.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            int sandOp = Integer.valueOf(intHash.get("telehatch1")) + Integer.valueOf(intHash.get("telehatch2"))
                    + Integer.valueOf(intHash.get("telehatch3")) + Integer.valueOf(intHash.get("telehatchCS"));
            teleHatch.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            if (intHash.get("endHabLevel") == null) {
                endPlat.setText("N/A");
            }
            int sandOp = Integer.valueOf(intHash.get("endHabLevel"));

            if(sandOp == 0){
                endPlat.setText("Not On Hab");
            }else if(sandOp == 1){
                endPlat.setText("Level 1");
            } else if(sandOp == 2) {
                endPlat.setText("Level 2");
            } else if(sandOp == 3){
                endPlat.setText("Level 3");
            } else {
                Log.d(TAG, "operationScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
        
    }
}
