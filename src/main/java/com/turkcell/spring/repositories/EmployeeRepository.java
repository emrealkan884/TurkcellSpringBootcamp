package com.turkcell.spring.repositories;

import com.turkcell.spring.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Short> {
}
