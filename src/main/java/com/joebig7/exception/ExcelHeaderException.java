package com.joebig7.exception;

/**
 * @Author JoeBig7
 * @date 2021/11/25 16:47:58
 */
public class ExcelHeaderException extends RuntimeException{
    public ExcelHeaderException() {
        super();
    }

    public ExcelHeaderException(String message) {
        super(message);
    }
}
