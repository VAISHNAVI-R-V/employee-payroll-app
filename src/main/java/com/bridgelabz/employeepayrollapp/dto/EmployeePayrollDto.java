package com.bridgelabz.employeepayrollapp.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

/**
 * Purpose : To implement Employee Payroll Dto for Employee Payroll AppApplication.
 *
 * @author : VAISHANAVI R. VISHWAKARMA.
 * @since : 16-12-2021
 */
@Data
public class EmployeePayrollDto {
    @NotNull
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = "Name should be start with capital latter " +
            "& should contain more then 3 character ")
    private String name;

    @NotNull
    @Pattern(regexp = "^[0-9]{5,}$", message = "salary should be more than 10000")
    private String salary;

    @NotNull
    @Pattern(regexp = "^(?:m|M|male|Male|f|F|female|Female|o|O|Other|other)$",
            message = "Please type gender F - female " +
            "M - male , O - others/Transgender")
    private String gender;

    @NotNull
    @CreationTimestamp
    private Date startDate;

    @NotNull(message = "Department should contain more then 2 character ")
//    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Department should contain more then 2 character ")
    private List<String> department;

    @NotNull(message = "Notes should contain more then 3 character ")
//    @Pattern(regexp = "^[a-zA-Z]{1,}$", message = "Notes should contain more then 3 character ")
    private String notes;

    @NotNull
    private String imagePath;

//    /**
//     * @purpose : to create list of departments.
//     *
//     * @return : department data.
//     */
//    public List<String> getDepartment() {
//        return department;
//    }
//
//    /**
//     * @purpose : to set departments for employees.
//     *
//     * @param department : department is used to add multiple list of department data.
//     */
//    public void setDepartment(List<String> department) {
//        this.department = department;
//    }
}
