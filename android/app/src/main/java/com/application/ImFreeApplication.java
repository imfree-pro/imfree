package com.application;

import android.app.Activity;
import android.app.Application;


import com.imfree.imfree.R;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender;

/**
 * Created by 종열 on 2015-06-18.
 */
//
@ReportsCrashes(
        formUri = "http://54.65.139.250/api/1.00.00/customReport/create",
        httpMethod=HttpSender.Method.POST,
        reportType = HttpSender.Type.JSON,
        customReportContent = {
                ReportField.BRAND,
                ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME,
                ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL,
                ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT
        }
)
public class ImFreeApplication extends Application{
    @Override
    public void onCreate() {
        //전역 변수 초기화
        super.onCreate();
        ACRA.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
