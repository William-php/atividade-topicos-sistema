package edu.will.sistema.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.will.sistema.models.DepartmentEntity;
import edu.will.sistema.services.DepartmentService;

@RestController
@RequestMapping("/departamento")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping
	private List<DepartmentEntity> findAll() {
		return this.departmentService.findAll();
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<DepartmentEntity> findById(@PathVariable Long id) {
		DepartmentEntity department = this.departmentService.findById(id);
		return ResponseEntity.ok().body(department);
	}
	
	@PostMapping
	private ResponseEntity<Void> create(@RequestBody DepartmentEntity department) {
		department = this.departmentService.create(department);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(department.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Void> update(@RequestBody DepartmentEntity department, @PathVariable Long id) {
		department.setId(id);
		this.departmentService.update(department);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Void> delete(@PathVariable Long id) {
		this.departmentService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
