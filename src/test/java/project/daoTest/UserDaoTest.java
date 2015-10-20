package project.daoTest;

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
import project.dao.EmployeeDao;
import project.dao.UserDao;
import project.model.Employee;
import project.model.User;
import project.model.User.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class UserDaoTest {
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	UserDao dao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Test
	public void crud(){
		
		//CREATE
		User user = new User();
		short valid = 1;
		user.setUsername("kg");
		user.setPassword("celtics");
		user.setValid(valid);
		user.setRole(Role.ROLE_USER);
		
		long employeeId = 15;
		Employee employee = employeeDao.findOne(employeeId);
		Assert.assertNotNull(employee);
	
		user.setEmployee(employee);
		
		user = dao.save(user);
		Assert.assertNotNull(user);
		
		//READ
		user = dao.findOne(user.getId());
		Assert.assertNotNull(user);
		
		List<User> users = dao.findAll();
		Assert.assertNotNull(users);
		Assert.assertEquals(10, users.size());
		
		users = dao.findByValid(valid);
		Assert.assertNotNull(users);
		Assert.assertEquals(4, users.size());
		
		Assert.assertEquals("kg", user.getUsername());
		Assert.assertEquals("celtics", user.getPassword());
		Assert.assertEquals(Role.ROLE_USER, user.getRole());
		
		//UPDATE
		String usernameUpdate = "Big Ticket";
		String passwordUpdate = "timberwolves";
		Role roleUpdate = Role.ROLE_ADMIN;
		
		user.setPassword(passwordUpdate);
		user.setUsername(usernameUpdate);
		user.setRole(roleUpdate);
		
		user = dao.saveAndFlush(user);
		Assert.assertNotNull(user);
		
		Assert.assertEquals(usernameUpdate, user.getUsername());
		Assert.assertEquals(passwordUpdate, user.getPassword());
		Assert.assertEquals(roleUpdate, user.getRole());
		
		long newEmployeeId = 1;
		employee = employeeDao.findOne(newEmployeeId);
		Assert.assertNotNull(employee);
		
		user.setEmployee(employee);
		Assert.assertNotNull(user);
		
		Assert.assertEquals(employee, user.getEmployee());
	}

}













