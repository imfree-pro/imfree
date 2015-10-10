package com.handler._entity;

/**
 * Created by 종열 on 2015-06-09.
 */
public class ContactsEntity {
    private String _hashPhone;

    private long _contactId;
    private String _phoneNo;
    private String _displayName;
    private String _photoId;
    private String _thumbUri;
    private String _favorite;

    public ContactsEntity(long contactId, String displayName, String phoneNo, String photoId, String thumbUri, String hashPhone, String favorite)
    {
        _contactId = contactId;
        _displayName = displayName;
        _phoneNo = phoneNo;
        _photoId = photoId;
        _thumbUri = thumbUri;

        _hashPhone = hashPhone;
        _favorite = favorite;
    }

    public ContactsEntity(long contactId, String displayName, String phoneNo, String photoId, String thumbUri)
    {
        _contactId = contactId;
        _displayName = displayName;
        _phoneNo = phoneNo;
        _photoId = photoId;
        _thumbUri = thumbUri;

        _hashPhone = "";
    }

    public long getContactId() { return _contactId; }

    public String getPhoneNo()
    {
        return _phoneNo;
    }

    public String getDisplayName()
    {
        return _displayName;
    }

    public String getPhotoId() { return _photoId; }

    public String getThumbUrl() { return _thumbUri; }

    public void setHashPhone(String hashPhone) { _hashPhone = hashPhone; }
    public String getHashPhone() { return _hashPhone; }
    public String getFavorite() { return _favorite; }
    public void setFavorite(String favorite) { _favorite = favorite; }
}
