package com.logibeat.cloud.common.exception;

/**
 * 业务异常，由于用户操作的原因造成的可预见的异常
 * 通常来说不需要打印，只需要返回给前台 message 即可
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 4898323862807130079L;

    private final Object[] args;

    public BusinessException(String message) {
        super(message);
        args = null;
    }

    public BusinessException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
