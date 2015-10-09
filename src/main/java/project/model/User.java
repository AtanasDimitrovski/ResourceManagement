package project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import project.deserializers.EmployeeDeserializer;
import project.deserializers.UserDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonDeserialize(using = UserDeserializer.class)
public class User extends BaseEntity {
	
	
	public static enum Role{	
		ROLE_ADMIN, ROLE_USER
	}
	
	@Column(unique = true)
	private String username;
	
	@Column(name = "user_password")
	private String password;
	
	private short valid;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", length = 20, nullable = false)
	private Role role;
	
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public short getValid() {
		return valid;
	}

	public void setValid(short valid) {
		this.valid = valid;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
