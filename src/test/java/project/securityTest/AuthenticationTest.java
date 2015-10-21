package project.securityTest;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import project.Application;
import project.controller.ProjectController;
import project.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class AuthenticationTest {
	

    @Value("${local.server.port}")
    private int port;

	private URL dataProjects;
	private URL dataEmployees;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.dataProjects = new URL("http://localhost:" + port + "/data/projects");
		this.dataEmployees = new URL("http://localhost:" + port + "/data/employees");
		template = new TestRestTemplate();
	}
	
	@Test
	public void security(){
		ResponseEntity<String> response = template.getForEntity(dataProjects.toString(), String.class);
		System.out.println(response.getBody());
		assertThat(response.getBody(), equalTo("{\"error\":\"unauthorized\",\"error_description\":\"Full authentication is required to access this resource\"}"));
	
		response = template.getForEntity(dataEmployees.toString(), String.class);
		System.out.println(response.getBody());
		assertThat(response.getBody(), equalTo("{\"error\":\"unauthorized\",\"error_description\":\"Full authentication is required to access this resource\"}"));
	}
	
}
