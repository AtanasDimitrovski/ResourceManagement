package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Effort;

public interface EffortDao extends JpaRepository<Effort, Long> {
	
	
}
