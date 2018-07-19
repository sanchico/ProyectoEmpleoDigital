package com.proyecto.angularjs.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.angularjs.models.MotorBike;
import com.proyecto.angularjs.services.MotorBikeService;
import com.proyecto.angularjs.types.CustomErrorType;

@RestController
@RequestMapping("/api/admin/motorbike")
public class MotorBikeController {
    public static final Logger logger = LoggerFactory.getLogger(MotorBikeController.class);

    @Autowired
    private MotorBikeService motorBikeService;
    
    //necesario para los testUnitarios
	public MotorBikeController() {
	}

	public MotorBikeController(MotorBikeService motorBikeService) {
		this.motorBikeService = motorBikeService;
	}
	
	/**
	 * Obtiene el listado con todas las motos del sistema.
	 */
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<MotorBike>> listAllMotorBikes() {
		List<MotorBike> motorBikes = motorBikeService.findAllMotorBikes();
		if (motorBikes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MotorBike>>(motorBikes, HttpStatus.OK);
	}
    
    /**
	 * Obtiene la información de una moto.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMotorBike(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		MotorBike motorBike = motorBikeService.findById(id);
		if (motorBike == null) {
			logger.error("MotorBike with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("MotorBike with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MotorBike>(motorBike, HttpStatus.OK);
	}
	
	/**
	 * Crea una moto, comprobando que no exista previamente.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createMotorBike(@RequestBody MotorBike motorBike) {
		logger.info("Creating MotorBike : {}", motorBike);

		if (motorBikeService.isMotorBikeExist(motorBike)) {
			logger.error("Unable to create. A MotorBike with name {} already exist", motorBike.getNumber());
			return new ResponseEntity(new CustomErrorType("Unable to create. A motorBike with number " + 
			motorBike.getNumber() + " already exist."),HttpStatus.CONFLICT);
		}
		motorBikeService.saveMotorBike(motorBike);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	/**
	 * Actualiza una moto.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMotorBike(@PathVariable("id") long id, @RequestBody MotorBike motorBike) {
		logger.info("Updating MotorBike with id {}", id);

		MotorBike currentMotorBike = motorBikeService.findById(id);

		if (currentMotorBike == null) {
			logger.error("Unable to update. MotorBike with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. MotorBike with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		logger.info("Updating MotorBike with id {}", currentMotorBike.getId_Motorbike());
		currentMotorBike.setNumber(motorBike.getNumber());

		motorBikeService.updateMotorBike(motorBike);
		return new ResponseEntity<MotorBike>(currentMotorBike, HttpStatus.OK);
	}
	
	/**
	 * Borra una moto.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMotorBike(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting MotorBike with id {}", id);

		MotorBike motorBike = motorBikeService.findById(id);
		if (motorBike == null) {
			logger.error("Unable to delete. MotorBike with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. MotorBike with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		motorBikeService.deleteMotorBikeById(id);
		return new ResponseEntity<MotorBike>(HttpStatus.NO_CONTENT);
	}

}
