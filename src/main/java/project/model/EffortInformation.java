package project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import project.deserializers.EffortInformationDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * The EffortInformation class represent information about the effort of the
 * employee that works on the given project.
 * <p>
 * It gives information about: How much a day is the employee working on the
 * project from one date to another, and what role has the employee on the project.
 * 
 * @author Atanas Dimitrovski
 *
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonDeserialize(using = EffortInformationDeserializer.class)
public class EffortInformation extends BaseEntity {

	
	public static enum Role{
		ROLE_BACKEND, ROLE_FRONTEND, ROLE_DESIGNER
	}
	
	@Temporal(TemporalType.DATE)
	private Date fromDate;
	
	@Temporal(TemporalType.DATE)
	private Date toDate;
	
	private int percent;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "effort_id")
	@JsonManagedReference
	private Effort effort;
	
	public EffortInformation(){
		
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

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Effort getEffort() {
		return effort;
	}

	public void setEffort(Effort effort) {
		this.effort = effort;
	}
	
	
	
}
