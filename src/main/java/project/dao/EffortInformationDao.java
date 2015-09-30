package project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.EffortInformation;

public interface EffortInformationDao extends JpaRepository<EffortInformation, Long> {
	
	public List<EffortInformation> findByEffortId(long id);
}	
