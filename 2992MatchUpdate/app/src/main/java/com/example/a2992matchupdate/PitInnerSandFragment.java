package com.example.a2992matchupdate;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;

import static com.example.a2992matchupdate.TeamActivity.listsOfStrings;

public class PitInnerSandFragment extends Fragment {

    private final String TAG = "PitInnerSand";

    private TextView startingPlat;
    private TextView operation;
    private CheckBox hatchcs;
    private CheckBox hatch1;
    private CheckBox hatch2;
    private CheckBox hatch3;
    private CheckBox cargoCS;
    private CheckBox cargo1;
    private CheckBox cargo2;
    private CheckBox cargo3;
    private TextView startingPiece;
    private TextView piecesScored;
    
    
    public PitInnerSandFragment(){

    }

    public static PitInnerSandFragment newInstance() {
        PitInnerSandFragment fragment = new PitInnerSandFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pit_inner_sand_fragment, container, false);

        startingPlat = rootView.findViewById(R.id.pit_inner_sand_startingplat_textview);
        operation = rootView.findViewById(R.id.pit_inner_sand_operation_textview);
        hatchcs = rootView.findViewById(R.id.pit_inner_sand_hatchcs_box);
        hatch1 = rootView.findViewById(R.id.pit_inner_sand_hatch1_box);
        hatch2 = rootView.findViewById(R.id.pit_inner_sand_hatch2_box);
        hatch3 = rootView.findViewById(R.id.pit_inner_sand_hatch3_box);
        cargoCS = rootView.findViewById(R.id.pit_inner_sand_cargocs_box);
        cargo1 = rootView.findViewById(R.id.pit_inner_sand_cargo1_box);
        cargo2 = rootView.findViewById(R.id.pit_inner_sand_cargo2_box);
        cargo3 = rootView.findViewById(R.id.pit_inner_sand_cargo3_box);
        startingPiece = rootView.findViewById(R.id.pit_inner_sand_startingpiece_textview);
        piecesScored = rootView.findViewById(R.id.pit_inner_sand_score_textview);

        try{
            if(listsOfStrings.get("PitStrings") != null){
                insertStrings(listsOfStrings.get("PitStrings"));
            }
            if(listsOfStrings.get("PitBooleans") != null){
                insertBooleans(listsOfStrings.get("PitBooleans"));
            }
            if(listsOfStrings.get("PitIntegers") != null){
                insertIntegers(listsOfStrings.get("PitIntegers"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }


        hatchcs.setClickable(false);
        hatch1.setClickable(false);
        hatch2.setClickable(false);
        hatch3.setClickable(false);
        cargoCS.setClickable(false);
        cargo1.setClickable(false);
        cargo2.setClickable(false);
        cargo3.setClickable(false);









        return rootView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(getContext());

        Log.d("On attach run", "pitinnersand");

    }


    public void insertBooleans(HashMap<String, String> boolHash){
        Log.d(TAG, "insertBooleans");
        hatchcs.setChecked(Boolean.valueOf(boolHash.get("sandHatchCS")));
        hatch1.setChecked(Boolean.valueOf(boolHash.get("sandHatch1")));
        hatch2.setChecked(Boolean.valueOf(boolHash.get("sandHatch2")));
        hatch3.setChecked(Boolean.valueOf(boolHash.get("sandHatch3")));
        cargoCS.setChecked(Boolean.valueOf(boolHash.get("sandCargoCS")));
        cargo1.setChecked(Boolean.valueOf(boolHash.get("sandCargo1")));
        cargo2.setChecked(Boolean.valueOf(boolHash.get("sandCargo2")));
        cargo3.setChecked(Boolean.valueOf(boolHash.get("sandCargo3")));
    }

    public void insertStrings(HashMap<String, String> stringHash){
        Log.d(TAG, "insertStrings");

    }

    public void insertIntegers(HashMap<String, String> intHash){
        for(String i: intHash.keySet()){
            Log.d(TAG + " intHash", i);
            Log.d(TAG + " value", intHash.get(i));
        }

        try{
            Log.d(TAG, "insertIntegers");
            if(intHash.get("sandOperationIndex") == null){
                operation.setText("Manual (Camera)");
            }
            int sandOp = Integer.valueOf(intHash.get("sandOperationIndex"));
            if(sandOp == 0){
                operation.setText("Manual (Camera)");
            } else if(sandOp == 1) {
                operation.setText("Autonomous");
            } else if(sandOp == 2){
                operation.setText("No Sandstorm Operation");
            } else {
                Log.d(TAG, "operationScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandStartingLocationIndex") == null){
                startingPlat.setText("HAB Level 1");
            }
            int plat = Integer.valueOf(intHash.get("sandStartingLocationIndex"));
            if(plat == 0){
                startingPlat.setText("HAB Level 1");
            } else if(plat == 1){
                startingPlat.setText("HAB Level 2");
            } else {
                Log.d(TAG, "platScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandStartingPieceIndex") == null){
                startingPiece.setText("No Preference");
            }
            int startingPieceInt = Integer.valueOf(intHash.get("sandStartingPieceIndex"));
            if(startingPieceInt == 0){
                startingPiece.setText("No Preference");
            } else if(startingPieceInt == 1){
                startingPiece.setText("Cargo");
            } else if(startingPieceInt == 2){
                startingPiece.setText("Hatch");
            } else {
                Log.d(TAG, "startScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("sandGamePiecesScored") == null){
                piecesScored.setText("0");
            }
            int scored = Integer.valueOf(intHash.get("sandGamePiecesScored"));
            piecesScored.setText(String.valueOf(scored));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
