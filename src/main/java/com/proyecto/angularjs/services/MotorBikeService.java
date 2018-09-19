package com.proyecto.angularjs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proyecto.angularjs.models.MotorBike;
import com.proyecto.angularjs.repositories.MotorBikeRepository;
import com.proyecto.angularjs.repositories.UserRepository;

@Component
public class MotorBikeService {

	@Autowired
	private MotorBikeRepository motorBikeRepository;
	
	public MotorBikeService() {
	}

	public MotorBikeService(MotorBikeRepository motorBikeRepository) {
		this.motorBikeRepository = motorBikeRepository;
	}

	public List<MotorBike> findAllMotorBikes() {
		return motorBikeRepository.findAll();
	}

	public MotorBike findById(Long id) {
		return motorBikeRepository.getOne(id);
	}

	public MotorBike findByNumber(String number) {
		return motorBikeRepository.findByNumber(number);
	}

	public boolean isMotorBikeExist(MotorBike motorBike) {
		return findByNumber(motorBike.getNumber()) != null;
	}

	public MotorBike saveMotorBike(MotorBike motorBike) {
		return motorBikeRepository.save(motorBike);
	}

	public MotorBike updateMotorBike(MotorBike motorBike){
		return saveMotorBike(motorBike);
	}

	public void deleteMotorBikeById(Long id){
		motorBikeRepository.deleteById(id);
	}

	public List<MotorBike> findByReserved(boolean reserved){
		return motorBikeRepository.findByReserved(reserved);
	}
	
}
