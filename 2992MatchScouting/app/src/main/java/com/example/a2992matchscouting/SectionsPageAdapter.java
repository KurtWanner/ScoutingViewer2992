package com.example.a2992matchscouting;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

public class SectionsPageAdapter extends FragmentPagerAdapter {

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position) {
            case 0: {
                return PreMatch.newInstance();
            }
            case 1: {
                return SandStorm.newInstance();
            }
            case 2: {
                return teleop.newInstance();
            }
            case 3: {
                return EndGame.newInstance();
            }
            case 4: {
                return Comments.newInstance();
            }
            case 5: {
                return Submit.newInstance();
            }
            default: {
                return PreMatch.newInstance();
            }
        //return MainActivity.PlaceholderFragment.newInstance(position + 1);
        }

    }

    @Override
    public int getCount() {
        return 6;
    }
}
