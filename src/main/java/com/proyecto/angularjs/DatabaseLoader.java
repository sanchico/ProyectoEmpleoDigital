package com.proyecto.angularjs;



import com.proyecto.angularjs.models.MotorBike;
import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.repositories.BookingRepository;
import com.proyecto.angularjs.repositories.MotorBikeRepository;
import com.proyecto.angularjs.repositories.UserRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private UserRepository userRepository;
	private MotorBikeRepository motorBikeRepository;


	@Autowired
	public DatabaseLoader(UserRepository userRepository, MotorBikeRepository motorBikeRepository,BookingRepository bookingRepository) {
		this.userRepository = userRepository;
		this.motorBikeRepository= motorBikeRepository;

	}
	
	//Carga de Usuarios y de motos por defecto al iniciar la aplicación.

	@Override
	public void run(String... strings) throws Exception {
		User admin = this.userRepository.save(new User("admin", "admin", "ROLE_ADMIN"));
        User demo = this.userRepository.save(new User("user", "user", "ROLE_USER"));
		User admin1 = this.userRepository.save(new User("teclado", "admin", "ROLE_ADMIN"));
        User demo2 = this.userRepository.save(new User("raton", "user", "ROLE_USER"));
		User admin2 = this.userRepository.save(new User("antonio", "admin", "ROLE_ADMIN"));
        User demo3 = this.userRepository.save(new User("paco", "user", "ROLE_USER"));
        
        MotorBike moto = this.motorBikeRepository.save(new MotorBike("Yamaha", "S67574", 37.993520, -1.135847, true));
        MotorBike moto1 = this.motorBikeRepository.save(new MotorBike("Honda", "S67574",37.993480, -1.129016, true));
        MotorBike moto2 = this.motorBikeRepository.save(new MotorBike("KTM", "S67574",37.989764, -1.133141, true));
        MotorBike moto3 = this.motorBikeRepository.save(new MotorBike("Suzuki", "S67574",37.989675, -1.130144, true));
        MotorBike moto4 = this.motorBikeRepository.save(new MotorBike("Aprillia", "S67574",37.990326, -1.127556, true));
        MotorBike moto5 = this.motorBikeRepository.save(new MotorBike("Jawa", "S67574",37.995198, -1.134534, true));
       
       
             
        SecurityContextHolder.clearContext();
	}
	
}