package com.imfree.friendsuggest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imfree.imfree.R;
import com.server.suggest.SuggestEntity;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-16.
 */
public class FriendSuggestAdapter  extends RecyclerView.Adapter<FriendSuggestAdapter.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_PROGRESSBAR = 0;

    private ArrayList<SuggestEntity> _suggestEntityList;
    private String _categorySN;
    private Context _context;
    public FriendSuggestAdapter(Context context, String categorySN) {
        super();
        _context = context;
        _categorySN = categorySN;
        ArrayList<SuggestEntity> suggestEntityList = new ArrayList<SuggestEntity>();
        _suggestEntityList = new ArrayList<SuggestEntity>();

        suggestEntityList.add(new SuggestEntity("1", "0", "1", "1", "10", "_test"));
        suggestEntityList.add(new SuggestEntity("2", "1", "1", "1", "11", "_test"));
        suggestEntityList.add(new SuggestEntity("3", "2", "2", "1", "12", "_test"));
        suggestEntityList.add(new SuggestEntity("4", "3", "2", "1", "13", "_test"));
        suggestEntityList.add(new SuggestEntity("1", "1", "3", "1", "14", "_test"));
        suggestEntityList.add(new SuggestEntity("2", "2", "1", "1", "15", "_test"));
        suggestEntityList.add(new SuggestEntity("3", "3", "4", "1", "16", "_test"));
        suggestEntityList.add(new SuggestEntity("1", "0", "4", "1", "17", "_test"));
        suggestEntityList.add(new SuggestEntity("5", "1", "4", "1", "18", "_test"));
        suggestEntityList.add(new SuggestEntity("4", "2", "4", "1", "19", "_test"));
        suggestEntityList.add(new SuggestEntity("4", "3", "4", "1", "20", "_test"));
        suggestEntityList.add(new SuggestEntity("4", "3", "0", "1", "21", "_test"));
        suggestEntityList.add(new SuggestEntity("4", "3", "0", "1", "22", "_test"));
        suggestEntityList.add(new SuggestEntity("4", "3", "0", "1", "23", "_test"));
        suggestEntityList.add(new SuggestEntity("4", "3", "1", "1", "24", "_test"));
        suggestEntityList.add(new SuggestEntity("4", "2", "2", "1", "25", "_test"));

        for(SuggestEntity item : suggestEntityList)
        {
            if ( item.getCategorySN().equals(_categorySN) || _categorySN.equals("0"))
                _suggestEntityList.add(item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.in_friend_suggest_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        SuggestEntity suggest = _suggestEntityList.get(position);
        viewHolder.getUserName().setText(suggest.getDisplayName() + "님");
        viewHolder.getThumbnail().setImageResource(R.drawable.background_poly);
    }

    @Override
    public int getItemCount() {
        return _suggestEntityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView _thumbnail;
        private TextView _displayName;
        private ImageView _up;
        public ViewHolder(View itemView) {
            super(itemView);
            _thumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            _displayName = (TextView)itemView.findViewById(R.id.tv_userName);
            _up = (ImageView)itemView.findViewById(R.id.img_up);
        }

        public void setThumbnail(ImageView thumbnail)
        {
            _thumbnail = thumbnail;
        }

        public ImageView getThumbnail()
        {
            return _thumbnail;
        }

        public void setUserName(TextView textView)
        {
            _displayName = textView;
        }

        public TextView getUserName()
        {
            return _displayName;
        }

        public void setUp(ImageView up)
        {
            _up = up;
        }

        public ImageView getUp()
        {
            return _up;
        }
    }
}
