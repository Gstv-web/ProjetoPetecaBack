package com.projetoIntegrador.App.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.projetoIntegrador.App.models.UserLogin;
import com.projetoIntegrador.App.repositories.UserRepository;
import com.projetoIntegrador.App.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private UserService userService;
	
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
		return ResponseEntity.ok(repository.findAllByEmailContainingIgnoreCase(user));
	}
	
	@PutMapping("/edit")
	public ResponseEntity<User> editUser(@RequestBody User newUser){
		return ResponseEntity.status(200).body(repository.save(newUser));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCategoria(@PathVariable long id) {
		repository.deleteById(id);
	}

	@PostMapping("/logar")
    public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> usuario){
		return userService.logar(usuario).map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

    @PostMapping("/cadastrar")
    public ResponseEntity<User> Post(@RequestBody User usuario) {
        return userService.cadastrarUsuario(usuario).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
		.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
}
