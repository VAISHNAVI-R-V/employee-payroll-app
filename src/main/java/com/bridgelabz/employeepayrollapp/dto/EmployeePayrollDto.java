package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeePayrollDto {
    private String name;
    private int salary;
    private String gender;
    private Date startDate;
    private String department;
    private String notes;
    private String imagePath;
}
