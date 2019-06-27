package com.example.a2992scoutingviewer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OverviewSectionsPageAdapter extends FragmentPagerAdapter {

    public OverviewSectionsPageAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position) {
            case 0: {
                return OverviewInnerAveragesFragment.newInstance();
            }default: {
                return OverviewInnerAveragesFragment.newInstance();
            }
            //return MainActivity.PlaceholderFragment.newInstance(position + 1);
        }

    }

    @Override
    public int getCount() {
        return 1;
    }
}
