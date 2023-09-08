package com.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Salary {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long salaryId;
	private int noOfDays;
	private double salaryPerDay;
	private double totalSalary;
	
	@ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

	
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Salary(Long salaryId, int noOfDays, double salaryPerDay, double totalSalary) {
		super();
		this.salaryId = salaryId;
		this.noOfDays = noOfDays;
		this.salaryPerDay = salaryPerDay;
		this.totalSalary = totalSalary;
	}
	public Long getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(Long salaryId) {
		this.salaryId = salaryId;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public double getSalaryPerDay() {
		return salaryPerDay;
	}
	public void setSalaryPerDay(double salaryPerDay) {
		this.salaryPerDay = salaryPerDay;
	}
	public double getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}
	
	
	@PrePersist
	public void calculateTotalSalary() {
		this.totalSalary=salaryPerDay*noOfDays;
	}
	
	

}
