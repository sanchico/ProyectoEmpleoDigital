package com.proyecto.angularjs.repositories;

import com.proyecto.angularjs.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {

	User save(User manager);

	User findByName(String name);
	

}