package com.bridgelabz.employeepayrollapp.builder;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeePayrollEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeePayRollBuilderTest {
    @InjectMocks
    private EmployeePayRollBuilder employeePayRollBuilder;
    private ModelMapper modelMapper;

    @Test
    void givenEmployeeDto_whenNeedToConvertToEmployeeEntityUsingModelMapper_shouldReturnEmployeeEntity() {
        EmployeePayrollDto employeeDto = new EmployeePayrollDto();
        employeeDto.setName("Shikhaa");
        employeeDto.setGender("Female");
        employeeDto.setSalary("35000");
        employeeDto.setDepartment("It");
        employeeDto.setNotes("Welcome to company");
        employeeDto.setImagePath("k.jpg");
        employeeDto.setStartDate(new Date());

        EmployeePayrollEntity employeeEntity = new EmployeePayrollEntity();
        employeeEntity = employeePayRollBuilder.buildEmployeeEntity(employeeDto, employeeEntity);
        assertEquals(employeeDto.getName(), employeeEntity.getName());
    }
}
