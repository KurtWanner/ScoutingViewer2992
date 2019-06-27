package com.example.a2992pitscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import static com.example.a2992pitscouting.MainActivity.stringsMap;

public class RobotPartsFragment extends Fragment {
    //The fragment argument representing the section number for this
    //fragment.

    private EditText drivetrain;
    private EditText motors;
    private EditText wheels;
    private EditText weight;



    private static final String ARG_SECTION_NUMBER = "section_number";

    public RobotPartsFragment() {
    }


    //Returns a new instance of this fragment for the given section
    //number.

    public static RobotPartsFragment newInstance() {
        RobotPartsFragment fragment = new RobotPartsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.robot_parts, container, false);

        Log.d("Robot fragement","Has run");

        drivetrain = (EditText) rootView.findViewById(R.id.drivetrainText);
        motors = (EditText) rootView.findViewById(R.id.motorsText);
        wheels = (EditText) rootView.findViewById(R.id.wheelsText);
        weight = (EditText) rootView.findViewById(R.id.weightText);

        Log.d("Drivetrain", "Tags set");
        drivetrain.setTag("R");
        motors.setTag("R");
        wheels.setTag("R");
        weight.setTag("R");





        drivetrain.setText(stringsMap.get("drivetrain"));
        motors.setText(stringsMap.get("motors"));
        wheels.setText(stringsMap.get("wheels"));
        weight.setText(stringsMap.get("weight"));

        drivetrain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Log.d("drivetrain", stringsMap.get("drivetrain"));
                //Log.d("robot drivetrain string", drivetrain.getText().toString());
                //Log.d("robot drivetrain", "edit started");
                //Log.d("drivetrain tag", String.valueOf(drivetrain.getTag() == null));

                if(!(drivetrain.getTag()==null)){
                    //Log.d("robot drivetrain", "returned");
                    drivetrain.setTag(null);
                    drivetrain.setText(stringsMap.get("drivetrain"));
                    //Log.d("New set text", drivetrain.getText().toString());
                    return;
                }
                stringsMap.put("drivetrain", drivetrain.getText().toString());
            }
        });
        motors.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(motors.getTag() == null)){
                    motors.setTag(null);
                    motors.setText(stringsMap.get("motors"));
                    return;
                }
                stringsMap.put("motors", motors.getText().toString());
            }
        });
        wheels.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(wheels.getTag()== null)){
                    wheels.setTag(null);
                    wheels.setText(stringsMap.get("wheels"));
                    return;
                }
                stringsMap.put("wheels", wheels.getText().toString());
            }
        });
        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(weight.getTag() == null)){
                    weight.setTag(null);
                    weight.setText(stringsMap.get("weight"));
                    return;
                }
                stringsMap.put("weight", weight.getText().toString());
            }
        });






        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("Robot onResume", "running");
        /*
        drivetrain.setTag("R");
        motors.setTag("R");
        wheels.setTag("R");
        weight.setTag("R");

        drivetrain.setText(stringsMap.get("drivetrain"));
        motors.setText(stringsMap.get("motors"));
        wheels.setText(stringsMap.get("wheels"));
        weight.setText(stringsMap.get("weight"));

        drivetrain.setTag(null);
        motors.setTag(null);
        wheels.setTag(null);
        weight.setTag(null);
        */


    }
}
