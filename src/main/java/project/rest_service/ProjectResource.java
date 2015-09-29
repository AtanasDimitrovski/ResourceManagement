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

import project.model.Employee;
import project.model.Project;
import project.service.ProjectService;

@RestController
@RequestMapping(value = "/data/projects")
public class ProjectResource {
	
	@Autowired
	private ProjectService projectService;

	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Project> getProjects(){
		return projectService.getProjects();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Project getProject(@PathVariable long id){
		return projectService.getProject(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable long id){
		projectService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public void create(@RequestBody Project project){
		projectService.createProject(project);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable long id, @RequestBody Project project){
		projectService.editProject(id, project.getName(), project.getDescription(), project.getFromDate(), project.getToDate(), project.getStatus());
	}
		
	@RequestMapping(value = "/{id}/employees", method = RequestMethod.GET, produces = "application/json")
	public List<Employee> getEmployees(@PathVariable long id){
		return projectService.getEmployees(id);
	}
	
	@RequestMapping(value = "/{id}/employees/{employeeId}", method = RequestMethod.DELETE)
	public void removeEmployeeFromProject(@PathVariable long id, @PathVariable long employeeId){
		projectService.removeEmployee(id, employeeId);
	}
	
	@RequestMapping(value = "/{id}/employees", method = RequestMethod.POST, produces = "application/json")
	public void addEmployee(@PathVariable long id, @RequestParam("employeeId") long employeeId, @RequestParam("percent") int percent){
		projectService.addEmployee(id, employeeId, percent);
	}
	
	
	
	
}












