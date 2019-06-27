package com.example.a2992scoutingviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.a2992scoutingviewer.TeamActivity.listsOfStrings;
import static com.example.a2992scoutingviewer.TeamActivity.matchNums;

public class OverviewInnerGraphsFragment extends Fragment {

    private final String TAG = "OverviewInnerGraphs";

    private LineChart totalHatches;


    public OverviewInnerGraphsFragment(){

    }

    public static OverviewInnerGraphsFragment newInstance() {
        OverviewInnerGraphsFragment fragment = new OverviewInnerGraphsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.overview_inner_graphs, container, false);

        totalHatches = (LineChart) rootView.findViewById(R.id.totalHatchChart);

        List<Entry> entries = new ArrayList<Entry>();

        int q = 0;
        for(String i: matchNums){
            q++;
            entries.add(new Entry(q, Integer.valueOf(listsOfStrings.get("Match"+i).get("telehatch1"))
                    + Integer.valueOf(listsOfStrings.get("Match"+i).get("telehatch2"))
                    + Integer.valueOf(listsOfStrings.get("Match"+i).get("telehatch3"))
                    + Integer.valueOf(listsOfStrings.get("Match"+i).get("telehatchCS"))));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        LineData lineData = new LineData(dataSet);
        totalHatches.setData(lineData);

        XAxis xAxis = totalHatches.getXAxis();

        xAxis.setAxisMinimum(0);
        xAxis.setAxisMinimum(13);

        totalHatches.invalidate();
        
        return rootView;
    }



}
