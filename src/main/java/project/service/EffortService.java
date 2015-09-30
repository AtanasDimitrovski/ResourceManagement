package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controller.EffortController;
import project.model.Effort;

@Service
public class EffortService {
	
	
	@Autowired
	private EffortController effortController;
	
	public List<Effort> getAll(){
		return effortController.findAll();
	}
	
	public Effort get(long id){
		return effortController.findOne(id);
	}
	
	public void delete(long id){
		effortController.delete(id);
	}
	
	public boolean create(long employeeId, long projectId, int percent){
		return effortController.create(employeeId, projectId, percent);
	}
	
	public boolean changeEmployee(long effortId, long employeeId){
		return effortController.changeEmployee(employeeId, effortId);
	}
	
	public boolean changeProject(long effortId, long projectId){
		return effortController.changeProject(projectId, effortId);
	}
	
	public boolean changePercent(long id, int percent){
		return effortController.updatePercent(id, percent);
	}
	
}
