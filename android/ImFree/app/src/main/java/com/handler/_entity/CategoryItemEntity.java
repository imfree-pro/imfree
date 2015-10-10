package com.handler._entity;

/**
 * Created by 종열 on 2015-06-06.
 */
public class CategoryItemEntity {
    private String _categorySN;
    private String _itemSN;
    private String _itemName;
    private String _cardFileName;

    public CategoryItemEntity() { }

    public CategoryItemEntity(String categorySN, String itemSN, String itemName)
    {
        _categorySN = categorySN;
        _itemSN = itemSN;
        _itemName = itemName;
    }

    public String getCategorySN() { return _categorySN;}
    public void setCategorySN(String categorySN){ _categorySN = categorySN; }

    public String getItemSN() { return _itemSN; }
    public void setItemSN(String itemSN) { _itemSN = itemSN; }

    public String getItemName() { return _itemName; }
    public void setItemName(String itemName)
    {
        _itemName = itemName;
    }

    public String getCardFileName() { return _cardFileName; }
    public void setCardFileName(String cardFileName)
    {
        _cardFileName = cardFileName;
    }

}
