package com.proyecto.angularjs.utils;

import java.util.LinkedList;
import java.util.List;

import com.proyecto.angularjs.models.Booking;

public class BookingHelper {
	
	


	public static List<Booking> createBookingList(int howMany) {
        List<Booking> list = new LinkedList<>();
        for (int i = 0; i < howMany; i++) {
            list.add(new Booking(null,null,null,null,null,null));
        }

		return list;
	}

	public static Booking createBooking() {
        Booking booking = new Booking(null,null,null,null,null,null);
		return booking;
	}

	
}
