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
	
	public List<Effort> getEffortsByEmployeeId(long id){
		try {
			return effortDao.findByEmployeeId(id);
		} catch (Exception e) {
			return new ArrayList<Effort>();
		}
	}
	
	public List<Effort> getEffortsByProjectId(long id){
		try {
			return effortDao.findByProjectId(id);
		} catch (Exception e) {
			return new ArrayList<Effort>();
		}
	}
	
}
