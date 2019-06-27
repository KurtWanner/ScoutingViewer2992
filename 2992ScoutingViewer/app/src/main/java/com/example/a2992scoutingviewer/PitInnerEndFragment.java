package com.example.a2992scoutingviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;

import static com.example.a2992scoutingviewer.TeamActivity.listsOfStrings;

public class PitInnerEndFragment extends Fragment {

    private final String TAG = "PitInnerEnd";

    private CheckBox soloYes;
    private CheckBox solo2Box;
    private CheckBox solo3Box;
    private TextView solo2;
    private TextView solo3;
    private CheckBox buddyYes;
    private CheckBox buddy2Box;
    private CheckBox buddy3Box;
    private TextView buddy2;
    private TextView buddy3;
    private TextView endHow;



    public PitInnerEndFragment(){

    }

    public static PitInnerEndFragment newInstance() {
        PitInnerEndFragment fragment = new PitInnerEndFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pit_inner_end_fragment, container, false);

        soloYes = rootView.findViewById(R.id.endSoloYesButton);
        solo2Box = rootView.findViewById(R.id.endSolo2Button);
        solo3Box = rootView.findViewById(R.id.endSolo3Button);
        solo2 = rootView.findViewById(R.id.endSolo2Time);
        solo3 = rootView.findViewById(R.id.endSolo3Seconds);
        buddyYes = rootView.findViewById(R.id.endBuddyYesButton);
        buddy2Box = rootView.findViewById(R.id.endBuddy2Button);
        buddy3Box = rootView.findViewById(R.id.endBuddy3Button);
        buddy2 = rootView.findViewById(R.id.endBuddy2Time);
        buddy3 = rootView.findViewById(R.id.endBuddy3Seconds);
        endHow = rootView.findViewById(R.id.endBuddyHowBlock);

        try{
            if(listsOfStrings.get("PitStrings") != null){
                insertStrings(listsOfStrings.get("PitStrings"));
            }
            if(listsOfStrings.get("PitBooleans") != null){
                insertBooleans(listsOfStrings.get("PitBooleans"));
            }
            if(listsOfStrings.get("PitIntegers") != null){
                insertIntegers(listsOfStrings.get("PitIntegers"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        
        
        soloYes.setClickable(false);
        solo2Box.setClickable(false);
        solo3Box.setClickable(false);
        buddyYes.setClickable(false);
        buddy3Box.setClickable(false);
        buddy2Box.setClickable(false);
        
        
        
        
        return rootView;
    }


    public void insertBooleans(HashMap<String, String> boolHash){
        Log.d(TAG, "insertBooleans");
        solo2Box.setChecked(Boolean.valueOf(boolHash.get("endSolo2")));
        solo3Box.setChecked(Boolean.valueOf(boolHash.get("endSolo3")));
        soloYes.setChecked(Boolean.valueOf(boolHash.get("endSoloYes")));
        buddy2Box.setChecked(Boolean.valueOf(boolHash.get("endBuddy2")));
        buddy3Box.setChecked(Boolean.valueOf(boolHash.get("endBuddy3")));
        buddyYes.setChecked(Boolean.valueOf(boolHash.get("endBuddyYes")));
    }

    public void insertStrings(HashMap<String, String> stringHash){
        Log.d(TAG, "insertStrings");
        try{
            if(stringHash.get("endHow") == null){
                endHow.setText("No Description");
            }
            endHow.setText(stringHash.get("endHow"));
        } catch (Exception e){
            e.printStackTrace();
        }
        



    }

    public void insertIntegers(HashMap<String, String> intHash){
        Log.d(TAG, "insertIntegers");

        try{
            if(intHash.get("endBuddy2") == null){
                buddy2.setText("N/A");
            }
            buddy2.setText(intHash.get("endBuddy2") + " seconds");
        } catch (Exception e){
            e.printStackTrace();
        }
        
        try{
            if(intHash.get("endBuddy3") == null){
                buddy3.setText("N/A");
            }
            buddy3.setText(intHash.get("endBuddy3") + " seconds");
        } catch (Exception e){
            e.printStackTrace();
        }
        
        try{
            if(intHash.get("endSolo2") == null){
                solo2.setText("N/A");
            }
            solo2.setText(intHash.get("endSolo2") + " seconds");
        } catch (Exception e){
            e.printStackTrace();
        }
        
        try{
            if(intHash.get("endSolo3") == null){
                solo3.setText("N/A");
            }
            solo3.setText(intHash.get("endSolo3") + " seconds");
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
