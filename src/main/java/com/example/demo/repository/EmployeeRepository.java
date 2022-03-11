package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee ,Long> {
	
	@Query(value ="Select * from employees where id =?1",nativeQuery=true)
	public Employee getEmployeeByIdRaw (long employeeId);
	
	@Transactional
	@Modifying
	@Query(value="delete from employees where id=?1",nativeQuery=true)
	public int deleEmpByRaw(long id);


}
