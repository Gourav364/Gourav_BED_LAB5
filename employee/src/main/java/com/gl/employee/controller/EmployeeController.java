package com.gl.employee.controller;

import com.gl.employee.exceptionhandler.EmployeeException;
import com.gl.employee.entity.Employee;
import com.gl.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String employees(Model model) {
        model.addAttribute("employees", employeeService.employeeList());
        return "employee/employees";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee/create_employee";
    }

    @GetMapping("/updateEmployee")
    public String updateEmployee(Model model, @RequestParam("employeeId") Long employeeId) throws EmployeeException {
        model.addAttribute("employee", employeeService.findEmployee(employeeId));
        return "employee/update_employee";
    }

    @GetMapping("/redirectionUrl")
    public String error(@ModelAttribute("flashAttribute") Object flashAttribute, Model model) {
        model.addAttribute("redirectionAttribute", flashAttribute);
        return "employee/error";
    }

    @PostMapping("/add")
    public String insert(@ModelAttribute Employee employee) throws EmployeeException {

        employeeService.addEmployee(employee);
        return "redirect:/employees/list";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("employee") Employee employee) throws EmployeeException {
        employeeService.updateEmployee(employee);
        return "redirect:/employees/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") Long employeeId) throws EmployeeException {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employees/list";
    }
}
