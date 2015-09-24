package project.rest_service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public void delete(long id){
		projectService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public void create(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("status") String status, @RequestParam("fromDate") Date from, @RequestParam("toDate") Date to){
		projectService.createProject(name, description, status, from, to);
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
	public void changeFromDate(@PathVariable long id, @RequestParam Date from){
		projectService.changeFromDate(id, from);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", params = "to")
	public void changeToDate(@PathVariable long id, @RequestParam Date to){
		projectService.changeToDate(id, to);
	}
}
