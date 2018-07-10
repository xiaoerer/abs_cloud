package com.abs.springabscommon.util.exception;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 13:36 2018/7/3
 * 检查异常
 **/
public class CheckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckedException() {
    }

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
