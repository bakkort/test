package com.example.Gifts.service;

import com.example.Gifts.exception.employee.EmployeeAlreadyExistException;
import com.example.Gifts.exception.employee.EmployeeNotFoundException;
import com.example.Gifts.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    public static List<Employee> employeeDatabase = new ArrayList<>();

    public static List<Employee> addEmployee(Employee employee) throws EmployeeAlreadyExistException {
        if (getEmployee(employee.getId()) != null) {
            throw new EmployeeAlreadyExistException("Employee already exists in the database");
        }

        employeeDatabase.add(employee);

        return employeeDatabase;
    }

    public static Employee getEmployee(int id) {
        return employeeDatabase.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<Employee> deleteEmployee(int id) throws EmployeeNotFoundException {
        Employee employee = getEmployee(id);

        if (employee != null) {
            employeeDatabase.remove(employee);

            return employeeDatabase;
        }

        throw new EmployeeNotFoundException("Employee does not exist in the database");
    }

    public static List<Employee> updateEmployee(Employee employee) throws EmployeeNotFoundException {
        Employee currentEmployee = getEmployee(employee.getId());

        if (currentEmployee != null) {
            employeeDatabase.set(employeeDatabase.indexOf(currentEmployee), employee);

            return employeeDatabase;
        }

        throw new EmployeeNotFoundException("Employee does not exist in the database");
    }
}
