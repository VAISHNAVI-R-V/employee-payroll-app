package com.bridgelabz.employeepayrollapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Purpose : To implement Employee Payroll AppApplication.
 *
 * @author : VAISHANAVI R. VISHWAKARMA.
 * @since : 16-12-2021
 */
@SpringBootApplication
public class EmployeePayrollAppApplication {
    /**
	 * @purpose : To add model mapper configuration.
	 *
     * @return : Model mapper.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // main method
    public static void main(String[] args) {
        SpringApplication.run(EmployeePayrollAppApplication.class, args);
    }

}
