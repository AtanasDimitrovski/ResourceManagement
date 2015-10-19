package project.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import project.model.EffortInformation;

public interface EffortInformationDao extends JpaRepository<EffortInformation, Long> {
	
	public List<EffortInformation> findByEffortId(long id);
	//public void deleteByEffortId(long id);
	
	 @Modifying
	 @Transactional
	  @Query("delete from EffortInformation e where e.effort.id = :id")
	  void deleteByEffortId(@Param("id") long id);
}	
