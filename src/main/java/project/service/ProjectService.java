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
		return projectController.findOne(id);
	}
	
	public List<Project> getProjects(){
		return projectController.findAll();
	}
	
	public void delete(long id){
		Project project = projectController.findOne(id);
		short valid = 0;
		project.setValid(valid);
		projectController.saveAndFlush(project);
	}
	
	public Project createProject(Project project){
		short valid = 1;
		project.setValid(valid);
		return projectController.save(project);
	}
	
	public Project editProject(long id, String name, String description, Date fromDate, Date toDate, String status){
		return projectController.edit(id, name, description, fromDate, toDate, status);
	}
	
	public List<Employee> getEmployees(long id){
		List<Effort> efforts = effortController.getEmployeesByProjectId(id);
		List<Employee> employees = new ArrayList<Employee>();
		for (Effort effort : efforts) {
			if (effort.getEmployee().getValid() == 1)
				employees.add(effort.getEmployee());
		}
		return employees;
	}
	
	public void removeEmployee(long projectId, long employeeId){
		Effort effort = effortController.getEffortByProjectAndEmployee(projectId, employeeId);
		effortController.delete(effort);
	}
	
	
	public boolean addEmployee(long projectId, long employeeId, int percent){
		return effortController.create(employeeId, projectId, percent);
	}
	
}
