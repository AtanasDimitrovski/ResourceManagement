package project.rest_service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import project.model.EffortInformation;
import project.model.Employee;
import project.model.Project;
import project.service.ProjectService;

@RestController
@RequestMapping(value = "/data/projects")
public class ProjectResource {
	
	@Autowired
	private ProjectService projectService;
	
	/**
	 * Gets all projects
	 * @return List od projects JSON
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjects(HttpServletResponse response) throws IOException{
		try{
			return projectService.getProjects();
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	
	/**
	 * Gets project with given id
	 * @param id project id
	 * @return Project
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Project getProject(@PathVariable long id, HttpServletResponse response) throws IOException{
		Project project = projectService.getProject(id);
		if (project==null)
			response.sendError(HttpServletResponse.SC_ACCEPTED, "Project with ID=" + id + " doesn't exist");
		return project;
	}
	
	/**
	 * Deletes project with given id
	 * @param id project id
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id, HttpServletResponse response) throws IOException{
		try{
			projectService.delete(id, response);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Creates project
	 * @param project Project in JSON format
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody Project project, HttpServletResponse response) throws IOException{
		try{
			projectService.createProject(project);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Edits project with given id and information
	 * @param id project id
	 * @param project Project information JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable long id, @RequestBody Project project, HttpServletResponse response) throws IOException{
		try{
			projectService.editProject(id, project.getName(), project.getDescription(), 
					project.getFromDate(), project.getToDate(), project.getStatus(), project.getManager());
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
		
	/**
	 * Gets all employees that are working on the project with given id
	 * @param id project id
	 * @return List of employees JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/employees", method = RequestMethod.GET, produces = "application/json")
	public List<Employee> getEmployees(@PathVariable long id, HttpServletResponse response) throws IOException{
		try{
			return projectService.getEmployees(id);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	
	/**
	 * Removes employee that works on project with given id
	 * @param id project id
	 * @param employeeId employee id
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/employees/{employeeId}", method = RequestMethod.DELETE)
	public void removeEmployeeFromProject(@PathVariable long id, @PathVariable long employeeId, HttpServletResponse response) throws IOException{
		try{
			projectService.removeEmployee(id, employeeId);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Adds employee to work on project with given id
	 * @param id project id
	 * @param employeeId employee id
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/employees", method = RequestMethod.POST)
	public void addEmployee(@PathVariable long id, @RequestParam("employeeId") long employeeId, HttpServletResponse response) throws IOException{
		try{
			projectService.addEmployee(id, employeeId);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Gets manager of project with given id 
	 * @param id project id
	 * @return Employee JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/manager", method = RequestMethod.GET, produces = "application/json")
	public Employee getManager(@PathVariable long id, HttpServletResponse response) throws IOException{
		try{
		return projectService.getManager(id);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	
	/**
	 * Gets effort information for employee with given id about project with given id
	 * @param id project id
	 * @param employeeId employee id
	 * @return List of effort information JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/employees/{employeeId}/effort", method = RequestMethod.GET, produces = "application/json")
	public List<EffortInformation> getEffortInformation(@PathVariable long id, @PathVariable long employeeId, HttpServletResponse response) throws IOException{
		try{
			return projectService.getEffortInformation(id, employeeId);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	
	/**
	 * Adds effort information about employee with given id about project with given id
	 * @param id project id
	 * @param employeeId employee id
	 * @param effortInformation Effort information JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/employees/{employeeId}/effort", method = RequestMethod.POST)
	public void addEffortInformation(@PathVariable long id, @PathVariable long employeeId, @RequestBody EffortInformation effortInformation, HttpServletResponse response) throws IOException{
		try{
			projectService.addEffortInformation(id, employeeId, effortInformation);
		}
		catch (Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Edits effort information about employee with given id
	 * @param id
	 * @param effortInformation
	 * @throws IOException 
	 */
	@RequestMapping(value = "/effort/{id}", method = RequestMethod.PUT)
	public void editEffortInformation(@PathVariable long id, @RequestBody EffortInformation effortInformation, HttpServletResponse response) throws IOException{
		try{
			projectService.editEffortInformation(id, effortInformation);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
		}
	}
	
	/**
	 * Gets all effort informations about employees that are working on the project with given id
	 * @param id project id
	 * @return List of effort information JSON
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{id}/employees/effort")
	public List<EffortInformation> getAllEffortInformationForProject(@PathVariable long id, HttpServletResponse response) throws IOException{
		try{
			return projectService.getEffortInformationsForProject(id);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_ACCEPTED, e.getMessage().toString());
			return null;
		}
	}
	

	
}












