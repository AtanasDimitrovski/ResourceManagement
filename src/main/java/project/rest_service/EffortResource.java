package project.rest_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.model.Effort;
import project.service.EffortService;

@RestController
@RequestMapping(value = "/data/efforts")
public class EffortResource {
	
	@Autowired
	private EffortService effortService;
	
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Effort> getAll(){
		return effortService.getAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Effort get(@PathVariable long id){
		return effortService.get(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id){
		effortService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestParam("projectId") long projectId, @RequestParam("employeeId") long employeeId){
		effortService.create(employeeId, projectId);
	}
	
/*	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, params = "employeeId")
	public void changeEmployee(@PathVariable long id, @RequestParam("employeeId") long employeeId){
		effortService.changeEmployee(id, employeeId);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, params = "projectId")
	public void changeProject(@PathVariable long id, @RequestParam("projectId") long projectId){
		effortService.changeProject(id, projectId);
	}*/
}
