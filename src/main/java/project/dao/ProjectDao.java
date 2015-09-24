package project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Project;

public interface ProjectDao extends JpaRepository<Project, Long> {
	
	public List<Project> findByValid(short valid);
}
