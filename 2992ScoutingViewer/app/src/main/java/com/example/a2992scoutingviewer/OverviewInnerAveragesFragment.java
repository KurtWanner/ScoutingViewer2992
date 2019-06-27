package com.example.a2992scoutingviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;

import static com.example.a2992scoutingviewer.TeamActivity.avgHabEnd;
import static com.example.a2992scoutingviewer.TeamActivity.avgHabStart;
import static com.example.a2992scoutingviewer.TeamActivity.avgPoints;
import static com.example.a2992scoutingviewer.TeamActivity.avgSandCargo1;
import static com.example.a2992scoutingviewer.TeamActivity.avgSandCargo2;
import static com.example.a2992scoutingviewer.TeamActivity.avgSandCargo3;
import static com.example.a2992scoutingviewer.TeamActivity.avgSandCargoCS;
import static com.example.a2992scoutingviewer.TeamActivity.avgSandHatch1;
import static com.example.a2992scoutingviewer.TeamActivity.avgSandHatch2;
import static com.example.a2992scoutingviewer.TeamActivity.avgSandHatch3;
import static com.example.a2992scoutingviewer.TeamActivity.avgSandHatchCS;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleCargo1;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleCargo2;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleCargo3;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleCargoCS;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleCargoDrops;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleHatch1;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleHatch2;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleHatch3;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleHatchCS;
import static com.example.a2992scoutingviewer.TeamActivity.avgTeleHatchDrops;
import static com.example.a2992scoutingviewer.TeamActivity.listsOfStrings;

public class OverviewInnerAveragesFragment extends Fragment {

    private final String TAG = "OverviewInnerAverages";


    private TextView overHabStart;
    private TextView overSandHatch;
    private TextView overSandCargo;
    private TextView overTotalHatch;
    private TextView overHatchCS;
    private TextView overHatch1;
    private TextView overHatch2;
    private TextView overHatch3;
    private TextView overTotalCargo;
    private TextView overCargoCS;
    private TextView overCargo1;
    private TextView overCargo2;
    private TextView overCargo3;
    private TextView overHabEnd;
    private TextView overTotalPoints;
    private TextView overCargoDrops;
    private TextView overHatchDrops;



    public OverviewInnerAveragesFragment(){

    }

    public static OverviewInnerAveragesFragment newInstance() {
        OverviewInnerAveragesFragment fragment = new OverviewInnerAveragesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.overview_inner_averages, container, false);

        overHabStart = rootView.findViewById(R.id.overHabStart);
        overSandHatch = rootView.findViewById(R.id.overSandHatch);
        overSandCargo = rootView.findViewById(R.id.overSandCargo);
        overTotalHatch = rootView.findViewById(R.id.overTotalHatch);
        overHatchCS = rootView.findViewById(R.id.overHatchCS);
        overHatch1 = rootView.findViewById(R.id.overHatch1);
        overHatch2 = rootView.findViewById(R.id.overHatch2);
        overHatch3 = rootView.findViewById(R.id.overHatch3);
        overTotalCargo = rootView.findViewById(R.id.overTotalCargo);
        overCargoCS = rootView.findViewById(R.id.overCargoCS);
        overCargo1 = rootView.findViewById(R.id.overCargo1);
        overCargo2 = rootView.findViewById(R.id.overCargo2);
        overCargo3 = rootView.findViewById(R.id.overCargo3);
        overHabEnd = rootView.findViewById(R.id.overHabEnd);
        overTotalPoints = rootView.findViewById(R.id.overTotalPoints);
        overCargoDrops = rootView.findViewById(R.id.overCargoDrops);
        overHatchDrops = rootView.findViewById(R.id.overHatchDrops);

        DecimalFormat df = new DecimalFormat("###.##");


        overHabStart.setText(String.valueOf(df.format(avgHabStart)));
        overSandHatch.setText(String.valueOf(df.format(avgSandHatch1 + avgSandHatch2 + avgSandHatch3 + avgSandHatchCS)));
        overSandCargo.setText(String.valueOf(df.format(avgSandCargo1 + avgSandCargo2 + avgSandCargo3 + avgSandCargoCS)));
        overTotalHatch.setText(String.valueOf(df.format(avgTeleHatch1 + avgTeleHatch2 + avgTeleHatch3 + avgTeleHatchCS)));
        overHatchCS.setText(String.valueOf(df.format(avgTeleHatchCS)));
        overHatch1.setText(String.valueOf(df.format(avgTeleHatch1)));
        overHatch2.setText(String.valueOf(df.format(avgTeleHatch2)));
        overHatch3.setText(String.valueOf(df.format(avgTeleHatch3)));
        overTotalCargo.setText(String.valueOf(df.format(avgTeleCargo1 + avgTeleCargo2 + avgTeleCargo3 + avgTeleCargoCS)));
        overCargoCS.setText(String.valueOf(df.format(avgTeleCargoCS)));
        overCargo1.setText(String.valueOf(df.format(avgTeleCargo1)));
        overCargo2.setText(String.valueOf(df.format(avgTeleCargo2)));
        overCargo3.setText(String.valueOf(df.format(avgTeleCargo3)));
        overHabEnd.setText(String.valueOf(df.format(avgHabEnd)));
        overTotalPoints.setText(String.valueOf(df.format(avgPoints)));
        overCargoDrops.setText(String.valueOf(df.format(avgTeleCargoDrops)));
        overHatchDrops.setText(String.valueOf(df.format(avgTeleHatchDrops)));

        return rootView;
    }

}
