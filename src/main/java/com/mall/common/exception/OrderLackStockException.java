package com.mall.common.exception;

/**
 * @author wy
 */
public class OrderLackStockException  extends RuntimeException{
    private int errorCode;

    public OrderLackStockException(int errorCode, String message) {
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
