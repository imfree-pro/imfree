package com.handler.httpcaller.contacts;

import android.content.Context;
import android.os.AsyncTask;

import com.code.ErrorCode;
import com.exception.http.HttpException;
import com.exception.http.HttpResultException;
import com.handler._entity.ContactsEntity;
import com.handler.dbhelper.contacts.ContactsDBHelper;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-07-04.
 */
public class ContactsHttpFirstAsyncTask extends AsyncTask<String, String , HttpResponseDataSync> {
    private Context _context;
    private OnContactsFirstAsyncTaskCompleted _onCompleted;

    public ContactsHttpFirstAsyncTask(Context context, OnContactsFirstAsyncTaskCompleted onContactsFirstAsyncTaskCompleted) {
        _onCompleted = onContactsFirstAsyncTaskCompleted;
        _context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HttpResponseDataSync doInBackground(String... params) {
        ContactsDBHelper contactsHelper = ContactsDBHelper.getInstance(_context);
        ArrayList<ContactsEntity> dbContactsList = contactsHelper.getAllContacts();

        HttpResponseDataSync result = new HttpResponseDataSync();
        if ( dbContactsList.size() > 0 )
        {
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
        }
        return result;
    }
}
