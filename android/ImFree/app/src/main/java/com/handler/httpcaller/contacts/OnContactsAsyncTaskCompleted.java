package com.handler.httpcaller.contacts;

import com.handler._entity.ContactsEntity;
import com.handler.httpcaller.contacts.HttpResponseDataSync;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-30.
 */
public interface OnContactsAsyncTaskCompleted {
    void onContactsAsyncTaskCompleted(HttpResponseDataSync response);
}
