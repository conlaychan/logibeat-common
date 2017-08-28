package com.logibeat.cloud.common.exception;

/**
 * 系统异常，意味着一个需要修复的bug或运行环境
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -8215936898476079653L;

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
