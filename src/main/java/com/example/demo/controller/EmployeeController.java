package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.services.EmployeeService;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {

	 @Autowired
	private EmployeeService employeeService;

	public EmployeeController( EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
//	build create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
		
	}
	
	//build get all employees REST API
	@GetMapping()
	public List<Employee> getAllEmployees() {
		return  employeeService.getAllEmployees ();
	}
	
	// build get employee by id REST API
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
		
	}
	
	//build update employee by REST API
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") long id ,
			                                           @RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
	}
	
	//build delete employee by REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		 String tmp53=employeeService.deleteEmployeeByRaw(id);

		 return new ResponseEntity<String>("54: "+tmp53,HttpStatus.OK);
	}
	
	
	//build create multiple employee by REST API
	@PostMapping("/ab")
	public List<Employee> insertAllEmployees(@RequestBody List<Employee> e){
	return  employeeService.insertAllEmployees(e);
		
//		return  ResponseEntity.saveAllEmployee (e);
	}
}
