package com.handler.httpcaller.category;

import android.content.Context;
import android.os.AsyncTask;

import com.exception.http.HttpException;
import com.exception.http.HttpResultException;
import com.handler.CategoryHandler;

import org.json.JSONObject;

/**
 * Created by 종열 on 2015-06-29.
 */
public class CategoryAsyncTask  extends AsyncTask<String, String, JSONObject> {
    private Context _context;

    public CategoryAsyncTask(Context context) {
        _context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject result = null;
        try {
            result = new CategoryHttpCaller(_context).category();
        } catch (HttpResultException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(String... progress) {
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        new CategoryHandler(_context).init(result);
    }
}
