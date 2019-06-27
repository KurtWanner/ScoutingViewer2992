package com.example.a2992matchupdate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import static com.example.a2992matchupdate.InsertMatchFragment.matchNum;
import static com.example.a2992matchupdate.TeamActivity.listsOfStrings;

public class MatchInnerCommentsFragment extends Fragment {
    
    private final String TAG = "MatchInnerComments";

    private TextView general;
    private TextView defense;
    private TextView broke;

    public MatchInnerCommentsFragment(){

    }

    public static MatchInnerCommentsFragment newInstance() {
        MatchInnerCommentsFragment fragment = new MatchInnerCommentsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.match_inner_comments, container, false);

        general = rootView.findViewById(R.id.generalNotes);
        defense = rootView.findViewById(R.id.defenseNotes);
        broke = rootView.findViewById(R.id.brokeNotes);


        try{
            if(listsOfStrings.get("Match" + matchNum) != null){
                insertStrings(listsOfStrings.get("Match" + matchNum));
                insertBooleans(listsOfStrings.get("Match" + matchNum));
                insertIntegers(listsOfStrings.get("Match" + matchNum));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return rootView;
    }

    public void insertBooleans(HashMap<String, String> boolHash){
        Log.d(TAG, "insertBooleans");
        
    }

    public void insertStrings(HashMap<String, String> stringHash){
        Log.d(TAG, "insertStrings");

        try{
            if(stringHash.get("comGenNotes") == null){
                general.setText("N/A");
            }
            general.setText(stringHash.get("comGenNotes"));
            if(stringHash.get("comGenNotes").isEmpty()){
                general.setText("N/A");
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("comDefNotes") == null){
                defense.setText("N/A");
            }
            defense.setText(stringHash.get("comDefNotes"));
            if(stringHash.get("comDefNotes").isEmpty()){
                defense.setText("N/A");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(stringHash.get("comBrokeNotes") == null){
                broke.setText("N/A");
            }
            broke.setText(stringHash.get("comBrokeNotes"));
            if(stringHash.get("comBrokeNotes").isEmpty()){
                broke.setText("N/A");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void insertIntegers(HashMap<String, String> intHash){
        Log.d(TAG, "insertIntegers");
        
    }
}
