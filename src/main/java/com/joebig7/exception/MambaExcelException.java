package com.joebig7.exception;

/**
 * @Author JoeBig7
 * @date 2021/7/12 18:03:09
 */
public class MambaExcelException extends RuntimeException{
    public MambaExcelException() {
        super();
    }

    public MambaExcelException(String message) {
        super(message);
    }

    public MambaExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
