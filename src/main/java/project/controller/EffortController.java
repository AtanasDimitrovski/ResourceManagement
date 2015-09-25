package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import project.dao.EffortDao;
import project.dao.EmployeeDao;
import project.dao.ProjectDao;
import project.model.Effort;

@Controller
public class EffortController {
	
	@Autowired
	private EffortDao effortDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired 
	private EmployeeDao employeeDao;

	
	public boolean create(long employeeId, long projectId, int percent){
		
		try {
			
			if (getEffortByProjectAndEmployee(projectId, employeeId) != null) return false;
			
			Effort effort = new Effort();
			
			
			effort.setEmployee(employeeDao.getOne(employeeId));
			effort.setProject(projectDao.getOne(projectId));
			effort.setPercent(percent);
			effortDao.save(effort);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
	
	public boolean changeProject(long project_id, long effort_id){
		try {
			Effort effort = effortDao.getOne(effort_id);
			effort.setProject(projectDao.getOne(project_id));
			effortDao.saveAndFlush(effort);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean changeEmployee(long employe_id, long effort_id){
		try {
			Effort effort = effortDao.getOne(effort_id);
			effort.setEmployee(employeeDao.getOne(employe_id));
			effortDao.saveAndFlush(effort);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public boolean updatePercent(long id, int percent){
		try {
			Effort effort = effortDao.getOne(id);
			effort.setPercent(percent);
			effortDao.saveAndFlush(effort);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public boolean delete(long id){
		try {
			effortDao.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public Effort read(long id){
		try {
			return effortDao.getOne(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public List<Effort> getProjectsByEmployeeId(long id){
		try {
			return effortDao.findByEmployeeId(id);
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<Effort>();
		}
	}
	
	public List<Effort> getEmployeesByProjectId(long id){
		try {
			return effortDao.findByProjectId(id);
		} catch (Exception e) {
			return new ArrayList<Effort>();
		}
	}
	
	public Effort getEffortByProjectAndEmployee(long projectId, long employeeId){
		try {
			List<Effort> effort = effortDao.findByEmployeeIdAndProjectId(employeeId, projectId);
			if(effort != null && effort.size() > 0)
				return effort.get(0);
			else
				return null;
			
		} catch (Exception e) {
			return null;
		}
	}
}
