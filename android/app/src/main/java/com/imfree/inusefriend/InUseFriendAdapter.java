package com.imfree.inusefriend;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.libs.image.ImageLib;
import com.imfree.imfree.R;
import com.server.inusefriend.DBInUseFriendHelper;
import com.handler._entity.InUseFriendEntity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-01.
 */
public class InUseFriendAdapter extends ArrayAdapter<InUseFriendEntity> {
    private SparseArray<WeakReference<View>> _viewArray;
    private ArrayList<InUseFriendEntity> _inUseFriendEntity;
    private LayoutInflater _inflater;
    private Context _context;

    public InUseFriendAdapter(Context context, int textViewResourceId, ArrayList<InUseFriendEntity> inUseFriendEntity) {
        super(context, textViewResourceId, inUseFriendEntity);
        _inUseFriendEntity = inUseFriendEntity;
        _context = context;
        _inflater = LayoutInflater.from(context);
        _viewArray = new SparseArray<WeakReference<View>>(_inUseFriendEntity.size());
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (_viewArray != null && _viewArray.get(position) != null) {
            convertView = _viewArray.get(position).get();
            if (convertView != null)
                return convertView;
        }

        try {
            convertView = _inflater.inflate(R.layout.in_use_friend_item, null);

            AQuery aq = new AQuery(convertView);

            final InUseFriendEntity model = getItem(position);

            TextView inUseCountView = (TextView) convertView.findViewById(R.id.inUseCount);

            ImageLib imageLib = new ImageLib(_context);
            Bitmap bitmap = imageLib.getContactPhotoThumbnail(model.getThumbUrl());
            if ( bitmap != null )
                aq.id(R.id.phoneImage).image(bitmap);
            aq.id(R.id.phoneName).text(model.getDisplayName());
            DBInUseFriendHelper helper = DBInUseFriendHelper.getInstance(_context);
            if ( helper == null ) {
                aq.id(R.id.inUseCount).text("0");
            }
            else {
                if ( helper.getAllCount() == null )
                    aq.id(R.id.inUseCount).text("0");
                else if (helper.getAllCount().containsKey(model.getUserSN()))
                    aq.id(R.id.inUseCount).text(helper.getAllCount().get(model.getUserSN()));
                else
                    aq.id(R.id.inUseCount).text("0");
            }

        } finally {
            _viewArray.put(position, new WeakReference<View>(convertView));
        }
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void update() {
        _viewArray.clear();
        notifyDataSetChanged();
    }
}