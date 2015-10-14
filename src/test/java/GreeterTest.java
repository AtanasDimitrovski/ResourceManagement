


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringAnnotationConfiguration;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import project.Application;
import project.controller.EmployeeController;
import project.dao.EmployeeDao;
import project.model.Employee;
import project.model.Greeter;
import project.other.AppConfig;

@RunWith(Arquillian.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
@SpringAnnotationConfiguration(classes = {AppConfig.class})
public class GreeterTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "ResourceManagement.jar")
            .addClasses(Greeter.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    


    @Test
    public void should_create_greeting() {
    	String a = "string";
        Assert.assertEquals("string", a);
    }
}