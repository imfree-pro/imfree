package com.handler.httpcaller.category;

import android.content.Context;

import com.code.HttpURL;
import com.exception.http.HttpException;
import com.exception.http.HttpResultException;
import com.handler.httpcaller.BaseHttpCaller;

import org.json.JSONObject;

/**
 * Created by 종열 on 2015-06-29.
 */
public class CategoryHttpCaller extends BaseHttpCaller {
    private Context _context;
    public CategoryHttpCaller(Context context) {
        super(context);
        _context = context;
    }

    public JSONObject category() throws HttpResultException, HttpException {
        JSONObject data = new JSONObject();
        JSONObject jsonRequest = getRequest(data);
        JSONObject jsonResponse = POST(HttpURL.getFullUrl(HttpURL.HTTP_ACTION_CATEGORY_ALL), jsonRequest);

        return jsonResponse;
    }
}
