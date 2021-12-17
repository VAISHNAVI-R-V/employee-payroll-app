package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * Purpose : To implement Employee PayRoll Controller for Employee Payroll AppApplication.
 *
 * @author : VAISHANAVI R. VISHWAKARMA.
 * @since : 16-12-2021
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeePayrollController {

    @Autowired
    private EmployeePayrollService employeePayrollService;

    /**
     * @purpose : To get all employee list from employee-payroll-app.
     *
     * @return : employee list method from employee service class.
     */
    @GetMapping(value = "/employees-list")
    public List<EmployeePayrollDto> employeesList() {
        return employeePayrollService.employeesList();
    }

    /**
     * @purpose : To add employee from employee-payroll-app.
     *
     * @return : add employee method from employee service class.
     */
    @PostMapping(value = "/add")
    public String addEmployee(@Valid @RequestBody EmployeePayrollDto employeePayrollDto) {
        return employeePayrollService.addEmployee(employeePayrollDto);
    }

    /**
     * @purpose : To update employee by ID from employee-payroll-app.
     *
     * @return : update employee method by ID from employee service class.
     */
    @PutMapping(value = "/update/{id}")
    public String updateEmployee(@PathVariable(value = "id") int id,
                                 @Valid @RequestBody EmployeePayrollDto employeePayrollDto) {
        return employeePayrollService.updateEmployee(id, employeePayrollDto);
    }

    /**
     * @purpose : To delete employee by ID from employee-payroll-app.
     *
     * @return : delete employee method by ID from employee service class.
     */
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return employeePayrollService.deleteEmployee(id);
    }
}
