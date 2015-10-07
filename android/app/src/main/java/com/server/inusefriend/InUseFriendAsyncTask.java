package com.server.inusefriend;

import android.content.Context;
import android.os.AsyncTask;

import com.handler._entity.ContactsEntity;
import com.handler.dbhelper.contacts.ContactsDBHelper;
import com.handler._entity.InUseFriendEntity;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-15.
 */
public class InUseFriendAsyncTask extends AsyncTask<String, String , String> {
    private Context _context;

    public InUseFriendAsyncTask(Context context) {
        _context = context;
    }

    //onPreExecute 함수는 이름대로 excute()로 실행 시 doInBackground() 실행 전에 호출되는 함수
    //여기서 ProgressDialog 생성 및 기본 세팅하고 show()
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        ArrayList<InUseFriendEntity> inUseFriendList = new ArrayList<InUseFriendEntity>();
        ContactsDBHelper contactsHelper = new ContactsDBHelper(_context);
        ArrayList<ContactsEntity> contactsList = contactsHelper.getAllContacts();
        int i = 0;
        for(ContactsEntity entity : contactsList)
        {
            if ( entity.getDisplayName().equals("울뽁이") || entity.getDisplayName().equals("김진규") ||
                    entity.getDisplayName().equals("권용범") || entity.getDisplayName().equals("최영욱")) {
                inUseFriendList.add(new InUseFriendEntity(String.valueOf(++i), entity));
            }
        }

        DBInUseFriendHelper.getInstance(_context).add(inUseFriendList);
        return String.valueOf(inUseFriendList.size());
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
