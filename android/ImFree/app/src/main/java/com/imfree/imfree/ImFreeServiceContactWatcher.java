package com.imfree.imfree;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.handler._entity.ContactsEntity;
import com.handler.dbhelper.contacts.ContactsProvider;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-07-12.
 */
public class ImFreeServiceContactWatcher extends ContentObserver {
    private static final String TAG = "ImFreeServiceContactWatcher";
    private Context _context;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public ImFreeServiceContactWatcher(Handler handler, Context context) {
        super(handler);

        _context = context;
    }

    /**
     * This method handles the content observer change notifications.
     */
    @Override
    public void onChange(boolean selfChange)
    {
        super.onChange(selfChange);
        queryContacts();
    }

    /**
     * This method looks for new contacts in the contacts content provider and compares
     * them to what exists already in the DroidWatch contacts table.
     */
    private void queryContacts()
    {
        ContactsProvider provider = new ContactsProvider(_context);
        ArrayList<ContactsEntity> contactsEntities = provider.getPhoneContacts();
    }
}
