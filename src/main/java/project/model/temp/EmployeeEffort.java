package project.model.temp;

import java.util.Date;

import project.model.EffortInformation;
import project.model.EffortInformation.Role;

public class EmployeeEffort {
	
	private long effortInformationId;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Role role;
	
	private long effortId;
	
	private long employeeId;
	
	private String name;
	
	private String lastName;
	
	private String jobDescription;
	
	private int percent;
	
	public EmployeeEffort(EffortInformation effortInfo){
		effortInformationId = effortInfo.getId();
		fromDate = effortInfo.getFromDate();
		toDate = effortInfo.getToDate();
		role = effortInfo.getRole();
		effortId = effortInfo.getEffort().getId();
		employeeId = effortInfo.getEffort().getEmployee().getId();
		name = effortInfo.getEffort().getEmployee().getName();
		lastName = effortInfo.getEffort().getEmployee().getLastName();
		jobDescription = effortInfo.getEffort().getEmployee().getJobDescription();
		percent = effortInfo.getPercent();
	}
	

	public long getEffortInformationId() {
		return effortInformationId;
	}

	public void setEffortInformationId(long effortInformationId) {
		this.effortInformationId = effortInformationId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getEffortId() {
		return effortId;
	}

	public void setEffortId(long effortId) {
		this.effortId = effortId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}


	public int getPercent() {
		return percent;
	}


	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	
	
	
}
