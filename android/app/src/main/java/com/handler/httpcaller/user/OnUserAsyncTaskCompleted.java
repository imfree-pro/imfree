package com.handler.httpcaller.user;

import com.handler.httpcaller.contacts.HttpResponseDataSync;

/**
 * Created by 종열 on 2015-06-29.
 */
public interface OnUserAsyncTaskCompleted {
    void onUserAuthAsyncTaskCompleted(HttpResponseUserAuth baseHttpResponse);
    void onUserCreateAsyncTaskCompleted(HttpResponseUserCreate baseHttpResponse);
}
