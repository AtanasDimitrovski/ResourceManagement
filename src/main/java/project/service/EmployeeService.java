package project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controller.EffortController;
import project.controller.EffortInformationController;
import project.controller.EmployeeController;
import project.controller.ProjectController;
import project.controller.UserController;
import project.model.Effort;
import project.model.EffortInformation;
import project.model.Employee;
import project.model.Project;
import project.model.User;
import project.model.User.Role;

@Service
public class EmployeeService {

	
	@Autowired
	private EmployeeController employeeController;
	
	@Autowired
	private EffortController effortController;
	
	@Autowired
	private ProjectController projectController;
	
	@Autowired
	private EffortInformationController effortInformationController;
	
	@Autowired
	private UserController userController;
	
	/**
	 * Gets employee with employee id
	 * @param id employee id
	 * @return Employee
	 */
	public Employee getEmployee(long id){
		return employeeController.findOne(id);
	}
	
	/**
	 * Gets all employees
	 * @return list od all employees
	 */
	public List<Employee> getEmployees(){
		return employeeController.findAll();
	}
	
	/**
	 * Deletes employee with employee id
	 * @param id employee id
	 */
	public void deleteEmployee(long id){
		Employee employee = employeeController.findOne(id);
		short valid = 0;
		employee.setValid(valid);
		User user = userController.findByEmployeeId(id);
		user.setValid(valid);
		employeeController.saveAndFlush(employee);
		userController.saveAndFlush(user);
	}
	
	/**
	 * Creates employee
	 * @param employee employee
	 * @return created employee
	 */
	public Employee createEmployee(Employee employee, User user){
		short a = 1;
		employee.setValid(a);
		user.setValid(a);
		user.setRole(Role.ROLE_USER);
		employee =  employeeController.save(employee);
		user.setEmployee(employee);
		user = userController.save(user);
		return employee;
	}
	
	/**
	 * Edits employee information
	 * @param id employee id
	 * @param name employee name
	 * @param lastName employee last name
	 * @param jobDescription employee job description
	 * @return Edited employee
	 */
	public Employee editEmployee(long id, String name, String lastName, String jobDescription){
		Employee employee = employeeController.findOne(id);
		if (name != null)
			employee.setName(name);
		if (lastName != null)
			employee.setLastName(lastName);
		if (jobDescription != null)
			employee.setJobDescription(jobDescription);
		return employeeController.saveAndFlush(employee);
	}
	
	/**
	 * Gets all projects that the employee with employee id is working on
	 * @param id employee id
	 * @return list od projects
	 */
	public List<Project> getProjects(long id){
		List<Effort> efforts = effortController.getEffortsByEmployeeId(id);
		List<Project> projects = new ArrayList<Project>();
		for (Effort effort : efforts) {
			if (effort.getProject().getValid() == 1)
				projects.add(effort.getProject());
		}
		return projects;
	}
	
	/**
	 * Removes emloyee with employee id from working on project with project id
	 * @param employeeId employee id
	 * @param projectId project id
	 */
	public void removeProject(long employeeId, long projectId){
		Effort effort = effortController.getEffortByProjectAndEmployee(projectId, employeeId);
		effortInformationController.deleteByEffortInformation(effort.getId());
		effortController.delete(effort);
	} 
	
	/**
	 * Sets employee with employee id to work on project with project id 
	 * @param employeeId employee id
	 * @param projectId project id
	 * @return True if employee set, false otherwise
	 */
	public boolean addProject(long employeeId, long projectId){
		
		if (effortController.getEffortByProjectAndEmployee(projectId, employeeId) == null){
			Effort effort = new Effort();
			Employee employee = employeeController.findOne(employeeId);
			if (employee == null) return false;
			effort.setEmployee(employee);
			Project project = projectController.findOne(projectId);
			if (project == null) return false;
			effort.setProject(project);
			effort = effortController.save(effort);
			if (effort == null)
				return false;
			else
				return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Gets all projects that the employee with employee id manages
	 * @param id employee id
	 * @return list od projects
	 */
	public List<Project> getManagerProjects(long id) {
		return projectController.findProjectByManager(id);
	}
	
	/**
	 * Get all effort information about employee with given id
	 * @param id employee id
	 * @return list of effort information
	 */
	public List<EffortInformation> getEffortInformation(long id) {
		List<Effort> efforts = effortController.getEffortsByEmployeeId(id);
		List<EffortInformation> effortInfos = new ArrayList<EffortInformation>();
		for (Effort effort : efforts) {
			effortInfos.addAll(effortInformationController.getEffortInformationByEffortId(effort.getId()));
		}
		return effortInfos;
	} 
	
}
