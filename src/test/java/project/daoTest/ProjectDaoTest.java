package project.daoTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.Application;
import project.dao.EmployeeDao;
import project.dao.ProjectDao;
import project.model.Employee;
import project.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class ProjectDaoTest {
	
	@Value("${local.server.port}")
	private int port;
	
	@Autowired
	ProjectDao dao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void crud() throws ParseException{
		
		short valid = 1;
		long managerId = 15;
		
		//CREATE
		Project project = new Project();
		project.setDescription("descriptionTest");
		project.setFromDate(format.parse("2015-10-20"));
		project.setToDate(format.parse("2015-10-30"));
		project.setName("projectNameTest");
		project.setStatus("test");
		project.setValid(valid);
		
		Employee manager = employeeDao.findOne(managerId);
		Assert.assertNotNull(manager);
		project.setManager(manager);
		
		project = dao.save(project);
		Assert.assertNotNull(project);
		
		//READ
		project = dao.findOne(project.getId());
		Assert.assertNotNull(project);
		int expected = 15;
		List<Project> projects = dao.findAll();
		Assert.assertNotNull(projects);
		Assert.assertEquals(expected, projects.size());
		int expectedValid = 9;
		projects = dao.findByValid(valid);
		Assert.assertNotNull(projects);
		Assert.assertEquals(expectedValid, projects.size());
		
		//
		
		String expectedDescription = "descriptionTest";
		Date expectedFromDate = format.parse("2015-10-20");
		Date expectedToDate = format.parse("2015-10-30");
		String expectedName = "projectNameTest";
		String expectedStatus = "test";
		long empId = 15;
		Employee expectedEmployee = employeeDao.findOne(empId);
		Assert.assertNotNull(expectedEmployee);
		
		Assert.assertEquals(expectedDescription, project.getDescription());
		Assert.assertEquals(expectedFromDate, project.getFromDate());
		Assert.assertEquals(expectedToDate, project.getToDate());
		Assert.assertEquals(expectedName, project.getName());
		Assert.assertEquals(expectedStatus, project.getStatus());
		Assert.assertEquals(expectedEmployee, project.getManager());		

		projects = dao.findByValidAndManagerId(valid, managerId);
		Assert.assertNotNull(projects);
		Assert.assertEquals(2, projects.size());
		
		//
		//UPDATE
		String updatedDescription = "descriptionUpdate";
		String updatedName = "nameUpdate";
		Date updateFromDate = format.parse("2015-24-11");
		Date updateToDate = format.parse("2015-10-12");
		String updateStatus = "updateStatus";
		short updateValid = 0;
		project.setDescription(updatedDescription);
		project.setName(updatedName);
		project.setFromDate(updateFromDate);
		project.setToDate(updateToDate);
		project.setStatus(updateStatus);
		project.setValid(updateValid);
		
		project = dao.saveAndFlush(project);
		Assert.assertNotNull(project);
		Assert.assertEquals(updatedDescription, project.getDescription());
		Assert.assertEquals(updatedName, project.getName());
		Assert.assertEquals(updateFromDate, project.getFromDate());
		Assert.assertEquals(updateToDate, project.getToDate());
		Assert.assertEquals(updateStatus, project.getStatus());
		Assert.assertEquals(updateValid, project.getValid());
		
		
	}

	
}








