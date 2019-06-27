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
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import static com.example.a2992matchscouting.MainActivity.booleanHashMap;
import static com.example.a2992matchscouting.MainActivity.stringHashMap;


public class Comments extends Fragment {

    private CheckBox defenseCheck;
    private CheckBox brokeCheck;
    private TextView genNotes;
    private TextView defNotes;
    private TextView brokeNotes;

    public Comments() {
        // Required empty public constructor
    }

    public static Comments newInstance() {
        Comments fragment = new Comments();
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
        View rootview = inflater.inflate(R.layout.fragment_comments, container, false);


        defenseCheck = rootview.findViewById(R.id.comDefCheck);
        brokeCheck = rootview.findViewById(R.id.comBrokeCheck);
        genNotes = rootview.findViewById(R.id.comGenNotes);
        defNotes = rootview.findViewById(R.id.comDefText);
        brokeNotes = rootview.findViewById(R.id.comBrokeText);

        MainActivity.canLoadGuess = false;


        brokeNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                stringHashMap.put("comBrokeNotes", s.toString());
            }
        });

        defNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                stringHashMap.put("comDefNotes", s.toString());

            }
        });

        genNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                stringHashMap.put("comGenNotes", s.toString());

            }
        });

        defenseCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleanHashMap.put("comDefCheck", isChecked);
                if (isChecked){
                    defNotes.setVisibility(View.VISIBLE);
                } else {
                    defNotes.setVisibility(View.INVISIBLE);

                }
            }
        });
        brokeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleanHashMap.put("comBrokeCheck", isChecked);
                if(isChecked){
                    brokeNotes.setVisibility(View.VISIBLE);
                } else {
                    brokeNotes.setVisibility(View.INVISIBLE);
                }
            }
        });





        return rootview;
    }

}
