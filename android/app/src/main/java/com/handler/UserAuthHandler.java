package com.handler;

import android.app.Application;
import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;

import com.code.ErrorCode;
import com.handler._entity.UserEntity;
import com.handler.dbhelper.contacts.ContactsLocalAsyncTask;
import com.handler.dbhelper.contacts.OnContactsLocalAsyncTaskCompleted;
import com.handler.httpcaller.contacts.ContactsHttpAsyncTask;
import com.handler.httpcaller.contacts.OnContactsAsyncTaskCompleted;
import com.handler.dbhelper.user.UserDBHelper;
import com.handler.httpcaller.category.CategoryAsyncTask;
import com.handler.httpcaller.contacts.HttpResponseDataSync;
import com.handler.httpcaller.user.HttpResponseUserAuth;
import com.handler.httpcaller.user.HttpResponseUserCreate;
import com.handler.httpcaller.user.OnUserAsyncTaskCompleted;
import com.handler.httpcaller.user.UserAuthAsyncTask;
import com.handler.httpcaller.user.UserCreateAsyncTask;
import com.libs.encrypt.Encrypt;

/**
 * Created by 종열 on 2015-06-29.
 */
public class UserAuthHandler implements OnUserAsyncTaskCompleted, OnContactsLocalAsyncTaskCompleted, OnContactsAsyncTaskCompleted {
    public final static int ACTION_TYPE_LOGIN_OK = 0;
    public final static int ACTION_TYPE_LOGIN_NEXT_CONTACTS_SYNC = 1;
    public final static int ACTION_TYPE_LOGIN_NEXT_CONTACTS_SYNC_OK = 2;

    public final static int ACTION_TYPE_USERNOTFOUND = 100;

    public final static int ACTION_TYPE_GOOGLE_LOGIN = 1000;
    public final static int ACTION_TYPE_GOOGLE_LOGIN_USER_MISS_MATCHING = 1001;

    public final static int ACTION_TYPE_ERROR = -100;

    public final static int ACTION_TYPE_HTTP_ERROR = -200;
    public final static int ACTION_TYPE_JSON_ERROR = -300;

    public final static int ACTION_TYPE_LOGIN_FAIL = -400;


    private Context _context;
    private Application _application;
    private OnUserAuthCompleted _onLoginCompleted;
    private UserEntity _userEntity;
    private boolean _contactsAsync = false;


    public UserAuthHandler(Context context, Application application) {
        _context = context;
        _application = application;
        _onLoginCompleted = (OnUserAuthCompleted)context;
        _userEntity = UserDBHelper.getInstance(_context).get();

        new ContactsLocalAsyncTask(_context, this).execute();
    }

    public void processLogin()
    {
        _userEntity = UserDBHelper.getInstance(_context).get();
        // 유저가 로그인이 존재하지 않음 //
        if ( _userEntity.getAccessToken().equals("") ) {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_GOOGLE_LOGIN, "");
            return;
        }

        // 유저가 존재하지 않아 생성 요청 //
        if ( _userEntity.getUserSN().equals("")) {
            new UserCreateAsyncTask(_context, this).execute();
            return;
        }
        login();
    }

    private void setUser()
    {
        TelephonyManager systemService = (TelephonyManager)_context.getSystemService(_context.TELEPHONY_SERVICE);
        String PhoneNumber = systemService.getLine1Number();
        PhoneNumber = PhoneNumber.substring(PhoneNumber.length()-10,PhoneNumber.length());
        PhoneNumber="0"+PhoneNumber;
        PhoneNumber = PhoneNumberUtils.formatNumber(PhoneNumber);

        _userEntity.setPhoneNo(PhoneNumber);
        _userEntity.setHashPhone(new Encrypt().SHA256(PhoneNumber));
        UserDBHelper.getInstance(_context).update(_userEntity);
    }

    @Override
    public void onUserCreateAsyncTaskCompleted(HttpResponseUserCreate response) {
        if ( response.getError() == ErrorCode.RESPONSE_ERROR_HTTPSTATUS)
        {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_HTTP_ERROR, response.getMessage());
            return;
        }
        if ( response.getError() == ErrorCode.RESPONSE_ERROR_JSON)
        {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_JSON_ERROR, response.getMessage());
            return;
        }

        if ( response.getError() != 0 ) {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_ERROR, response.getMessage());
            return;
        }
        login();
    }


    private void login()
    {
        _userEntity = UserDBHelper.getInstance(_context).get();
        new CategoryAsyncTask(_context).execute();
        new UserAuthAsyncTask(_context, this).execute();
    }

    @Override
    public void onUserAuthAsyncTaskCompleted(HttpResponseUserAuth response) {
        if ( response.getError() == ErrorCode.RESPONSE_ERROR_HTTPSTATUS)
        {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_HTTP_ERROR, response.getMessage());
            return;
        }
        if ( response.getError() == ErrorCode.RESPONSE_ERROR_JSON)
        {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_JSON_ERROR, response.getMessage());
            return;
        }

        if ( response.getError() != 0 )
        {
            if ( response.getError() == ErrorCode.RESPONSE_ERROR_MISS_MATCHING_PHONE) {
                _onLoginCompleted.onLoginCompleted(ACTION_TYPE_GOOGLE_LOGIN_USER_MISS_MATCHING, response.getMessage());
            } else {
                _onLoginCompleted.onLoginCompleted(ACTION_TYPE_USERNOTFOUND, response.getMessage());
            }
            return;
        }

        if ( response.getUserSN().equals(_userEntity.getUserSN()) == false)
        {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_LOGIN_FAIL, "");
            return;
        }


        if ( _contactsAsync == true ) {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_LOGIN_OK, "");
            new ContactsHttpAsyncTask(_context, null).execute();
        } else {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_LOGIN_NEXT_CONTACTS_SYNC, "");
            new ContactsHttpAsyncTask(_context, this).execute();
        }
    }

    @Override
    public void onContactsAsyncTaskCompleted(HttpResponseDataSync response) {
        if ( response.getError() == ErrorCode.RESPONSE_ERROR_HTTPSTATUS)
        {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_HTTP_ERROR, response.getMessage());
            return;
        }
        if ( response.getError() == ErrorCode.RESPONSE_ERROR_JSON)
        {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_JSON_ERROR, response.getMessage());
            return;
        }

        if ( response.getError() != 0 )
        {
            _onLoginCompleted.onLoginCompleted(ACTION_TYPE_ERROR, response.getMessage());
            return;
        }
        _onLoginCompleted.onLoginCompleted(ACTION_TYPE_LOGIN_NEXT_CONTACTS_SYNC_OK, "");
    }

    @Override
    public void onContactsLocalAsyncTaskCompleted() {
        _contactsAsync = true;
    }
}
