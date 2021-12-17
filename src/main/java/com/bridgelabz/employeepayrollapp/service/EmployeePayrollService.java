package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeePayrollEntity;
import com.bridgelabz.employeepayrollapp.exception.EmployeeCustomException;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose : To implement Employee PayRoll Service in Employee Payroll AppApplication.
 *
 * @author : VAISHANAVI R. VISHWAKARMA.
 * @since : 16-12-2021
 */
@Service
public class EmployeePayrollService implements IEmployeePayrollService{
    @Autowired
    private EmployeePayrollRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @purpose : To get list of Employees.
     *
     * @return : List of Employees.
     */
    public List<EmployeePayrollDto> employeesList() {
        return employeeRepository.findAll().stream()
                .map(employeePayrollEntity -> modelMapper.map(employeePayrollEntity, EmployeePayrollDto.class))
                .collect(Collectors.toList());
    }

    /**
     * @purpose : To Add Employee in Employee payroll.
     *
     * @param employeePayrollDto : employeePayrollDto is used to add employee in repository.
     * @return : String Message.
     */
    public String addEmployee(EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollEntity employeePayrollEntityEntity = modelMapper.map(employeePayrollDto, EmployeePayrollEntity.class);
        employeeRepository.save(employeePayrollEntityEntity);
        return "Employee Added Successfully!";
    }

    /**
     * @purpose : To Update Employee in Employee payroll.
     *
     * @param id : I'd is used to search existing employee data.
     * @param employeePayrollDto : employeePayrollDto is used to upload employee in repository.
     * @return : String Message.
     */
    public String updateEmployee(int id, EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollEntity employeePayrollEntity = getEmployeeById(id);
        modelMapper.map(employeePayrollDto, employeePayrollEntity);
        employeeRepository.save(employeePayrollEntity);
        return "Employee Updated Successfully!!";
    }

    /**
     * @purpose : To get Employees by By ID.
     *
     * @param id : I'd is used to search existing employee data.
     * @return : String Message.
     */
        public EmployeePayrollEntity getEmployeeById(int id) {
            EmployeePayrollEntity employeePayrollEntity = employeeRepository.findById(id)
                    .orElseThrow(() -> new EmployeeCustomException(
                            "Invalid Employee Id -> " + id));
            return employeePayrollEntity;
    }

    /**
     * @purpose : To Delete Employee in Employee payroll.
     *
     * @param id : I'd is used to search existing employee data.
     * @return : String Message.
     */
    public String deleteEmployee(int id) {
        EmployeePayrollEntity employeePayrollEntity = getEmployeeById(id);
        employeeRepository.delete(employeePayrollEntity);
        return "Employee Deleted Successfully!!";
    }
}
