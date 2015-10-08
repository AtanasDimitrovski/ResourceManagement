package project.rest_service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import project.model.User.Role;
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
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Employee> getEmployees(HttpServletResponse response) throws IOException{
		try{
			return employeeService.getEmployees();
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	
	/**
	 * Gets employee with given id
	 * @param id employee id
	 * @return Employee in JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Employee getEmployee(@PathVariable long id, HttpServletResponse response) throws IOException{	
		try{
			return employeeService.getEmployee(id);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
		return null;
	}
	
	/**
	 * Deletes employee with given id
	 * @param id employee id
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable long id, HttpServletResponse response) throws IOException{
		try{
		employeeService.deleteEmployee(id);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Creates employee
	 * @param employee JSON employee
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void createEmployee(@RequestBody Employee employee, HttpServletResponse response) throws IOException{
		try{
			employeeService.createEmployee(employee);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Edits employee with given id and information
	 * @param id employee id
	 * @param employee Employee information JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void editEmployee(@PathVariable long id, @RequestBody Employee employee, HttpServletResponse response) throws IOException{
		try{
			employeeService.editEmployee(id, employee.getName(), employee.getLastName(), employee.getJobDescription());
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Gets all projects that the employee with given id is working on
	 * @param id employee id
	 * @return List of projects JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/projects", method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjects(@PathVariable long id, HttpServletResponse response) throws IOException{
		try{
			return employeeService.getProjects(id);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	
	/**
	 * Removes Project with project id that the Employee with given id is working on
	 * @param id employee id
	 * @param projectId project id
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/projects/{projectId}", method = RequestMethod.DELETE)
	public void removeProjectFromEmployee(@PathVariable long id, @PathVariable long projectId, HttpServletResponse response) throws IOException{
		try{
			employeeService.removeProject(id, projectId);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Adds a project for employee with given id to work on
	 * @param id employee id
	 * @param projectId project id
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/projects", method = RequestMethod.POST)
	public void addProject(@PathVariable long id, @RequestParam("projectId") long projectId, HttpServletResponse response) throws IOException{
		try{
			employeeService.addProject(id, projectId);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Gets all projects that the employee with given id is managing them 
	 * @param id employee id
	 * @return list od projects JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/projects/manager", method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjectsManged(@PathVariable long id, HttpServletResponse response) throws IOException{
		try{
			return employeeService.getManagerProjects(id);
		}
		catch (Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	/**
	 * Gets employee from currently logged in user
	 * @param request http request
	 * @return Employee JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/user")
	public Employee getEmployeeFromUser(HttpServletResponse response) throws IOException{
		try {
			return userService.getLoggedInUser().getEmployee();
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	
	/**
	 * Gets role from currently logged in user
	 * @param request http request
	 * @return String Role
	 * @throws IOException 
	 */
	@RequestMapping(value = "/user/role")
	public Object getRoleFromUser(HttpServletResponse response) throws IOException{
		try {
			return userService.getLoggedInUser().getRole();
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	
	/**
	 * Gets all effort information about employee with given id
	 * @param id employee id
	 * @return List of employee informations JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/effort/{id}")
	public List<EffortInformation> getEffortInfromation(@PathVariable long id, HttpServletResponse response) throws IOException{
		try{
			return employeeService.getEffortInformation(id);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	
}
