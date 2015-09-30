package project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Effort;
import project.model.Project;

public interface EffortDao extends JpaRepository<Effort, Long> {
		
	public List<Effort> findByProjectId(long id);

	public List<Effort> findByEmployeeId(long id);
	
	public List<Effort> findByEmployeeIdAndProjectId(long employeeId, long projectId);
}
