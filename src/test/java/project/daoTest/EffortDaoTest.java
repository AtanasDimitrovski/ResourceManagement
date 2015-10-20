package project.daoTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.Application;
import project.dao.EffortDao;
import project.dao.EmployeeDao;
import project.dao.ProjectDao;
import project.model.Effort;
import project.model.Employee;
import project.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class EffortDaoTest {
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	EffortDao dao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	ProjectDao projectDao;
	
	
	@Test
	public void crud() throws ParseException{
		
		//CREATE
		long employeeId = 15;
		Employee employee = employeeDao.findOne(employeeId);
		Assert.assertNotNull(employee);
		
		long projectId = 7;
		Project project = projectDao.findOne(projectId);
		Assert.assertNotNull(project);
		
		Effort effort = new Effort();
		effort.setEmployee(employee);
		effort.setProject(project);
		
		effort = dao.save(effort);
		Assert.assertNotNull(effort);
		Assert.assertEquals(employee, effort.getEmployee());
		Assert.assertEquals(project, effort.getProject());
		
		//READ
		
		effort = dao.findOne(effort.getId());
		Assert.assertNotNull(effort);
		
		List<Effort> efforts = dao.findAll();
		Assert.assertNotNull(efforts);
		Assert.assertEquals(15, efforts.size());
		
		//UPDATE
		
		long newEmployee = 1;
		employee = employeeDao.findOne(newEmployee);
		Assert.assertNotNull(employee);
		effort.setEmployee(employee);
		
		effort = dao.saveAndFlush(effort);
		Assert.assertNotNull(efforts);
		
		Assert.assertEquals(employee, effort.getEmployee());
		
		long newProjectId = 6;
		project = projectDao.findOne(newProjectId);
		Assert.assertNotNull(project);
		effort.setProject(project);
		
		effort = dao.saveAndFlush(effort);
		Assert.assertNotNull(effort);
		
		Assert.assertEquals(project, effort.getProject());
		
		//DELETE
		long effortId = effort.getId();
		dao.delete(effort);
		Assert.assertNull(dao.findOne(effortId));
	}
}










