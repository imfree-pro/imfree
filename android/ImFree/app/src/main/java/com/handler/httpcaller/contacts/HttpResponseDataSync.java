package com.handler.httpcaller.contacts;

import com.handler.httpcaller.BaseHttpResponse;

import java.util.ArrayList;

/**
 * Created by 종열 on 2015-06-29.
 */
public class HttpResponseDataSync extends BaseHttpResponse {
    private ArrayList<String> _installUser;
    public void setInstallUser(ArrayList<String> installUser) { _installUser = installUser; }
    public ArrayList<String> getInstallUser() { return _installUser; }
}
