package com.imfree.imfree;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.List;

public class ImFreeService extends Service {
    private final String LOG_NAME = ImFreeService.class.getSimpleName();
    public static Thread _thread;
    private ComponentName _componentName;
    private ActivityManager _activityManager;
    private boolean _serviceRunning = false;
    private ImFreeServiceContactWatcher _contactWatcher;

    public ImFreeService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        if (_thread == null) {
            _thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (_serviceRunning) {
                        List<ActivityManager.RecentTaskInfo> info = _activityManager.getRecentTasks(1, Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (info != null) {
                            ActivityManager.RecentTaskInfo recent = info.get(0);
                            Intent mIntent = recent.baseIntent;
                            ComponentName name = mIntent.getComponent();

                            if (name.equals(_componentName)) {
                                Log.d(LOG_NAME, "== pre App, recent App is same App");
                            } else {
                                _componentName = name;
                                Log.d(LOG_NAME, "== Application is catched: " + name);
                            }
                        }
                        SystemClock.sleep(2000);
                    }
                }
            });
            _thread.start();
        } else if (_thread.isAlive() == false) {
            try {
                _thread.start();
            }
            catch (Exception ex)
            {
                Log.d(LOG_NAME, "==Exception" + ex.getStackTrace());
            }
        }
        return START_STICKY;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        _activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        _serviceRunning = true;
        startWatchers();
    }

    @Override
    public void onDestroy(){
        _serviceRunning = false;
        _thread = null;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onDestroy();
        cancelWatchers();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startWatchers()
    {
        Handler contactsHandler = new Handler();
        _contactWatcher = new ImFreeServiceContactWatcher(contactsHandler, this.getApplicationContext());
        getContentResolver().registerContentObserver(ContactsContract.RawContacts.CONTENT_URI, false, _contactWatcher);
    }

    private void cancelWatchers()
    {
        if ( _contactWatcher != null )
            getContentResolver().unregisterContentObserver(_contactWatcher);
    }
}
