package com.example.a2992scoutingviewer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PitScoutingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PitScoutingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TEAMNUM = "teamNum";
    private PitSectionsPageAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    // TODO: Rename and change types of parameters
    private String mParam1;


    public PitScoutingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PitScoutingFragment.
     */
    public static PitScoutingFragment newInstance(String param1) {
        PitScoutingFragment fragment = new PitScoutingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEAMNUM, param1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_TEAMNUM);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_pit_scouting, container, false);
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Log.d("PitScoutingFragment", "onCreateView");

        mSectionsPagerAdapter = new PitSectionsPageAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = rootview.findViewById(R.id.pitScoutingContainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) rootview.findViewById(R.id.toolbar2);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        //TODO Everything

        Log.d("pitScoutingFragment", "mViewPagerItem: "+ mViewPager.getCurrentItem());

        return rootview;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(getContext());

        Log.d("PitScoutingFragment", "has run onAttach");


    }


}
