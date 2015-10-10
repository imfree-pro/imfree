package com.server.suggest;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by 종열 on 2015-06-21.
 */
public class FriendSuggestAsyncTask extends AsyncTask<String, String, Object> {
    private Context _context;
    private OnSuggestAsyncTaskCompleted _listener;

    public FriendSuggestAsyncTask(Context context, OnSuggestAsyncTaskCompleted listener) {
        _context = context;
        _listener = listener;
    }


    //onPreExecute 함수는 이름대로 excute()로 실행 시 doInBackground() 실행 전에 호출되는 함수
    //여기서 ProgressDialog 생성 및 기본 세팅하고 show()
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //onProgressUpdate() 함수는 publishProgress() 함수로 넘겨준 데이터들을 받아옴
    @Override
    protected void onProgressUpdate(String... progress) {
    }

    @Override
    protected void onPostExecute(Object o){
        super.onPostExecute(o);
        // your stuff
        if ( _listener != null)
            _listener.onSuggestCompleted();
    }
}
