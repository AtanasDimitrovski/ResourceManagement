package project.daoTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import project.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class EmployeeDaoTest {

	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	EmployeeDao dao;
	
	@Test
	public void crud(){
		
		//CREATE
		String desc = "newDesc";
		String name = "newName";
		String lastName = "newLastName";
		short valid = 1;
		Employee employee = new Employee();
		employee.setJobDescription(desc);
		employee.setName(name);
		employee.setLastName(lastName);
		employee.setValid(valid);
		
		employee = dao.save(employee);
		Assert.assertNotNull(employee);
		
		//READ
		employee = dao.findOne(employee.getId());
		Assert.assertNotNull(employee);
		
		Assert.assertEquals(desc, employee.getJobDescription());
		Assert.assertEquals(name, employee.getName());
		Assert.assertEquals(lastName, employee.getLastName());
		Assert.assertEquals(valid, employee.getValid());
		
		List<Employee> employees = dao.findAll();
		Assert.assertNotNull(employees);
		Assert.assertEquals(18, employees.size());
		
		employees = dao.findByValid(valid);
		Assert.assertNotNull(employees);
		Assert.assertEquals(4, employees.size());
		
		
		//UPDATE
		String descUpdate = "descUpdate";
		String nameUpdate = "nameUpdate";
		String lastNameUpdate = "lastNameUpdate";
		short validUpdate = 0;
		employee.setName(nameUpdate);
		employee.setLastName(lastNameUpdate);
		employee.setJobDescription(descUpdate);
		employee.setValid(validUpdate);
		
		employee = dao.saveAndFlush(employee);
		Assert.assertNotNull(employee);
		
		Assert.assertEquals(descUpdate, employee.getJobDescription());
		Assert.assertEquals(nameUpdate, employee.getName());
		Assert.assertEquals(lastNameUpdate, employee.getLastName());
		Assert.assertEquals(validUpdate, employee.getValid());
	}	
}





