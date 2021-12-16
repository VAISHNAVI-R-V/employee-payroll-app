package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.builder.EmployeePayRollBuilder;
import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.entity.EmployeePayrollEntity;
import com.bridgelabz.employeepayrollapp.exception.EmployeeCustomException;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
//@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeePayrollServiceTest {
    @InjectMocks
    private EmployeePayrollService employeePayrollService;
    @Mock
    private EmployeePayrollRepository employeePayrollRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    EmployeePayRollBuilder employeePayRollBuilder;

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
        employeeResponseDto.setName("Dipak");
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
        String actualStringMessage = employeePayrollService.addEmployee(employeeDto);
        assertEquals("Employee Added Successfully!", actualStringMessage);
        verify(employeePayrollRepository, times(1)).save(employeePayrollEntity);

    }

    @Test
    void whenEditEmployeeMethodIsCalled_ShouldUpdateEmployeeDetailsAndReturnSuccessMessage() {
        ArgumentCaptor<EmployeePayrollEntity> argumentCaptor = ArgumentCaptor.forClass(EmployeePayrollEntity.class);

        int id = 1;
        EmployeePayrollDto employeeDto = new EmployeePayrollDto();
        employeeDto.setName("Preksha");
        employeeDto.setGender("Female");
        employeeDto.setDepartment("It");

        EmployeePayrollEntity employeeEntity = new EmployeePayrollEntity();

        when(employeePayrollRepository.findById(id)).thenReturn(Optional.of(employeeEntity));
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setDepartment(employeeDto.getDepartment());

        when(employeePayRollBuilder.buildEmployeeEntity(employeeDto, employeeEntity)).thenReturn(employeeEntity);
        String actualSuccessMessage = employeePayrollService.updateEmployee(id, employeeDto);
        verify(employeePayrollRepository, times(1)).save(argumentCaptor.capture());

        assertEquals("Employee Updated Successfully!!", actualSuccessMessage);
        assertEquals(employeeDto.getName(), employeeEntity.getName());

    }

    @Test
    void whenUpdateEmployeeMethodIsCalled_ShouldUpdateEmployeeDetailsAndReturnSuccessMessage() {
        int id = 1;
        EmployeePayrollDto employeeDto = new EmployeePayrollDto();
        employeeDto.setName("Gauri");
        employeeDto.setGender("F");
        employeeDto.setDepartment("It");
        employeeDto.setSalary("15000");
        employeeDto.setStartDate(new Date());
        employeeDto.setNotes("Welcome to it department");
        employeeDto.setImagePath("a.jpg");

        EmployeePayrollEntity employeeEntity = new EmployeePayrollEntity();
        employeeEntity.setEmployeeId(1);
        employeeEntity.setName("Pooja");
        employeeEntity.setGender("F");
        employeeEntity.setDepartment("It");
        employeeEntity.setSalary("15000");
        employeeEntity.setStartDate(new Date());
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setImagePath("a.jpg");

        when(employeePayrollRepository.findById(id)).thenReturn(Optional.of(employeeEntity));
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setDepartment(employeeDto.getDepartment());

        when(employeePayRollBuilder.buildEmployeeEntity(employeeDto, employeeEntity)).thenReturn(employeeEntity);
        String actualSuccessMessage = employeePayrollService.updateEmployee(id, employeeDto);
        verify(employeePayrollRepository, times(1)).save(employeeEntity);
        assertEquals("Employee Updated Successfully!!", actualSuccessMessage);
        assertEquals(employeeDto.getName(), employeeEntity.getName());
    }

    @Test
    void whenEditEmployeeMethodIsCalled_IfNotFoundId_shouldThrowExceptionMessage() {
        int id = 1;
        EmployeePayrollDto payrollDto = new EmployeePayrollDto();
        payrollDto.setName("Ashwini");
        payrollDto.setGender("Female");
        payrollDto.setDepartment("It");
        payrollDto.setSalary("60000");

        when(employeePayrollRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EmployeeCustomException.class, () -> employeePayrollService.updateEmployee(id, payrollDto));
    }

    @Test
    void givenDeleteEmployeeMethodIsCalledWithAnId_ShouldDeleteTheDataOfThatId() {
        int id = 1;
        EmployeePayrollEntity employee = new EmployeePayrollEntity();
        employee.setEmployeeId(1);
        employee.setName("Mahesh");
        employee.setGender("Male");
        employee.setDepartment("Non-It");

        when(employeePayrollRepository.findById(id)).thenReturn(Optional.of(employee));
        String actualMessage = employeePayrollService.deleteEmployee(id);
        assertEquals("Employee Deleted Successfully!!", actualMessage);
        verify(employeePayrollRepository, times(1)).delete(employee);
    }

    @Test
    void whenDeleteEmployeeMethodIsCalled_IfIdNotFound_shouldThrowExceptionMessage() {
        EmployeePayrollEntity employeeEntity = new EmployeePayrollEntity();
        employeeEntity.setEmployeeId(1);
        employeeEntity.setName("Dipali");
        employeeEntity.setGender("F");
        employeeEntity.setDepartment("It");
        employeeEntity.setSalary("25000");
        employeeEntity.setStartDate(new Date());
        employeeEntity.setNotes("Welcome to it department");
        employeeEntity.setImagePath("a.jpg");
        when(employeePayrollRepository.findById(employeeEntity.getEmployeeId())).thenReturn(Optional.empty());
        assertThrows(EmployeeCustomException.class, () -> employeePayrollService.deleteEmployee(employeeEntity.getEmployeeId()));
    }
}

