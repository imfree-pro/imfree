package com.imfree.friendsuggest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-06.
 */
public class FriendSuggestPagerAdpater extends FragmentPagerAdapter  {

    private ArrayList<FriendSuggestPagerItem> _tabs = new ArrayList<FriendSuggestPagerItem>();

    public FriendSuggestPagerAdpater(FragmentManager fm, ArrayList<FriendSuggestPagerItem> tabs) {
        super(fm);
        _tabs = (ArrayList<FriendSuggestPagerItem>)tabs.clone();
    }

    @Override
    public Fragment getItem(int i) {
        return _tabs.get(i).createFragment();
    }

    @Override
    public int getCount() {
        return _tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return _tabs.get(position).getTitle();
    }

}
