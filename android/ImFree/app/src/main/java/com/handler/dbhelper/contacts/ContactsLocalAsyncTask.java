package com.handler.dbhelper.contacts;

import android.content.Context;
import android.os.AsyncTask;

import com.code.CodeFavoriteFriend;
import com.handler._entity.ContactsEntity;
import com.libs.encrypt.Encrypt;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by 종열 on 2015-07-04.
 */
public class ContactsLocalAsyncTask extends AsyncTask<String, String , String> {
    private Context _context;
    private OnContactsLocalAsyncTaskCompleted _onCompleted;

    public ContactsLocalAsyncTask(Context context, OnContactsLocalAsyncTaskCompleted onContactsLocalAsyncTaskCompleted) {
        _onCompleted = onContactsLocalAsyncTaskCompleted;
        _context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        ContactsDBHelper contactsHelper = ContactsDBHelper.getInstance(_context);
        ArrayList<ContactsEntity> dbContactsList = contactsHelper.getAllContacts();
        if ( dbContactsList.size() == 0 ) {
            Encrypt encrypt = new Encrypt();
            ArrayList<ContactsEntity> contactsList = new ContactsProvider(_context).getPhoneContacts();
            for (ContactsEntity item : contactsList) {
                String hashPhone = null;
                hashPhone = encrypt.SHA256(item.getPhoneNo());
                item.setHashPhone(hashPhone);
                item.setFavorite(CodeFavoriteFriend.FRIEND);
                dbContactsList.add(item);
            }
            contactsHelper.addContacts(dbContactsList);
        }

        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        if ( _onCompleted != null )
        {
            _onCompleted.onContactsLocalAsyncTaskCompleted();
        }
    }
}
