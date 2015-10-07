package com.external.NavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.imfree.imfree.FriendSuggestActivity;
import com.imfree.imfree.InUseFriendActivity;
import com.imfree.imfree.InviteFriendActivity;
import com.imfree.imfree.MySuggestActivity;
import com.imfree.imfree.R;

/**
 * Created by 이종열 on 2015-05-31.
 */
public abstract class NavigationDrawerActionBarActivity extends ActionBarActivity implements NavigationDrawerCallback {
    private long _backKeyPressedTime = 0;
    private Toast _toast;

    protected Toolbar _toolbar;
    protected ActionBar _actionbar;

    protected String[] _navigationDrawerItems;
    protected NavigationDrawerFragment _navigationDrawer;

    protected abstract int getNavigationDrawerId();
    protected abstract int getDrawerLayoutId();
    protected abstract int getMainLayoutId();
    protected abstract int getToolBarId();
    protected abstract int getNavigationDrawerSelectedPosition();
    protected int getCustomActionBarId()
    {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getMainLayoutId());

        _toolbar = (Toolbar) findViewById(getToolBarId());
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        _actionbar = getSupportActionBar();

        if ( getCustomActionBarId() != 0 ) {
            _actionbar.setDisplayShowTitleEnabled(false);
            _actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            _actionbar.setCustomView(getCustomActionBarId());
        }

        _navigationDrawerItems = getResources().getStringArray(R.array.navigationdrawer_items);

        _navigationDrawer = (NavigationDrawerFragment) getFragmentManager().findFragmentById(getNavigationDrawerId());
        _navigationDrawer.setup(getNavigationDrawerId(), (DrawerLayout) findViewById(getDrawerLayoutId()), _toolbar, _actionbar, getNavigationDrawerSelectedPosition());
    }

    @Override
    public void onNavigationDrawerSelected(int position) {
        Intent intent = new Intent();
        switch (position)
        {
            case 1 : intent.setClass(NavigationDrawerActionBarActivity.this, FriendSuggestActivity.class); break;
            case 2 : intent.setClass(NavigationDrawerActionBarActivity.this, MySuggestActivity.class); break;
            case 4 : intent.setClass(NavigationDrawerActionBarActivity.this, InUseFriendActivity.class); break;
            case 5 : intent.setClass(NavigationDrawerActionBarActivity.this, InviteFriendActivity.class); break;
        }
        startActivity(intent);
        overridePendingTransition(R.anim.anim_stable, R.anim.anim_stable);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (_navigationDrawer.isDrawerOpen())
            _navigationDrawer.closeDrawer();
        else {
            if (System.currentTimeMillis() > _backKeyPressedTime + 2000) {
                _backKeyPressedTime = System.currentTimeMillis();
                showGuide();
                return;
            }

            if (System.currentTimeMillis() <= _backKeyPressedTime + 2000) {
                finish();
                _toast.cancel();
            }
        }
    }

    private void showGuide() {
        _toast = Toast.makeText(this, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        _toast.show();
    }
}