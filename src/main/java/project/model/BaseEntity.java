package project.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public boolean isNew() {
		return (this.id == null);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity be = (BaseEntity) obj;

		if (this.getId() == null || be.getId() == null) {
			return super.equals(obj);
		} else {
			return this.getId().equals(be.getId());
		}
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id != null ? (this.getClass() + "-" + this.id).hashCode()
				: super.hashCode();
	}
}
