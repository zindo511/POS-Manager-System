package com.pos.service.impl;

import com.pos.model.Employee;
import com.pos.repository.EmployeeRepository;
import com.pos.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null || employee.getFullName() == null || employee.getFullName().isEmpty()) {
            return false;
        }

        // Check username unique
        boolean exits = employeeRepository.findByUsername(employee.getUsername()).isPresent();
        if (exits) {
            return false;
        }

        employeeRepository.save(employee);
        return true;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            Employee employee1 = existingEmployee.get();
            employee1.setUsername(employee.getUsername());
            employee1.setPassword(employee.getPassword());
            employee1.setFullName(employee.getFullName());
            employee1.setRole(employee.getRole());
            employee1.setPhone(employee.getPhone());

            employeeRepository.save(employee1);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {
        if (!employeeRepository.existsById(id)) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }

    @Override
    public Employee login(String username, String password) {
        return employeeRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
