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

@Service
public class EmployeePayrollService {
    @Autowired
    private EmployeePayrollRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<EmployeePayrollDto> employeesList() {
        return employeeRepository.findAll().stream()
                .map(employeePayrollEntity -> modelMapper.map(employeePayrollEntity, EmployeePayrollDto.class))
                .collect(Collectors.toList());
    }

    public String addEmployee(EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollEntity employeePayrollEntityEntity = modelMapper.map(employeePayrollDto, EmployeePayrollEntity.class);
        employeeRepository.save(employeePayrollEntityEntity);
        return "Employee Added Successfully!";
    }

//    public String  updateEmployee(int id, EmployeeDto employeeDTO) {
//        EmployeeEntity employeeEntity = getEmployeeById(id);
//        modelMapper.map(employeeDTO, employeeEntity);
//        employeeRepository.save(employeeEntity);
//        return "Employee Updated Successfully";
//    }
    public String updateEmployee(int id, EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollEntity employeePayrollEntity = getEmployeeById(id);
        modelMapper.map(employeePayrollDto, employeePayrollEntity);
        employeeRepository.save(employeePayrollEntity);
        return "Employee Updated Successfully!!";
    }
//    public EmployeeEntity getEmployeeById(int id) {
//        EmployeeEntity employeeEntity = employeeRepository.findById(id)
//                .orElseThrow(() -> new EmployeeCustomException(
//                        "Invalid ATM Id -> " + id));
//        return employeeEntity;

        private EmployeePayrollEntity getEmployeeById(int id) {
            EmployeePayrollEntity employeePayrollEntity = employeeRepository.findById(id)
                    .orElseThrow(() -> new EmployeeCustomException(
                            "Invalid Employee Id -> " + id));
            return employeePayrollEntity;
    }
}
