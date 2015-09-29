package project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonDeserialize(using = EmployeeDeserializer.class)
public class Employee extends BaseEntity implements Serializable {
	
	
	private String name;
	
	private String lastName;
	
	private String jobDescription;
	
	private short valid;
	
	@OneToMany(mappedBy = "employee")
	@JsonBackReference
	private List<Effort> efforts = new ArrayList<Effort>();
	
	public Employee(){
		
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

	public short getValid() {
		return valid;
	}

	public void setValid(short valid) {
		this.valid = valid;
	}
	
	public List<Effort> getEfforts() {
		return efforts;
	}
	
	public void setEfforts(List<Effort> efforts) {
		this.efforts = efforts;
	}
	
	
	
	
}
