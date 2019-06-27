package com.example.a2992scoutingviewer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PitSectionsPageAdapter extends FragmentPagerAdapter {

    public PitSectionsPageAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position) {
            case 0: {
                return PitInnerSandFragment.newInstance();
            }case 1: {
                return PitInnerTeleFragment.newInstance();
            }case 2: {
                return PitInnerEndFragment.newInstance();
            }case 3: {
                return PitInnerRobotFragment.newInstance();
            }case 4: {
                return PitInnerDriveFragment.newInstance();
            }default: {
                return PitInnerSandFragment.newInstance();
            }
            //return MainActivity.PlaceholderFragment.newInstance(position + 1);
        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
