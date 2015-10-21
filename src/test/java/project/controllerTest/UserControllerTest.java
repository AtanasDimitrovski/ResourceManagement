package project.controllerTest;


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
import project.controller.UserController;
import project.model.User;
import project.model.User.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class UserControllerTest {
	
	@Autowired
	UserController controller;
	
	@Test
	public void test(){
		
		String expectedUsername = "user";
		String expectedPassword = "pass";
		Role expectedRole = Role.ROLE_USER;
		short expectedValid = 1;
		long expectedId = 1;
		
		User user = controller.findByUsername(expectedUsername);
		Assert.assertNotNull(user);
		
		Assert.assertEquals(expectedUsername, user.getUsername());
		Assert.assertEquals(expectedPassword, user.getPassword());
		Assert.assertEquals(expectedRole, user.getRole());
		Assert.assertEquals(expectedValid, user.getValid());
		Assert.assertEquals(new Long(expectedId), user.getId());
		
		String wrongUsername = "wrong";
		User userNull = controller.findByUsername(wrongUsername);
		Assert.assertNull(userNull);
		
		long employeeId = 1;
		User userByEmployeeId = controller.findByEmployeeId(employeeId);
		Assert.assertNotNull(userByEmployeeId);
		Assert.assertEquals(user, userByEmployeeId);
		
	}

}
