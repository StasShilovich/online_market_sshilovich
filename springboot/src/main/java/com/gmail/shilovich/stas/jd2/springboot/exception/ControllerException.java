package com.gmail.shilovich.stas.jd2.springboot.exception;

public class ControllerException extends RuntimeException {

    public ControllerException(String errorMessage) {
        super(errorMessage);
    }

    public ControllerException(String errorMessage, Throwable e) {
        super(errorMessage);
    }
}
