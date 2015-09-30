package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import project.dao.EffortDao;
import project.dao.EmployeeDao;
import project.dao.ProjectDao;
import project.model.Effort;

@Controller
public class EffortController extends BaseController<Effort, JpaRepository<Effort,Long>> {
	
	@Autowired
	private EffortDao effortDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired 
	private EmployeeDao employeeDao;
	
	@Override
	protected JpaRepository<Effort, Long> getDao() {
		return effortDao;
	}
	
	public Effort getEffortByProjectAndEmployee(long projectId, long employeeId){
		try {
			List<Effort> effort = effortDao.findByEmployeeIdAndProjectId(employeeId, projectId);
			return effort.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	
	public boolean create(long employeeId, long projectId, int percent){
		
		try {
			
			if (getEffortByProjectAndEmployee(projectId, employeeId) != null) return false;
			
			Effort effort = new Effort();
			
			
			effort.setEmployee(employeeDao.getOne(employeeId));
			effort.setProject(projectDao.getOne(projectId));
			effort.setPercent(percent);
			super.save(effort);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
	
	public boolean changeProject(long projectId, long effortId){
		try {
			Effort effort = effortDao.getOne(effortId);
			effort.setProject(projectDao.getOne(projectId));
			super.saveAndFlush(effort);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean changeEmployee(long employeId, long effortId){
		try {
			Effort effort = effortDao.getOne(effortId);
			effort.setEmployee(employeeDao.getOne(employeId));
			super.saveAndFlush(effort);
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
			super.saveAndFlush(effort);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	
	public List<Effort> getProjectsByEmployeeId(long id){
		try {
			return effortDao.findByEmployeeId(id);
		} catch (Exception e) {
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
	
}
