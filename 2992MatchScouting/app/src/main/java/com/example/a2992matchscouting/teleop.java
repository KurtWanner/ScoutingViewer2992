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
import android.widget.Button;
import android.widget.TextView;

import static com.example.a2992matchscouting.MainActivity.integerHashMap;


public class teleop extends Fragment {
    
    
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

    private TextView hatchDrops;
    private Button hatchDropsAdd;
    private Button hatchDropsSub;
    private TextView cargoDrops;
    private Button cargoDropsAdd;
    private Button cargoDropsSub;
    

    public teleop() {
    }

    public static teleop newInstance() {
        teleop fragment = new teleop();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_teleop, container, false);

        hatchCS = (TextView) rootview.findViewById(R.id.teleHatchCS);
        hatchCSAdd = (Button) rootview.findViewById(R.id.teleHatchCSAdd);
        hatchCSSub = (Button) rootview.findViewById(R.id.teleHatchCSSub);
        hatch1 = (TextView) rootview.findViewById(R.id.teleHatch1);
        hatch1Add = (Button) rootview.findViewById(R.id.teleHatch1Add);
        hatch1Sub = (Button) rootview.findViewById(R.id.teleHatch1Sub);
        hatch2 = (TextView) rootview.findViewById(R.id.teleHatch2);
        hatch2Add = (Button) rootview.findViewById(R.id.teleHatch2Add);
        hatch2Sub = (Button) rootview.findViewById(R.id.teleHatch2Sub);
        hatch3 = (TextView) rootview.findViewById(R.id.teleHatch3);
        hatch3Add = (Button) rootview.findViewById(R.id.teleHatch3Add);
        hatch3Sub = (Button) rootview.findViewById(R.id.teleHatch3Sub);
        cargoCS = (TextView) rootview.findViewById(R.id.teleCargoCS);
        cargoCSAdd = (Button) rootview.findViewById(R.id.teleCargoCSAdd);
        cargoCSSub = (Button) rootview.findViewById(R.id.teleCargoCSSub);
        cargo1 = (TextView) rootview.findViewById(R.id.teleCargo1);
        cargo1Add = (Button) rootview.findViewById(R.id.teleCargo1Add);
        cargo1Sub = (Button) rootview.findViewById(R.id.teleCargo1Sub);
        cargo2 = (TextView) rootview.findViewById(R.id.teleCargo2);
        cargo2Add = (Button) rootview.findViewById(R.id.teleCargo2Add);
        cargo2Sub = (Button) rootview.findViewById(R.id.teleCargo2Sub);
        cargo3 = (TextView) rootview.findViewById(R.id.teleCargo3);
        cargo3Add = (Button) rootview.findViewById(R.id.teleCargo3Add);
        cargo3Sub = (Button) rootview.findViewById(R.id.teleCargo3Sub);

        hatchDrops = (TextView) rootview.findViewById(R.id.teleHatchDrops);
        hatchDropsAdd = (Button) rootview.findViewById(R.id.teleHatchDropsAdd);
        hatchDropsSub = (Button) rootview.findViewById(R.id.teleHatchDropsSub);
        cargoDrops = (TextView) rootview.findViewById(R.id.teleCargoDrops);
        cargoDropsAdd = (Button) rootview.findViewById(R.id.teleCargoDropsAdd);
        cargoDropsSub = (Button) rootview.findViewById(R.id.teleCargoDropsSub);

        MainActivity.canLoadGuess = false;

        hatchCS.setText(String.valueOf(integerHashMap.get("telehatchCS")));
        hatch1.setText(String.valueOf(integerHashMap.get("telehatch1")));
        hatch2.setText(String.valueOf(integerHashMap.get("telehatch2")));
        hatch3.setText(String.valueOf(integerHashMap.get("telehatch3")));
        cargoCS.setText(String.valueOf(integerHashMap.get("telecargoCS")));
        cargo1.setText(String.valueOf(integerHashMap.get("telecargo1")));
        cargo2.setText(String.valueOf(integerHashMap.get("telecargo2")));
        cargo3.setText(String.valueOf(integerHashMap.get("telecargo3")));
        
        hatchDrops.setText(String.valueOf(integerHashMap.get("telehatchDrops")));
        cargoDrops.setText(String.valueOf(integerHashMap.get("telecargoDrops")));


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
                integerHashMap.put("telehatchCS", Integer.valueOf(hatchCS.getText().toString()));
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
                integerHashMap.put("telehatch1", Integer.valueOf(hatch1.getText().toString()));
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
                integerHashMap.put("telehatch2", Integer.valueOf(hatch2.getText().toString()));
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
                integerHashMap.put("telehatch3", Integer.valueOf(hatch3.getText().toString()));
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
                integerHashMap.put("telecargoCS", Integer.valueOf(cargoCS.getText().toString()));
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
                integerHashMap.put("telecargo1", Integer.valueOf(cargo1.getText().toString()));
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
                integerHashMap.put("telecargo2", Integer.valueOf(cargo2.getText().toString()));
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
                integerHashMap.put("telecargo3", Integer.valueOf(cargo3.getText().toString()));
            }
        });

        cargoDropsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargoDrops.getText().toString());
                i++;
                cargoDrops.setText(String.valueOf(i));
            }
        });
        cargoDropsSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(cargoDrops.getText().toString());
                i--;
                cargoDrops.setText(String.valueOf(i));
            }
        });


        cargoDrops.addTextChangedListener(new TextWatcher() {
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
                    cargoDrops.setText("0");
                }
                if(i > 99) {
                    cargoDrops.setText("99");
                }
                integerHashMap.put("telecargoDrops", Integer.valueOf(cargoDrops.getText().toString()));
            }
        });

        hatchDropsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatchDrops.getText().toString());
                i++;
                hatchDrops.setText(String.valueOf(i));
            }
        });
        hatchDropsSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.valueOf(hatchDrops.getText().toString());
                i--;
                hatchDrops.setText(String.valueOf(i));
            }
        });


        hatchDrops.addTextChangedListener(new TextWatcher() {
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
                    hatchDrops.setText("0");
                }
                if(i > 99) {
                    hatchDrops.setText("99");
                }
                integerHashMap.put("telehatchDrops", Integer.valueOf(hatchDrops.getText().toString()));
            }
        });
        
        
        return rootview;
    }
    
}
