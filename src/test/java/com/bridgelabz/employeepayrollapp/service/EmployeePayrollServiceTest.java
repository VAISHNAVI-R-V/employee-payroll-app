package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeePayrollEntity;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollServiceTest {
    @InjectMocks
    private EmployeePayrollService employeePayrollService;
    @Mock
    private EmployeePayrollRepository employeePayrollRepository;
    @Mock
    ModelMapper modelMapper;

    @Test
    void whenGetAllEmployeeMethodIsCalled_ShouldReturnListOfEmployeeResponseDto() {
        List<EmployeePayrollEntity> employeeList = new ArrayList<>();
        EmployeePayrollEntity employee = new EmployeePayrollEntity();
        employee.setEmployeeId(1);
        employee.setName("Shalini");
        employee.setGender("Female");
        employee.setDepartment("Cse");
        employeeList.add(employee);
        EmployeePayrollEntity employee2 = new EmployeePayrollEntity();
        employee2.setEmployeeId(2);
        employee2.setName("Suresh");
        employee2.setGender("Male");
        employee2.setDepartment("It");
        employeeList.add(employee2);

        List<EmployeePayrollDto> employeeResponseList = new ArrayList<>();
        EmployeePayrollDto employeeResponseDto = new EmployeePayrollDto();
        employeeResponseDto.setName("Dipika");
        employeeResponseDto.setGender("Male");
        employeeResponseDto.setDepartment("Cse");
        employeeResponseList.add(employeeResponseDto);
        EmployeePayrollDto employeeResponseDto2 = new EmployeePayrollDto();
        employeeResponseDto2.setName("Shankar");
        employeeResponseDto2.setGender("Male");
        employeeResponseDto2.setDepartment("It");
        employeeResponseList.add(employeeResponseDto2);


        when(employeePayrollRepository.findAll()).thenReturn(employeeList);
        when(modelMapper.map(employeeList.get(0), EmployeePayrollDto.class)).thenReturn(employeeResponseDto);
        when(modelMapper.map(employeeList.get(1), EmployeePayrollDto.class)).thenReturn(employeeResponseDto2);
        List<EmployeePayrollDto> actualListOfAtm = employeePayrollService.employeesList();
        assertEquals(2, actualListOfAtm.size());
        assertEquals(employeeResponseList, actualListOfAtm);
    }
    @Test
    void whenAddAtmCalled_shouldAddAtmAndGenerateSuccessMessage() {

        EmployeePayrollEntity employeePayrollEntity = new EmployeePayrollEntity();
        employeePayrollEntity.setEmployeeId(1);
        employeePayrollEntity.setName("Roopa");
        employeePayrollEntity.setGender("Female");
        employeePayrollEntity.setDepartment("It");

        EmployeePayrollDto employeeDto = new EmployeePayrollDto();
        employeeDto.setName("Shakti");
        employeeDto.setGender("Female");
        employeeDto.setDepartment("It");

        when(modelMapper.map(employeeDto, EmployeePayrollEntity.class)).thenReturn(employeePayrollEntity);
        String  actualStringMessage = employeePayrollService.addEmployee(employeeDto);
        assertEquals("Employee Added Successfully!", actualStringMessage);
        verify(employeePayrollRepository,times(1)).save(employeePayrollEntity);

    }
}

