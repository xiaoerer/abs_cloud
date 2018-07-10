package com.abs.springabscommon.util.exception;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 13:38 2018/7/3
 * 403授权拒绝
 **/
public class DeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DeniedException() {
    }

    public DeniedException(String message) {
        super(message);
    }

    public DeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeniedException(Throwable cause) {
        super(cause);
    }

    public DeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
