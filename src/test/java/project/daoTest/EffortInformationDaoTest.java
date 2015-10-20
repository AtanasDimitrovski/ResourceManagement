package project.daoTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.Application;
import project.dao.EffortDao;
import project.dao.EffortInformationDao;
import project.dao.EmployeeDao;
import project.dao.ProjectDao;
import project.model.Effort;
import project.model.EffortInformation;
import project.model.Employee;
import project.model.Project;
import project.model.EffortInformation.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:create.sql")
public class EffortInformationDaoTest {
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	EffortInformationDao dao;
	
	@Autowired
	EffortDao effortDao;
	
	@Test
	public void crud() throws ParseException{
		
		//CREATE
		Date fromDate = format.parse("2015-12-30");
		Date toDate = format.parse("2016-01-20");
		Role role = Role.ROLE_BACKEND;
		int percent = 80;
		
		long effortId = 9;
		Effort effort = effortDao.findOne(effortId);
		Assert.assertNotNull(effort);
		
		EffortInformation effortInformation = new EffortInformation();
		effortInformation.setFromDate(fromDate);
		effortInformation.setToDate(toDate);
		effortInformation.setRole(role);
		effortInformation.setEffort(effort);
		effortInformation.setPercent(percent);
		
		effortInformation = dao.save(effortInformation);
		Assert.assertNotNull(effortInformation);
		
		//READ
		effortInformation = dao.findOne(effortInformation.getId());
		Assert.assertNotNull(effortInformation);
		
		Assert.assertEquals(fromDate, effortInformation.getFromDate());
		Assert.assertEquals(toDate, effortInformation.getToDate());
		Assert.assertEquals(role, effortInformation.getRole());
		Assert.assertEquals(effort, effortInformation.getEffort());
		
		List<EffortInformation> effortInformations = dao.findAll();
		Assert.assertNotNull(effortInformations);
		Assert.assertEquals(16, effortInformations.size());
		
		List<EffortInformation> effortInfoById = dao.findByEffortId(effortId);
		Assert.assertNotNull(effortInfoById);
		Assert.assertEquals(5, effortInfoById.size());
		
		//UPDATE
		Date fromUpdate = format.parse("2016-01-20");
		Date toUpdate = format.parse("2016-02-10");
		Role roleUpdate = Role.ROLE_DESIGNER;
		int percentUpdate = 70;
		effortInformation.setFromDate(fromUpdate);
		effortInformation.setToDate(toUpdate);
		effortInformation.setRole(roleUpdate);
		effortInformation.setPercent(percentUpdate);
		
		effortInformation = dao.saveAndFlush(effortInformation);
		Assert.assertNotNull(effortInformation);
		
		Assert.assertEquals(fromUpdate, effortInformation.getFromDate());
		Assert.assertEquals(toUpdate, effortInformation.getToDate());
		Assert.assertEquals(roleUpdate, effortInformation.getRole());
		Assert.assertEquals(percentUpdate, effortInformation.getPercent());
		Assert.assertEquals(effort, effortInformation.getEffort());
		
		long newEffort = 17;
		effort = effortDao.findOne(newEffort);
		Assert.assertNotNull(effort);
		effortInformation.setEffort(effort);
		effortInformation = dao.saveAndFlush(effortInformation);
		Assert.assertNotNull(effortInformation);
		Assert.assertEquals(effort, effortInformation.getEffort());
		
	}
}





