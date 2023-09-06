package com.employee.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
