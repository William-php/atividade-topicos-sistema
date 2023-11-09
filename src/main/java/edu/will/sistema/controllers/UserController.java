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

import edu.will.sistema.models.UserEntity;
import edu.will.sistema.services.UserService;

@RestController
@RequestMapping("/usuario")
public class UserController {
	@Autowired
	private UserService userService;
	public UserController() {
		this.userService = new UserService();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
		UserEntity user = this.userService.findById(id);
		System.out.println("Que porra é essa: " + user);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping
	public List<UserEntity> findAll() {
		return this.userService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UserEntity user) {
		user = this.userService.create(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UserEntity user, @PathVariable Long id) {
		user.setId(id);
		this.userService.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
