package com.mall.common.exception;


/**
 * @author wy
 */
public class InvalidInputException extends RuntimeException{
    private int errorCode;

    public InvalidInputException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
