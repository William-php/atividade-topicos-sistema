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
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DepartmentService departmentService;
	
	public UserService() {}
	
	public UserEntity findById(Long id) {
		Optional<UserEntity> user = this.userRepo.findById(id);
		
		return user.orElseThrow( () -> new RuntimeException(
				"Usuário não encontrado pelo id: " + id
		));
	}
	
	public List<UserEntity> findAll() {
		return this.userRepo.findAll();
	}
	
	@Transactional
	public UserEntity create(UserEntity obj) {
		//DepartmentEntity d = this.departmentService.findById(id);
		obj.setId(null);
		//obj.setDepartment(d);
		this.userRepo.save(obj);
		return obj;
	}
	
	@Transactional 
	public UserEntity update(UserEntity obj) {
		UserEntity newObj = findById(obj.getId());
		
		newObj.setEmail(obj.getEmail());
		newObj.setName(obj.getName());

		return newObj;
	}
	
	
	public void delete(Long id) {
		findById(id);
		
		try {
			this.userRepo.deleteById(id);
		} catch (Exception e) {
			new RuntimeException("Não é possível excluir pois o objeto está relacionado a outra entidade");
		}
	}
}
