package com.abs.springabscommon.util.exception;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 13:41 2018/7/3
 **/
public class ValidateCodeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidateCodeException() {
    }

    public ValidateCodeException(String message) {
        super(message);
    }

    public ValidateCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateCodeException(Throwable cause) {
        super(cause);
    }

    public ValidateCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
