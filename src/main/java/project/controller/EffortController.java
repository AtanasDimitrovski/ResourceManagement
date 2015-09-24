package project.controller;

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

	
	public boolean create(long employee_id, long project_id, int percent){
		
		try {
			Effort effort = new Effort();
			effort.setEmployee(employeeDao.getOne(employee_id));
			effort.setProject(projectDao.getOne(project_id));
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
}
