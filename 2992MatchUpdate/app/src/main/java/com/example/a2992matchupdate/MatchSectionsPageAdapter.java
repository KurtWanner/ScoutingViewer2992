package com.example.a2992matchupdate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MatchSectionsPageAdapter extends FragmentPagerAdapter {

    public MatchSectionsPageAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position) {
            case 0: {
                return MatchInnerSummaryFragment.newInstance();
            }case 1: {
                return MatchInnerCommentsFragment.newInstance();
            }case 2: {
                return MatchInnerSandFragment.newInstance();
            }case 3: {
                return MatchInnerTeleFragment.newInstance();
            }case 4: {
                return MatchInnerEndFragment.newInstance();
            }default: {
                return MatchInnerSandFragment.newInstance();
            }
            //return MainActivity.PlaceholderFragment.newInstance(position + 1);
        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
