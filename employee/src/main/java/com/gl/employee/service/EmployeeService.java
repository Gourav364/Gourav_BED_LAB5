package com.gl.employee.service;

import com.gl.employee.exceptionhandler.EmployeeException;
import com.gl.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> employeeList();

    Employee addEmployee(Employee employee) throws EmployeeException;

    Employee updateEmployee(Employee employee) throws EmployeeException;

    void deleteEmployee(Long id) throws EmployeeException;

    public Employee findEmployee(Long id) throws EmployeeException;
}


