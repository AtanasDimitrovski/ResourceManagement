package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import project.dao.EmployeeDao;
import project.model.Employee;
import project.model.Project;

@Controller
public class EmployeeController {
	
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	public boolean create(Employee employee){
		
		try {
			short a = 1;
			employee.setValid(a);
			employeeDao.save(employee);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}		
	}
	
	public boolean updateName(long id, String name){
		
		try {
			
			Employee employee = employeeDao.getOne(id);
			employee.setName(name);
			employeeDao.saveAndFlush(employee);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean updateLastName(long id, String lastName){
		try {
			
			Employee employee = employeeDao.getOne(id);
			employee.setLastName(lastName);
			employeeDao.saveAndFlush(employee);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean delete(long id){
		try {
			
			Employee employee = employeeDao.getOne(id);
			short valid = 0;
			employee.setValid(valid);
			employeeDao.saveAndFlush(employee);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean updateJobDescription(long id, String jobDescription){
		try {
			
			Employee employee = employeeDao.getOne(id);
			employee.setJobDescription(jobDescription);
			employeeDao.saveAndFlush(employee);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public Employee get(long id){
		
		try {
			return employeeDao.getOne(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public List<Employee> getAll(){
		try {
			short valid = 1;
			return employeeDao.findByValid(valid);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean edit(long id, String name, String lastName, String jobDescription) {
		// TODO Auto-generated method stub
		try {
			Employee employee = employeeDao.getOne(id);
			if (name != null)
				employee.setName(name);
			if (lastName != null)
				employee.setLastName(lastName);
			if (jobDescription != null)
				employee.setJobDescription(jobDescription);
			employeeDao.saveAndFlush(employee);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}	
}
