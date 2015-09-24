package project.rest_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.model.Employee;
import project.service.EmployeeService;

@RestController
@RequestMapping(value = "/data/employees")
public class EmployeeResource {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Employee getEmployee(@PathVariable long id){		
		return employeeService.getEmployee(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void deleteEmployee(@PathVariable long id){
		employeeService.deleteEmployee(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public void createEmployee(@RequestParam("name") String name,
			@RequestParam("lastName") String lastName, @RequestParam("jobDescription") String jobDescription){
		employeeService.createEmployee(name, lastName, jobDescription);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public void changeName(@PathVariable long id, @RequestParam("name") String name){
		employeeService.changeName(id, name);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", params = "lastName")
	public void changeLastName(@PathVariable long id, @RequestParam String lastName){
		employeeService.changeLastName(id, lastName);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", params = "jobDescription")
	public void changeJobDescription(@PathVariable long id, @RequestParam String jobDescription){
		employeeService.changeJobDescription(id, jobDescription);
	}
	
	
}
