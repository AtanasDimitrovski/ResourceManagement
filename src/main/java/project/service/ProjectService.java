package project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controller.EffortController;
import project.controller.EffortInformationController;
import project.controller.ProjectController;
import project.model.Effort;
import project.model.EffortInformation;
import project.model.Employee;
import project.model.Project;

@Service
public class ProjectService {
	
	
	@Autowired
	private ProjectController projectController;
	
	@Autowired
	private EffortController effortController;
	
	@Autowired
	private EffortInformationController effortInformationController;
	
	@Autowired
	private EffortService effortService;
	
	/**
	 * Gets project with project id 
	 * @param id project id
	 * @return Project
	 */
	public Project getProject(long id){
		return projectController.findOne(id);
	}
	
	/**
	 * Gets all projects
	 * @return list od all projects
	 */
	public List<Project> getProjects(){
		return projectController.findAll();
	}
	
	/**
	 * Deletes project with project id
	 * @param id project id
	 */
	public void delete(long id){
		Project project = projectController.findOne(id);
		short valid = 0;
		project.setValid(valid);
		projectController.saveAndFlush(project);
	}
	
	/**
	 * Creates new Project
	 * @param project project 
	 * @return created project
	 */
	public Project createProject(Project project){
		short valid = 1;
		project.setValid(valid);
		return projectController.save(project);
	}
	
	/**
	 * Edits project information
	 * @param id project id
	 * @param name project name
	 * @param description project description
	 * @param fromDate project from date
	 * @param toDate project to date
	 * @param status project status
	 * @param manager project manager
	 * @return edited project
	 */
	public Project editProject(long id, String name, String description, Date fromDate, Date toDate, String status, Employee manager){
		return projectController.edit(id, name, description, fromDate, toDate, status, manager);
	}
	
	/**
	 * Gets all employees that are working on project with id
	 * @param id project id
	 * @return list of all employees working on the project
	 */
	public List<Employee> getEmployees(long id){
		List<Effort> efforts = effortController.getEmployeesByProjectId(id);
		List<Employee> employees = new ArrayList<Employee>();
		for (Effort effort : efforts) {
			if (effort.getEmployee().getValid() == 1)
				employees.add(effort.getEmployee());
		}
		return employees;
	}
	
	/**
	 * Removes employee with employeeId from working on project with projectId
	 * @param projectId project id
	 * @param employeeId employee id
	 */
	public void removeEmployee(long projectId, long employeeId){
		Effort effort = effortController.getEffortByProjectAndEmployee(projectId, employeeId);
		effortController.delete(effort);
	}
	
	/**
	 * Adds employee with employeeId to work on project with projectId
	 * @param projectId project id
	 * @param employeeId employee id
	 * @param percent
	 * @return true if employee was added, false otherwise
	 */
	public boolean addEmployee(long projectId, long employeeId, int percent){
		return effortController.create(employeeId, projectId, percent);
	}

	/**
	 * Gets Manager for given project with id
	 * @param id project id
	 * @return project Manager
	 */
	public Employee getManager(long id) {
		return projectController.getManager(id);
	}
	
	/**
	 * Gets all effort information for emloyee with employee id about project with id
	 * @param id project id
	 * @param employeeId employee id
	 * @return list of effort informations
	 */
	public List<EffortInformation> getEffortInformation(long id, long employeeId){
		Effort effort = effortController.getEffortByProjectAndEmployee(id, employeeId);
		return effortInformationController.getEffortInformationByEffortId(effort.getId());
	}

	/**
	 * Adds Effort information for employee with employeeId about project with id.
	 * @param id project id
	 * @param employeeId employee id
	 * @param effortInformation Effort information
	 */
	public void addEffortInformation(long id, long employeeId,
			EffortInformation effortInformation) {
		 Effort effort = effortController.getEffortByProjectAndEmployee(id, employeeId);
		 if (effort == null){
			 if(effortService.create(employeeId, id, 20))
				 effort = effortController.getEffortByProjectAndEmployee(id, employeeId);
		 }
		 if (effort != null){
			 effortInformation.setEffort(effort);
			 effortInformationController.save(effortInformation);
		 }
	}
	
}
