package com.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Repo.EmployeeRepo;
import com.employee.model.Employee;

@RestController

public class ControllerEmployee {

	@Autowired
	private EmployeeRepo employeeRepo;

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
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee)
			throws Exception {
		Employee employee1 = employeeRepo.findById(id)
				.orElseThrow(() -> new Exception("Employee not exists with this Id :" + id));
		employee1.setFirstname(employee.getFirstname());
		employee1.setLastname(employee.getLastname());
		employee1.setEmail(employee.getEmail());
		employee1.setPhone(employee.getPhone());

		Employee updatedemployee = employeeRepo.save(employee1);
		return ResponseEntity.ok(updatedemployee);

	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleateEmployee(@PathVariable Long id) throws Exception {
		Employee employee1 = employeeRepo.findById(id)
				.orElseThrow(() -> new Exception("Employee not exists with this Id :" + id));
		employeeRepo.delete(employee1);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}

}
