package com.maxm.just_stamped.tabs;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Edwin on 15/02/2015
 * Modified by MaxM on 27.10.2016.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int numberOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fragmentManager,CharSequence mTitles[], int setedNumberOfTabs) {
        super(fragmentManager);
        this.titles = mTitles;
        this.numberOfTabs = setedNumberOfTabs;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                TabCommon tabCommon = new TabCommon();
                fragment = tabCommon;
                break;
            case 1:
                TabMine tabMine = new TabMine();
                fragment = tabMine;
                break;
            case 2:
                TabMostVisited tabMostVisited = new TabMostVisited();
                fragment = tabMostVisited;
                break;
            default:
                break;
        }
        return fragment;
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
