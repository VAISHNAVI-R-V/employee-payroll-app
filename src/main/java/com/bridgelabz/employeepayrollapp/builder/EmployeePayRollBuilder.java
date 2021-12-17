package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeePayrollEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Purpose : To implement Employee PayRoll Builder for Employee Payroll AppApplication.
 *
 * @author : VAISHANAVI R. VISHWAKARMA.
 * @since : 16-12-2021
 */
@Component
public class EmployeePayRollBuilder {
    private ModelMapper modelMapper = new ModelMapper();

    /**
     * @purpose : To build employee builder using model mapper.
     *
     * @param employeeDto : employee dto used to add the fields of dto as per regex pattern.
     * @param employeeEntity : employee entity is used to map data's from Employee Dto.
     * @return : employee entity.
     */
    public EmployeePayrollEntity buildEmployeeEntity(EmployeePayrollDto employeeDto,
                                                     EmployeePayrollEntity employeeEntity) {
        modelMapper.map(employeeDto, employeeEntity);
        return employeeEntity;
    }
}
