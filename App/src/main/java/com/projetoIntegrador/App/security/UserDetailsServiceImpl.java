package com.projetoIntegrador.App.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetoIntegrador.App.models.User;
import com.projetoIntegrador.App.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException(email + " not found."));
		
		return user.map(UserDetailsImpl::new).get();
	}
	
}
