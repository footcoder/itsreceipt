package kr.footcoder.receipt.exceptions;

import kr.footcoder.receipt.enumclass.ErrorCode;

/**
 * 예외 발생시
 * return client error message
 */
public class LogicException extends RuntimeException {

    private final ErrorCode errorCode;

    public LogicException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}

