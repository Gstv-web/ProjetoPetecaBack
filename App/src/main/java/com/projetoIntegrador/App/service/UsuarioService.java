package com.projetoIntegrador.App.service;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
	public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario);
	}
	
	public Optional(UsuarioLogin) Logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get.getUsuario());
		
		if(usuario.isPresente()) {
			if(encoder.matches(user.get().getSenha(),usuario.get().getUsuario())); {
			
			String auth = user.get().getUsuario() + ":" user.get().getSenha();
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic" + new String(encodedAuth);
			
			user.get().setToken(authHeader);
			user.get().setNome(usuario.get().getNome());
			
			return user
		}
	}
	
		return null;
	}
}
