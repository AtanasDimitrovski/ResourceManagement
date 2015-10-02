package project.rest_service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjects(){
		return projectService.getProjects();
	}
	
	/**
	 * Gets project with given id
	 * @param id project id
	 * @return Project
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Project getProject(@PathVariable long id){
		return projectService.getProject(id);
	}
	
	/**
	 * Deletes project with given id
	 * @param id project id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id){
		projectService.delete(id);
	}
	
	/**
	 * Creates project
	 * @param project Project in JSON format
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody Project project){
		projectService.createProject(project);
	}
	
	/**
	 * Edits project with given id and information
	 * @param id project id
	 * @param project Project information JSON
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable long id, @RequestBody Project project){
		projectService.editProject(id, project.getName(), project.getDescription(), project.getFromDate(), project.getToDate(), project.getStatus(), project.getManager());
	}
		
	/**
	 * Gets all employees that are working on the project with given id
	 * @param id project id
	 * @return List of employees JSON
	 */
	@RequestMapping(value = "/{id}/employees", method = RequestMethod.GET, produces = "application/json")
	public List<Employee> getEmployees(@PathVariable long id){
		return projectService.getEmployees(id);
	}
	
	/**
	 * Removes employee that works on project with given id
	 * @param id project id
	 * @param employeeId employee id
	 */
	@RequestMapping(value = "/{id}/employees/{employeeId}", method = RequestMethod.DELETE)
	public void removeEmployeeFromProject(@PathVariable long id, @PathVariable long employeeId){
		projectService.removeEmployee(id, employeeId);
	}
	
	/**
	 * Adds employee to work on project with given id
	 * @param id project id
	 * @param employeeId employee id
	 */
	@RequestMapping(value = "/{id}/employees", method = RequestMethod.POST)
	public void addEmployee(@PathVariable long id, @RequestParam("employeeId") long employeeId){
		projectService.addEmployee(id, employeeId);
	}
	
	/**
	 * Gets manager of project with given id 
	 * @param id project id
	 * @return Employee JSON
	 */
	@RequestMapping(value = "/{id}/manager", method = RequestMethod.GET, produces = "application/json")
	public Employee getManager(@PathVariable long id){
		return projectService.getManager(id);
	}
	
	/**
	 * Gets effort information for employee with given id about project with given id
	 * @param id project id
	 * @param employeeId employee id
	 * @return List of effort information JSON
	 */
	@RequestMapping(value = "/{id}/employees/{employeeId}/effort", method = RequestMethod.GET, produces = "application/json")
	public List<EffortInformation> getEffortInformation(@PathVariable long id, @PathVariable long employeeId){
		return projectService.getEffortInformation(id, employeeId);
	}
	
	/**
	 * Adds effort information about employee with given id about project with given id
	 * @param id project id
	 * @param employeeId employee id
	 * @param effortInformation Effort information JSON
	 */
	@RequestMapping(value = "/{id}/employees/{employeeId}/effort", method = RequestMethod.POST)
	public void addEffortInformation(@PathVariable long id, @PathVariable long employeeId, @RequestBody EffortInformation effortInformation){
		projectService.addEffortInformation(id, employeeId, effortInformation);
	}
	
	/**
	 * Edits effort information about employee with given id
	 * @param id
	 * @param effortInformation
	 */
	@RequestMapping(value = "/effort/{id}", method = RequestMethod.PUT)
	public void editEffortInformation(@PathVariable long id, @RequestBody EffortInformation effortInformation){
		projectService.editEffortInformation(id, effortInformation);
	}
	
	/**
	 * Gets all effort informations about employees that are working on the project with given id
	 * @param id project id
	 * @return List of effort information JSON
	 */
	@RequestMapping(value = "/{id}/employees/effort")
	public List<EffortInformation> getAllEffortInformationForProject(@PathVariable long id){
		return projectService.getEffortInformationsForProject(id);
	}
	
}












