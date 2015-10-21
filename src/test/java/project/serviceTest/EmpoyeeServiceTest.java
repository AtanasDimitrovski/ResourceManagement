package project.serviceTest;

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
import project.model.EffortInformation;
import project.model.Employee;
import project.model.Project;
import project.model.User;
import project.model.User.Role;
import project.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class EmpoyeeServiceTest {

	@Autowired
	EmployeeService service;
	
	@Test
	public void test(){
		
		long employeeId = 1;
		//EffortInformation for empployee test
		List<EffortInformation> effortInformations = service.getEffortInformation(employeeId);
		Assert.assertNotNull(effortInformations);
		Assert.assertEquals(5, effortInformations.size());
		
		//test delete
		service.deleteEmployee(employeeId);
		Employee employee = service.getEmployee(employeeId);
		Assert.assertNull(employee);
		
		//create emplpyee
		Employee emp = new Employee();
		emp.setName("testEmployee");
		emp.setJobDescription("testDesc");
		emp.setLastName("testLastName");
		
		User user = new User();
		user.setPassword("passTest");
		user.setUsername("userTest");
		user.setRole(Role.ROLE_USER);
		
		int size = service.getEmployees().size();
		emp = service.createEmployee(emp, user);
		
		Assert.assertNotNull(emp);
		Assert.assertEquals(size+1, service.getEmployees().size());
		
		//Eddit employee
		String changeName = "newNameTest";
		String changeLastName = "newLastNameTest";
		String changeDescription = "newDescTest";
		
		emp = service.editEmployee(emp.getId(), changeName, changeLastName, changeDescription);
		Assert.assertNotNull(emp);
		Assert.assertEquals(changeName, emp.getName());
		Assert.assertEquals(changeLastName, emp.getLastName());
		Assert.assertEquals(changeDescription, emp.getJobDescription());
		
		//Get projects that the employee is currently working on
		List<Project> projects = service.getProjects(employeeId);
		Assert.assertNotNull(projects);
		Assert.assertEquals(2, projects.size());
		
		//Remove project from employee
		long projectId = 14;
		service.removeProject(employeeId, projectId);
		
		projects = service.getProjects(employeeId);
		Assert.assertNotNull(projects);
		Assert.assertEquals(1, projects.size());
		
		//Add project to employee
		boolean result = service.addProject(employeeId, projectId);
		Assert.assertFalse(result);
		
		employeeId = 15;
		
		result = service.addProject(employeeId, projectId);
		Assert.assertTrue(result);
		
		projects = service.getProjects(employeeId);
		Assert.assertNotNull(projects);
		Assert.assertEquals(1, projects.size());
		
	}
}






