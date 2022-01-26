package com.projetoIntegrador.App.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoIntegrador.App.models.User;
import com.projetoIntegrador.App.repositories.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{user}")
	public ResponseEntity<List<User>> GetByUser(@PathVariable String user){
		return ResponseEntity.ok(repository.findAllByRazaoSocialContainingIgnoreCase(user));
	}
	
	@PostMapping("/new")
	public ResponseEntity<User> newUser(@RequestBody User newUser){
		return ResponseEntity.status(201).body(repository.save(newUser));
	}
	
	@PutMapping("/edit")
	public ResponseEntity<User> editUser(@RequestBody User newUser){
		return ResponseEntity.status(200).body(repository.save(newUser));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCategoria(@PathVariable long id) {
		repository.deleteById(id);
	}	

	
}
