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

import com.proyecto.angularjs.models.Booking;
import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.services.BookingService;
import com.proyecto.angularjs.services.UserService;
import com.proyecto.angularjs.types.CustomErrorType;

@RestController
@RequestMapping("/api/user/booking")
public class BookingController {
	public static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private BookingService bookingService;
	
	//necesario para los testUnitarios
		public BookingController() {
		}

		public BookingController(BookingService bookingService) {
			this.bookingService = bookingService;
		}
		
		/**
		 * Obtiene el listado con todos los alquileres del sistema.
		 */
	    @RequestMapping(value = "/", method = RequestMethod.GET)
		public ResponseEntity<List<Booking>> listAllBookings() {
			List<Booking> bookings = bookingService.findAllBookings();
			if (bookings.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
		}
	    
	    /**
		 * Obtiene la información de un alquiler.
		 */
		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getBooking(@PathVariable("id") long id) {
			logger.info("Fetching Booking with id {}", id);
			Booking booking = bookingService.findById(id);
			if (booking == null) {
				logger.error("Booking with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("Booking with id " + id 
						+ " not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Booking>(booking, HttpStatus.OK);
		}
		
		/**
		 * Borra un alquiler.
		 */
		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteBooking(@PathVariable("id") long id) {
			logger.info("Fetching & Deleting Booking with id {}", id);

			Booking booking = bookingService.findById(id);
			if (booking == null) {
				logger.error("Unable to delete. Booking with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("Unable to delete. Booking with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			bookingService.deleteBookingById(id);
			return new ResponseEntity<Booking>(HttpStatus.NO_CONTENT);
		}
}
