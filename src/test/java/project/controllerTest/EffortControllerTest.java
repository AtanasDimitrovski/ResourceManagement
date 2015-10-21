package project.controllerTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.Application;
import project.controller.EffortController;
import project.model.Effort;
import project.model.Employee;
import project.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class EffortControllerTest {
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	EffortController controller;
	
	@Test
	public void test() throws ParseException{
		
		//Find effort by employee and project;
		
		long employeeId = 1;
		long projectId = 1;
		
		Effort effort = controller.getEffortByProjectAndEmployee(projectId, employeeId);
		Assert.assertNotNull(effort);
		
		Employee employee = effort.getEmployee();
		Project project = effort.getProject();
		
		Assert.assertNotNull(employee);
		Assert.assertNotNull(project);
		
		String employeeDesc = "basketball player in fener";
		String employeeLastName = "antic";
		String employeeName = "pero1";
		short employeeValid = 1;
		
		String projectDesc = "descNewRB12";
		Date projectFromDate = format.parse("2015-10-11");
		String projectName = "project1New11";
		String projectStatus = "inDevelopment";
		Date projectToDate = format.parse("2015-10-15");
		short projectValid = 1;
		
		Assert.assertEquals(employeeDesc, employee.getJobDescription());
		Assert.assertEquals(employeeLastName, employee.getLastName());
		Assert.assertEquals(employeeName, employee.getName());
		Assert.assertEquals(employeeValid, employee.getValid());
		
		Assert.assertEquals(projectDesc, project.getDescription());
		Assert.assertEquals(projectFromDate, project.getFromDate());
		Assert.assertEquals(projectName, project.getName());
		Assert.assertEquals(projectStatus, project.getStatus());
		Assert.assertEquals(projectToDate, project.getToDate());
		Assert.assertEquals(projectValid, project.getValid());
		
		//Test getting efforts by employee id
		List<Effort> effortsByEmployee = controller.getEffortsByEmployeeId(employeeId);
		Assert.assertNotNull(effortsByEmployee);
		Assert.assertEquals(3, effortsByEmployee.size());
		
		//Test getting efforts by project id
		List<Effort> effortsByProject = controller.getEffortsByProjectId(projectId);
		Assert.assertNotNull(effortsByProject);
		Assert.assertEquals(4, effortsByProject.size());
		
		
	}
	
}




