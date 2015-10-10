package com.handler._entity;

/**
 * Created by 종열 on 2015-06-01.
 */
public class InUseFriendEntity {
    private String _userSN;
    private String _displayName;
    private String _photoId;
    private String _thumbUri;
    private String _count;

    private String _hashPhone;
    public InUseFriendEntity(String userSN, String displayName, String photoId, String thumbUri, String hashPhone) {
        _displayName = displayName;
        _photoId = photoId;
        _thumbUri = thumbUri;
        _hashPhone = hashPhone;

        _userSN = userSN;
    }

    public InUseFriendEntity(String userSN, ContactsEntity entity)
    {
        _userSN = userSN;

        _displayName = entity.getDisplayName();
        _photoId = entity.getPhotoId();
        _thumbUri = entity.getThumbUrl();
        _hashPhone = entity.getHashPhone();
    }

    public String getDisplayName() { return _displayName;}

    public String getPhotoId() { return _photoId; }

    public String getThumbUrl() { return _thumbUri; }

    public String getHashPhone() { return _hashPhone;}

    public String getUserSN() { return _userSN; }

    public void setCount(String count) { _count = count;}

    public String getCount() { return _count; }
}
