package com.handler.httpcaller;

import android.content.Context;
import android.util.Log;

import com.exception.http.HttpException;
import com.exception.http.HttpResultException;
import com.handler.dbhelper.user.UserDBHelper;
import com.handler._entity.UserEntity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 종열 on 2015-06-28.
 */
public class BaseHttpCaller {
    protected final static String RESPONSE_JSONKEY_ERROR = "error";
    protected final static String RESPONSE_JSONKEY_MESSAGE = "message";
    protected final static String RESPONSE_JSONKEY_DATA = "data";

    protected final static String REQUEST_JSONKEY_TOKEN = "token";
    protected final static String REQUEST_JSONKEY_GUID = "guid";
    protected final static String REQUEST_JSONKEY_DATA = "data";

    private Context _context;
    private UserEntity _user;
    public BaseHttpCaller(Context context)
    {
        _user = UserDBHelper.getInstance(context).get();
    }

    public UserEntity getUser()
    {
        return _user;
    }

    public JSONObject getRequest(JSONObject data)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(REQUEST_JSONKEY_TOKEN, _user.getAccessToken());
            jsonObject.put(REQUEST_JSONKEY_GUID, _user.getGuid());
            jsonObject.put(REQUEST_JSONKEY_DATA, data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public JSONObject POST(String url, JSONObject jsonObject) throws HttpResultException, HttpException {
        InputStream inputStream = null;
        JSONObject result = new JSONObject();
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(jsonObject.toString(), HTTP.UTF_8);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json;charset=UTF-8");

            HttpResponse httpResponse = httpclient.execute(httpPost);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                HttpException httpException = new HttpException(statusCode, httpResponse.getStatusLine().getReasonPhrase());
                throw httpException;
            }

            inputStream = httpResponse.getEntity().getContent();
            if (inputStream != null && inputStream.equals("") == false) {
                String responseString = convertInputStreamToString(inputStream);
                result = new JSONObject(responseString);
            } else {
                HttpResultException httpResultException = new HttpResultException(HttpResultException.ERRORCODE_JSONNOTFOUND, "");
                throw httpResultException;
            }
        }
        catch (HttpException he) {
            throw he;
        }catch (HttpResultException hre) {
            throw hre;
        }catch (Exception e) {
            e.printStackTrace();
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

}
