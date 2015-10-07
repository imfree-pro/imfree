package com.handler.httpcaller.user;

import android.content.Context;

import com.exception.http.HttpException;
import com.exception.http.HttpResultException;
import com.handler._entity.ContactsEntity;
import com.code.HttpURL;
import com.handler.httpcaller.BaseHttpCaller;
import com.handler.httpcaller.BaseHttpResponse;
import com.handler._entity.UserEntity;
import com.handler.dbhelper.user.UserDBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-28.
 */
public class UserHttpCaller extends BaseHttpCaller {

    private final static String RESPONSE_JSONKEY_GUID = "guid";
    private final static String RESPONSE_JSONKEY_USER = "user";
    private final static String RESPONSE_JSONKEY_USERSN = "usersn";
    private final static String RESPONSE_JSONKEY_CONTACTS = "contacts";
    private final static String RESPONSE_JSONKEY_USER_HASHPHONE = "hashphone";
    private final static String RESPONSE_JSONKEY_USER_DEVICEID = "deviceid";
    private final static String RESPONSE_JSONKEY_USER_PUSHKEY = "pushkey";

    private final static String RESPONSE_JSONKEY_CONTACTS_HASHPHONE = "hashphone";

    private final static String RESPONSE_JSONKEY_RESPONSE_INSTALLUSER = "hashphone";

    private Context _context;
    public UserHttpCaller(Context context) {
        super(context);
        _context = context;
    }

    public HttpResponseUserAuth auth() throws HttpResultException, HttpException, JSONException {
        HttpResponseUserAuth result = new HttpResponseUserAuth();
        try {
            JSONObject data = new JSONObject();
            data.put(RESPONSE_JSONKEY_USER_HASHPHONE, getUser().getHashPhone());
            JSONObject jsonRequest = getRequest(data);
            JSONObject jsonResponse = POST(HttpURL.getFullUrl(HttpURL.HTTP_ACTION_USER_AUTH), jsonRequest);

            result.setError(jsonResponse.getInt(RESPONSE_JSONKEY_ERROR));
            result.setMessage(jsonResponse.getString(RESPONSE_JSONKEY_MESSAGE));

            JSONObject responseData =  jsonResponse.getJSONObject(RESPONSE_JSONKEY_DATA);
            result.setGuid(responseData.getString(RESPONSE_JSONKEY_GUID));
            result.setUserSN(String.valueOf(responseData.getLong(RESPONSE_JSONKEY_USERSN)));
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }
        UserEntity userInfo = getUser();
        userInfo = UserDBHelper.getInstance(_context).get();
        userInfo.setGuid(result.getGuid());
        UserDBHelper.getInstance(_context).update(userInfo);

        return result;
    }

    public HttpResponseUserCreate create() throws HttpResultException, HttpException, JSONException {
        HttpResponseUserCreate result = new HttpResponseUserCreate();
        JSONObject data = new JSONObject();
        JSONObject user = new JSONObject();

        UserEntity userInfo = getUser();
        try {

            user.put(RESPONSE_JSONKEY_USER_HASHPHONE, userInfo.getHashPhone());
            user.put(RESPONSE_JSONKEY_USER_DEVICEID, userInfo.getDeviceId());
            user.put(RESPONSE_JSONKEY_USER_PUSHKEY, userInfo.getPushKey());
            data.put(RESPONSE_JSONKEY_USER, user);
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }

        JSONObject jsonRequest = getRequest(data);
        JSONObject jsonResponse = POST(HttpURL.getFullUrl(HttpURL.HTTP_ACTION_USER_CREATE), jsonRequest);

        try {
            result.setError(jsonResponse.getInt(RESPONSE_JSONKEY_ERROR));
            result.setMessage(jsonResponse.getString(RESPONSE_JSONKEY_MESSAGE));


            JSONObject responseData =  jsonResponse.getJSONObject(RESPONSE_JSONKEY_DATA);
            result.setGuid(responseData.getString(RESPONSE_JSONKEY_GUID));
            result.setUserSN(String.valueOf(responseData.getLong(RESPONSE_JSONKEY_USERSN)));
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }

        userInfo = UserDBHelper.getInstance(_context).get();
        userInfo.setGuid(result.getGuid());
        userInfo.setUserSN(result.getUserSN());
        UserDBHelper.getInstance(_context).update(userInfo);

        return result;
    }
}
