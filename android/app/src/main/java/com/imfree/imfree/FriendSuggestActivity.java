package com.imfree.imfree;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.util.TypedValue;

import android.support.v4.view.ViewPager;


import com.external.FloatingActionButton.FloatingActionButton;
import com.external.NavigationDrawer.NavigationDrawerActionBarActivity;
import com.external.SlidingTab.PagerSlidingTabStrip;
import com.handler.CategoryHandler;
import com.imfree.friendsuggest.FriendSuggestPagerAdpater;
import com.imfree.friendsuggest.FriendSuggestPagerItem;
import com.handler._entity.CategoryEntity;

import java.util.ArrayList;


public class FriendSuggestActivity extends NavigationDrawerActionBarActivity {

    @Override
    protected int getNavigationDrawerId() {
        return R.id.fi_navigationDrawer;
    }

    @Override
    protected int getDrawerLayoutId() {
        return R.id.drawerLayout;
    }

    @Override
    protected int getMainLayoutId() {
        return R.layout.activity_friend_suggest;
    }

    @Override
    protected int getToolBarId() {
        return R.id.toolbar;
    }

    @Override
    protected int getNavigationDrawerSelectedPosition() {
        return 1;
    }

    private PagerSlidingTabStrip _slidingTab;
    private ViewPager _viewPager;
    private FriendSuggestPagerAdpater _pagerAdapter;
    private FloatingActionButton _actionButton;
    private ArrayList<FriendSuggestPagerItem> _tabList = new ArrayList<FriendSuggestPagerItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        CategoryHandler categoryHandler = new CategoryHandler(getApplication());
        ArrayList<CategoryEntity> categoryList = categoryHandler.getCategoryList();

        for (CategoryEntity entity : categoryList)
        {
            _tabList.add(new FriendSuggestPagerItem(
                    entity.getCategorySN(),
                    entity.getCategoryName()
            ));
        }

        _actionButton = (FloatingActionButton)this.findViewById(R.id.fab_actionButton);
        _slidingTab = (PagerSlidingTabStrip) this.findViewById(R.id.pagerSlidingTab);
        _viewPager = (ViewPager)findViewById(R.id.viewer_mainContents);

        _pagerAdapter = new FriendSuggestPagerAdpater(getSupportFragmentManager(), _tabList);
        _viewPager.setAdapter(_pagerAdapter);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        _viewPager.setPageMargin(pageMargin);
        _slidingTab.setViewPager(_viewPager);

        _actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FriendSuggestActivity.this, AddSuggestCategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stable);
            }
        });

        ProgressDialog dialog = ProgressDialog.show(this, "", "로딩중입니다.", true);
        dialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friend_suggest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            recreate();
        } else {
            final Intent intent = getIntent();
            finish();
            overridePendingTransition(R.anim.anim_stable, R.anim.anim_stable);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_stable, R.anim.anim_stable);
        }

        return super.onOptionsItemSelected(item);
    }

}
