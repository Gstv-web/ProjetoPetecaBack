package com.projetoIntegrador.App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoIntegrador.App.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
