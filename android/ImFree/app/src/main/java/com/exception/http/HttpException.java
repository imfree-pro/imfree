package com.exception.http;

/**
 * Created by 종열 on 2015-07-02.
 */
public class HttpException extends Exception {
    private int _httpStatusCode;
    public HttpException(int httpStatusCode, String message)
    {
        super(message);
        _httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() { return _httpStatusCode; }
}
