package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import project.dao.EffortInformationDao;
import project.model.EffortInformation;

@Controller
public class EffortInformationController extends BaseController<EffortInformation, JpaRepository<EffortInformation,Long>> {

	@Autowired
	private EffortInformationDao effortInformationDao;
	
	@Override
	protected JpaRepository<EffortInformation, Long> getDao() {
		return effortInformationDao;
	}
	
	public List<EffortInformation> getEffortInformationByEffortId(long id){
		return effortInformationDao.findByEffortId(id);
	}
	
	public void deleteByEffortInformation(long id){
		effortInformationDao.deleteByEffortId(id);
	}
}
