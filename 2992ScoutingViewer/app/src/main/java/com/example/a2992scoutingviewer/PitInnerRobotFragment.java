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

public class PitInnerRobotFragment extends Fragment {

    private final String TAG = "PitInnerRobot";

    private TextView drivetrain;
    private TextView motors;
    private TextView wheels;
    private TextView weight;



    public PitInnerRobotFragment(){

    }

    public static PitInnerRobotFragment newInstance() {
        PitInnerRobotFragment fragment = new PitInnerRobotFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pit_inner_robot_fragment, container, false);

        drivetrain = rootView.findViewById(R.id.drivetrainText);
        motors = rootView.findViewById(R.id.motorsText);
        wheels = rootView.findViewById(R.id.wheelsText);
        weight = rootView.findViewById(R.id.weightText);

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
            if(stringHash.get("drivetrain") == null){
                drivetrain.setText("N/A");
            }
            drivetrain.setText(stringHash.get("drivetrain"));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("motors") == null){
                motors.setText("N/A");
            }
            motors.setText(stringHash.get("motors"));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("weight") == null){
                weight.setText("N/A");
            }
            weight.setText(stringHash.get("weight"));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("wheels") == null){
                wheels.setText("N/A");
            }
            wheels.setText(stringHash.get("wheels"));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void insertIntegers(HashMap<String, String> intHash){
        Log.d(TAG, "insertIntegers");
        
    }
}
