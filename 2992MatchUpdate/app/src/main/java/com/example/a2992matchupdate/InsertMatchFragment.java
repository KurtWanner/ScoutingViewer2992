package com.example.a2992matchupdate;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link InsertMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertMatchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "InsertMatchFragment";

    private MatchSectionsPageAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private static final String ARG_PARAM1 = "teamNum";
    private static final String ARG_PARAM2 = "matchNum";

    // TODO: Rename and change types of parameters
    public static String teamNum;
    public static String matchNum;


    public InsertMatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment InsertMatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertMatchFragment newInstance(String teamNum, String matchNum) {
        Log.d(TAG, "newInstance");
        InsertMatchFragment fragment = new InsertMatchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, teamNum);
        args.putString(ARG_PARAM2, matchNum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (getArguments() != null) {
            teamNum = getArguments().getString(ARG_PARAM1);
            matchNum = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_insert_match, container, false);


        Log.d("PitScoutingFragment", "onCreateView");

        mSectionsPagerAdapter = new MatchSectionsPageAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = rootview.findViewById(R.id.matchViewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) rootview.findViewById(R.id.tabLayout);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        //TODO Everything

        Log.d("pitScoutingFragment", "mViewPagerItem: "+ mViewPager.getCurrentItem());




        return rootview;
    }

}
