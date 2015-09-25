package project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controller.EffortController;
import project.controller.ProjectController;
import project.model.Effort;
import project.model.Employee;
import project.model.Project;

@Service
public class ProjectService {
	
	
	@Autowired
	private ProjectController projectController;
	
	@Autowired
	private EffortController effortController;
	
	public Project getProject(long id){
		return projectController.get(id);
	}
	
	public List<Project> getProjects(){
		return projectController.getAll();
	}
	
	public boolean delete(long id){
		return projectController.delete(id);
	}
	
	public boolean createProject(String name, String description, String status, Date from, Date to){
		return projectController.create(name, description, from, to, status);
	}
	
	public boolean changeName(long id, String name){
		return projectController.updateName(id, name);
	}
	
	public boolean changeDescription(long id, String description){
		return projectController.updateDescription(id, description);
	}
	
	public boolean changeStatus(long id, String status){
		return projectController.updateStatus(id, status);
	}
	
	public boolean changeFromDate(long id, Date from){
		return projectController.changeFromDate(id, from);
	}
	
	public boolean changeToDate(long id, Date to){
		return projectController.changeToDate(id, to);
	}
	
	public List<Employee> getEmployees(long id){
		List<Effort> efforts = effortController.getEmployeesByProjectId(id);
		List<Employee> employees = new ArrayList<Employee>();
		for (Effort effort : efforts) {
			System.out.println(effort.getId());
			employees.add(effort.getEmployee());
		}
		return employees;
	}
	
	public boolean removeEmployee(long projectId, long employeeId){
		Effort effort = effortController.getEffortByProjectAndEmployee(projectId, employeeId);
		if (effort != null)
			return effortController.delete(effort.getId());
		return false;
	}
	
	
	public boolean addEmployee(long projectId, long employeeId, int percent){
		return effortController.create(employeeId, projectId, percent);
	}
	
}
