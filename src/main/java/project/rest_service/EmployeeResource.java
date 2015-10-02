package project.rest_service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.model.EffortInformation;
import project.model.Employee;
import project.model.Project;
import project.model.User;
import project.service.EmployeeService;
import project.service.UserService;

@RestController
@RequestMapping(value = "/data/employees")
public class EmployeeResource {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Gets all employees
	 * @return List of all employees in JSON
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	/**
	 * Gets employee with given id
	 * @param id employee id
	 * @return Employee in JSON
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Employee getEmployee(@PathVariable long id){		
		return employeeService.getEmployee(id);
	}
	
	/**
	 * Deletes employee with given id
	 * @param id employee id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable long id){
		employeeService.deleteEmployee(id);
	}
	
	/**
	 * Creates employee
	 * @param employee JSON employee
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void createEmployee(@RequestBody Employee employee){
		employeeService.createEmployee(employee);
	}
	
	/**
	 * Edits employee with given id and information
	 * @param id employee id
	 * @param employee Employee information JSON
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void editEmployee(@PathVariable long id, @RequestBody Employee employee){
		employeeService.editEmployee(id, employee.getName(), employee.getLastName(), employee.getJobDescription());
	}
	
	/**
	 * Gets all projects that the employee with given id is working on
	 * @param id employee id
	 * @return List of projects JSON
	 */
	@RequestMapping(value = "/{id}/projects", method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjects(@PathVariable long id){
		return employeeService.getProjects(id);
	}
	
	/**
	 * Removes Project with project id that the Employee with given id is working on
	 * @param id employee id
	 * @param projectId project id
	 */
	@RequestMapping(value = "/{id}/projects/{projectId}", method = RequestMethod.DELETE)
	public void removeProjectFromEmployee(@PathVariable long id, @PathVariable long projectId){
		employeeService.removeProject(id, projectId);
	}
	
	/**
	 * Adds a project for employee with given id to work on
	 * @param id employee id
	 * @param projectId project id
	 */
	@RequestMapping(value = "/{id}/projects", method = RequestMethod.POST)
	public void addProject(@PathVariable long id, @RequestParam("projectId") long projectId){
		employeeService.addProject(id, projectId);
	}
	
	/**
	 * Gets all projects that the employee with given id is managing them 
	 * @param id employee id
	 * @return list od projects JSON
	 */
	@RequestMapping(value = "/{id}/projects/manager", method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjectsManged(@PathVariable long id){
		return employeeService.getManagerProjects(id);
	}
	/**
	 * Gets employee from currently logged in user
	 * @param request http request
	 * @return Employee JSON
	 */
	@RequestMapping(value = "/user")
	public Employee getEmployeeFromUser(HttpServletRequest request){
		String[] split = request.getHeader("Authorization").split(" ");
		byte[] info = Base64Utils.decode(split[1].getBytes());
		String userPass = new String(info);
		split = userPass.split(":");
		User user = userService.findByUsername(split[0]);
		return user.getEmployee();
	}
	
	/**
	 * Gets all effort information about employee with given id
	 * @param id employee id
	 * @return List of employee informations JSON
	 */
	@RequestMapping(value = "/effort/{id}")
	public List<EffortInformation> getEffortInfromation(@PathVariable long id){
		return employeeService.getEffortInformation(id);
	}
	
}
