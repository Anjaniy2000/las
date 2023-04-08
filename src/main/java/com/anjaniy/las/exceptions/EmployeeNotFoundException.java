package com.anjaniy.las.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }
}
