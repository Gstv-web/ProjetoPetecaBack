package com.projetoIntegrador.App.service;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projetoIntegrador.App.DTO.CredentialsDTO;
import com.projetoIntegrador.App.DTO.UserLoginDTO;
import com.projetoIntegrador.App.models.User;
import com.projetoIntegrador.App.repositories.UserRepository;

	@Service
	public class UserService {

	
	private CredentialsDTO credentials;

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper mapper;


	// Criptografar senha
	private static String encrypt(String encrypt) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(encrypt);
	}

	// Gera o token Basic
	private static String basicTokenGenerator(String email, String senha) {
		String auth = email + ":" + senha;
		byte[] authBase64 = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(authBase64);
	}
	
	//Cadastrar usuário
	public ResponseEntity<User> signUser(User user) {
		Optional<User> optional = repository.findByEmail(user.getEmail());
		
		if(optional.isEmpty()) {
			user.setSenha(encrypt(user.getSenha()));
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(user));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO - Usuário já existente!");
		}
	}
	
	// Autentica usuário para login
	public ResponseEntity<CredentialsDTO> login(@Valid UserLoginDTO dto) {
		return repository.findByEmail(dto.getEmail()).map(resp -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(dto.getSenha(), resp.getSenha())) {
				credentials = new CredentialsDTO(
								resp.getUserId(),
								basicTokenGenerator(dto.getEmail(), dto.getSenha()),
								resp.getEmail(),
								dto.getRazaoSocial(),
								resp.getFoto());
				return ResponseEntity.status(HttpStatus.OK).body(credentials);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha inválida.");
			}
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não existe.");
		});
	}
	
	// Edita um usuário. Caso a senha seja alterada, criptografa a nova senha
	public ResponseEntity<User> updateUser(@Valid User user) {
		Optional<User> optional = repository.findByEmail(user.getEmail());

		if (optional.isPresent()) {
			if (user.getSenha() != optional.get().getSenha()) {
				user.setSenha(user.getSenha());
				user.setSenha(encrypt(user.getSenha()));
			}
		}
		return repository.findById(user.getUserId())
							.map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(user)))
							.orElseGet(() -> {
								throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não encontrado");
							});
	}

	// Mapeamento de usuários e devolve uma lista
	public ResponseEntity<List<User>> foundUsers(List<User> user) {
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK)
			.body(user.stream()
			.map(resp -> mapper.map(resp, User.class))
			.collect(Collectors.toList()));
		}
	}

	// Mapeamento de usuários e devolve uma lista por Razão Social
	public ResponseEntity<List<User>> getUserByRazaoSocial(String razaoSocial) {
		List<User> userByRazaoSocial = repository.findAllByRazaoSocialIgnoringCase(razaoSocial);
		return foundUsers(userByRazaoSocial);
	}

	// Cria uma lista e devolve todos os usuários
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = repository.findAll();

		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity deleteUser(Long id) {
		Optional<User> optional = repository.findById(id);

		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
		}
	}
}
