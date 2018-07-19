package com.proyecto.angularjs;


import com.proyecto.angularjs.models.Booking;
import com.proyecto.angularjs.models.MotorBike;
import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.repositories.BookingRepository;
import com.proyecto.angularjs.repositories.MotorBikeRepository;
import com.proyecto.angularjs.repositories.UserRepository;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private UserRepository userRepository;
	private MotorBikeRepository motorBikeRepository;
	private BookingRepository bookingRepository;

	@Autowired
	public DatabaseLoader(UserRepository userRepository, MotorBikeRepository motorBikeRepository,BookingRepository bookingRepository) {
		this.userRepository = userRepository;
		this.motorBikeRepository= motorBikeRepository;
		this.bookingRepository=bookingRepository;
	}
	
	

	@Override
	public void run(String... strings) throws Exception {
		User admin = this.userRepository.save(new User("admin", "admin", "ROLE_ADMIN"));
        User demo = this.userRepository.save(new User("user", "user", "ROLE_USER"));
        MotorBike moto = this.motorBikeRepository.save(new MotorBike("Yamaha", "S67574",(double) 0,(double)0, true));
        MotorBike moto1 = this.motorBikeRepository.save(new MotorBike("honda", "S67574",(double) 0,(double)0, true));
		
        Booking booking= this.bookingRepository.save(new Booking(admin.getId_user(), moto.getId_Motorbike(), new Date(), new Date(), 1));
        Booking booking2= this.bookingRepository.save(new Booking(demo.getId_user(), moto1.getId_Motorbike(), new Date(), new Date(), 2));
        
       

        
//        List<Booking> bookings=booking2.getMotorBike().getBookings();
//        
//        for(Booking book:bookings)
//        {
//        	
//        	System.out.println("dentro del for = "+book.toString());
//        }
//        
//        System.out.println("Bookings  "+bookings);
//        
        SecurityContextHolder.clearContext();
	}
	
}