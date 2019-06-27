package com.example.a2992matchscouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.example.a2992matchscouting.MainActivity.b1;
import static com.example.a2992matchscouting.MainActivity.b2;
import static com.example.a2992matchscouting.MainActivity.b3;
import static com.example.a2992matchscouting.MainActivity.booleanHashMap;
import static com.example.a2992matchscouting.MainActivity.canLoadGuess;
import static com.example.a2992matchscouting.MainActivity.integerHashMap;
import static com.example.a2992matchscouting.MainActivity.matchNum;
import static com.example.a2992matchscouting.MainActivity.r1;
import static com.example.a2992matchscouting.MainActivity.r2;
import static com.example.a2992matchscouting.MainActivity.r3;
import static com.example.a2992matchscouting.MainActivity.stringHashMap;


public class PreMatch extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "preMatch";

    private TextView guessingLocked;
    private Spinner previousGuesses;
    private Spinner preloadedGamePiece;
    private Spinner startingHab;
    public TextView redScore;
    public TextView blueScore;
    private TextView winningAlliance;
    private TextView byHowMuch;
    private CheckBox redPick;
    private CheckBox tiePick;
    private CheckBox bluePick;
    private TextView userGuess;
    private List<Double> redList = new ArrayList<>();
    private List<Double> blueList = new ArrayList<>();

    private List<String> previousGuessList = new ArrayList<>();

    private ArrayAdapter guessAdapter;
    
    private HashMap<String, Double> teamPoints = new HashMap<>();
    private HashMap<String, Integer> teamNumOfPoints = new HashMap<>();



    // TODO: Rename and change types of parameters


    //private OnFragmentInteractionListener mListener;

    public PreMatch() {
        // Required empty public constructor
    }

    public static PreMatch newInstance() {
        PreMatch fragment = new PreMatch();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_pre_match, container, false);

        teamPoints.put("r1", 0.0);
        teamPoints.put("r2", 0.0);
        teamPoints.put("r3", 0.0);
        teamPoints.put("b1", 0.0);
        teamPoints.put("b2", 0.0);
        teamPoints.put("b3", 0.0);

        teamNumOfPoints.put("r1", 0);
        teamNumOfPoints.put("r2", 0);
        teamNumOfPoints.put("r3", 0);
        teamNumOfPoints.put("b1", 0);
        teamNumOfPoints.put("b2", 0);
        teamNumOfPoints.put("b3", 0);

        guessingLocked = (TextView) rootview.findViewById(R.id.previousGuessingLocked);
        previousGuesses = (Spinner) rootview.findViewById(R.id.previousGuessSpinner);
        preloadedGamePiece = (Spinner) rootview.findViewById(R.id.preGamePieceSpinner);
        startingHab = (Spinner) rootview.findViewById(R.id.preStartingLocation);
        redScore = (TextView) rootview.findViewById(R.id.preRedAllianceScore);
        blueScore = (TextView) rootview.findViewById(R.id.preBlueAllianceScore);
        winningAlliance = (TextView) rootview.findViewById(R.id.preWinningAllianceText);
        byHowMuch = (TextView) rootview.findViewById(R.id.preTheSpread);
        redPick = (CheckBox) rootview.findViewById(R.id.preRedAllianceCheck);
        tiePick = (CheckBox) rootview.findViewById(R.id.preTieAllianceCheck);
        bluePick = (CheckBox) rootview.findViewById(R.id.preBlueAllianceCheck);
        userGuess = (TextView) rootview.findViewById(R.id.preGuessText);

        previousGuessList.clear();
        previousGuessList.add("User Guesses From Last Match");


        guessAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, previousGuessList);
        guessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        previousGuesses.setAdapter(guessAdapter);

        fillPreviousGuesses();


        if(!canLoadGuess){
            guessingLocked.setVisibility(View.VISIBLE);
            redPick.setEnabled(false);
            bluePick.setEnabled(false);
            tiePick.setEnabled(false);
            userGuess.setEnabled(false);
        }

        //redPick.setEnabled(canLoadGuess);
        //bluePick.setEnabled(canLoadGuess);
        //tiePick.setEnabled(canLoadGuess);
        //userGuess.setEnabled(canLoadGuess);


        preloadedGamePiece.setSelection(integerHashMap.get("preLoadedGamePiece"));
        startingHab.setSelection(integerHashMap.get("preStartingHab"));

        setVegasGuess();

        blueScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                int bScore = Integer.valueOf(s.toString());
                int rScore = 0;

                rScore = Integer.valueOf(redScore.getText().toString());

                if(rScore > bScore){
                    winningAlliance.setText("The Red Alliance is estimated to win this match by:");
                    byHowMuch.setText(String.valueOf(rScore - bScore) + " Points");
                } else if(bScore > rScore){
                    winningAlliance.setText("The Blue Alliance is estimated to win this match by:");
                    byHowMuch.setText(String.valueOf(bScore - rScore)+ " Points");
                } else {
                    winningAlliance.setText("The Match Ends in a Tie");
                    byHowMuch.setText("0 Points");
                }
            }
        });
        redScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int rScore = Integer.valueOf(s.toString());
                int bScore = 0;

                bScore = Integer.valueOf(blueScore.getText().toString());

                if(rScore > bScore){
                    winningAlliance.setText("The Red Alliance is estimated to win this match by:");
                    byHowMuch.setText(String.valueOf(rScore - bScore) + " Points");
                } else if(bScore > rScore){
                    winningAlliance.setText("The Blue Alliance is estimated to win this match by:");
                    byHowMuch.setText(String.valueOf(bScore - rScore)+ " Points");
                } else {
                    winningAlliance.setText("The Match Ends in a Tie");
                    byHowMuch.setText("0 Points");
                }
            }
        });
        
        

        userGuess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                stringHashMap.put("preUserGuess", s.toString());
                if (tiePick.isChecked() && !s.toString().equals("0")){
                    userGuess.setText("0");
                }
            }
        });

        startingHab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integerHashMap.put("preStartingHab", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        preloadedGamePiece.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                integerHashMap.put("preLoadedGamePiece", position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        redPick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    booleanHashMap.put("preRedCheck", true);
                    tiePick.setChecked(false);
                    bluePick.setChecked(false);
                } else {
                    booleanHashMap.put("preRedCheck", false);
                }

            }
        });

        bluePick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    booleanHashMap.put("preBlueCheck", true);
                    tiePick.setChecked(false);
                    redPick.setChecked(false);
                } else {
                    booleanHashMap.put("preBlueCheck", false);
                }

            }
        });
        tiePick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    booleanHashMap.put("preTieCheck", true);
                    redPick.setChecked(false);
                    bluePick.setChecked(false);
                    userGuess.setText("0");
                } else {
                    booleanHashMap.put("preTieCheck", false);
                }

            }
        });





        return rootview;
    }

    public void setVegasGuess(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference r1Ref = mDatabase.child("Teams").child(r1).child("MatchScouting");
        DatabaseReference r2Ref = mDatabase.child("Teams").child(r2).child("MatchScouting");
        DatabaseReference r3Ref = mDatabase.child("Teams").child(r3).child("MatchScouting");
        DatabaseReference b1Ref = mDatabase.child("Teams").child(b1).child("MatchScouting");
        DatabaseReference b2Ref = mDatabase.child("Teams").child(b2).child("MatchScouting");
        DatabaseReference b3Ref = mDatabase.child("Teams").child(b3).child("MatchScouting");
        redList.clear();
        blueList.clear();

        ValueEventListener redEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d("red1dataSnapshot", dataSnapshot.getKey());

                int points = 0;
                int numOfPoints = 0;
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    Log.d("red1 i child", i.getKey());

                    numOfPoints ++;
                    try {
                        points += Integer.valueOf(i.child("Integers").child("totalPoints").getValue().toString());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

                if(numOfPoints > 0){
                    redList.add(Double.valueOf(points / numOfPoints));
                } else {
                    redList.add(Double.valueOf(0));
                }


                double yeet = 0;
                for(double i: redList){
                    yeet += i;
                }

                redScore.setText(String.valueOf(Math.round(yeet)));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ValueEventListener blueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d("dataSnapshot", dataSnapshot.getKey());
                int points = 0;
                int numOfPoints = 0;
                for(DataSnapshot i: dataSnapshot.getChildren()){
                    numOfPoints ++;
                    Log.d("i child", i.getKey());
                    try{
                        points += Integer.valueOf(i.child("Integers").child("totalPoints").getValue().toString());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

                if(numOfPoints > 0){
                    blueList.add(Double.valueOf(points / numOfPoints));
                } else {
                    blueList.add(Double.valueOf(0));
                }

                double yeet = 0;
                for(double i: blueList){
                    yeet += i;
                }
                
                blueScore.setText(String.valueOf(Math.round(yeet)));
                
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        
        r1Ref.addListenerForSingleValueEvent(redEventListener);
        r2Ref.addListenerForSingleValueEvent(redEventListener);
        r3Ref.addListenerForSingleValueEvent(redEventListener);
        b1Ref.addListenerForSingleValueEvent(blueEventListener);
        b2Ref.addListenerForSingleValueEvent(blueEventListener);
        b3Ref.addListenerForSingleValueEvent(blueEventListener);






    }

    public void fillPreviousGuesses(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String guess = dataSnapshot.child("Guess").getValue().toString();
                String num = dataSnapshot.child("Points").getValue().toString();
                if(!guess.equals("Tie")){
                    previousGuessList.add(dataSnapshot.getKey() + ": " + guess + " Wins By " + num);
                } else {
                    previousGuessList.add(dataSnapshot.getKey() + ": Match Ends In A Tie");
                }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.child("MatchGuesses").child(String.valueOf(Integer.valueOf(matchNum)-1)).addChildEventListener(childEventListener);

    }


/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
/*
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
/*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
*/
}
