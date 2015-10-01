package project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import project.dao.EffortDao;
import project.dao.ProjectDao;
import project.model.Effort;
import project.model.Employee;
import project.model.Project;

@Controller
public class ProjectController extends BaseController<Project, JpaRepository<Project,Long>> {
	
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	protected JpaRepository<Project, Long> getDao() {
		return projectDao;
	}
	
	@Override
	public List<Project> findAll() {
		short valid = 1;
		return projectDao.findByValid(valid);
	}
	
	public Employee getManager(long id){
		return projectDao.findOne(id).getManager();
	}

	public Project edit(long id, String name, String description,
			Date fromDate, Date toDate, String status, Employee manager) {
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
			if (manager != null)
				project.setManager(manager);
			return super.saveAndFlush(project);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Project> findProjectByManager(long id){
		short valid = 1;
		return projectDao.findByValidAndManagerId(valid, id);
	}
}
