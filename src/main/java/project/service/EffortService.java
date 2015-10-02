/*package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controller.EffortController;
import project.model.Effort;

@Service
public class EffortService {
	
	
	@Autowired
	private EffortController effortController;
	
	*//**
	 * Gets all efforts (employee id, project id)
	 * @return list of efforts
	 *//*
	public List<Effort> getAll(){
		return effortController.findAll();
	}
	
	*//**
	 * Gets effort with effort id
	 * @param id effort id
	 * @return
	 *//*
	public Effort get(long id){
		return effortController.findOne(id);
	}
	
	*//**
	 * Deletes effort with effort id
	 * @param id effort id
	 *//*
	public void delete(long id){
		effortController.delete(id);
	}
	
	*//**
	 * Sets employee with employee id to work on project with project id. Creates effort
	 * @param employeeId employee id
	 * @param projectId project id
	 * @return True if effort created, false otherwise
	 *//*
	public boolean create(long employeeId, long projectId){
		return effortController.create(employeeId, projectId);
	}
	
	*//**
	 * Changes employee working on project.
	 * @param effortId effort id
	 * @param employeeId employee id
	 * @return True if changed, false  otherwise
	 *//*
	public boolean changeEmployee(long effortId, long employeeId){
		return effortController.changeEmployee(employeeId, effortId);
	}
	
	*//**
	 * Changes project that the employee is working on
	 * @param effortId effort id
	 * @param projectId project id
	 * @return True if changed, false otherwise
	 *//*
	public boolean changeProject(long effortId, long projectId){
		return effortController.changeProject(projectId, effortId);
	}

	
}
*/