package com.imfree.imfree;

import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.external.materialdesign.widgets.Dialog;
import com.handler._entity.ContactsEntity;
import com.handler.dbhelper.contacts.ContactsDBHelper;
import com.external.NavigationDrawer.NavigationDrawerActionBarActivity;
import com.imfree.invitefriend.InviteFriendAdapter;
import com.imfree.invitefriend.PhoneUserEntity;

import java.util.ArrayList;


public class InviteFriendActivity extends NavigationDrawerActionBarActivity {

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
        return R.layout.activity_invite_friend;
    }

    @Override
    protected int getToolBarId() {
        return R.id.toolbar;
    }

    @Override
    protected int getNavigationDrawerSelectedPosition() {
        return 5;
    }

    protected int getCustomActionBarId()
    {
        return R.layout.in_menu_invite_friend;
    }

    private ListView _listInviteFriend;
    private ArrayList<PhoneUserEntity> _phoneUserList;
    private InviteFriendAdapter _inviteFriendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _phoneUserList = new ArrayList<PhoneUserEntity>();
        _listInviteFriend = (ListView) findViewById(R.id.listInviteFriend);
        _inviteFriendAdapter = new InviteFriendAdapter(this, R.layout.in_invite_friend_item, _phoneUserList);
        _listInviteFriend.setAdapter(_inviteFriendAdapter);
        _listInviteFriend.setOnItemClickListener(onInviteItemClickListener);

        ContactsDBHelper contactsHelper = new ContactsDBHelper(this);
        ArrayList<ContactsEntity> contactsEntities = contactsHelper.getAllContacts();
        _phoneUserList.clear();
        for (ContactsEntity entity : contactsEntities)
        {
            _phoneUserList.add(new PhoneUserEntity(entity));
        }
        _inviteFriendAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart()
    {
        super.onStart();


    }

    private AdapterView.OnItemClickListener onInviteItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {
            _inviteFriendAdapter.selection(position);
            TextView txtSelectCount = (TextView) findViewById(R.id.naviselectCount);
            txtSelectCount.setText(String.valueOf(_inviteFriendAdapter.getSelectCount()));

            invalidateOptionsMenu();

            Toast.makeText(getApplicationContext(), "" + _phoneUserList.get(position).getHashPhone(), Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_invite_friend, menu);
        if ( _inviteFriendAdapter.getSelectCount() == 0 ) {
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(false);
        } else {
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == R.id.menu_check_enabled)
        {
            showPopUp();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showPopUp() {
        Dialog dialog = new Dialog(InviteFriendActivity.this, String.valueOf(_inviteFriendAdapter.getSelectCount()));
        dialog.setOnAcceptButtonClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InviteFriendActivity.this, "Click accept button", 1).show();
            }
        });
        dialog.setOnCancelButtonClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InviteFriendActivity.this, "Click cancel button", 1).show();
            }
        });
        dialog.show();

    }
}
