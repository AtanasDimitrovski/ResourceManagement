package project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
	
	public List<Employee> findByValid(short valid);
	
	public Employee findByIdAndValid(long id, short valid);
	
}
