package project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	
	
	
	
}
