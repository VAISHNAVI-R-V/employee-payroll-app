package com.bridgelabz.employeepayrollapp.controller;


import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    @GetMapping(value = "/employees-list")
    public List<EmployeePayrollDto> employeesList() {
        return employeePayrollService.employeesList();
    }

    @PostMapping(value = "/add")
    public String addEmployee(@Valid @RequestBody EmployeePayrollDto employeePayrollDto) {
        return employeePayrollService.addEmployee(employeePayrollDto);
    }
}
