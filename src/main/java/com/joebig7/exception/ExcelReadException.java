package com.joebig7.exception;

/**
 * @Author JoeBig7
 * @date 2021/11/19 10:13:02
 */
public class ExcelReadException extends RuntimeException{
    public ExcelReadException() {
        super();
    }

    public ExcelReadException(String message) {
        super(message);
    }
}
