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
import com.projetoIntegrador.App.service.PostagemService;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;

	@Autowired
	private PostagemService postService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> GetAll(){
		return postService.findAllposts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById (@PathVariable Long id) {
		return postService.findPostById(id);
	}

	@GetMapping("/titulo/{titulo}") 
	public ResponseEntity<List<Postagem>> GetByTitle(@PathVariable String titulo)  {
		return postService.findByTitle(titulo);
	}     

	@PostMapping("/new")
	public ResponseEntity <Postagem> newPostagem (@RequestBody Postagem newPost) {
		return postService.newPost(newPost);
	}

	@PutMapping("/edit")
	public ResponseEntity <Postagem> editPostagem (@RequestBody Postagem editPost) {
		return postService.editPost(editPost);
	}

	@DeleteMapping ("/delete/{id}")
	public void deletePostagem (@PathVariable long id) {
		repository.deleteById(id);
	}

}
