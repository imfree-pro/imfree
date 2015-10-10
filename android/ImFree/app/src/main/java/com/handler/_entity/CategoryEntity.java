package com.handler._entity;

/**
 * Created by 종열 on 2015-06-06.
 */
public class CategoryEntity {
    private String _categorySN;
    private String _categoryName;
    private String _iconFileName;

    public CategoryEntity(){ }

    public CategoryEntity(String categorySN, String categoryName)
    {
        _categorySN = categorySN;
        _categoryName = categoryName;
    }

    public String getCategorySN(){ return _categorySN; }
    public void setCategorySN(String categorySN){ _categorySN = categorySN; }

    public String getCategoryName() { return _categoryName; }
    public void setCategoryName(String categoryName) { _categoryName = categoryName; }

    public String getIconFileName() { return _iconFileName; }
    public void setIconFileName(String iconFileName) { _iconFileName = iconFileName; }
}
