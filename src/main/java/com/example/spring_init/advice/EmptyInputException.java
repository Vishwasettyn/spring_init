package com.example.spring_init.advice;

public class EmptyInputException extends RuntimeException {

    String message;
    public EmptyInputException(){}

    public EmptyInputException(String message){
        this.message = message;
    }

}
