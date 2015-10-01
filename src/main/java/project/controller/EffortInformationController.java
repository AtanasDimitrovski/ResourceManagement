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

	public void edit(long id, EffortInformation effortInformation) {
		try {
			EffortInformation effortInfo = super.findOne(id);
			if (effortInformation.getFromDate() != null)
				effortInfo.setFromDate(effortInformation.getFromDate());
			if (effortInformation.getToDate() != null)
				effortInfo.setToDate(effortInformation.getToDate());
			if (effortInformation.getPercent() != 0)
				effortInfo.setPercent(effortInformation.getPercent());
			if (effortInformation.getRole() != null)
				effortInfo.setRole(effortInformation.getRole());
			super.saveAndFlush(effortInfo);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
