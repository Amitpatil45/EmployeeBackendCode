package com.employee.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.Salary;

public interface SalaryRepo extends JpaRepository<Salary, Long> {

}
