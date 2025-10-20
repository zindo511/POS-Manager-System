package com.pos.repository;

import com.pos.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsernameAndPassword(String username, String password);

    Optional<Employee> findByUsername(String username);
}
