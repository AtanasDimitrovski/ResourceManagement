package project.serviceTest;

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
import project.model.Effort;
import project.model.User;
import project.model.User.Role;
import project.service.EffortService;
import project.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class EffortServiceController {
	
	@Autowired
	EffortService service;
	
	@Test
	public void test(){
		
		//Create
		long employeeId = 1;
		long projectId = 11;
		
		Effort effort = service.create(employeeId, projectId);
		Assert.assertNotNull(effort);
		
		employeeId = 2;
		effort = service.create(employeeId, projectId);
		Assert.assertNull(effort);
		
	}
	
}
