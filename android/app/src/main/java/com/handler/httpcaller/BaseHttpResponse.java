package com.handler.httpcaller;

/**
 * Created by 종열 on 2015-06-28.
 */
public class BaseHttpResponse {
    private int _error;
    private String _guid;
    private String _message;
    public BaseHttpResponse() { }

    public int getError() { return _error; }
    public void setError(int error) { _error = error; }
    public String getMessage() { return _message; }
    public void setMessage(String message ) { _message = message; }
    public String getGuid() { return _guid; }
    public void setGuid(String guid ) { _guid = guid; }
}
