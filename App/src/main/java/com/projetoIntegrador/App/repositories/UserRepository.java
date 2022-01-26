package com.projetoIntegrador.App.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador.App.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public List<User> findAllByRazaoSocialContainingIgnoreCase (String razaoSocial);	
	

}
