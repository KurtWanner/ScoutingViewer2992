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

public class MatchInnerTeleFragment extends Fragment {
    
    private final String TAG = "MatchInnerTele";

    private TextView hatch1;
    private TextView hatch2;
    private TextView hatch3;
    private TextView hatchCS;
    private TextView cargo1;
    private TextView cargo2;
    private TextView cargo3;
    private TextView cargoCS;
    
    private TextView hatchDrops;
    private TextView cargoDrops;

    public MatchInnerTeleFragment(){

    }

    public static MatchInnerTeleFragment newInstance() {
        MatchInnerTeleFragment fragment = new MatchInnerTeleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.match_inner_tele, container, false);

        hatch1 = rootView.findViewById(R.id.match_inner_tele_hatch1);
        hatch2 = rootView.findViewById(R.id.match_inner_tele_hatch2);
        hatch3 = rootView.findViewById(R.id.match_inner_tele_hatch3);
        hatchCS = rootView.findViewById(R.id.match_inner_tele_hatchcs);
        cargo1 = rootView.findViewById(R.id.match_inner_tele_cargo1);
        cargo2 = rootView.findViewById(R.id.match_inner_tele_cargo2);
        cargo3 = rootView.findViewById(R.id.match_inner_tele_cargo3);
        cargoCS = rootView.findViewById(R.id.match_inner_tele_cargocs);
        
        hatchDrops = rootView.findViewById(R.id.match_inner_tele_hatchdrops);
        cargoDrops = rootView.findViewById(R.id.match_inner_tele_cargodrops);


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


    //TODO Set variables
    public void insertBooleans(HashMap<String, String> boolHash){
        Log.d(TAG, "insertBooleans");
        
    }

    public void insertStrings(HashMap<String, String> stringHash){
        Log.d(TAG, "insertStrings");

    }

    public void insertIntegers(HashMap<String, String> intHash){
        Log.d(TAG, "insertIntegers");

        try{
            if(intHash.get("telecargoDrops") == null){
                cargoDrops.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telecargoDrops"));
            cargoDrops.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("telehatchDrops") == null){
                hatchDrops.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telehatchDrops"));
            hatchDrops.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
        try{
            if(intHash.get("telecargo1") == null){
                cargo1.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telecargo1"));
            cargo1.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("telecargo2") == null){
                cargo2.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telecargo2"));
            cargo2.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("telecargo3") == null){
                cargo3.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telecargo3"));
            cargo3.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("telecargoCS") == null){
                cargoCS.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telecargoCS"));
            cargoCS.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("telehatch1") == null){
                hatch1.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telehatch1"));
            hatch1.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("telehatch2") == null){
                hatch2.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telehatch2"));
            hatch2.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("telehatch3") == null){
                hatch3.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telehatch3"));
            hatch3.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(intHash.get("telehatchCS") == null){
                hatchCS.setText("0");
            }
            int teleOp = Integer.valueOf(intHash.get("telehatchCS"));
            hatchCS.setText(String.valueOf(teleOp));
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
    }
}
