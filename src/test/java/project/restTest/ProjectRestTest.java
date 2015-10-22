package project.restTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

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
public class ProjectRestTest {
	
	@Value("${local.server.port}")
    private int port;
	
	private URL dataProjects;
	private URL dataEmployees;
	private RestTemplate template;
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		this.dataProjects = new URL("http://localhost:" + port + "/data/projects");	
		this.dataEmployees = new URL("http://localhost:" + port + "/data/employees");
		template = new TestRestTemplate();
	}
	
}
