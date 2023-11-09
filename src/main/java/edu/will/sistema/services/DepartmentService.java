package edu.will.sistema.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.will.sistema.models.DepartmentEntity;
import edu.will.sistema.models.UserEntity;
import edu.will.sistema.repositories.DepartmentRepository;
import edu.will.sistema.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepo;
	
	
	public DepartmentEntity findById(Long id) {
		Optional<DepartmentEntity> department = this.departmentRepo.findById(id);
		return department.orElseThrow( () -> new RuntimeException(
				"Departamento não encontrado por id: " + id
		));
	}
	
	public List<DepartmentEntity> findAll() {
		return this.departmentRepo.findAll();
	}
	
	@Transactional
	public DepartmentEntity create(DepartmentEntity department) {		
		department.setId(null);
		
		this.departmentRepo.save(department);
		return department;
	}
	
	@Transactional
	public DepartmentEntity update(DepartmentEntity department) {
		department = findById(department.getId());
		department.setName(department.getName());
		return department;
	}
	
	public void delete(Long id) {
		findById(id);
		
		try {
			this.departmentRepo.deleteById(id);
		} catch (Exception e) {
			new RuntimeException("Departamento não encontrado pelo id");
		}
	}
}
