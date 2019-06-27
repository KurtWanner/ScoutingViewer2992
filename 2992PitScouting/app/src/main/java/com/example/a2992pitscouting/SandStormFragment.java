package com.example.a2992pitscouting;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;


import static com.example.a2992pitscouting.MainActivity.booleansMap;
import static com.example.a2992pitscouting.MainActivity.integersMap;
import static com.example.a2992pitscouting.MainActivity.stringsMap;

public class SandStormFragment extends Fragment {
    //The fragment argument representing the section number for this
    //fragment.

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Spinner startingPlat;
    private Spinner sandOperation;
    private Spinner gamePiece;
    private EditText gamePiecesScored;
    private Switch hatchCS;
    private Switch hatch1;
    private Switch hatch2;
    private Switch hatch3;
    private Switch cargoCS;
    private Switch cargo1;
    private Switch cargo2;
    private Switch cargo3;
    private Boolean reloadFrame = false;

    public SandStormFragment() {
    }


    //Returns a new instance of this fragment for the given section
    //number.

    public static SandStormFragment newInstance() {
        SandStormFragment fragment = new SandStormFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sand_storm, container, false);

        Log.d("Sandstorm fragement","Has run");

        gamePiecesScored = (EditText) rootView.findViewById(R.id.sandGamePiecesScored);
        hatchCS = (Switch) rootView.findViewById(R.id.sandHatchCSBtn);
        hatch1 = (Switch) rootView.findViewById(R.id.sandHatchRock1Btn);
        hatch2 = (Switch) rootView.findViewById(R.id.sandHatchRock2Btn);
        hatch3 = (Switch) rootView.findViewById(R.id.sandHatchRock3Btn);
        cargoCS = (Switch) rootView.findViewById(R.id.sandCargoCSBtn);
        cargo1 = (Switch) rootView.findViewById(R.id.sandCargoRock1Btn);
        cargo2 = (Switch) rootView.findViewById(R.id.sandCargoRock2Btn);
        cargo3 = (Switch) rootView.findViewById(R.id.sandCargoRock3Btn);
        startingPlat = (Spinner) rootView.findViewById(R.id.sandStartingPlat);
        sandOperation = (Spinner) rootView.findViewById(R.id.sandOperation);
        gamePiece = (Spinner) rootView.findViewById(R.id.sandStartingGamePiece);



        Log.d("sandHatchCs", String.valueOf(booleansMap.get("sandHatchCS")));
        Log.d("sandHatch1", String.valueOf(booleansMap.get("sandHatch1")));
        Log.d("sandHatch2", String.valueOf(booleansMap.get("sandHatch2")));
        Log.d("sandHatch3", String.valueOf(booleansMap.get("sandHatch3")));

        /*
        hatchCS.setOnCheckedChangeListener(null);
        hatch1.setOnCheckedChangeListener(null);
        hatch2.setOnCheckedChangeListener(null);
        hatch3.setOnCheckedChangeListener(null);
        cargoCS.setOnCheckedChangeListener(null);
        cargo1.setOnCheckedChangeListener(null);
        cargo2.setOnCheckedChangeListener(null);
        cargo3.setOnCheckedChangeListener(null);
        gamePiecesScored.addTextChangedListener(null);
        startingPlat.setOnItemSelectedListener(null);
        sandOperation.setOnItemSelectedListener(null);
        gamePiece.setOnItemSelectedListener(null);
        */


        hatchCS.setTag("R");
        hatch1.setTag("R");
        hatch2.setTag("R");
        hatch3.setTag("R");
        cargoCS.setTag("R");
        cargo1.setTag("R");
        cargo2.setTag("R");
        cargo3.setTag("R");
        gamePiecesScored.setTag("R");
        
        
        
        if(!String.valueOf(integersMap.get("sandGamePiecesScored")).equals("null")){
            gamePiecesScored.setText(String.valueOf(integersMap.get("sandGamePiecesScored")));
        }
        hatchCS.setChecked(booleansMap.get("sandHatchCS"));
        hatch1.setChecked(booleansMap.get("sandHatch1"));
        hatch2.setChecked(booleansMap.get("sandHatch2"));
        hatch3.setChecked(booleansMap.get("sandHatch3"));
        cargoCS.setChecked(booleansMap.get("sandCargoCS"));
        cargo1.setChecked(booleansMap.get("sandCargo1"));
        cargo2.setChecked(booleansMap.get("sandCargo2"));
        cargo3.setChecked(booleansMap.get("sandCargo3"));
        startingPlat.setSelection(integersMap.get("sandStartingLocationIndex"));
        sandOperation.setSelection(integersMap.get("sandOperationIndex"));
        gamePiece.setSelection(integersMap.get("sandStartingPieceIndex"));
        

        Log.d("New sandHatchCs", String.valueOf(booleansMap.get("sandHatchCS")));
        Log.d("New sandHatch1", String.valueOf(booleansMap.get("sandHatch1")));
        Log.d("New sandHatch2", String.valueOf(booleansMap.get("sandHatch2")));
        Log.d("New sandHatch3", String.valueOf(booleansMap.get("sandHatch3")));

        Log.d("Old sandHatchCs", String.valueOf(hatchCS.isChecked()));
        Log.d("Old sandHatch1", String.valueOf(hatch1.isChecked()));
        Log.d("Old sandHatch2", String.valueOf(hatch2.isChecked()));
        Log.d("Old sandHatch3", String.valueOf(hatch3.isChecked()));


        gamePiecesScored.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(gamePiecesScored.getTag() == null)){
                    gamePiecesScored.setTag(null);
                    if(integersMap.get("sandGamePiecesScored") != null){
                        gamePiecesScored.setText(String.valueOf(integersMap.get("sandGamePiecesScored")));
                    } else {
                        gamePiecesScored.setText("");
                    }
                    return;
                }
                if(!gamePiecesScored.getText().toString().equals("")) {
                    integersMap.put("sandGamePiecesScored", Integer.valueOf(gamePiecesScored.getText().toString()));
                }
            }
        });
        hatchCS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(hatchCS.getTag() == null)){
                    hatchCS.setTag(null);
                    hatchCS.setChecked(booleansMap.get("sandHatchCS"));
                    return;
                }
                booleansMap.put("sandHatchCS", isChecked);

            }
        });
        hatch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(hatch1.getTag() == null)){
                    hatch1.setTag(null);
                    hatch1.setChecked(booleansMap.get("sandHatch1"));
                    return;
                }
                    booleansMap.put("sandHatch1", isChecked);

                
            }
        });
        hatch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("Previous check", String.valueOf(booleansMap.get("sandHatch2")));

                Log.d("buttonView",String.valueOf(buttonView.isPressed()));
                if(!(hatch2.getTag() == null)){
                    hatch2.setTag(null);
                    hatch2.setChecked(booleansMap.get("sandHatch2"));
                    return;
                }
                    booleansMap.put("sandHatch2", isChecked);
                    Log.d("Stupid stuff", "has run");
                
                Log.d("Sand Hatch 2 Btn", "presed");
                Log.d("Sand Hatch 2 state", String.valueOf(isChecked));
                Log.d("Booleansmap ref ",String.valueOf(booleansMap.get("sandHatch2")));
            }
        });
        hatch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(hatch3.getTag() == null)){
                    hatch3.setTag(null);
                    hatch3.setChecked(booleansMap.get("sandHatch3"));
                    return;
                }
                    booleansMap.put("sandHatch3", isChecked);

                
            }
        });
        cargoCS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(cargoCS.getTag() == null)){
                    cargoCS.setTag(null);
                    cargoCS.setChecked(booleansMap.get("sandCargoCS"));
                    return;
                }
                
                booleansMap.put("sandCargoCS", isChecked);
            }
        });
        cargo1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(cargo1.getTag() == null)){
                    cargo1.setTag(null);
                    cargo1.setChecked(booleansMap.get("sandCargo1"));
                    return;
                }
                booleansMap.put("sandCargo1", isChecked);
            }
        });
        cargo2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(cargo2.getTag() == null)){
                    cargo2.setTag(null);
                    cargo2.setChecked(booleansMap.get("sandCargo2"));
                    return;
                }
                booleansMap.put("sandCargo2", isChecked);
            }
        });
        cargo3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(cargo3.getTag() == null)){
                    cargo3.setTag(null);
                    cargo3.setChecked(booleansMap.get("sandCargo3"));
                    return;
                }
                booleansMap.put("sandCargo3", isChecked);
            }
        });
        startingPlat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integersMap.put("sandStartingLocationIndex", startingPlat.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sandOperation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integersMap.put("sandOperationIndex", sandOperation.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gamePiece.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integersMap.put("sandStartingPieceIndex", gamePiece.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d("onCreate", "run");
        Log.d("Old sandHatchCs", String.valueOf(hatchCS.isChecked()));
        Log.d("Old sandHatch1", String.valueOf(hatch1.isChecked()));
        Log.d("Old sandHatch2", String.valueOf(hatch2.isChecked()));
        Log.d("Old sandHatch3", String.valueOf(hatch3.isChecked()));

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();

        Log.d("onResume", "run");

        if(!String.valueOf(integersMap.get("sandGamePiecesScored")).equals("null")){
            gamePiecesScored.setText(String.valueOf(integersMap.get("sandGamePiecesScored")));
        }
        /*
        hatchCS.setChecked(booleansMap.get("sandHatchCS"));
        hatch1.setChecked(booleansMap.get("sandHatch1"));
        hatch2.setChecked(booleansMap.get("sandHatch2"));
        hatch3.setChecked(booleansMap.get("sandHatch3"));
        cargoCS.setChecked(booleansMap.get("sandCargoCS"));
        cargo1.setChecked(booleansMap.get("sandCargo1"));
        cargo2.setChecked(booleansMap.get("sandCargo2"));
        cargo3.setChecked(booleansMap.get("sandCargo3"));
        */
        startingPlat.setSelection(integersMap.get("sandStartingLocationIndex"));
        sandOperation.setSelection(integersMap.get("sandOperationIndex"));
        gamePiece.setSelection(integersMap.get("sandStartingPieceIndex"));
/*
        Log.d("Old sandHatchCs", String.valueOf(hatchCS.isChecked()));
        Log.d("Old sandHatch1", String.valueOf(hatch1.isChecked()));
        Log.d("Old sandHatch2", String.valueOf(hatch2.isChecked()));
        Log.d("Old sandHatch3", String.valueOf(hatch3.isChecked()));
        */
        //getFragmentManager().beginTransaction().detach(this).attach(this).commit();

    }


    @CallSuper
    public void onStart() {
        super.onStart();
        Log.d("SandStorm", "looking at view");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser)
            Log.d("MyFragment", "Fragment is visible.");
            if(reloadFrame){
                reloadFrame = false;
                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            }
        else
            Log.d("MyFragment", "Fragment is not visible.");
            reloadFrame = true;
        }

}
