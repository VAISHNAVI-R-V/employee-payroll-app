package com.bridgelabz.employeepayrollapp.exception;

/**
 * Purpose : To implement Employee PayRoll Custom Exception in Employee Payroll AppApplication.
 *
 * @author : VAISHANAVI R. VISHWAKARMA.
 * @since : 16-12-2021
 */
public class EmployeeCustomException extends RuntimeException{
    public EmployeeCustomException(String message) {
        super(message);
    }
}
