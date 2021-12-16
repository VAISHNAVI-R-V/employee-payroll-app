package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeePayrollEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmployeePayRollBuilder {
    private ModelMapper modelMapper = new ModelMapper();

    public EmployeePayrollEntity buildEmployeeEntity(EmployeePayrollDto employeeDto,
                                                     EmployeePayrollEntity employeeEntity) {
        modelMapper.map(employeeDto, employeeEntity);
        return employeeEntity;
    }
}
