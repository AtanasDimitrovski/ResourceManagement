package project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controller.EffortController;
import project.controller.EmployeeController;
import project.model.Effort;
import project.model.Employee;
import project.model.Project;

@Service
public class EmployeeService {

	
	@Autowired
	private EmployeeController employeeController;
	
	@Autowired
	private EffortController effortController;
	
	
	public Employee getEmployee(long id){
		return employeeController.findOne(id);
	}
	
	public List<Employee> getEmployees(){
		return employeeController.findAll();
	}
	
	public void deleteEmployee(long id){
		Employee employee = employeeController.findOne(id);
		short valid = 0;
		employee.setValid(valid);
		employeeController.saveAndFlush(employee);
	}
		
	public Employee createEmployee(Employee employee){
		short a = 1;
		employee.setValid(a);
		return employeeController.save(employee);
	}
	
	public Employee editEmployee(long id, String name, String lastName, String jobDescription){
		return employeeController.edit(id, name, lastName, jobDescription);
	}
	
	public List<Project> getProjects(long id){
		List<Effort> efforts = effortController.getProjectsByEmployeeId(id);
		List<Project> projects = new ArrayList<Project>();
		for (Effort effort : efforts) {
			if (effort.getProject().getValid() == 1)
				projects.add(effort.getProject());
		}
		return projects;
	}
	
	public void removeProject(long employeeId, long projectId){
		Effort effort = effortController.getEffortByProjectAndEmployee(projectId, employeeId);
		effortController.delete(effort);
	} 
	
	
	public boolean addProject(long employeeId, long projectId, int percent){
		return effortController.create(employeeId, projectId, percent);
	}
	
}
