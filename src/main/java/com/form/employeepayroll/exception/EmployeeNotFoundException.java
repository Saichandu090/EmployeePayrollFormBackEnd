package com.form.employeepayroll.exception;

public class EmployeeNotFoundException extends RuntimeException
{
    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}
