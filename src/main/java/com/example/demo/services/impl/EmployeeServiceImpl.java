package com.example.demo.services.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = Optional.ofNullable(employeeRepository.getEmployeeByIdRaw(id));		
		if(employee.isPresent()){
			return employee.get();
			
		}else {
			throw new ResourceNotFoundException("Employee", "Id",id);
		}
		

	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		//We need to check whether employee with given id is exist in DB or not 
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow( () -> 
			new	ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existing employee to DB 
		employeeRepository.save(existingEmployee);
		
		
		return existingEmployee;
	}

    @Override
	public String deleteEmployees(String rParam) {
		List<Long> longIds = Stream.of(rParam.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
                String tmp105="";
		for(int i=0;i< longIds.size();i++ )
		{
			if(employeeRepository.existsById(longIds.get(i))){
				employeeRepository.deleteById(longIds.get(i));
				tmp105=tmp105+" deleted successfully: "+longIds.get(i);
			}else{
				tmp105=tmp105+" not found: "+longIds.get(i);
			}
		}
		
		return tmp105;
	}
	@Override
	public List<Employee> insertAllEmployees(List<Employee> e) {
		return employeeRepository.saveAll(e);
	}


	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String deleteEmployeeByRaw(long id) {
		String msg=""; 
		if(employeeRepository.existsById(id)) {
		 employeeRepository.deleEmpByRaw(id);

		 msg="Record Deleted";
		 }
		 else {
			 msg="Not Found Record";
		 }
		return msg;
	}


	}


	

