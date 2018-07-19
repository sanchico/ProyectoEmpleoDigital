package com.proyecto.angularjs.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.proyecto.angularjs.models.Booking;
import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.repositories.BookingRepository;

@Component
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	public BookingService() {}
	
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	public List<Booking> findAllBookings() {
		return bookingRepository.findAll();
	}
	
	public Booking findById(Long id) {
		return bookingRepository.getOne(id);
	}
	
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	public Booking updateBooking(Booking booking){
		return saveBooking(booking);
	}

	public void deleteBookingById(Long id){
		bookingRepository.deleteById(id);
	}
}

