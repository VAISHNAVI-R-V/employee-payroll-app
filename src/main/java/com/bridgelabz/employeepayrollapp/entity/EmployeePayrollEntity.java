package com.bridgelabz.employeepayrollapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Purpose : To implement Employee Payroll Entity for Employee Payroll AppApplication.
 *
 * @author : VAISHANAVI R. VISHWAKARMA.
 * @since : 16-12-2021
 */
@Data
@Entity
@Table(name = "employee_payroll")
public class EmployeePayrollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;
    private String name;
    private String salary;
    private String gender;
    private Date startDate;
    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "department")
    private List<String> department;
    private String notes;
    private String imagePath;

    /**
     * @purpose : to create list of departments.
     *
     * @return : department data.
     */
    public List<String> getDepartment() {
        return department;
    }

    /**
     * @purpose : to set departments for employees.
     *
     * @param department : department is used to add multiple list of department data.
     */
    public void setDepartment(List<String> department) {
        this.department = department;
    }


}
