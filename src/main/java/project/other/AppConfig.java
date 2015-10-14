package project.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import project.dao.EmployeeDao;

@Configuration
public class AppConfig {

	@Autowired
	EmployeeDao dao;
	
    @Bean
    public EmployeeDao employeeDao() {

        return dao;
    }

}