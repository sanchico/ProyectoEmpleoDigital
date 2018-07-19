package com.proyecto.angularjs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.proyecto.angularjs.models.MotorBike;

@RepositoryRestResource(exported = false)
public interface MotorBikeRepository extends JpaRepository<MotorBike, Long>{

	MotorBike save(MotorBike manager);
	
	MotorBike findByNumber(String number);
	
	List<MotorBike> findByReserved (Boolean isreserved);
	
}
