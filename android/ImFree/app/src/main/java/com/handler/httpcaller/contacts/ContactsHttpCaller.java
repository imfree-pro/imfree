package com.handler.httpcaller.contacts;

import android.content.Context;

import com.code.HttpURL;
import com.exception.http.HttpException;
import com.exception.http.HttpResultException;
import com.handler._entity.ContactsEntity;
import com.handler.httpcaller.BaseHttpCaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-07-04.
 */
public class ContactsHttpCaller  extends BaseHttpCaller {
    private Context _context;
    public ContactsHttpCaller(Context context) {
        super(context);
        _context = context;
    }

    private final static String RESPONSE_JSONKEY_CONTACTS_INSTALLUSRS = "installusers";
    private final static String RESPONSE_JSONKEY_RESPONSE_INSTALLUSER = "hashphone";

    public HttpResponseDataSync datasync(ArrayList<ContactsEntity> contactsEntities) throws HttpResultException, HttpException, JSONException {
        HttpResponseDataSync result = new HttpResponseDataSync();
        JSONObject data = new JSONObject();
        JSONArray contacts = new JSONArray();
        try {
            for(ContactsEntity contact : contactsEntities)
            {
                contacts.put(contact.getHashPhone());
            }
            data.put("contacts", contacts);
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }

        JSONObject jsonRequest = getRequest(data);
        JSONObject jsonResponse = POST(HttpURL.getFullUrl(HttpURL.HTTP_ACTION_CONTACTS_SYNC), jsonRequest);

        if ( jsonResponse.has(RESPONSE_JSONKEY_DATA) ){
            try {
                JSONObject responseData = jsonResponse.getJSONObject(RESPONSE_JSONKEY_DATA);
                if ( responseData.has(RESPONSE_JSONKEY_CONTACTS_INSTALLUSRS)) {
                    JSONArray installUserArray = responseData.getJSONArray(RESPONSE_JSONKEY_CONTACTS_INSTALLUSRS);
                    ArrayList<String> installUserList = new ArrayList<String>();
                    for (int i = 0; i < installUserArray.length(); i ++)
                    {
                        installUserList.add(installUserArray.getString(i));
                    }
                    result.setInstallUser(installUserList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return result;
    }
}
