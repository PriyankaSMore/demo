package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	   Employee saveEmployee(Employee employee);
	   
	   List <Employee> getAllEmployees();
	   
	   Employee getEmployeeById(long id);
	   
	   Employee updateEmployee(Employee employee, long id);
	   
	   void deleteEmployee(long id);
	  
	   List<Employee> insertAllEmployees(List<Employee> e);

	   public String deleteEmployees(String rParam);
		String deleteEmployeeByRaw(long id);
	    

	}