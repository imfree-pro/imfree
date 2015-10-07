package com.imfree.friendsuggest;

import android.support.v4.app.Fragment;

/**
 * Created by 종열 on 2015-06-06.
 */
public class FriendSuggestPagerItem {
    private String _title;
    private String _categoryNo;

    public FriendSuggestPagerItem(String categoryNo, String title) {
        _categoryNo = categoryNo;
        _title = title;
    }

    Fragment createFragment() {
        return FriendSuggestListFragment.newInstance(_categoryNo, _title);
    }

    public String getCategoryNo()
    {
        return _categoryNo;
    }

    public String getTitle() {
        return _title;
    }
}
