package project.controllerTest;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.Application;
import project.controller.EmployeeController;
import project.controller.ProjectController;
import project.model.Employee;
import project.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class EmployeeControllerTest {
	
	
	@Autowired
	EmployeeController controller;
	
	@Test
	public void test(){
		
		String expectedDesc = "basketball player in fener";
		String expectedLastName = "antic";
		String expectedName = "pero1";
		short expectedValid = 1;
		
		long employeeId = 1;
		Employee employee = controller.findOne(employeeId);
		
		Assert.assertNotNull(employee);
		
		Assert.assertEquals(expectedDesc, employee.getJobDescription());
		Assert.assertEquals(expectedLastName, employee.getLastName());
		Assert.assertEquals(expectedName, employee.getName());
		Assert.assertEquals(expectedValid, employee.getValid());
		Assert.assertEquals(new Long(employeeId), employee.getId());
		
		long employeeIdNull = 2;
		employee = controller.findOne(employeeIdNull);
		Assert.assertNull(employee);
		
		
	}
}





