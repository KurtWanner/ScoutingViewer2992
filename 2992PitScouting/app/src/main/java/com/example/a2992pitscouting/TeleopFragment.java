package com.example.a2992pitscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import static com.example.a2992pitscouting.MainActivity.booleansMap;
import static com.example.a2992pitscouting.MainActivity.integersMap;

public class TeleopFragment extends Fragment {
    //The fragment argument representing the section number for this
    //fragment.
    
    
    
    private Switch HatchCS;
    private Switch Hatch1;
    private Switch Hatch2;
    private Switch Hatch3;
    private Switch CargoCS;
    private Switch Cargo1;
    private Switch Cargo2;
    private Switch Cargo3;
    private Spinner GamePiece;
    private Spinner ScoringLocation;


    private static final String ARG_SECTION_NUMBER = "section_number";

    public TeleopFragment() {
    }


    //Returns a new instance of this fragment for the given section
    //number.

    public static TeleopFragment newInstance() {
        TeleopFragment fragment = new TeleopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.teleop, container, false);

        Log.d("Teleop fragment", "has run");

        HatchCS = (Switch) rootView.findViewById(R.id.teleHatchCSBtn);
        Hatch1 = (Switch) rootView.findViewById(R.id.teleHatch1Btn);
        Hatch2 = (Switch) rootView.findViewById(R.id.teleHatch2Btn);
        Hatch3 = (Switch) rootView.findViewById(R.id.teleHatch3Btn);
        CargoCS = (Switch) rootView.findViewById(R.id.teleCargoCSBtn);
        Cargo1 = (Switch) rootView.findViewById(R.id.teleCargo1Btn);
        Cargo2 = (Switch) rootView.findViewById(R.id.teleCargo2Btn);
        Cargo3 = (Switch) rootView.findViewById(R.id.teleCargo3Btn);
        GamePiece = (Spinner) rootView.findViewById(R.id.teleGamePieceSpinner);
        ScoringLocation = (Spinner) rootView.findViewById(R.id.teleScoringLocationSpinner);

        HatchCS.setTag("R");
        Hatch1.setTag("R");
        Hatch2.setTag("R");
        Hatch3.setTag("R");
        CargoCS.setTag("R");
        Cargo1.setTag("R");
        Cargo2.setTag("R");
        Cargo3.setTag("R");

        HatchCS.setChecked(booleansMap.get("teleHatchCS"));
        Hatch1.setChecked(booleansMap.get("teleHatch1"));
        Hatch2.setChecked(booleansMap.get("teleHatch2"));
        Hatch3.setChecked(booleansMap.get("teleHatch3"));
        CargoCS.setChecked(booleansMap.get("teleCargoCS"));
        Cargo2.setChecked(booleansMap.get("teleCargo2"));
        Cargo3.setChecked(booleansMap.get("teleCargo3"));

        //Log.d("TeleCargo 1 Tele ", String.valueOf(booleansMap.get("teleCargo1")));
        Cargo1.setChecked(booleansMap.get("teleCargo1"));
        //Log.d("TeleCargo1 after ", String.valueOf(Cargo1.isChecked()));



        GamePiece.setSelection(integersMap.get("teleGamePiece"));
        ScoringLocation.setSelection(integersMap.get("teleScoringLocation"));


        HatchCS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(HatchCS.getTag() == null)){
                    HatchCS.setTag(null);
                    HatchCS.setChecked(booleansMap.get("teleHatchCS"));
                    return;
                }
                booleansMap.put("teleHatchCS", isChecked);
            }
        });
        Hatch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(Hatch1.getTag() == null)){
                    Hatch1.setTag(null);
                    Hatch1.setChecked(booleansMap.get("teleHatch1"));
                    return;
                }
                booleansMap.put("teleHatch1", isChecked);
            }
        });
        Hatch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(Hatch2.getTag() == null)){
                    Hatch2.setTag(null);
                    Hatch2.setChecked(booleansMap.get("teleHatch2"));
                    return;
                }
                booleansMap.put("teleHatch2", isChecked);
            }
        });
        Hatch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(Hatch3.getTag() == null)){
                    Hatch3.setTag(null);
                    Hatch3.setChecked(booleansMap.get("teleHatch3"));
                    return;
                }
                booleansMap.put("teleHatch3", isChecked);
            }
        });
        CargoCS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(CargoCS.getTag() == null)){
                    CargoCS.setTag(null);
                    CargoCS.setChecked(booleansMap.get("teleCargoCS"));
                    return;
                }
                booleansMap.put("teleCargoCS", isChecked);
            }
        });

        Cargo1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(Cargo1.getTag() == null)){
                    Cargo1.setTag(null);
                    Cargo1.setChecked(booleansMap.get("teleCargo1"));
                    return;
                }
                Log.d("Cargo1 Change ", String.valueOf(isChecked));
                booleansMap.put("teleCargo1", isChecked);
            }
        });

        Cargo2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(Cargo2.getTag() == null)){
                    Cargo2.setTag(null);
                    Cargo2.setChecked(booleansMap.get("teleCargo2"));
                    return;
                }
                booleansMap.put("teleCargo2", isChecked);
            }
        });
        Cargo3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!(Cargo3.getTag() == null)){
                    Cargo3.setTag(null);
                    Cargo3.setChecked(booleansMap.get("teleCargo3"));
                    return;
                }
                booleansMap.put("teleCargo3", Cargo3.isChecked());
            }
        });

        GamePiece.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integersMap.put("teleGamePiece", GamePiece.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ScoringLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integersMap.put("teleScoringLocation", ScoringLocation.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }
    @Override
    public void onResume(){
        super.onResume();

        GamePiece.setSelection(integersMap.get("teleGamePiece"));
        ScoringLocation.setSelection(integersMap.get("teleScoringLocation"));

    }
}
