package edu.will.sistema.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_department")
public class DepartmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private Long id;
	
	@Column(name = "department_name")
	private String name;
	
	@OneToMany(mappedBy = "department")
	private List<UserEntity> users;
	
	public DepartmentEntity() {}
	
	public DepartmentEntity(String name) {
		this.name = name;
		this.users = new ArrayList<UserEntity>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		DepartmentEntity other = (DepartmentEntity) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return "Nome departamento: " + this.getName() + 
				"\nUsuários: " + this.getUsers();
	}
	
	
	
}
