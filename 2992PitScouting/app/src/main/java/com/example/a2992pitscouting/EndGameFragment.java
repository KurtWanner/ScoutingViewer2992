package com.example.a2992pitscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import static com.example.a2992pitscouting.MainActivity.booleansMap;
import static com.example.a2992pitscouting.MainActivity.integersMap;
import static com.example.a2992pitscouting.MainActivity.stringsMap;

public class EndGameFragment extends Fragment {
    //The fragment argument representing the section number for this
    //fragment.

    private static final String ARG_SECTION_NUMBER = "section_number";

    
    private Switch endSoloYes;
    private Switch endSolo2;
    private Switch endSolo3;
    private Switch endBuddyYes;
    private Switch endBuddy2;
    private Switch endBuddy3;
    private EditText endSolo2t;
    private EditText endSolo3t;
    private EditText endBuddy2t;
    private EditText endBuddy3t;
    private EditText endHow;
    
    
    public EndGameFragment() {
    }


    //Returns a new instance of this fragment for the given section
    //number.

    public static EndGameFragment newInstance() {
        EndGameFragment fragment = new EndGameFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.endgame, container, false);

        Log.d("Endgame fragement","Has run");

        endSolo2t = (EditText) rootView.findViewById(R.id.endSolo2Time);
        endSolo3t = (EditText) rootView.findViewById(R.id.endSolo3Seconds);
        endBuddy2t = (EditText) rootView.findViewById(R.id.endBuddy2Time);
        endBuddy3t = (EditText) rootView.findViewById(R.id.endBuddy3Seconds);
        endHow = (EditText) rootView.findViewById(R.id.endBuddyHowBlock);
        endSoloYes = (Switch) rootView.findViewById(R.id.endSoloYesButton);
        endSolo2 = (Switch) rootView.findViewById(R.id.endSolo2Button);
        endSolo3 = (Switch) rootView.findViewById(R.id.endSolo3Button);
        endBuddyYes = (Switch) rootView.findViewById(R.id.endBuddyYesButton);
        endBuddy2 = (Switch) rootView.findViewById(R.id.endBuddy2Button);
        endBuddy3 = (Switch) rootView.findViewById(R.id.endBuddy3Button);

        endSoloYes.setTag("R");
        endSolo2.setTag("R");
        endSolo3.setTag("R");
        endBuddy2.setTag("R");
        endBuddy3.setTag("R");
        endBuddyYes.setTag("R");
        endHow.setTag("R");


        endSolo2t.setTag("R");
        endSolo3t.setTag("R");
        endBuddy2t.setTag("R");
        endBuddy3t.setTag("R");

        /*
        endSolo2t.setText("endSolo2");
        endSolo3t.setText("endSolo3");
        endBuddy2t.setText("endBuddy2");
        endBuddy3t.setText("endBuddy3");
        */

        if(!String.valueOf(integersMap.get("endSolo2")).equals("null")){
            endSolo2t.setText(String.valueOf(integersMap.get("endSolo2")));
        }
        if(!String.valueOf(integersMap.get("endSolo3")).equals("null")){
            endSolo3t.setText(String.valueOf(integersMap.get("endSolo3")));
        }
        if(!String.valueOf(integersMap.get("endBuddy2")).equals("null")){
            endBuddy2t.setText(String.valueOf(integersMap.get("endBuddy2")));
        }
        if(!String.valueOf(integersMap.get("endBuddy3")).equals("null")){
            endBuddy3t.setText(String.valueOf(integersMap.get("endBuddy3")));
        }

        endHow.setText(stringsMap.get("endHow"));
        endSoloYes.setChecked(booleansMap.get("endSoloYes"));
        endSolo2.setChecked(booleansMap.get("endSolo2"));
        endSolo3.setChecked(booleansMap.get("endSolo3"));
        endBuddyYes.setChecked(booleansMap.get("endBuddyYes"));
        endBuddy2.setChecked(booleansMap.get("endBuddy2"));
        endBuddy3.setChecked(booleansMap.get("endBuddy3"));

        

        endSolo2t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.d("Endgame tab before", "this has run");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d("Endgame tab on", "this has run");

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!(endSolo2t.getTag() == null)){
                    endSolo2t.setTag(null);
                    Log.d("Endgame tab", "this has run");
                    if(integersMap.get("endSolo2") != null) {
                        Log.d("endSolo2", String.valueOf(integersMap.get("endSolo2")));
                        endSolo2t.setText(String.valueOf(integersMap.get("endSolo2")));
                    } else {
                        endSolo2t.setText("");
                    }
                    return;
                }
                if(!endSolo2t.getText().toString().equals("")) {
                    integersMap.put("endSolo2", Integer.valueOf(endSolo2t.getText().toString()));
                }
            }
        });
        endSolo3t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(endSolo3t.getTag() == null)){
                    endSolo3t.setTag(null);
                    if(integersMap.get("endSolo3") != null) {
                        endSolo3t.setText(String.valueOf(integersMap.get("endSolo3")));
                    } else {
                        endSolo3t.setText("");
                    }
                    return;
                }
                if(!endSolo3t.getText().toString().equals("")) {
                    integersMap.put("endSolo3", Integer.valueOf(endSolo3t.getText().toString()));
                }
            }
        });
        endBuddy2t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(endBuddy2t.getTag() == null)){
                    endBuddy2t.setTag(null);
                    if(integersMap.get("endBuddy2") != null) {
                        endBuddy2t.setText(String.valueOf(integersMap.get("endBuddy2")));
                    } else {
                        endBuddy2t.setText("");
                    }
                    return;
                }
                if(!endBuddy2t.getText().toString().equals("")) {
                    integersMap.put("endBuddy2", Integer.valueOf(endBuddy2t.getText().toString()));
                }
            }
        });
        endBuddy3t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(endBuddy3t.getTag() == null)){
                    endBuddy3t.setTag(null);
                    if(integersMap.get("endBuddy3") != null){
                        endBuddy3t.setText(String.valueOf(integersMap.get("endBuddy3")));
                    } else {
                        endBuddy3t.setText("");
                    }
                    return;
                }
                if(!endBuddy3t.getText().toString().equals("")) {
                    integersMap.put("endBuddy3", Integer.valueOf(endBuddy3t.getText().toString()));
                }
            }
        });
        endHow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(endHow.getTag() == null)){
                    endHow.setTag(null);
                    endHow.setText(stringsMap.get("endHow"));
                    return;
                }
                stringsMap.put("endHow", endHow.getText().toString());
            }
        });
        endSoloYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(endSoloYes.getTag() == null)){
                    endSoloYes.setTag(null);
                    endSoloYes.setChecked(booleansMap.get("endSoloYes"));
                    return;
                }
                booleansMap.put("endSoloYes", endSoloYes.isChecked());
            }
        });
        endSolo2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(endSolo2.getTag() == null)){
                    endSolo2.setTag(null);
                    endSolo2.setChecked(booleansMap.get("endSolo2"));
                    return;
                }
                booleansMap.put("endSolo2", endSolo2.isChecked());
            }
        });
        endSolo3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(endSolo3.getTag() == null)){
                    endSolo3.setTag(null);
                    endSolo3.setChecked(booleansMap.get("endSolo3"));
                    return;
                }
                booleansMap.put("endSolo3", endSolo3.isChecked());
            }
        });
        endBuddyYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(endBuddyYes.getTag() == null)){
                    endBuddyYes.setTag(null);
                    endBuddyYes.setChecked(booleansMap.get("endBuddyYes"));
                    return;
                }
                booleansMap.put("endBuddyYes", endBuddyYes.isChecked());
            }
        });
        endBuddy2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(endBuddy2.getTag() == null)){
                    endBuddy2.setTag(null);
                    endBuddy2.setChecked(booleansMap.get("endBuddy2"));
                    return;
                }
                booleansMap.put("endBuddy2", endBuddy2.isChecked());
            }
        });
        endBuddy3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(endBuddy3.getTag() == null)){
                    endBuddy3.setTag(null);
                    endBuddy3.setChecked(booleansMap.get("endBuddy3"));
                    return;
                }
                booleansMap.put("endBuddy3", endBuddy3.isChecked());
            }
        });








        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        /*
        endSoloYes.setChecked(booleansMap.get("endSoloYes"));
        endSolo2.setChecked(booleansMap.get("endSolo2"));
        endSolo3.setChecked(booleansMap.get("endSolo3"));
        endBuddyYes.setChecked(booleansMap.get("endBuddyYes"));
        endBuddy2.setChecked(booleansMap.get("endBuddy2"));
        endBuddy3.setChecked(booleansMap.get("endBuddy3"));
        
        endSolo2t.setTag("R");
        endSolo3t.setTag("R");
        endBuddy2t.setTag("R");
        endBuddy3t.setTag("R");

        if(!String.valueOf(integersMap.get("endSolo2")).equals("null")){
            endSolo2t.setText(String.valueOf(integersMap.get("endSolo2")));
        }
        if(!String.valueOf(integersMap.get("endSolo3")).equals("null")){
            endSolo3t.setText(String.valueOf(integersMap.get("endSolo3")));
        }
        if(!String.valueOf(integersMap.get("endBuddy2")).equals("null")){
            endBuddy2t.setText(String.valueOf(integersMap.get("endBuddy2")));
        }
        if(!String.valueOf(integersMap.get("endBuddy3")).equals("null")){
            endBuddy3t.setText(String.valueOf(integersMap.get("endBuddy3")));
        }
        endHow.setText(stringsMap.get("endHow"));
        */

    }
}
