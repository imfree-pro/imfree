package com.external.NavigationDrawer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imfree.imfree.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 종열 on 2015-05-31.
 */
public class NavigationDrawerFragment extends Fragment implements NavigationDrawerCallback {
    private NavigationDrawerCallback _callbacks;
    private RecyclerView _drawerList;
    private View _fragmentContainerView;
    private DrawerLayout _drawerLayout;
    private ActionBarDrawerToggle _actionBarDrawerToggle;
    private int _currentSelectedPosition;
    private List<NavigationDrawerItem> _navigationDrawerItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigationdrawer, container, false);
        _drawerList = (RecyclerView) view.findViewById(R.id.navigationDrawerRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _drawerList.setLayoutManager(layoutManager);
        _drawerList.setHasFixedSize(true);
        _navigationDrawerItem = getMenu();
        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(_navigationDrawerItem);
        adapter.setNavigationDrawerCallbacks(this);
        _drawerList.setAdapter(adapter);
      //selectItem(_currentSelectedPosition);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            _callbacks = (NavigationDrawerCallback) activity;
        } catch (ClassCastException e) {

        }
    }
    public void setup(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar, ActionBar actionBar, int currentSelectedPositionposition) {
        _fragmentContainerView = getActivity().findViewById(fragmentId);
        _currentSelectedPosition = currentSelectedPositionposition;
        _drawerLayout = drawerLayout;
        actionBar.setTitle(_navigationDrawerItem.get(_currentSelectedPosition-1).getText());
        ((NavigationDrawerAdapter) _drawerList.getAdapter()).selectPosition(_currentSelectedPosition);
        _actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), _drawerLayout, toolbar, R.string.naviationDrawer_open, R.string.naviationDrawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) return;
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) return;
                getActivity().invalidateOptionsMenu();
            }
        };

        _drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                _actionBarDrawerToggle.syncState();
            }
        });
        _drawerLayout.setDrawerListener(_actionBarDrawerToggle);
    }

    public void openDrawer() {
        _drawerLayout.openDrawer(_fragmentContainerView);
    }

    public void closeDrawer() {
        _drawerLayout.closeDrawer(_fragmentContainerView);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _callbacks = null;
    }

    public List<NavigationDrawerItem> getMenu() {
        ArrayList<NavigationDrawerItem> items = new ArrayList<NavigationDrawerItem>();
        String[] _leftTopMenu = getResources().getStringArray(R.array.navigationdrawer_items);
        for(int i = 0; i < _leftTopMenu.length; i ++) {
            items.add(new NavigationDrawerItem(_leftTopMenu[i], getResources().getDrawable(R.drawable.abc_btn_radio_material)));
        }
        return items;
    }

    void selectItem(int position) {
        _currentSelectedPosition = position;
        if (_drawerLayout != null) {
            _drawerLayout.closeDrawer(_fragmentContainerView);
        }
        if (_callbacks != null) {
            _callbacks.onNavigationDrawerSelected(position);
        }
        ((NavigationDrawerAdapter) _drawerList.getAdapter()).selectPosition(position);
    }

    public boolean isDrawerOpen() {
        return _drawerLayout != null && _drawerLayout.isDrawerOpen(_fragmentContainerView);
    }


    @Override
    public void onNavigationDrawerSelected(int position) {
        selectItem(position);
    }

    public DrawerLayout getDrawerLayout() {
        return _drawerLayout;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        _drawerLayout = drawerLayout;
    }
}