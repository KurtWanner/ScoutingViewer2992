package com.example.a2992matchupdate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;

import static com.example.a2992matchupdate.InsertMatchFragment.matchNum;
import static com.example.a2992matchupdate.TeamActivity.listsOfStrings;

public class MatchInnerSandFragment extends Fragment {

    private final String TAG = "MatchInnerSand";
    
    
    private TextView preload;
    private TextView start;
    private TextView hatch1;
    private TextView hatch2;
    private TextView hatch3;
    private TextView hatchCS;
    private TextView cargo1;
    private TextView cargo2;
    private TextView cargo3;
    private TextView cargoCS;
    private CheckBox habCross;
    

    public MatchInnerSandFragment(){

    }

    public static MatchInnerSandFragment newInstance() {
        MatchInnerSandFragment fragment = new MatchInnerSandFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.match_inner_sand, container, false);


        preload = rootView.findViewById(R.id.match_inner_sand_preload);
        start = rootView.findViewById(R.id.match_inner_sand_startinghab);
        hatch1 = rootView.findViewById(R.id.match_inner_sand_hatch1);
        hatch2 = rootView.findViewById(R.id.match_inner_sand_hatch2);
        hatch3 = rootView.findViewById(R.id.match_inner_sand_hatch3);
        hatchCS = rootView.findViewById(R.id.match_inner_sand_hatchcs);
        cargo1 = rootView.findViewById(R.id.match_inner_sand_cargo1);
        cargo2 = rootView.findViewById(R.id.match_inner_sand_cargo2);
        cargo3 = rootView.findViewById(R.id.match_inner_sand_cargo3);
        cargoCS = rootView.findViewById(R.id.match_inner_sand_cargocs);
        habCross = rootView.findViewById(R.id.match_inner_sand_hab_box);


        try{
            if(listsOfStrings.get("Match" + matchNum) != null){
                insertStrings(listsOfStrings.get("Match" + matchNum));
                insertBooleans(listsOfStrings.get("Match" + matchNum));
                insertIntegers(listsOfStrings.get("Match" + matchNum));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        habCross.setClickable(false);

        return rootView;
    }


    //TODO Set variables
    public void insertBooleans(HashMap<String, String> boolHash){
        Log.d(TAG, "insertBooleans");

        habCross.setChecked(Boolean.valueOf(boolHash.get("sandCrossHab")));
    }

    public void insertStrings(HashMap<String, String> stringHash){
        Log.d(TAG, "insertStrings");


    }

    public void insertIntegers(HashMap<String, String> intHash){
        Log.d(TAG, "insertIntegers");

        try{
            if(intHash.get("sandcargo1") == null){
                cargo1.setText("0");
            }
            int sandOp = Integer.valueOf(intHash.get("sandcargo1"));
            cargo1.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandcargo2") == null){
                cargo2.setText("0");
            }
            int sandOp = Integer.valueOf(intHash.get("sandcargo2"));
            cargo2.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandcargo3") == null){
                cargo3.setText("0");
            }
            int sandOp = Integer.valueOf(intHash.get("sandcargo3"));
            cargo3.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandcargoCS") == null){
                cargoCS.setText("0");
            }
            int sandOp = Integer.valueOf(intHash.get("sandcargoCS"));
            cargoCS.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandhatch1") == null){
                hatch1.setText("0");
            }
            int sandOp = Integer.valueOf(intHash.get("sandhatch1"));
            hatch1.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandhatch2") == null){
                hatch2.setText("0");
            }
            int sandOp = Integer.valueOf(intHash.get("sandhatch2"));
            hatch2.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandhatch3") == null){
                hatch3.setText("0");
            }
            int sandOp = Integer.valueOf(intHash.get("sandhatch3"));
            hatch3.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandhatchCS") == null){
                hatchCS.setText("0");
            }
            int sandOp = Integer.valueOf(intHash.get("sandhatchCS"));
            hatchCS.setText(String.valueOf(sandOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("preLoadedGamePiece") == null){
                preload.setText("N/A");
            }
            int sandOp = Integer.valueOf(intHash.get("preLoadedGamePiece"));
            if(sandOp == 0){
                preload.setText("None");
            } else if(sandOp == 1) {
                preload.setText("Hatch");
            } else if(sandOp == 2){
                preload.setText("Cargo");
            } else {
                Log.d(TAG, "preLoadedGamePieceScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("preStartingHab") == null){
                start.setText("N/A");
            }
            int sandOp = Integer.valueOf(intHash.get("preStartingHab"));
            if(sandOp == 0){
                start.setText("Level 1");
            } else if(sandOp == 1) {
                start.setText("Level 2");
            } else if(sandOp == 2){
                start.setText("Was a No Show");
            } else {
                Log.d(TAG, "startScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
