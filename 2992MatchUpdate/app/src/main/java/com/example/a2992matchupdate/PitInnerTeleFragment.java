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

import static com.example.a2992matchupdate.TeamActivity.listsOfStrings;

public class PitInnerTeleFragment extends Fragment {

    private final String TAG = "PitInnerTele";

    private CheckBox hatchcs;
    private CheckBox hatch1;
    private CheckBox hatch2;
    private CheckBox hatch3;
    private CheckBox cargoCS;
    private CheckBox cargo1;
    private CheckBox cargo2;
    private CheckBox cargo3;
    private TextView scoringPiece;
    private TextView scoringLocation;

    public PitInnerTeleFragment(){

    }

    public static PitInnerTeleFragment newInstance() {
        PitInnerTeleFragment fragment = new PitInnerTeleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pit_inner_tele_fragment, container, false);
        
        hatchcs = rootView.findViewById(R.id.pit_inner_tele_hatchcs_box);
        hatch1 = rootView.findViewById(R.id.pit_inner_tele_hatch1_box);
        hatch2 = rootView.findViewById(R.id.pit_inner_tele_hatch2_box);
        hatch3 = rootView.findViewById(R.id.pit_inner_tele_hatch3_box);
        cargoCS = rootView.findViewById(R.id.pit_inner_tele_cargocs_box);
        cargo1 = rootView.findViewById(R.id.pit_inner_tele_cargo1_box);
        cargo2 = rootView.findViewById(R.id.pit_inner_tele_cargo2_box);
        cargo3 = rootView.findViewById(R.id.pit_inner_tele_cargo3_box);
        scoringPiece = rootView.findViewById(R.id.pit_inner_tele_preferred_piece_textview);
        scoringLocation = rootView.findViewById(R.id.pit_inner_tele_scoring_location_textview);

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


    public void insertBooleans(HashMap<String, String> boolHash){
        Log.d(TAG, "insertBooleans");
        hatchcs.setChecked(Boolean.valueOf(boolHash.get("teleHatchCS")));
        hatch1.setChecked(Boolean.valueOf(boolHash.get("teleHatch1")));
        hatch2.setChecked(Boolean.valueOf(boolHash.get("teleHatch2")));
        hatch3.setChecked(Boolean.valueOf(boolHash.get("teleHatch3")));
        cargoCS.setChecked(Boolean.valueOf(boolHash.get("teleCargoCS")));
        cargo1.setChecked(Boolean.valueOf(boolHash.get("teleCargo1")));
        cargo2.setChecked(Boolean.valueOf(boolHash.get("teleCargo2")));
        cargo3.setChecked(Boolean.valueOf(boolHash.get("teleCargo3")));
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
            if(intHash.get("teleGamePiece") == null){
                scoringPiece.setText("No Preference");
            }
            int sandOp = Integer.valueOf(intHash.get("sandOperationIndex"));
            if(sandOp == 0){
                scoringPiece.setText("No Preference");
            } else if(sandOp == 1) {
                scoringPiece.setText("Cargo");
            } else if(sandOp == 2){
                scoringPiece.setText("Hatch");
            } else {
                Log.d(TAG, "scoringPieceScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("teleScoringLocation") == null){
                scoringLocation.setText("No Preference");
            }
            int plat = Integer.valueOf(intHash.get("teleScoringLocation"));
            if(plat == 0){
                scoringLocation.setText("No Preference");
            } else if(plat == 1){
                scoringLocation.setText("CargoShip");
            } else if(plat == 2) {
                scoringLocation.setText("Rocket");
            } else{
                Log.d(TAG, "platScreamed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
