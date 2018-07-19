package com.proyecto.angularjs.services;

import java.util.List;

import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
	private UserRepository userRepository;
	
	public UserService() {
	}

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return userRepository.getOne(id);
	}

	public User findByName(String name) {
	
		return userRepository.findByName(name);
		
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User user){
		return saveUser(user);
	}

	public void deleteUserById(Long id){
		userRepository.deleteById(id);
	}
}