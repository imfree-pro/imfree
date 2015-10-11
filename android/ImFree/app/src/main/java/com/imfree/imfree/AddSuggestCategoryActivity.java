package com.imfree.imfree;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.handler.CategoryHandler;
import com.imfree.addsuggest.category.AddCategoryAdapter;
import com.handler._entity.CategoryEntity;
import com.handler._entity.CategoryItemEntity;

import java.util.ArrayList;
import java.util.HashMap;


public class AddSuggestCategoryActivity extends ActionBarActivity {

    private Toolbar _toolbar;
    private ActionBar _actionbar;
    private ExpandableListView _expandableList;

    private ArrayList<CategoryEntity> _categoryList = new ArrayList<CategoryEntity>();
    private HashMap<String, ArrayList<CategoryItemEntity>> _categoryItemList = new HashMap<String, ArrayList<CategoryItemEntity>>();
    private AddCategoryAdapter _addCategoryAdapter;
    private int lastClickedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_suggest_category);

        _toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);
        _actionbar = getSupportActionBar();

        _expandableList = (ExpandableListView) findViewById(R.id.in_suggest_category);

        CategoryHandler categoryHandler = new CategoryHandler(getApplication());
        _categoryList = categoryHandler.getCategoryList();
        _categoryItemList =  categoryHandler.categoryItemGroupList();

        _addCategoryAdapter = new AddCategoryAdapter(this, _categoryList, _categoryItemList);
        _expandableList.setAdapter(_addCategoryAdapter);

        _expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
            @Override
            public boolean onGroupClick(ExpandableListView listView, View view, int position, long arg3) {
                Boolean isExpand = (!_expandableList.isGroupExpanded(position));
                if ( lastClickedPosition != -1 )
                    _expandableList.collapseGroup(lastClickedPosition);
                if(isExpand)
                    _expandableList.expandGroup(position);
                lastClickedPosition = position;

                String categorySN = _categoryList.get(position).getCategorySN();

                if ( _categoryItemList.containsKey(categorySN) )
                {
                    Toast.makeText(AddSuggestCategoryActivity.this, (String) _addCategoryAdapter.getGroup(position).getCategoryName() + ( isExpand ? " 열기" : " 닫기" ), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AddSuggestCategoryActivity.this, (String) _addCategoryAdapter.getGroup(position).getCategoryName() + " 다음으로 이동", Toast.LENGTH_SHORT).show();
                }


                return true;
            }
        });

        _expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(AddSuggestCategoryActivity.this, (String) _addCategoryAdapter.getChild(groupPosition, childPosition).getItemName() + " 다음으로 이동", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_suggest_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_close) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_stable, R.anim.anim_slide_out_right);
    }
}
