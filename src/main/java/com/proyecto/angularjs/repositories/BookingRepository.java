package com.proyecto.angularjs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.proyecto.angularjs.models.Booking;

@RepositoryRestResource(exported = false)
public interface BookingRepository extends JpaRepository<Booking, Long>{

	Booking save(Booking manager);
	
	
}
