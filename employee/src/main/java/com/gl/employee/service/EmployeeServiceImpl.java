package com.gl.employee.service;

import com.gl.employee.exceptionhandler.EmployeeException;
import com.gl.employee.entity.Employee;
import com.gl.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> employeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) throws EmployeeException {

        if (StringUtils.isEmpty(employee.getFirstName())) {
            throw new EmployeeException("First name  should not be null", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(employee.getLastName())) {
            throw new EmployeeException("Last name  should not be null", HttpStatus.BAD_REQUEST);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeException {
        if (Objects.isNull(employee.getId())) {
            throw new EmployeeException("Id is mandatory parameter", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(employee.getFirstName())) {
            throw new EmployeeException("First name is mandatory parameter", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(employee.getLastName())) {
            throw new EmployeeException("Last name  is mandatory parameter", HttpStatus.BAD_REQUEST);
        }

        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employee.getId());
        if (!existingEmployeeOptional.isPresent()) {
            throw new EmployeeException("Employee not present in system", HttpStatus.BAD_REQUEST);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) throws EmployeeException {
        if (Objects.isNull(id)) {
            throw new EmployeeException("Id is mandatory parameter", HttpStatus.BAD_REQUEST);
        }

        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);
        if (!existingEmployeeOptional.isPresent()) {
            throw new EmployeeException("Employee not present in system to delete", HttpStatus.BAD_REQUEST);
        }

        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findEmployee(Long id) throws EmployeeException {
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);
        if (!existingEmployeeOptional.isPresent()) {
            throw new EmployeeException("Employee not present in system.", HttpStatus.BAD_REQUEST);
        }

        return existingEmployeeOptional.get();
    }
}
