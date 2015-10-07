package com.imfree.invitefriend;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.androidquery.AQuery;
import com.handler.dbhelper.contacts.ContactsProvider;
import com.libs.image.ImageLib;
import com.imfree.imfree.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by 종열 on 2015-05-31.
 */
public class InviteFriendAdapter extends ArrayAdapter<PhoneUserEntity> {
    private SparseArray<WeakReference<View>> _viewArray;
    private ArrayList<PhoneUserEntity> _phoneUserList;
    private LayoutInflater _inflater;
    private Context _context;
    private int _selectCount;


    public InviteFriendAdapter(Context context, int textViewResourceId, ArrayList<PhoneUserEntity> phoneUserList) {
        super(context, textViewResourceId, phoneUserList);
        _phoneUserList = phoneUserList;
        _context = context;
        _inflater = LayoutInflater.from(context);
        _viewArray = new SparseArray<WeakReference<View>>(_phoneUserList.size());

        _selectCount = 0;
    }

    public int getSelectCount()
    {
        return _selectCount;
    }

    public void selection(int position)
    {
        _selectCount += ( _phoneUserList.get(position).isSelected() == true ? -1 : 1 );
        _phoneUserList.get(position).setSelect(!_phoneUserList.get(position).isSelected());
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (_viewArray != null && _viewArray.get(position) != null) {
            convertView = _viewArray.get(position).get();
            if (convertView != null) {
                AQuery aq = new AQuery(convertView);
                if ( _phoneUserList.get(position).isSelected())
                    aq.backgroundColorId(R.color.ListItemSelection);
                else
                    aq.backgroundColorId(R.color.ListItemUnSelection);
                return convertView;
            }
        }

        try {
            convertView = _inflater.inflate(R.layout.in_invite_friend_item, null);
            AQuery aq = new AQuery(convertView);

            ContactsProvider provider = new ContactsProvider(_context);
            ImageLib imageLib = new ImageLib(_context);
            Bitmap bitmap = imageLib.getContactPhotoThumbnail(_phoneUserList.get(position).getThumbUrl());
            if ( bitmap != null )
                aq.id(R.id.phoneImage).image(bitmap);
            PhoneUserEntity model = getItem(position);
            aq.id(R.id.phoneName).text(model.getDisplayName());
            aq.id(R.id.phoneNo).text(model.getPhoneNo());
        } finally {
            _viewArray.put(position, new WeakReference<View>(convertView));
        }
        if ( _phoneUserList.get(position).isSelected())
            convertView.setBackgroundResource(R.color.ListItemSelection);
        else
            convertView.setBackgroundResource(R.color.ListItemUnSelection);

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