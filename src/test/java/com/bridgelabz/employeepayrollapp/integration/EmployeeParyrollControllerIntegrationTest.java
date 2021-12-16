package com.bridgelabz.employeepayrollapp.integration;

import com.bridgelabz.employeepayrollapp.controller.EmployeePayrollController;
import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollapp.service.EmployeePayrollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeePayrollController.class)
public class EmployeeParyrollControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeePayrollService employeePayrollService;

    @Test
    void getAllEmployeeTest() throws Exception {
        when(employeePayrollService.employeesList()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/employees-list")).andExpect(status().isOk());
    }

    @Test
    void addEmployeeTest() throws Exception {
        when(employeePayrollService.addEmployee(any())).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/add")
                .content("{\"name\":\"Roopa\",\"salary\":10050,\"gender\":\"f\",\"startDate\":\"2021-12-15\"" + "," +
                        "\"department\":\"It\",\"notes\":\"Welcome\",\"imagePath\":\"jl.jpg\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void updateEmployeeTest() throws Exception {
        EmployeePayrollDto employeeDto = new EmployeePayrollDto();
        employeeDto.setName("Sheela");
        employeeDto.setGender("Female");
        employeeDto.setSalary("45000");
        employeeDto.setDepartment("It");
        employeeDto.setNotes("Welcome to home");
        employeeDto.setImagePath("a.jpg");
        employeeDto.setStartDate(new Date());
        int id = 1;
        when(employeePayrollService.updateEmployee(id, employeeDto)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update/1")
                .content("{\"name\":\"Shivani\",\"salary\":\"60000\",\"gender\":\"F\"," +
                        "\"startDate\":\"2021-12-16\",\"department\":\"It\",\"notes\":\"Welcome\"," +
                        "\"imagePath\":\"n.jpg\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployeeTest() throws Exception {
        when(employeePayrollService.deleteEmployee(1)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/delete/1")
                .content("{\"name\":\"Shivani\",\"salary\":\"60000\",\"gender\":\"F\"," +
                        "\"startDate\":\"2021-12-16\",\"department\":\"It\",\"notes\":\"Welcome\"," +
                        "\"imagePath\":\"n.jpg\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}