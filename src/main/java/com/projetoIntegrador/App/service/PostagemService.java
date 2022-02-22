package com.projetoIntegrador.App.service;

import java.util.List;
import java.util.stream.Collectors;

import com.projetoIntegrador.App.models.Postagem;
import com.projetoIntegrador.App.repositories.PostagemRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {
    
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostagemRepository repository;


    public ResponseEntity<List<Postagem>> foundPosts(List<Postagem> posts) {
        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
            .body(posts.stream()
            .map(resp -> mapper.map(posts, Postagem.class))
            .collect(Collectors.toList()));
        }
    }

    public ResponseEntity<List<Postagem>> findAllposts() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    public ResponseEntity<List<Postagem>> findByTitle(String titulo) {
        List<Postagem> postsByTitle = repository.findAllByTituloContainingIgnoreCase(titulo);
        return foundPosts(postsByTitle);
    }

    public ResponseEntity<Postagem> newPost(Postagem post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }

    public ResponseEntity<Postagem> editPost(Postagem edit) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(edit));
    }

    public void deletePost(Long id) {
        repository.deleteById(id);
    }
}
