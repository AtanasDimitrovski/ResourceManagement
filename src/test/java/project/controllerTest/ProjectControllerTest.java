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
import project.controller.ProjectController;
import project.model.Employee;
import project.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class ProjectControllerTest {
	
	@Autowired
	ProjectController controller;
	
	@Test
	public void test(){
		
		//Test get manager
		long projectId = 1;
		long managerId = 15;
		short valid = 1;
		
		Employee employee = Mockito.mock(Employee.class);
		
		Mockito.when(employee.getId()).thenReturn(managerId);
		Mockito.when(employee.getName()).thenReturn("atanas");
		Mockito.when(employee.getLastName()).thenReturn("dimitrovski");
		Mockito.when(employee.getValid()).thenReturn(valid);
		Mockito.when(employee.getJobDescription()).thenReturn("dev");
		
		Employee manager = controller.getManager(projectId);
		Assert.assertNotNull(manager);
		
		Assert.assertEquals(employee.getId(), manager.getId());
		Assert.assertEquals(employee.getName(), manager.getName());
		Assert.assertEquals(employee.getLastName(), manager.getLastName());
		Assert.assertEquals(employee.getValid(), manager.getValid());
		Assert.assertEquals(employee.getJobDescription(), manager.getJobDescription());
		
		Project project = controller.findOne(projectId);
		Assert.assertNotNull(project);
		
		List<Project> projectsManaged = controller.findProjectByManager(manager.getId());
		Assert.assertTrue(projectsManaged.contains(project));
		
		
		//Test findOne
		long projectId2 = 2;
		Project projectNull = controller.findOne(projectId2);
		Assert.assertNull(projectNull);
		
	}
	
}











