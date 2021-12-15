package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
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
}
