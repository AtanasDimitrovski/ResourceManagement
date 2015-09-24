package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controller.EmployeeController;
import project.model.Employee;

@Service
public class EmployeeService {

	
	@Autowired
	private EmployeeController employeeController;
	
	public Employee getEmployee(long id){
		return employeeController.get(id);
	}
	
	public List<Employee> getEmployees(){
		return employeeController.getAll();
	}
	
	public boolean deleteEmployee(long id){
		return employeeController.delete(id);
	}
	
	public boolean changeJobDescription(long id, String jobDescription){
		return employeeController.updateJobDescription(id, jobDescription);
	}
	
	public boolean changeName(long id, String name){
		return employeeController.updateName(id, name);
	}
	
	public boolean changeLastName(long id, String lastName){
		return employeeController.updateLastName(id, lastName);
	}
	
	public boolean createEmployee(String name, String lastName, String jobDescription){
		return employeeController.create(name, lastName, jobDescription);
	}
	
}
