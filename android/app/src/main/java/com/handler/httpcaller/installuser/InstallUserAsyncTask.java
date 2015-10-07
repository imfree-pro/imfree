package com.handler.httpcaller.installuser;

import android.content.Context;
import android.os.AsyncTask;

import com.exception.http.HttpException;
import com.exception.http.HttpResultException;

import org.json.JSONObject;

/**
 * Created by 종열 on 2015-07-20.
 */
public class InstallUserAsyncTask extends AsyncTask<String, String, JSONObject> {
    private Context _context;
    private InstallUserOnCompleted _onCompleted;

    public InstallUserAsyncTask(Context context) {
        _context = context;
        _onCompleted = (InstallUserOnCompleted)context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject result = null;
        try {
            result = new InstallUserHttpCaller(_context).installUser();
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
        //new CategoryHandler(_context).init(result);
    }
}
