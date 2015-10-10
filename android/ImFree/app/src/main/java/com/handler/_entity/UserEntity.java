package com.handler._entity;

/**
 * Created by 종열 on 2015-06-21.
 */
public class UserEntity {
    private int _id;

    private String _userSN;
    private String _guid;
    private String _phoneNo;
    private String _accessToken;
    private String _hashPhone;

    private String _deviceId;
    private String _pushKey;

    public void setId(int id) { _id = id; }

    public int getId() { return _id; }

    public void setUserSN(String userSN) { _userSN = userSN; }

    public String getUserSN() { return _userSN == null ? "" : _userSN; }

    public void setGuid(String guid)
    {
        _guid = guid;
    }

    public String getGuid()
    {
        return _guid == null ? "" : _guid;
    }

    public void setPhoneNo(String phoneNo)
    {
        _phoneNo = phoneNo;
    }

    public String getPhoneNo()
    {
        return _phoneNo;
    }

    public void setAccessToken(String accessToken) { _accessToken = accessToken; }

    public String getAccessToken()
    {
        return _accessToken;
    }

    public void setHashPhone(String hashPhone) { _hashPhone = hashPhone; }

    public String getHashPhone()
    {
        return _hashPhone;
    }

    public void setDeviceId(String deviceId) { _deviceId = deviceId;}

    public String getDeviceId() { return _deviceId; }

    public void setPushKey(String pushKey) { _pushKey = pushKey;}

    public String getPushKey() { return _pushKey; }
}
