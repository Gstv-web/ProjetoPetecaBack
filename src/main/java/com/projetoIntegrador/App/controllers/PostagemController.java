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

import com.projetoIntegrador.App.models.Postagem;
import com.projetoIntegrador.App.repositories.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{postagem}") 
	public ResponseEntity<List<Postagem>> GetByPostagem (@PathVariable String postagem)  {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(postagem));
	}     

	@PostMapping("/new")
	public ResponseEntity <Postagem> newPostagem (@RequestBody Postagem newPostagem) {
		return ResponseEntity.status(201).body(repository.save(newPostagem));
	}

	@PutMapping("/edit")
	public ResponseEntity <Postagem> editPostagem (@RequestBody Postagem editPostagem) {
		return ResponseEntity.status (200).body(repository.save(editPostagem));
	}

	@DeleteMapping ("/delete/{id}")
	public void deletePostagem (@PathVariable long id) {
		repository.deleteById(id);
	}

}
