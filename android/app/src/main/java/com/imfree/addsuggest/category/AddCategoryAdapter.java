package com.imfree.addsuggest.category;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imfree.imfree.R;
import com.handler._entity.CategoryEntity;
import com.handler._entity.CategoryItemEntity;

/**
 * Created by 종열 on 2015-06-07.
 */
public class AddCategoryAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<CategoryEntity> _categoryList; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<CategoryItemEntity>> _categoryItemList;

    public AddCategoryAdapter(Context context, ArrayList<CategoryEntity> categoryList,
                                 HashMap<String, ArrayList<CategoryItemEntity>> categoryItemList) {
        this._context = context;
        this._categoryList = categoryList;
        this._categoryItemList = categoryItemList;
    }

    @Override
    public CategoryItemEntity getChild(int groupPosition, int childPosititon) {
        String categorySN = this._categoryList.get(groupPosition).getCategorySN();

        if ( this._categoryItemList.containsKey(categorySN) == true )
            return this._categoryItemList.get(categorySN).get(childPosititon);
        return new CategoryItemEntity("", "", "");
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition).getItemName();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.in_add_suggest_category_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.in_suggest_category_item);

        txtListChild.setText(childText);
        convertView.setBackgroundResource(R.color.ListItemUnSelection);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String categorySN = this._categoryList.get(groupPosition).getCategorySN();
        if ( this._categoryItemList.containsKey(categorySN))
            return this._categoryItemList.get(categorySN).size();

        return 0;
    }

    @Override
    public CategoryEntity getGroup(int groupPosition) {
        return this._categoryList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._categoryList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).getCategoryName();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.in_add_suggest_category_listgroup, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.in_suggest_category_header_title);
        ImageView indicator = (ImageView)convertView.findViewById(R.id.in_suggest_category_header_indicator);
        indicator.setImageResource(isExpanded ? R.drawable.ic_expand_less_black : R.drawable.ic_expand_more_black);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        convertView.setBackgroundResource(isExpanded ? R.color.ListItemSelection : R.color.ListItemUnSelection);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
