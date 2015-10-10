package com.imfree.imfree;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.external.NavigationDrawer.NavigationDrawerActionBarActivity;
import com.imfree.inusefriend.InUseFriendAdapter;
import com.server.inusefriend.DBInUseFriendHelper;
import com.handler._entity.InUseFriendEntity;

import java.util.ArrayList;


public class InUseFriendActivity extends NavigationDrawerActionBarActivity {

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
        return R.layout.activity_in_use_friend;
    }

    @Override
    protected int getToolBarId() {
        return R.id.toolbar;
    }

    @Override
    protected int getNavigationDrawerSelectedPosition() {
        return 4;
    }

    private ListView _listInUseFriend;
    private ArrayList<InUseFriendEntity> _inUseFriendList;
    private InUseFriendAdapter _inUseFriendAdapter;
    private Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _inUseFriendList = new ArrayList<InUseFriendEntity>();
        _inUseFriendList = DBInUseFriendHelper.getInstance(_context).getAll();

        _listInUseFriend = (ListView) findViewById(R.id.listInUseFriend);
        _inUseFriendAdapter = new InUseFriendAdapter(this, R.layout.in_use_friend_item, _inUseFriendList);
        _listInUseFriend.setAdapter(_inUseFriendAdapter);
        _listInUseFriend.setOnItemClickListener(onListItemClickListener);
        _context = this;
        _inUseFriendAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart()
    {
        super.onStart();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_in_use_friend, menu);

        return true;
    }

    private AdapterView.OnItemClickListener onListItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {
            //InUseFriendAdapter adpater = (InUseFriendAdapter)parent.getAdapter();
            //Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.setClass(InUseFriendActivity.this, InUseFriendBySuggestActivity.class);
            intent.putExtra("userSN", _inUseFriendList.get(position).getUserSN());
            intent.putExtra("position", String.valueOf(position));
            intent.putExtra("displayName", _inUseFriendList.get(position).getDisplayName());
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_stable);
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
