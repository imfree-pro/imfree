package com.server.suggest;

/**
 * Created by 종열 on 2015-06-07.
 */
public class SuggestEntity {
    private String _suggestSN;
    private String _userSN;
    private String _categorySN;
    private String _itemSN;
    private String _acceptedUserCount;
    private String _displayName;

    public SuggestEntity(String suggestSN, String userSN, String categorySN, String itemSN, String acceptedUserCount, String displayName)
    {
        _suggestSN = suggestSN;
        _userSN = userSN;
        _categorySN = categorySN;
        _itemSN = itemSN;
        _acceptedUserCount = acceptedUserCount;
        _displayName = displayName;
    }

    public SuggestEntity(String suggestSN, String categorySN, String itemSN, String acceptedUserCount)
    {
        _suggestSN = suggestSN;
        _categorySN = categorySN;
        _itemSN = itemSN;
        _acceptedUserCount = acceptedUserCount;
    }

    public String getSuggestSN() { return _suggestSN;}

    public String getUserSN() {return _userSN;}

    public String getCategorySN() { return _categorySN; }

    public String getItemSN() { return _itemSN; }

    public String getAcceptedUserCount() { return _acceptedUserCount; }

    public String getDisplayName() { return _displayName; }
    public void setDisplayName(String displayName) { _displayName = displayName; }
}
