package com.handler;

import android.content.Context;
import android.content.SharedPreferences;

import com.code.CacheName;
import com.handler._entity.CategoryEntity;
import com.handler._entity.CategoryItemEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 종열 on 2015-06-28.
 */
public class CategoryHandler {
    private Context _context;
    public CategoryHandler(Context context) {
        _context = context;
    }

    private final static String JSONKEY_DATA = "data";
    private final static String JSONKEY_CATEGORY = "categorys";
    private final static String JSONKEY_CATEGORYIEM = "items";
    private final static String JSONKEY_CATEGORY_SN = "categorysn";
    private final static String JSONKEY_CATEGORY_NAME = "categoryname";
    private final static String JSONKEY_CATEGORY_ICON = "iconfilename";

    private final static String JSONKEY_CATEGORYITEN_SN = "itemsn";
    private final static String JSONKEY_CATEGORYITEN_NAME = "itemname";

    public void init(JSONObject jsonResponse)
    {
        try {
            JSONObject jsonData = jsonResponse.getJSONObject(JSONKEY_DATA);
            JSONArray jsonCategoryArray = jsonData.getJSONArray(JSONKEY_CATEGORY);
            JSONArray jsonCategoryItemsArray = jsonData.getJSONArray(JSONKEY_CATEGORYIEM);
            SharedPreferences sharedPreferences = _context.getSharedPreferences(CacheName.FILENAME_CATEGORY, _context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(CacheName.KEY_CATEGORY, jsonCategoryArray.toString());
            editor.putString(CacheName.KEY_CATEGORY_ITEM, jsonCategoryItemsArray.toString());
            editor.commit();
            getCategoryList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void clear()
    {
        SharedPreferences sharedPreferences = _context.getSharedPreferences(CacheName.FILENAME_CATEGORY, _context.MODE_PRIVATE);
        sharedPreferences.edit().clear();
        sharedPreferences.edit().commit();
    }

    public ArrayList<CategoryEntity> getCategoryList()
    {
        ArrayList<CategoryEntity> result = new ArrayList<CategoryEntity>();
        SharedPreferences sharedPreferences = _context.getSharedPreferences(CacheName.FILENAME_CATEGORY, _context.MODE_PRIVATE);
        try {
            String categoryList = sharedPreferences.getString(CacheName.KEY_CATEGORY, "[]");
            if ( categoryList == null || categoryList.equals("") || categoryList.equals("[]"))
            {
                categoryList = sharedPreferences.getString(CacheName.KEY_CATEGORY, "[]");
            }
            JSONArray jsonCategoryArray = new JSONArray(categoryList);
            for (int i = 0; i < jsonCategoryArray.length(); i ++ )
            {
                result.add(
                        getJsonToCategoryEntity(jsonCategoryArray.getJSONObject(i))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<CategoryItemEntity> getCategoryItemList()
    {
        ArrayList<CategoryItemEntity> result = new ArrayList<CategoryItemEntity>();
        SharedPreferences sharedPreferences = _context.getSharedPreferences(CacheName.FILENAME_CATEGORY, _context.MODE_WORLD_READABLE);
        try {
            String categoryItemList = sharedPreferences.getString(CacheName.KEY_CATEGORY_ITEM, "[]");
            if ( categoryItemList == null || categoryItemList.equals("") || categoryItemList.equals("[]"))
            {
                categoryItemList = sharedPreferences.getString(CacheName.KEY_CATEGORY_ITEM, "[]");
            }
            JSONArray jsonCategoryItemsArray = new JSONArray(categoryItemList);
            for (int i = 0; i < jsonCategoryItemsArray.length(); i ++ )
            {
                result.add(
                        getJsonToCategoryItemEntity(jsonCategoryItemsArray.getJSONObject(i))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public HashMap<String, ArrayList<CategoryItemEntity>> categoryItemGroupList()
    {
        HashMap<String, ArrayList<CategoryItemEntity>> result = new HashMap<String, ArrayList<CategoryItemEntity>>();
        ArrayList<CategoryItemEntity> categoryItemEntities = getCategoryItemList();

        for(CategoryItemEntity item : categoryItemEntities)
        {
            if ( result.containsKey(item.getCategorySN()) == false)
                result.put(item.getCategorySN(), new ArrayList<CategoryItemEntity>());
            result.get(item.getCategorySN()).add(item);
        }

        return result;
    }

    private CategoryItemEntity getJsonToCategoryItemEntity(JSONObject jsonCategoryItem)
    {
        CategoryItemEntity categoryEntity = new CategoryItemEntity();
        try {
            categoryEntity.setCategorySN(String.valueOf(jsonCategoryItem.getInt(JSONKEY_CATEGORY_SN)));
            categoryEntity.setItemSN(String.valueOf(jsonCategoryItem.getInt(JSONKEY_CATEGORYITEN_SN)));
            categoryEntity.setItemName(jsonCategoryItem.getString(JSONKEY_CATEGORYITEN_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categoryEntity;
    }

    private CategoryEntity getJsonToCategoryEntity(JSONObject jsonCategory)
    {
        CategoryEntity categoryEntity = new CategoryEntity();
        try {
            categoryEntity.setCategorySN(String.valueOf(jsonCategory.getInt(JSONKEY_CATEGORY_SN)));
            categoryEntity.setCategoryName(jsonCategory.getString(JSONKEY_CATEGORY_NAME));
            categoryEntity.setIconFileName(jsonCategory.getString(JSONKEY_CATEGORY_ICON));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categoryEntity;
    }
}
