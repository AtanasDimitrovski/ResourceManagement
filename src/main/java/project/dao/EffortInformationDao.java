package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.EffortInformation;

public interface EffortInformationDao extends JpaRepository<EffortInformation, Long> {

}
