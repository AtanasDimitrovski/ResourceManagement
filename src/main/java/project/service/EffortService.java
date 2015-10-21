package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controller.EffortController;
import project.controller.EffortInformationController;
import project.controller.EmployeeController;
import project.controller.ProjectController;
import project.model.Effort;
import project.model.Employee;
import project.model.Project;

@Service
public class EffortService {
	
	
	@Autowired
	private EffortController effortController;
	
	@Autowired
	private EmployeeController employeeController;
	
	@Autowired
	private ProjectController projectController;
	
	@Autowired
	private EffortInformationController effortInformationController;
	
	/**
	 * Gets all efforts (employee id, project id)
	 * @return list of efforts
	 */
	public List<Effort> getAll(){
		return effortController.findAll();
	}
	
	/**
	 * Gets effort with effort id
	 * @param id effort id
	 * @return
	 */
	public Effort get(long id){
		return effortController.findOne(id);
	}
	
	/**
	 * Deletes effort with effort id
	 * @param id effort id
	 */
	public void delete(long id){
		effortController.delete(id);
	}
	
	/**
	 * Sets employee with employee id to work on project with project id. Creates effort
	 * @param employeeId employee id
	 * @param projectId project id
	 * @return True if effort created, false otherwise
	 */
	public Effort create(long employeeId, long projectId){
		Effort effort = new Effort();
		Employee employee = employeeController.findOne(employeeId);
		if (employee == null) return null;
		effort.setEmployee(employee);
		Project project = projectController.findOne(projectId);
		if (project == null) return null;
		effort.setProject(project);
		return effortController.save(effort);
	}
	
	public void deleteEffortInformation(long id){
		effortInformationController.delete(id);
	}
	
/*	*//**
	 * Changes employee working on project.
	 * @param effortId effort id
	 * @param employeeId employee id
	 * @return True if changed, false  otherwise
	 *//*
	public boolean changeEmployee(long effortId, long employeeId){
		return effortController.changeEmployee(employeeId, effortId);
	}*/
	
	
/*	*//**
	 * Changes project that the employee is working on
	 * @param effortId effort id
	 * @param projectId project id
	 * @return True if changed, false otherwise
	 *//*
	public boolean changeProject(long effortId, long projectId){
		return effortController.changeProject(projectId, effortId);
	}*/

	
}
