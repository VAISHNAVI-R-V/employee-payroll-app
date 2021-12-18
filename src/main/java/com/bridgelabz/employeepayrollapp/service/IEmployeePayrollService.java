package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeePayrollEntity;

import java.util.List;

public interface IEmployeePayrollService {
    List<EmployeePayrollDto> employeesList();

    EmployeePayrollEntity getEmployeeById(int id);

    String addEmployee(EmployeePayrollDto employeeDto);

    String updateEmployee(int id, EmployeePayrollDto employeeDto);

    String deleteEmployee(int id);
}
