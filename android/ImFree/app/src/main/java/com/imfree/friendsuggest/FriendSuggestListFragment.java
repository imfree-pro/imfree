package com.imfree.friendsuggest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.external.FloatingActionButton.FloatingActionButton;
import com.imfree.imfree.R;

/**
 * Created by 종열 on 2015-06-06.
 */
public class FriendSuggestListFragment extends Fragment {

    private static final String KEY_CATEGORYSN = "categorySN";
    private static final String KEY_TITLE = "title";

    private int mScrollOffset = 4;
    private int mMaxProgress = 100;
    private Handler mUiHandler = new Handler();


    private RecyclerView _recyclerView;
    private RecyclerView.LayoutManager _layoutManager;
    private String _categorySN;
    private View _fragmentView;
    private Context _context;
    private FriendSuggestAdapter _friendSuggestAdapter;

    private FloatingActionButton _actionButton;

    public static FriendSuggestListFragment newInstance(String categoryNo, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CATEGORYSN, categoryNo);
        bundle.putString(KEY_TITLE, title);

        FriendSuggestListFragment fragment = new FriendSuggestListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _fragmentView = inflater.inflate(R.layout.fragment_friend_suggest_list, container, false);
        _context = getActivity();
        _categorySN = getArguments().getString(KEY_CATEGORYSN);

        _actionButton = (FloatingActionButton)getActivity().findViewById(R.id.fab_actionButton);
        _actionButton.show(true);

        _recyclerView = (RecyclerView)_fragmentView.findViewById(R.id.recycler_view);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new GridLayoutManager(_context, 2);
        _recyclerView.setLayoutManager(_layoutManager);

        _friendSuggestAdapter = new FriendSuggestAdapter(_context, _categorySN);
        _recyclerView.setAdapter(_friendSuggestAdapter);

        _recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > mScrollOffset) {
                    if (dy > 0) {
                        _actionButton.hide(true);
                    } else {
                        _actionButton.show(true);
                    }
                }
            }
        });

        return _fragmentView;
    }
}
