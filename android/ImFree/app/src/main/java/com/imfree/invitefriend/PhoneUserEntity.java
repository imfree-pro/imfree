package com.imfree.invitefriend;

import com.handler._entity.ContactsEntity;

/**
 * Created by 종열 on 2015-05-31.
 */
public class PhoneUserEntity extends ContactsEntity {
    private boolean _isSelected = false;

    public PhoneUserEntity(long contactId, String displayName, String phoneNo, String photoId, String thumbUri, String hashPhone) {
        super(contactId, displayName, phoneNo, photoId, thumbUri, hashPhone, "");
    }

    public PhoneUserEntity(ContactsEntity entity)
    {
        super(entity.getContactId(), entity.getDisplayName(), entity.getPhoneNo(), entity.getPhotoId(), entity.getThumbUrl(), entity.getHashPhone(), entity.getFavorite());
    }

    public boolean isSelected() { return _isSelected; }

    public void setSelect(boolean selection) {
        _isSelected = selection;
    }
}
