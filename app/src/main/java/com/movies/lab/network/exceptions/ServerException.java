package com.movies.lab.network.exceptions;


import com.movies.lab.constants.ApiConstants;

public class ServerException extends Exception {
    private String message;
    private String messageCode;

    public ServerException(String message) {
        this(ApiConstants.EXCEPTION_SERVER, message);
    }

    public ServerException(String messageCode, String message) {
        super(message);
        this.message = message;
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
