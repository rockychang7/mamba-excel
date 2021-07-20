package com.joebig7.exception;

/**
 * @Author JoeBig7
 * @date 2021/7/12 18:03:09
 * @description excel manipulation exception
 */
public class ExcelManipulationException extends RuntimeException{
    public ExcelManipulationException() {
        super();
    }

    public ExcelManipulationException(String message) {
        super(message);
    }

    public ExcelManipulationException(String message, Throwable cause) {
        super(message, cause);
    }
}
