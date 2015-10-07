package com.imfree.imfree;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.imfree.inusefriend.InUseFriendSuggestAdapter;


public class InUseFriendBySuggestActivity extends ActionBarActivity {

    protected Toolbar _toolbar;
    protected ActionBar _actionbar;
    protected String _suggestName;
    protected String _userSN;
    protected String _position;


    private RecyclerView _recyclerView;
    private RecyclerView.LayoutManager _layoutManager;
    private RecyclerView.Adapter _inUseFriendSuggestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_use_friend_by_suggest);

        Intent intent = getIntent();

        _suggestName = intent.getExtras().getString("displayName");
        _userSN = intent.getExtras().getString("userSN");
        _position = intent.getExtras().getString("position");

        _toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);
        _actionbar = getSupportActionBar();

        _recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new GridLayoutManager(this, 2);
        _recyclerView.setLayoutManager(_layoutManager);
        _inUseFriendSuggestAdapter = new InUseFriendSuggestAdapter(this, _position);
        _recyclerView.setAdapter(_inUseFriendSuggestAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_in_use_friend_by_suggest, menu);
        _actionbar.setTitle(_suggestName + getResources().getString(R.string.title_activity_in_use_friend_by_suggest).toString());
        _actionbar.setDisplayHomeAsUpEnabled(true);
        _actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_stable, R.anim.anim_slide_out_left);
    }
}
