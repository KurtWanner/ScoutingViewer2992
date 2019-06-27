package com.example.a2992matchscouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.a2992matchscouting.MainActivity.booleanHashMap;
import static com.example.a2992matchscouting.MainActivity.integerHashMap;
import static com.example.a2992matchscouting.MainActivity.stringHashMap;


public class SandStorm extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "sandStorm";

    private CheckBox habCross;
    private TextView hatchCS;
    private Button hatchCSAdd;
    private Button hatchCSSub;
    private TextView hatch1;
    private Button hatch1Add;
    private Button hatch1Sub;
    private TextView hatch2;
    private Button hatch2Add;
    private Button hatch2Sub;
    private TextView hatch3;
    private Button hatch3Add;
    private Button hatch3Sub;
    private TextView cargoCS;
    private Button cargoCSAdd;
    private Button cargoCSSub;
    private TextView cargo1;
    private Button cargo1Add;
    private Button cargo1Sub;
    private TextView cargo2;
    private Button cargo2Add;
    private Button cargo2Sub;
    private TextView cargo3;
    private Button cargo3Add;
    private Button cargo3Sub;
    // TODO: Rename and change types of parameters


    //private OnFragmentInteractionListener mListener;

    public SandStorm() {
        // Required empty public constructor
    }

    public static SandStorm newInstance() {
        SandStorm fragment = new SandStorm();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_sandstorm, container, false);

        MainActivity.canLoadGuess = false;

        hatchCS = (TextView) rootview.findViewById(R.id.sandHatchCS);
        hatchCSAdd = (Button) rootview.findViewById(R.id.sandHatchCSAdd);
        hatchCSSub = (Button) rootview.findViewById(R.id.sandHatchCSSub);
        hatch1 = (TextView) rootview.findViewById(R.id.sandHatch1);
        hatch1Add = (Button) rootview.findViewById(R.id.sandHatch1Add);
        hatch1Sub = (Button) rootview.findViewById(R.id.sandHatch1Sub);
        hatch2 = (TextView) rootview.findViewById(R.id.sandHatch2);
        hatch2Add = (Button) rootview.findViewById(R.id.sandHatch2Add);
        hatch2Sub = (Button) rootview.findViewById(R.id.sandHatch2Sub);
        hatch3 = (TextView) rootview.findViewById(R.id.sandHatch3);
        hatch3Add = (Button) rootview.findViewById(R.id.sandHatch3Add);
        hatch3Sub = (Button) rootview.findViewById(R.id.sandHatch3Sub);
        cargoCS = (TextView) rootview.findViewById(R.id.sandCargoCS);
        cargoCSAdd = (Button) rootview.findViewById(R.id.sandCargoCSAdd);
        cargoCSSub = (Button) rootview.findViewById(R.id.sandCargoCSSub);
        cargo1 = (TextView) rootview.findViewById(R.id.sandCargo1);
        cargo1Add = (Button) rootview.findViewById(R.id.sandCargo1Add);
        cargo1Sub = (Button) rootview.findViewById(R.id.sandCargo1Sub);
        cargo2 = (TextView) rootview.findViewById(R.id.sandCargo2);
        cargo2Add = (Button) rootview.findViewById(R.id.sandCargo2Add);
        cargo2Sub = (Button) rootview.findViewById(R.id.sandCargo2Sub);
        cargo3 = (TextView) rootview.findViewById(R.id.sandCargo3);
        cargo3Add = (Button) rootview.findViewById(R.id.sandCargo3Add);
        cargo3Sub = (Button) rootview.findViewById(R.id.sandCargo3Sub);
        habCross = (CheckBox) rootview.findViewById(R.id.sandHabCrossCheck);


        habCross.setChecked(booleanHashMap.get("sandCrossHab"));
        hatchCS.setText(String.valueOf(integerHashMap.get("sandhatchCS")));
        hatch1.setText(String.valueOf(integerHashMap.get("sandhatch1")));
        hatch2.setText(String.valueOf(integerHashMap.get("sandhatch2")));
        hatch3.setText(String.valueOf(integerHashMap.get("sandhatch3")));
        cargoCS.setText(String.valueOf(integerHashMap.get("sandcargoCS")));
        cargo1.setText(String.valueOf(integerHashMap.get("sandcargo1")));
        cargo2.setText(String.valueOf(integerHashMap.get("sandcargo2")));
        cargo3.setText(String.valueOf(integerHashMap.get("sandcargo3")));
        

        habCross.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleanHashMap.put("sandCrossHab", isChecked);
            }
        });

        hatchCSAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatchCS.getText().toString());
                i++;
                hatchCS.setText(String.valueOf(i));
            }
        });
        hatchCSSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatchCS.getText().toString());
                i--;
                hatchCS.setText(String.valueOf(i));
            }
        });


        hatchCS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 int i = Integer.valueOf(s.toString());
                 if(i < 0){
                     hatchCS.setText("0");
                 }
                 if(i > 9) {
                     hatchCS.setText("9");
                 }
                 integerHashMap.put("sandhatchCS", Integer.valueOf(hatchCS.getText().toString()));
            }
        });


        hatch1Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatch1.getText().toString());
                i++;
                hatch1.setText(String.valueOf(i));
            }
        });
        hatch1Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatch1.getText().toString());
                i--;
                hatch1.setText(String.valueOf(i));
            }
        });


        hatch1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = Integer.valueOf(s.toString());
                if(i < 0){
                    hatch1.setText("0");
                }
                if(i > 9) {
                    hatch1.setText("9");
                }
                integerHashMap.put("sandhatch1", Integer.valueOf(hatch1.getText().toString()));
            }
        });

        hatch2Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatch2.getText().toString());
                i++;
                hatch2.setText(String.valueOf(i));
            }
        });
        hatch2Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatch2.getText().toString());
                i--;
                hatch2.setText(String.valueOf(i));
            }
        });


        hatch2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = Integer.valueOf(s.toString());
                if(i < 0){
                    hatch2.setText("0");
                }
                if(i > 9) {
                    hatch2.setText("9");
                }
                integerHashMap.put("sandhatch2", Integer.valueOf(hatch2.getText().toString()));
            }
        });
        hatch3Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatch3.getText().toString());
                i++;
                hatch3.setText(String.valueOf(i));
            }
        });
        hatch3Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatch3.getText().toString());
                i--;
                hatch3.setText(String.valueOf(i));
            }
        });


        hatch3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = Integer.valueOf(s.toString());
                if(i < 0){
                    hatch3.setText("0");
                }
                if(i > 9) {
                    hatch3.setText("9");
                }
                integerHashMap.put("sandhatch3", Integer.valueOf(hatch3.getText().toString()));
            }
        });
        cargoCSAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargoCS.getText().toString());
                i++;
                cargoCS.setText(String.valueOf(i));
            }
        });
        cargoCSSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargoCS.getText().toString());
                i--;
                cargoCS.setText(String.valueOf(i));
            }
        });


        cargoCS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = Integer.valueOf(s.toString());
                if(i < 0){
                    cargoCS.setText("0");
                }
                if(i > 9) {
                    cargoCS.setText("9");
                }
                integerHashMap.put("sandcargoCS", Integer.valueOf(cargoCS.getText().toString()));
            }
        });
        cargo1Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargo1.getText().toString());
                i++;
                cargo1.setText(String.valueOf(i));
            }
        });
        cargo1Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargo1.getText().toString());
                i--;
                cargo1.setText(String.valueOf(i));
            }
        });


        cargo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = Integer.valueOf(s.toString());
                if(i < 0){
                    cargo1.setText("0");
                }
                if(i > 9) {
                    cargo1.setText("9");
                }
                integerHashMap.put("sandcargo1", Integer.valueOf(cargo1.getText().toString()));
            }
        });
        cargo2Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargo2.getText().toString());
                i++;
                cargo2.setText(String.valueOf(i));
            }
        });
        cargo2Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargo2.getText().toString());
                i--;
                cargo2.setText(String.valueOf(i));
            }
        });


        cargo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = Integer.valueOf(s.toString());
                if(i < 0){
                    cargo2.setText("0");
                }
                if(i > 9) {
                    cargo2.setText("9");
                }
                integerHashMap.put("sandcargo2", Integer.valueOf(cargo2.getText().toString()));
            }
        });
        cargo3Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargo3.getText().toString());
                i++;
                cargo3.setText(String.valueOf(i));
            }
        });
        cargo3Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargo3.getText().toString());
                i--;
                cargo3.setText(String.valueOf(i));
            }
        });


        cargo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = Integer.valueOf(s.toString());
                if(i < 0){
                    cargo3.setText("0");
                }
                if(i > 9) {
                    cargo3.setText("9");
                }
                integerHashMap.put("sandcargo3", Integer.valueOf(cargo3.getText().toString()));
            }
        });

        return rootview;
    }

}
