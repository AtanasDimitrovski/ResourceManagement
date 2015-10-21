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
import project.model.User;
import project.model.User.Role;
import project.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class UserServiceTest {
	
	@Autowired
	UserService service;
	
	@Test
	public void test(){
		
		//Create
		User user = new User();
		user.setUsername("testUser");
		user.setPassword("password");
		user.setRole(Role.ROLE_USER);
		
		user = service.create(user);
		Assert.assertNotNull(user);
		
		Assert.assertEquals("testUser", user.getUsername());
		Assert.assertEquals("password", user.getPassword());
		Assert.assertEquals(Role.ROLE_USER, user.getRole());
		
		//Delete
		long id = user.getId();
		service.delete(user.getId());
		
		user = service.get(id);
		Assert.assertNotNull(user);
		short valid = 0;
		Assert.assertEquals(valid, user.getValid());
	}
}
















