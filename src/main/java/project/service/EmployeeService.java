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
	
	public boolean createEmployee(Employee employee){
		return employeeController.create(employee);
	}
	
	public boolean editEmployee(long id, String name, String lastName, String jobDescription){
		return employeeController.edit(id, name, lastName, jobDescription);
	}
	
	public List<Project> getProjects(long id){
		List<Effort> efforts = effortController.getProjectsByEmployeeId(id);
		List<Project> projects = new ArrayList<Project>();
		for (Effort effort : efforts) {
			projects.add(effort.getProject());
		}
		return projects;
	}
	
	public boolean removeProject(long employeeId, long projectId){
		Effort effort = effortController.getEffortByProjectAndEmployee(projectId, employeeId);
		if (effort!=null)
			return effortController.delete(effort.getId());
		return false;
	} 
	
	
	public boolean addProject(long employeeId, long projectId, int percent){
		return effortController.create(employeeId, projectId, percent);
	}
	
}
