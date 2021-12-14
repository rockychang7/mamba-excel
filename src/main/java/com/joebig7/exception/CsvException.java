package com.joebig7.exception;

/**
 * @Author JoeBig7
 * @date 2021/12/14 18:06:14
 * @description csv异常
 */
public class CsvException extends RuntimeException{

    public CsvException() {
        super();
    }

    public CsvException(String message) {
        super(message);
    }
}
