package com.example.Gifts.controller;

import com.example.Gifts.model.Employee;
import com.example.Gifts.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping("/")
    public List<Employee> getAllEmployee() {
        return EmployeeService.employeeDatabase;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return EmployeeService.getEmployee(id);
    }
    @PostMapping("/addEmployee")
    public List<Employee> addEmployee(@RequestBody Employee employee) {
        return EmployeeService.addEmployee(employee);
    }

    @PutMapping("/updateEmployee")
    public List<Employee> updateEmployee(@RequestBody Employee employee) {
        return EmployeeService.updateEmployee(employee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id) {
        return EmployeeService.deleteEmployee(id);
    }}
