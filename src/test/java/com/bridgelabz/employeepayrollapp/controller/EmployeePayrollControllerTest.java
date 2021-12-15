package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollControllerTest {
    @InjectMocks
    private EmployeePayrollController employeePayrollController;

    @Mock
    private EmployeePayrollService employeeService;

    @Test
    void given2EmployeeDto_whenCalledGetAllMethod_shouldReturnListOfEmployeeDto() {
        List<EmployeePayrollDto> employeeList = new ArrayList<>();
        EmployeePayrollDto employeePayrollDto = new EmployeePayrollDto();
        employeePayrollDto.setName("Vaishnavi");
        employeePayrollDto.setGender("F");
        employeePayrollDto.setDepartment("It");
        employeePayrollDto.setSalary("150000");
        employeePayrollDto.setStartDate(new Date());
        employeePayrollDto.setNotes("Welcome to It feild");
        employeePayrollDto.setImagePath("v.jpg");
        employeeList.add(employeePayrollDto);

        EmployeePayrollDto employeePayrollDto1 = new EmployeePayrollDto();
        employeePayrollDto1.setName("Siva");
        employeePayrollDto1.setGender("M");
        employeePayrollDto1.setDepartment("It");
        employeePayrollDto1.setSalary("15000");
        employeePayrollDto1.setStartDate(new Date());
        employeePayrollDto1.setImagePath("b.jpg");
        employeePayrollDto1.setNotes("Welcome to it department");
        employeeList.add(employeePayrollDto1);

        when(employeeService.employeesList()).thenReturn(employeeList);
        List<EmployeePayrollDto> actualResponse = employeePayrollController.employeesList();
        for (int i = 0; i < actualResponse.size(); i++) {
            assertEquals(employeeList.get(i).getName(), actualResponse.get(i).getName());
            assertEquals(employeeList.get(i).getDepartment(), actualResponse.get(i).getDepartment());
            assertEquals(employeeList.get(i).getGender(), actualResponse.get(i).getGender());
            assertEquals(employeeList.get(i).getNotes(), actualResponse.get(i).getNotes());
            assertEquals(employeeList.get(i).getSalary(), actualResponse.get(i).getSalary());
            assertEquals(employeeList.get(i).getImagePath(), actualResponse.get(i).getImagePath());
        }
    }

}
