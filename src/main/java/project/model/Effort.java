package project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Effort extends BaseEntity implements Serializable {
	
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	@JsonManagedReference
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonManagedReference
	private Employee employee;
	
	private int percent;
	
	@OneToMany(mappedBy = "effort")
	@JsonBackReference
	private List<EffortInformation> effortInfromations;
	
	
	public Effort(){
		
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	
	
	
}
