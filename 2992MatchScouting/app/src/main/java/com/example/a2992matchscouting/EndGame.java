package com.example.a2992matchscouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.a2992matchscouting.MainActivity.booleanHashMap;
import static com.example.a2992matchscouting.MainActivity.integerHashMap;
import static com.example.a2992matchscouting.MainActivity.stringHashMap;


public class EndGame extends Fragment {

    private Spinner endHab;
    private Spinner assistTeam;
    private TextView timerText;
    private CheckBox buddyYesCheck;
    private TextView howMany;
    private TextView team1Text;
    private TextView team2Text;
    private TextView hab1Text;
    private TextView hab2Text;
    private CheckBox robo1Check;
    private CheckBox robo2Check;
    private Spinner team1Spinner;
    private Spinner hab1Spinner;
    private Spinner team2Spinner;
    private Spinner hab2Spinner;
    private Button timeButton;
    private Timer timer;
    private Boolean startTimer = false;
    private int time = 0;



    public EndGame() {
    }


    // TODO: Rename and change types and number of parameters
    public static EndGame newInstance() {
        EndGame fragment = new EndGame();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_end_game, container, false);

        timer = new Timer();
        endHab = rootview.findViewById(R.id.endHabSpinner);
        timerText = rootview.findViewById(R.id.timeText);
        timeButton = rootview.findViewById(R.id.endTimerBtn);
        buddyYesCheck = rootview.findViewById(R.id.endCheckBox);
        assistTeam = rootview.findViewById(R.id.endAssistSpinner);
        team1Text = rootview.findViewById(R.id.end1TeamText);
        team2Text = rootview.findViewById(R.id.end2TeamText);
        howMany = rootview.findViewById(R.id.endHowManyText);
        hab1Text = rootview.findViewById(R.id.end1HabText);
        hab2Text = rootview.findViewById(R.id.end2HabText);
        robo1Check = rootview.findViewById(R.id.end1RoboCheck);
        robo2Check = rootview.findViewById(R.id.end2RoboCheck);
        team1Spinner = rootview.findViewById(R.id.end1TeamSpinner);
        hab1Spinner = rootview.findViewById(R.id.end1HabSpinner);
        team2Spinner = rootview.findViewById(R.id.end2TeamSpinner);
        hab2Spinner = rootview.findViewById(R.id.end2HabSpinner);
        
        assistTeam.setAdapter(MainActivity.assistTeamAdapter);
        team1Spinner.setAdapter(MainActivity.teamAdapter);
        team2Spinner.setAdapter(MainActivity.teamAdapter);

        MainActivity.canLoadGuess = false;

        buddyYesCheck.setChecked(booleanHashMap.get("endBuddyAssist"));
        robo1Check.setChecked(booleanHashMap.get("end1Robo"));
        robo2Check.setChecked(booleanHashMap.get("end2Robo"));
        Log.d("end2Hab", String.valueOf(integerHashMap.get("end2Hab")));

        if(startTimer){
            timeButton.setText("Stop Timer");
        }


        if(robo1Check.isChecked()){
            team1Text.setVisibility(View.VISIBLE);
            hab1Text.setVisibility(View.VISIBLE);
            team1Spinner.setVisibility(View.VISIBLE);
            hab1Spinner.setVisibility(View.VISIBLE);
            team2Text.setVisibility(View.INVISIBLE);
            hab2Text.setVisibility(View.INVISIBLE);
            team2Spinner.setVisibility(View.INVISIBLE);
            hab2Spinner.setVisibility(View.INVISIBLE);
        }

        if(robo2Check.isChecked()){
            team1Text.setVisibility(View.VISIBLE);
            team2Text.setVisibility(View.VISIBLE);
            hab1Text.setVisibility(View.VISIBLE);
            hab2Text.setVisibility(View.VISIBLE);
            team1Spinner.setVisibility(View.VISIBLE);
            hab1Spinner.setVisibility(View.VISIBLE);
            team2Spinner.setVisibility(View.VISIBLE);
            hab2Spinner.setVisibility(View.VISIBLE);
        }

        if(buddyYesCheck.isChecked()){
            howMany.setVisibility(View.VISIBLE);
            robo1Check.setVisibility(View.VISIBLE);
            robo2Check.setVisibility(View.VISIBLE);
        }


        hab2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integerHashMap.put("end2Hab", position);
                Log.d("end2Hab", String.valueOf(integerHashMap.get("end2Hab")));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        hab1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integerHashMap.put("end1Hab", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        team2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringHashMap.put("end2TeamNum", parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        team1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringHashMap.put("end1TeamNum", parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        assistTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringHashMap.put("endAssisted", String.valueOf(parent.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        endHab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integerHashMap.put("endHabLevel", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        class Helper extends TimerTask
        {
            public int i = -1;
            public void run()
            {
                i++;
                Log.d("timer", String.valueOf(i));
                time = i;
            }
        }

        timerText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!s.toString().equals("")){
                    integerHashMap.put("endTime", Integer.valueOf(s.toString()));
                } else {
                    integerHashMap.put("endTime", 0);
                }
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!startTimer){

                    timer = new Timer();
                    timeButton.setText(R.string.stop_timer);
                    //timer.cancel();
                    TimerTask task = new Helper();
                    timer.schedule(task,0, 1000);
                    startTimer = true;
                } else {
                    timer.cancel();
                    timerText.setText(String.valueOf(time));
                    timeButton.setText(R.string.start_timer);
                    startTimer = false;
                }

            }
        });
        buddyYesCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                
                if(isChecked){
                    booleanHashMap.put("endBuddyAssist", true);
                    howMany.setVisibility(View.VISIBLE);
                    robo1Check.setVisibility(View.VISIBLE);
                    robo2Check.setVisibility(View.VISIBLE);
                } else {
                    booleanHashMap.put("endBuddyAssist", false);
                    robo1Check.setChecked(false);
                    robo2Check.setChecked(false);
                    howMany.setVisibility(View.INVISIBLE);
                    robo1Check.setVisibility(View.INVISIBLE);
                    robo2Check.setVisibility(View.INVISIBLE);
                    team1Text.setVisibility(View.INVISIBLE);
                    team2Text.setVisibility(View.INVISIBLE);
                    hab1Text.setVisibility(View.INVISIBLE);
                    hab2Text.setVisibility(View.INVISIBLE);
                    team1Spinner.setVisibility(View.INVISIBLE);
                    hab1Spinner.setVisibility(View.INVISIBLE);
                    team2Spinner.setVisibility(View.INVISIBLE);
                    hab2Spinner.setVisibility(View.INVISIBLE);
                }
                
            }
        });
        
        robo1Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                booleanHashMap.put("end1Robo", isChecked);
                if(!buttonView.isPressed()){
                    return;
                }
                robo2Check.setChecked(false);
                if(isChecked){
                    team1Text.setVisibility(View.VISIBLE);
                    hab1Text.setVisibility(View.VISIBLE);
                    team1Spinner.setVisibility(View.VISIBLE);
                    hab1Spinner.setVisibility(View.VISIBLE);
                    team2Text.setVisibility(View.INVISIBLE);
                    hab2Text.setVisibility(View.INVISIBLE);
                    team2Spinner.setVisibility(View.INVISIBLE);
                    hab2Spinner.setVisibility(View.INVISIBLE);
                } else {

                    team1Text.setVisibility(View.INVISIBLE);
                    team2Text.setVisibility(View.INVISIBLE);
                    hab1Text.setVisibility(View.INVISIBLE);
                    hab2Text.setVisibility(View.INVISIBLE);
                    team1Spinner.setVisibility(View.INVISIBLE);
                    hab1Spinner.setVisibility(View.INVISIBLE);
                    team2Spinner.setVisibility(View.INVISIBLE);
                    hab2Spinner.setVisibility(View.INVISIBLE);
                }
            }
        });
        
        robo2Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleanHashMap.put("end2Robo", isChecked);
                if(!buttonView.isPressed()){
                    return;
                }
                robo1Check.setChecked(false);
                if(isChecked){

                    team2Spinner.setSelection(Math.abs(team1Spinner.getSelectedItemPosition() - 1));


                    team1Text.setVisibility(View.VISIBLE);
                    team2Text.setVisibility(View.VISIBLE);
                    hab1Text.setVisibility(View.VISIBLE);
                    hab2Text.setVisibility(View.VISIBLE);
                    team1Spinner.setVisibility(View.VISIBLE);
                    hab1Spinner.setVisibility(View.VISIBLE);
                    team2Spinner.setVisibility(View.VISIBLE);
                    hab2Spinner.setVisibility(View.VISIBLE);
                } else {

                    team1Text.setVisibility(View.INVISIBLE);
                    team2Text.setVisibility(View.INVISIBLE);
                    hab1Text.setVisibility(View.INVISIBLE);
                    hab2Text.setVisibility(View.INVISIBLE);
                    team1Spinner.setVisibility(View.INVISIBLE);
                    hab1Spinner.setVisibility(View.INVISIBLE);
                    team2Spinner.setVisibility(View.INVISIBLE);
                    hab2Spinner.setVisibility(View.INVISIBLE);
                }
            }
        });

        return rootview;
    }

}
