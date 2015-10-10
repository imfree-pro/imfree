package com.handler.httpcaller.contacts;

import android.content.Context;
import android.os.AsyncTask;

import com.code.CodeFavoriteFriend;
import com.code.ErrorCode;
import com.exception.http.HttpException;
import com.exception.http.HttpResultException;
import com.handler._entity.ContactsEntity;
import com.handler.dbhelper.contacts.ContactsDBHelper;
import com.handler.dbhelper.contacts.ContactsProvider;
import com.libs.encrypt.Encrypt;

import org.json.JSONException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by 종열 on 2015-06-16.
 */
public class ContactsHttpAsyncTask extends AsyncTask<String, String , HttpResponseDataSync> {
    private Context _context;
    private OnContactsAsyncTaskCompleted _onCompleted;

    public ContactsHttpAsyncTask(Context context, OnContactsAsyncTaskCompleted onContactsAsyncTaskCompleted) {
        _onCompleted = onContactsAsyncTaskCompleted;
        _context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HttpResponseDataSync doInBackground(String... params) {
        ContactsDBHelper contactsHelper = ContactsDBHelper.getInstance(_context);

        ArrayList<ContactsEntity> contactsList = new ContactsProvider(_context).getPhoneContacts();
        ArrayList<ContactsEntity> dbContactsList = contactsHelper.getAllContacts();
        Encrypt encrypt = new Encrypt();

        if ( dbContactsList.size() == 0 ) {
            for (ContactsEntity item : contactsList) {
                String hashPhone = null;
                hashPhone = encrypt.SHA256(item.getPhoneNo());
                item.setHashPhone(hashPhone);
                item.setFavorite(CodeFavoriteFriend.FRIEND);
                dbContactsList.add(item);
            }
            contactsHelper.addContacts(dbContactsList);
        } else {
            ArrayList<ContactsEntity> addContacts = new ArrayList<ContactsEntity>();
            ArrayList<ContactsEntity> removeContacts = new ArrayList<ContactsEntity>();
            HashSet<String> setTest = new HashSet<String>();


            for (ContactsEntity contactsEntity : contactsList) {
                boolean isAdd = true;
                setTest.add(contactsEntity.getPhoneNo());
                for (ContactsEntity dbContact : dbContactsList) {
                    if (dbContact.getPhoneNo().equals(contactsEntity.getPhoneNo())) {
                        isAdd = false;
                        break;
                    }
                }
                if (isAdd == true) {
                    String hashPhone = null;
                    hashPhone = encrypt.SHA256(contactsEntity.getPhoneNo());

                    if (hashPhone != null && hashPhone.equals("") == false) {
                        contactsEntity.setHashPhone(hashPhone);
                        contactsEntity.setFavorite(CodeFavoriteFriend.FRIEND);
                        addContacts.add(contactsEntity);
                    }
                }
            }

            if (addContacts.size() > 0) {
                contactsHelper.addContacts(addContacts);
                dbContactsList.addAll(addContacts);
            }

            for (ContactsEntity dbContact : dbContactsList) {
                if (setTest.contains(dbContact.getPhoneNo()) == false) {
                    setTest.remove(dbContact.getPhoneNo());
                    removeContacts.add(dbContact);
                }
            }
            if (removeContacts.size() > 0) {
                contactsHelper.deleteContacts(removeContacts);
            }
        }

        HttpResponseDataSync result = new HttpResponseDataSync();
        try {
            result = new ContactsHttpCaller(_context).datasync(dbContactsList);
        } catch (HttpResultException e) {
            result.setError(ErrorCode.RESPONSE_ERROR_JSON);
            result.setMessage(this.getClass().getName());
            e.printStackTrace();
        } catch (HttpException e) {
            result.setError(ErrorCode.RESPONSE_ERROR_HTTPSTATUS);
            result.setMessage(this.getClass().getName());
            e.printStackTrace();
        } catch (JSONException e) {
            result.setError(ErrorCode.RESPONSE_ERROR_JSON);
            result.setMessage(this.getClass().getName());
            e.printStackTrace();
        }
        return result;

    }

    @Override
    protected void onProgressUpdate(String... progress) {
    }

    @Override
    protected void onPostExecute(HttpResponseDataSync result) {
        if ( _onCompleted != null )
        {
            _onCompleted.onContactsAsyncTaskCompleted(result);
        }
    }
}
