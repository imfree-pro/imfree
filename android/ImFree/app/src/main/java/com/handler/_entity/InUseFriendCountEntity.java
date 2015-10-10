package com.handler._entity;

/**
 * Created by 종열 on 2015-06-15.
 */
public class InUseFriendCountEntity {
    private String _userSN;
    private String _count;
    public InUseFriendCountEntity(String userSN, String count) {
        _userSN = userSN;
        _count = count;
    }

    public String getUserSN() { return _userSN; }

    public String getCount() { return _count; }
}
