package com.handler.httpcaller.user;

import com.handler.httpcaller.BaseHttpResponse;

/**
 * Created by 종열 on 2015-07-04.
 */
public class HttpResponseUserAuth extends BaseHttpResponse {
    public HttpResponseUserAuth() {}

    private String _usersn;
    public String getUserSN() { return _usersn; }
    public void setUserSN(String usersn) { _usersn = usersn; }
}
