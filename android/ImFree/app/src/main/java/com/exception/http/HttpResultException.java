package com.exception.http;

/**
 * Created by 종열 on 2015-07-02.
 */
public class HttpResultException extends Exception{
    public final static int ERRORCODE_JSONNOTFOUND = 1;


    private int _errorCode;
    public HttpResultException(int errorCode, String message)
    {
        super(message);
        _errorCode = errorCode;
    }

    public int getErrorCode() { return _errorCode; }
}
