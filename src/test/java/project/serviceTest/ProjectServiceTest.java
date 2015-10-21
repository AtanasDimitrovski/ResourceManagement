package project.serviceTest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import antlr.collections.List;
import project.Application;
import project.model.Effort;
import project.model.EffortInformation;
import project.model.EffortInformation.Role;
import project.model.Employee;
import project.model.Project;
import project.model.temp.EmployeeEffort;
import project.service.EmployeeService;
import project.service.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class ProjectServiceTest {
	
	@Value("${local.server.port}")
	private int port;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	ProjectService service;
	
	@Autowired
	EmployeeService employeeService;

	@Test
	public void test() throws ParseException {

		Project project = Mockito.mock(Project.class);
		java.util.List<Effort> efforts = Mockito.mock(java.util.List.class);
		Employee employee = Mockito.mock(Employee.class);

		

		Long id = new Long(1);
		String name = "Project";
		String desc = "Description";
		Date fromDate = format.parse("2015-10-25");
		Date toDate = format.parse("2015-10-30");
		String status = "Development";
		short valid = 1;

		Mockito.when(project.getId()).thenReturn(id);
		Mockito.when(project.getName()).thenReturn(name);
		Mockito.when(project.getDescription()).thenReturn(desc);
		Mockito.when(project.getEfforts()).thenReturn(efforts);
		Mockito.when(project.getFromDate()).thenReturn(fromDate);
		Mockito.when(project.getToDate()).thenReturn(toDate);
		Mockito.when(project.getManager()).thenReturn(employee);
		Mockito.when(project.getStatus()).thenReturn(status);
		Mockito.when(project.getValid()).thenReturn(valid);

		Assert.assertEquals(id, project.getId());
		Assert.assertEquals(name, project.getName());
		Assert.assertEquals(desc, project.getDescription());
		Assert.assertEquals(efforts, project.getEfforts());
		Assert.assertEquals(fromDate, project.getFromDate());
		Assert.assertEquals(toDate, project.getToDate());
		Assert.assertEquals(employee, project.getManager());
		Assert.assertEquals(status, project.getStatus());
		Assert.assertEquals(valid, project.getValid());
	}


	
	@Test
	public void create() throws ParseException{
		
		//CREATE
		java.util.List<Project> projects = service.getProjects();
		Assert.assertEquals(8, projects.size());
		
		Project project = new Project();
		project.setDescription("desc");
		project.setManager(employeeService.getEmployee(11));
		project.setName("project");
		project.setStatus("dev");
		project.setFromDate(format.parse("2015-11-12"));
		project.setToDate(format.parse("2015-11-25"));
		project = service.createProject(project);
		Assert.assertNotNull(project);
		
		projects = service.getProjects();
		Assert.assertEquals(9, projects.size());
		
		project = service.getProject(project.getId());
		Assert.assertNotNull(project);
		
		
		//EDIT
		String name = "Name after edit";
		String description = "Desc after edit";
		Date fromDate = format.parse("2015-12-11");
		project.setName(name);
		project.setDescription(description);
		project.setFromDate(fromDate);
		Employee employee = employeeService.getEmployee(1);
		project.setManager(employee);
		
		project = service.editProject(project.getId(), project.getName(), project.getDescription(), project.getFromDate(), project.getToDate(), project.getStatus(), project.getManager());
		Assert.assertNotNull(project);
		
		project = service.getProject(project.getId());
		Assert.assertNotNull(project);
		Assert.assertEquals(name, project.getName());
		Assert.assertEquals(description, project.getDescription());
		Assert.assertEquals(fromDate, project.getFromDate());
		Assert.assertEquals(employee, project.getManager());
		
		//DELETE
		projects = service.getProjects();

		Assert.assertEquals(9, projects.size());

		long id = projects.get(0).getId();
		service.delete(id);

		projects = service.getProjects();
		Assert.assertNull(service.getProject(id));

		Assert.assertEquals(8, projects.size());
		
		// Gets all employees that are working on project with id Test
		
		id = 14;
		java.util.List<Employee> employees = service.getEmployees(id);
		
		Assert.assertNotNull(employees);
		Assert.assertEquals(1, employees.size());
		Employee emp = employeeService.getEmployee(1);
		Assert.assertNotNull(emp);
		Assert.assertEquals(emp, employees.get(0));
		
		//Adds employee with employeeId to work on project with projectId Test
		
		Assert.assertEquals(1, employees.size());
		
		long emp_id = 15;
		emp = employeeService.getEmployee(emp_id);
		Assert.assertNotNull(emp);
		
		project = service.getProject(emp_id);
		Assert.assertNotNull(project);
		
		service.addEmployee(project.getId(), emp.getId());
		
		//Gets all effort information for emloyee with employee id about project with id Test
		
		emp_id = 1;
		emp = employeeService.getEmployee(emp_id);
		Assert.assertNotNull(emp);
		
		project = service.getProject(id);
		Assert.assertNotNull(project);
		
		
		java.util.List<EffortInformation> effortInformations = service.getEffortInformation(project.getId(), emp.getId());
		Assert.assertNotNull(effortInformations);
		Assert.assertEquals(1, effortInformations.size());
		
		//Adds Effort information for employee with employeeId about project with id. Test
		
		EffortInformation effortInformation = new EffortInformation();
		effortInformation.setFromDate(format.parse("2015-11-23"));
		effortInformation.setToDate(format.parse("2015-11-30"));
		effortInformation.setPercent(80);
		effortInformation.setRole(Role.ROLE_BACKEND);

		emp = employeeService.getEmployee(emp_id);
		Assert.assertNotNull(emp);
		
		project = service.getProject(id);
		Assert.assertNotNull(project);
		
		service.addEffortInformation(project.getId(), emp.getId(), effortInformation);
		effortInformations = service.getEffortInformation(project.getId(), emp.getId());
		Assert.assertNotNull(effortInformations);
		Assert.assertEquals(2, effortInformations.size());
		
		//Gets all effort informations for project with project id Test
		
		project = service.getProject(id);
		Assert.assertNotNull(project);
		
		java.util.List<EmployeeEffort> employeeEffortInformations = service.getEffortInformationsForProject(project.getId());
		Assert.assertNotNull(employeeEffortInformations);
		Assert.assertEquals(4, employeeEffortInformations.size());
		
		//Edit effortinfromation test
		
		fromDate = format.parse("2015-11-23");
		Date toDate = format.parse("2015-11-30");
		int percent = 80;
		Role role = Role.ROLE_BACKEND;
		
		effortInformation = new EffortInformation();
		effortInformation.setFromDate(fromDate);
		effortInformation.setToDate(toDate);
		effortInformation.setPercent(percent);
		effortInformation.setRole(role);

		effortInformation = service.editEffortInformation(id, effortInformation);
		Assert.assertNotNull(effortInformation);
		
		Assert.assertEquals(fromDate, effortInformation.getFromDate());
		Assert.assertEquals(toDate, effortInformation.getToDate());
		Assert.assertEquals(percent, effortInformation.getPercent());
		Assert.assertEquals(role, effortInformation.getRole());
		
		//Removes employee with employeeId from working on project with projectId Test
		

		Assert.assertEquals(1, employees.size());
		
		service.removeEmployee(14, employees.get(0).getId());
		employees = service.getEmployees(id);
		
		Assert.assertNotNull(employees);
		Assert.assertEquals(0, employees.size());
	}
	
}
