package com.proyecto.angularjs.controllers;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.angularjs.models.Booking;
import com.proyecto.angularjs.models.MotorBike;
import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.services.BookingService;
import com.proyecto.angularjs.services.MotorBikeService;
import com.proyecto.angularjs.services.UserService;
import com.proyecto.angularjs.types.CustomErrorType;

@RestController
@RequestMapping("/api/user/booking")
public class BookingController {
	public static final Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserService userService;

	@Autowired
	private MotorBikeService motorBikeService;

	// necesario para los testUnitarios
	public BookingController() {
	}

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	/**
	 * Obtiene el listado con todos los alquileres del sistema.
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<MotorBike>> listAllMotorbikeDisponible() {

		List<MotorBike> motosDisponibles = motorBikeService.findByReserved(true);

		if (motosDisponibles.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<MotorBike>>(motosDisponibles, HttpStatus.OK);
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
			return new ResponseEntity(new CustomErrorType("MotorBike with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MotorBike>(motorBike, HttpStatus.OK);
	}

	/**
	 * Obtiene el listado con todos los alquileres del sistema.
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Booking> listMotorbikeUsuario() {

		Booking booking = null;
		List<MotorBike> motosDisponibles = motorBikeService.findByReserved(true);

		List<Booking> bookingUsuario = userService.findById(UsarioSesion().getId_user()).getBookingsUser();

		if (bookingUsuario.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		for (Booking book : bookingUsuario) {

			if (motorBikeService.findById(book.getMotorbike_id()).getReserved() == false) {
				System.out.println("obeternet booking  " + book.getId_booking());
				booking = book;

			}

		}

		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}

	/**
	 * Crear booking y actualiza la moto y la pone a no disponible (alquilada)
	 */
	@RequestMapping(value = "/alquilar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> createBooking(@PathVariable("id") Long id, @RequestBody Booking booking) {

		logger.info("Creating Booking : {}");

		if (booking == null || id == null) {
			logger.error("Unable to reserve. No moto id");
			return new ResponseEntity(new CustomErrorType("Unable to reserved. No motorBike id provided "),
					HttpStatus.CONFLICT);
		}

		booking.setUser_id(UsarioSesion().getId_user());
		System.out.println("id motoooooooooooo" + id);

		motorBikeService.findById(id).setReserved(false);
		booking.setMotorbike_id(id);
		System.out.println("crear booking " + booking.getId_booking());
		bookingService.saveBooking(booking);
		return new ResponseEntity<String>(HttpStatus.CREATED);

	}
	//Actualiza booking y actualiza la moto y la pone a disponible (no alquilada)
	@RequestMapping(value = "/devolver/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBooking(@PathVariable("id") Long id, @RequestBody Booking booking) {

		logger.info("devolviendo moto: {}");

		if (booking == null || id == null) {
			logger.error("No booking found");
			return new ResponseEntity(new CustomErrorType("No booking found"), HttpStatus.CONFLICT);
		}

		booking.setUser_id(UsarioSesion().getId_user());

		motorBikeService.findById(id).setReserved(true);
		booking.setMotorbike_id(id);
		bookingService.updateBooking(booking);

		return new ResponseEntity<String>(HttpStatus.ACCEPTED);

	}

	private User UsarioSesion() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return userService.findByName(auth.getName());

	}

}
