package com.code;

/**
 * Created by 종열 on 2015-06-28.
 */
public class HttpURL {
    public final static String HTTP_DOMAIN = "http://54.65.139.250";
    public final static String HTTP_VERSION = "1.00.00";

    public final static String HTTP_ACTION_USER_AUTH = "/user/auth";
    public final static String HTTP_ACTION_USER_CREATE = "/user/create";
    public final static String HTTP_ACTION_CONTACTS_SYNC = "/data/sync";
    public final static String HTTP_ACTION_CATEGORY_ALL = "/category/listAll";
    public final static String HTTP_ACTION_SUGGEST_CREATE = "/suggest/create";
    public final static String HTTP_ACTION_SUGGEST_DELETE = "/suggest/delete";
    public final static String HTTP_ACTION_SUGGEST_MYLIST = "/suggest/mylist";
    public final static String HTTP_ACTION_SUGGEST_FROENDLOST = "/suggest/friendlist";
    public final static String HTTP_ACTION_CONTACTS_INSTALLUSER = "/contacts/installuserlist";

    public static String getFullUrl(String httpActionName)
    {
        return HTTP_DOMAIN + "/api/" + HTTP_VERSION + httpActionName;
    }

}
