package com.example.a2992scoutingviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import static com.example.a2992scoutingviewer.TeamActivity.listsOfStrings;

public class PitInnerDriveFragment extends Fragment {

    private final String TAG = "PitInnerDrive";
    
    private TextView competitions;
    private TextView driverExperience;
    private TextView driveCoach;
    private TextView hours;
    private TextView studentCoach;

    public PitInnerDriveFragment(){

    }

    public static PitInnerDriveFragment newInstance() {
        PitInnerDriveFragment fragment = new PitInnerDriveFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pit_inner_drive_fragment, container, false);

        competitions = rootView.findViewById(R.id.generalNotes);
        driverExperience =  rootView.findViewById(R.id.defenseNotes);
        driveCoach = rootView.findViewById(R.id.driveCoach);
        hours =  rootView.findViewById(R.id.hours);
        studentCoach = rootView.findViewById(R.id.brokeNotes);

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


        return rootView;
    }

    public void insertBooleans(HashMap<String, String> boolHash){
        Log.d(TAG, "insertBooleans");

    }

    public void insertStrings(HashMap<String, String> stringHash){
        Log.d(TAG, "insertStrings");

        try{
            if(stringHash.get("competitions") == null){
                competitions.setText("N/A");
            }
            competitions.setText(stringHash.get("competitions"));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("driverExperience") == null){
                driverExperience.setText("N/A");
            }
            driverExperience.setText(stringHash.get("driverExperience"));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("driveCoach") == null){
                driveCoach.setText("N/A");
            }
            driveCoach.setText(stringHash.get("driveCoach"));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("hours") == null){
                hours.setText("N/A");
            }
            hours.setText(stringHash.get("hours"));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void insertIntegers(HashMap<String, String> intHash){
        Log.d(TAG, "insertIntegers");

        try{
            if(intHash.get("studentCoach") == null){
                studentCoach.setText("No Preference");
            }
            int studentCoachInt = Integer.valueOf(intHash.get("studentCoach"));
            if(studentCoachInt == 0){
                studentCoach.setText("Student");
            } else if(studentCoachInt == 1){
                studentCoach.setText("Mentor");
            } else if(studentCoachInt == 2){
                studentCoach.setText("Mix");
            } else if(studentCoachInt == 3) {
                studentCoach.setText("No Coach");
            } else {
                Log.d(TAG, "startScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
