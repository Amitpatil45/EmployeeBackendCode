package com.employee.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class MyService {

	 private final DataSource dataSource ;
	   @Autowired
	    public MyService(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }
}
