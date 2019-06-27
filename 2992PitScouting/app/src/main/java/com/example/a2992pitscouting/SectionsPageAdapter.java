package com.example.a2992pitscouting;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPageAdapter extends FragmentPagerAdapter {

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0: {
                return HomePageFragment.newInstance();
            }
            case 1: {
                return SandStormFragment.newInstance();
            }
            case 2: {
                return TeleopFragment.newInstance();
            }
            case 3: {
                return EndGameFragment.newInstance();
            }
            case 4: {
                return RobotPartsFragment.newInstance();
            }
            case 5: {
                return DriveteamFragment.newInstance();
            }
            case 6: {
                return SubmitFragment.newInstance();
            }
            default: {
                return HomePageFragment.newInstance();
            }
        }

        //return MainActivity.PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 7 total pages.
        return 7;
    }
}
