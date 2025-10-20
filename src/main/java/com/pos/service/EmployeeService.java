package com.pos.service;

import com.pos.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    boolean addEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(int id);

    Employee login(String username, String password);
}
