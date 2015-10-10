package com.imfree.imfree;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.handler.OnUserAuthCompleted;
import com.handler.UserAuthHandler;
import com.libs.encrypt.Encrypt;
import com.handler.dbhelper.user.UserDBHelper;
import com.handler._entity.UserEntity;


public class MainActivity extends ActionBarActivity implements OnUserAuthCompleted {

    private ImFreeServiceMonitor _serviceMonitor = ImFreeServiceMonitor.getInstance();
    private UserAuthHandler _userAuthHandler;
    private Context _context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (_serviceMonitor.isMonitoring() == false) {
            _serviceMonitor.startMonitoring(getApplicationContext());
        }


        _context = this;
        _userAuthHandler = new UserAuthHandler(_context, getApplication());

        loginProcess();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void loginProcess()
    {
        AQuery aq = new AQuery((MainActivity)_context);
        aq.id(R.id.btn_googleAuth).invisible();
        aq.id(R.id.tv_loading).text("");
        _userAuthHandler.processLogin();
    }

    @Override
    public void onLoginCompleted(int actionType, String message) {
        AQuery aq = new AQuery(this);
        switch (actionType)
        {
            case UserAuthHandler.ACTION_TYPE_LOGIN_OK :
            case UserAuthHandler.ACTION_TYPE_LOGIN_NEXT_CONTACTS_SYNC_OK :
                aq.id(R.id.tv_loading).text("OK");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FriendSuggestActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_stable, R.anim.anim_stable);
                finish();
                break;
            case UserAuthHandler.ACTION_TYPE_USERNOTFOUND :
                aq.id(R.id.tv_loading).text("ACTION_TYPE_USERNOTFOUND");
                break;
            case UserAuthHandler.ACTION_TYPE_GOOGLE_LOGIN :
                aq.id(R.id.tv_loading).text("ACTION_TYPE_GOOGLE_LOGIN");
                googleAuthentication();
                break;
            case UserAuthHandler.ACTION_TYPE_GOOGLE_LOGIN_USER_MISS_MATCHING :
                aq.id(R.id.tv_loading).text("ACTION_TYPE_GOOGLE_LOGIN_USER_MISS_MATCHING");
                break;

            case UserAuthHandler.ACTION_TYPE_ERROR :
                aq.id(R.id.tv_loading).text("ACTION_TYPE_ERROR : " + message);
                break;
            case  UserAuthHandler.ACTION_TYPE_HTTP_ERROR :
                aq.id(R.id.tv_loading).text("ACTION_TYPE_HTTP_ERROR : " + message);
                break;
            case  UserAuthHandler.ACTION_TYPE_JSON_ERROR :
                aq.id(R.id.tv_loading).text("ACTION_TYPE_JSON_ERROR : " + message);
                break;
            case UserAuthHandler.ACTION_TYPE_LOGIN_FAIL:
                aq.id(R.id.tv_loading).text("ACTION_TYPE_LOGIN_FAIL : " + message);
        }
    }

    private void googleAuthentication()
    {
        AQuery aq = new AQuery(this);

        aq.id(R.id.btn_googleAuth).visible().clicked(this, "processGoogleAuthentication");
        // 구글 인증을 요기에 들어갑니다. //
    }

    public void processGoogleAuthentication(View view) {
        Toast.makeText(_context, "인증완료", Toast.LENGTH_SHORT).show();
        UserEntity userInfo = UserDBHelper.getInstance(_context).get();
        TelephonyManager systemService = (TelephonyManager) getSystemService(_context.TELEPHONY_SERVICE);
        String PhoneNumber = systemService.getLine1Number();
        PhoneNumber = PhoneNumber.substring(PhoneNumber.length() - 10, PhoneNumber.length());
        PhoneNumber = "0" + PhoneNumber;
        PhoneNumber = PhoneNumberUtils.formatNumber(PhoneNumber);

        userInfo.setPhoneNo(PhoneNumber);
        userInfo.setHashPhone(new Encrypt().SHA256(PhoneNumber));
        userInfo.setAccessToken(PhoneNumber);

        UserDBHelper.getInstance(_context).update(userInfo);

        loginProcess();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
