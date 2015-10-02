package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import project.dao.EmployeeDao;
import project.model.Employee;
import project.model.Project;

@Controller
public class EmployeeController extends BaseController<Employee, JpaRepository<Employee, Long>> {
	
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	protected JpaRepository<Employee, Long> getDao() {
		return employeeDao;
	}	
	
	@Override
	public List<Employee> findAll() {
		short valid = 1;
		return employeeDao.findByValid(valid);
	}	
}
