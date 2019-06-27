package com.example.a2992pitscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import static com.example.a2992pitscouting.MainActivity.integersMap;
import static com.example.a2992pitscouting.MainActivity.stringsMap;

public class DriveteamFragment extends Fragment {
    //The fragment argument representing the section number for this
    //fragment.

    private EditText competitions;
    private EditText driverExperience;
    private EditText driveCoach;
    private EditText hours;
    private Spinner studentCoach;
    
    
    private static final String ARG_SECTION_NUMBER = "section_number";

    public DriveteamFragment() {
    }


    //Returns a new instance of this fragment for the given section
    //number.

    public static DriveteamFragment newInstance() {
        DriveteamFragment fragment = new DriveteamFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.driveteam, container, false);
        Log.d("Driveteam fragement","Has run");



        competitions = (EditText) rootView.findViewById(R.id.competitions);
        driverExperience = (EditText) rootView.findViewById(R.id.driverExperience);
        driveCoach = (EditText) rootView.findViewById(R.id.driveCoach);
        hours = (EditText) rootView.findViewById(R.id.hours);
        studentCoach = (Spinner) rootView.findViewById(R.id.studentCoach);


        competitions.setTag("R");
        driverExperience.setTag("R");
        driveCoach.setTag("R");
        hours.setTag("R");





        competitions.setText(stringsMap.get("competitions"));
        driverExperience.setText(stringsMap.get("driverExperience"));
        driveCoach.setText(stringsMap.get("driveCoach"));
        hours.setText(stringsMap.get("hours"));


        studentCoach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integersMap.put("studentCoach", studentCoach.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        competitions.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(competitions.getTag() == null)){
                    competitions.setTag(null);
                    competitions.setText(stringsMap.get("competitions"));
                    return;
                }
                stringsMap.put("competitions", competitions.getText().toString());
            }
        });
        driverExperience.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(driverExperience.getTag() == null)){
                    driverExperience.setTag(null);
                    driverExperience.setText(stringsMap.get("driverExperience"));
                    return;
                }
                stringsMap.put("driverExperience", driverExperience.getText().toString());
            }
        });
        driveCoach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(driveCoach.getTag() == null)){
                    driveCoach.setTag(null);
                    driveCoach.setText(stringsMap.get("driveCoach"));
                    return;
                }
                stringsMap.put("driveCoach", driveCoach.getText().toString());
            }
        });
        hours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(hours.getTag() == null)){
                    hours.setTag(null);
                    hours.setText(stringsMap.get("hours"));
                    return;
                }
                stringsMap.put("hours", hours.getText().toString());
            }
        });
        
        
        
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();

        studentCoach.setSelection(integersMap.get("studentCoach"));
        /*
        competitions.setText(stringsMap.get("competitions"));
        driverExperience.setText(stringsMap.get("driverExperience"));
        driveCoach.setText(stringsMap.get("driveCoach"));
        hours.setText(stringsMap.get("hours"));
        */
    }
}
