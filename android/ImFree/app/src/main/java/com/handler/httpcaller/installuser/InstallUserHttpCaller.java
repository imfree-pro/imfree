package com.handler.httpcaller.installuser;

import android.content.Context;

import com.code.HttpURL;
import com.exception.http.HttpException;
import com.exception.http.HttpResultException;
import com.handler.httpcaller.BaseHttpCaller;

import org.json.JSONObject;

/**
 * Created by 종열 on 2015-07-20.
 */
public class InstallUserHttpCaller extends BaseHttpCaller {
    private Context _context;
    public InstallUserHttpCaller(Context context) {
        super(context);
        _context = context;
    }

    public JSONObject installUser() throws HttpResultException, HttpException {
        JSONObject data = new JSONObject();
        JSONObject jsonRequest = getRequest(data);
        JSONObject jsonResponse = POST(HttpURL.getFullUrl(HttpURL.HTTP_ACTION_CONTACTS_INSTALLUSER), jsonRequest);

        return jsonResponse;
    }
}
