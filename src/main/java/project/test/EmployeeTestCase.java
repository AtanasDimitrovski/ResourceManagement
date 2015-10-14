/*package project.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import project.Application;
import project.controller.EmployeeController;
import project.dao.EmployeeDao;
import project.model.Employee;
import project.service.EmployeeService;

@RunWith(Arquillian.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EmployeeTestCase {
	
	@Deployment
	public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "ResourceManagement.jar")
                .addClasses(Employee.class,
                        EmployeeService.class, EmployeeDao.class, EmployeeController.class);
    }
	
	@Autowired
	private EmployeeService service;
	
	@Test
	public void testGetEmployees() {

        List<Employee> result = service.getEmployees();

        assertNotNull("Method returned null list as result.", result);
        assertEquals("Two employees were expected.", 2, result.size());
    }
}
*/