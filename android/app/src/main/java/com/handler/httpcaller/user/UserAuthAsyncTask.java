package com.handler.httpcaller.user;

import android.content.Context;
import android.os.AsyncTask;

import com.code.ErrorCode;
import com.exception.http.HttpException;
import com.exception.http.HttpResultException;
import com.handler.httpcaller.BaseHttpResponse;

import org.json.JSONException;

/**
 * Created by 종열 on 2015-06-28.
 */
public class UserAuthAsyncTask extends AsyncTask<String, String, HttpResponseUserAuth> {
    private Context _context;
    private OnUserAsyncTaskCompleted _completed;

    public UserAuthAsyncTask(Context context, OnUserAsyncTaskCompleted completed) {
        _context = context;
        _completed = completed;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected HttpResponseUserAuth doInBackground(String... params) {
        HttpResponseUserAuth result = null;
        try {
            result = new UserHttpCaller(_context).auth();
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
    protected void onPostExecute(HttpResponseUserAuth result) {
        if ( _completed != null )
        {
            _completed.onUserAuthAsyncTaskCompleted(result);
        }
    }
}
