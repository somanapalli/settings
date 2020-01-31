package com.spring.dao;

import java.util.List;

import com.spring.model.Employee;



public interface EmployeeDAO {
	
	//create
	public void save(Employee employee);
	//read
	public Employee getById(int id);
	//update
	public void update(Employee employee);
	//delete
	public void deleteById(int id);
	//getAll
	public List<Employee> getAll();

}
