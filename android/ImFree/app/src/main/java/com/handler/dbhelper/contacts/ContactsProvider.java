package com.handler.dbhelper.contacts;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;

import com.handler._entity.ContactsEntity;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-10.
 */
public class ContactsProvider {
    private Context _context;
    public ContactsProvider(Context context)
    {
        _context = context;
    }

    public ArrayList<ContactsEntity> getPhoneContacts() {
        ArrayList<ContactsEntity> contactsEntitie = new ArrayList<ContactsEntity>();
        String [] project = new String[] {ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.Data.PHOTO_ID, ContactsContract.Data.PHOTO_THUMBNAIL_URI};
        Cursor clsCursor = _context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, project, ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1", null, ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
        String [] arrPhoneProjection = { ContactsContract.CommonDataKinds.Phone.NUMBER };
        if(clsCursor.moveToFirst()) {
            do {
                int contactId = clsCursor.getInt(clsCursor.getColumnIndex(ContactsContract.Data._ID));
                Cursor clsPhoneCursor = _context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, arrPhoneProjection, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + String.valueOf(contactId), null, null);
                String displayName;
                String photoId = "";
                String thumbUri;

                String mobilePhone = "";
                int thumbColumn;
                if(clsPhoneCursor.moveToFirst()) {
                    do {
                        mobilePhone = clsPhoneCursor.getString(clsPhoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if ( mobilePhone.length() > 5 && mobilePhone.substring(0,3).equals("010")) {
                            displayName = clsCursor.getString(clsCursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                            photoId = clsCursor.getString(clsCursor.getColumnIndex(ContactsContract.Data.PHOTO_ID));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                thumbColumn = clsCursor.getColumnIndex(ContactsContract.Data.PHOTO_THUMBNAIL_URI);
                            } else {
                                thumbColumn = contactId;
                            }
                            thumbUri = clsCursor.getString(thumbColumn);
                            mobilePhone = PhoneNumberUtils.formatNumber(mobilePhone);
                            contactsEntitie.add(new ContactsEntity(contactId, displayName, mobilePhone, photoId, thumbUri));
                            break;
                        }
                    }while(clsPhoneCursor.moveToNext());
                }
                clsPhoneCursor.close();
            }while(clsCursor.moveToNext());
        }
        clsCursor.close( );
        return contactsEntitie;
    }
}
