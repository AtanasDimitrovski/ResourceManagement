package project.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import project.dao.EffortDao;
import project.dao.ProjectDao;
import project.model.Effort;
import project.model.Project;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectDao projectDao;
		
	public boolean create(Project project){
		
		try {
			short valid = 1;
			project.setValid(valid);			
			projectDao.save(project);
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
		
	}
	
	public boolean delete(long id){
		
		try {
			
			Project project = projectDao.getOne(id);
			short valid = 0;
			project.setValid(valid);
			projectDao.saveAndFlush(project);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean updateName(long id, String name){
		
		try {
			
			Project project = projectDao.getOne(id);
			project.setName(name);
			projectDao.saveAndFlush(project);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
		
	}
	
	public boolean updateStatus(long id, String status){
		
		try {
			
			Project project = projectDao.getOne(id);
			project.setStatus(status);
			projectDao.saveAndFlush(project);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
		
	}
	
	public boolean updateDescription(long id, String desc){
		
		try {
			
			Project project = projectDao.getOne(id);
			project.setDescription(desc);
			projectDao.saveAndFlush(project);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
		
	}
	
	public boolean changeToDate(long id, Date to){
		
		try {
			
			Project project = projectDao.findOne(id);
			project.setToDate(to);
			projectDao.saveAndFlush(project);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean changeFromDate(long id, Date from){
		
		try {
			
			Project project = projectDao.findOne(id);
			project.setFromDate(from);
			projectDao.saveAndFlush(project);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public Project get(long id){
		try {		
			return projectDao.getOne(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public List<Project> getAll(){
		try {
			short valid = 1;
			return projectDao.findByValid(valid);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean edit(long id, String name, String description,
			Date fromDate, Date toDate, String status) {
		try {
			Project project = projectDao.getOne(id);
			if (name != null)
				project.setName(name);
			if (description != null)
				project.setDescription(description);
			if (fromDate != null)
				project.setFromDate(fromDate);
			if (toDate != null)
				project.setToDate(toDate);
			if (status != null)
				project.setStatus(status);
			projectDao.saveAndFlush(project);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	

}
