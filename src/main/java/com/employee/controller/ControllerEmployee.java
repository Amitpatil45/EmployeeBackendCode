package com.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Repo.EmployeeRepo;
import com.employee.Repo.SalaryRepo;
import com.employee.model.Employee;
import com.employee.model.Salary;

@RestController
@CrossOrigin("*")
public class ControllerEmployee {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private SalaryRepo  salaryRepo;


	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	// create employee
	@PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}

	// employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = null;
		try {
			employee = employeeRepo.findById(id)
					.orElseThrow(() -> new Exception("Employee not exists with this Id :" + id));
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return ResponseEntity.ok(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee1)
			throws Exception {
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new Exception("Employee not exists with this Id :" + id));
		employee.setFirstname(employee1.getFirstname());
		employee.setLastname(employee1.getLastname());
		employee.setEmail(employee1.getEmail());
		employee.setPhone(employee1.getPhone());
		employee.setId(employee1.getId());

		Employee updatedemployee = employeeRepo.save(employee);
		return ResponseEntity.ok(updatedemployee);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleateEmployee(@PathVariable Long id) throws Exception {
		Employee employee1 = employeeRepo.findById(id)
				.orElseThrow(() -> new Exception("Employee not exists with this Id :" + id));
		employeeRepo.delete(employee1);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
	
	@PostMapping("employees/salary")
	public Salary createSalary(@RequestBody Salary salary) {
		salary.calculateTotalSalary();
		return salaryRepo.save(salary);
	}
	
	@GetMapping("/employees/salary")
	public List<Salary> getAllSalary() {
		return salaryRepo.findAll();
	}
	
	@GetMapping("/employees/salary/{id}")
	public ResponseEntity<Salary> getSalaryById(@PathVariable Long id) {
		Salary salary = null;
		try {
			salary = salaryRepo.findById(id)
					.orElseThrow(() -> new Exception("Employee not exists with this Id :" + id));
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return ResponseEntity.ok(salary);
	}
	
	
	@PutMapping("/employees/salary/{id}")
	public ResponseEntity<Salary> updateSalary(@PathVariable Long id, @RequestBody Salary salary1)
			throws Exception {
		Salary salary = salaryRepo.findById(id)
				.orElseThrow(() -> new Exception("Salary not exists with this Id :" + id));
		salary.setNoOfDays(salary1.getNoOfDays());
		salary.setSalaryPerDay(salary1.getSalaryPerDay());
		
		salary.calculateTotalSalary();

		Salary updatedsalary = salaryRepo.save(salary);
		return ResponseEntity.ok(updatedsalary);
	}
	
	@DeleteMapping("/employees/salary/{id}")
	public ResponseEntity<Map<String, Boolean>> deleateSalary(@PathVariable Long id) throws Exception {
		Salary salary = salaryRepo.findById(id)
				.orElseThrow(() -> new Exception("Salary not exists with this Id :" + id));
		salaryRepo.delete(salary);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
