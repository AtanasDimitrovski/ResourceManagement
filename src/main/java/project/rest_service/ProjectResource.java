package project.rest_service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	public void create(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("status") String status, @RequestParam("fromDate") String from, @RequestParam("toDate") String to){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
		Date fromDate = format.parse(from);
		Date toDate = format.parse(to);
		projectService.createProject(name, description, status, fromDate, toDate);
		}
		catch(Exception e){
			
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", params = "name")
	public void changeName(@PathVariable long id, @RequestParam String name){
		projectService.changeName(id, name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", params = "description")
	public void changeDescription(@PathVariable long id, @RequestParam String description){
		projectService.changeDescription(id, description);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", params = "status")
	public void changeStatus(@PathVariable long id, @RequestParam String status){
		projectService.changeStatus(id, status);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", params = "from")
	public void changeFromDate(@PathVariable long id, @RequestParam String from){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date fromDate = format.parse(from);
			projectService.changeFromDate(id, fromDate);
		}
		catch(Exception e){
			
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", params = "to")
	public void changeToDate(@PathVariable long id, @RequestParam String to){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date toDate = format.parse(to);
			projectService.changeToDate(id, toDate);
		}
		catch(Exception e){
			
		}
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












