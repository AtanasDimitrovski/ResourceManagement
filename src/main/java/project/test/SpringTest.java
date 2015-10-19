package project.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import project.Application;
import project.dao.ProjectDao;

@RunWith(SpringJUnit4ClassRunner.class)
// 1
@SpringApplicationConfiguration(classes = Application.class)
// 2
@WebAppConfiguration
// 3
@IntegrationTest
public class SpringTest {


	@Autowired
	ProjectDao dao;

	@Test
	public void should_create_greeting() {

		if (dao == null)
			System.out.println("============NULL=============");
		else
			System.out.println("============NOT NULL=========");
		String a = "string";
		Assert.assertEquals("string", a);
	}

}
