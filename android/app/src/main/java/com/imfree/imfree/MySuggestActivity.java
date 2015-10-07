package com.imfree.imfree;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.external.FloatingActionButton.FloatingActionButton;
import com.external.NavigationDrawer.NavigationDrawerActionBarActivity;


public class MySuggestActivity extends NavigationDrawerActionBarActivity {
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
        return R.layout.activity_my_suggest;
    }

    @Override
    protected int getToolBarId() {
        return R.id.toolbar;
    }

    @Override
    protected int getNavigationDrawerSelectedPosition() {
        return 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _actionButton = (FloatingActionButton)this.findViewById(R.id.fab_actionButton);
        _actionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MySuggestActivity.this, AddSuggestCategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_stable);
            }
        });
    }

    private FloatingActionButton _actionButton;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_suggest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
