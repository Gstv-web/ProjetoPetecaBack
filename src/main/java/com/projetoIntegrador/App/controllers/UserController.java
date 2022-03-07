package com.projetoIntegrador.App.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.projetoIntegrador.App.DTO.CredentialsDTO;
import com.projetoIntegrador.App.DTO.UserLoginDTO;
import com.projetoIntegrador.App.models.User;
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
	
	@PostMapping("/login")
	public ResponseEntity<CredentialsDTO> login(@Valid @RequestBody UserLoginDTO dto){
		return userService.login(dto);
	}

	@PostMapping("/sign")
	public ResponseEntity<User> cadastro(@RequestBody User user) {
		return userService.signUser(user);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<User> editUser(@Valid @RequestBody User user){
		return userService.updateUser(user);
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> GetAll(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable long id) {
		return userService.findById(id);
	}

	@GetMapping("/{razaoSocial}")
	public ResponseEntity<List<User>> GetByRazaoSocial(@PathVariable String razaoSocial){
		return userService.getUserByRazaoSocial(razaoSocial);
	}
	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
}
