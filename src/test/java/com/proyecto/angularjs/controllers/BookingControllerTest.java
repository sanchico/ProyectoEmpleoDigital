package com.proyecto.angularjs.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import com.proyecto.angularjs.models.Booking;
import com.proyecto.angularjs.services.BookingService;
import com.proyecto.angularjs.utils.BookingHelper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    private BookingController bookingController;

    @Before
    public void setUp() throws Exception {//esto prueba
    	bookingController = new BookingController(bookingService);
    }


    private void stubServiceToReturnExistingBookings(int howMany) {
        when(bookingService.findAllBookings()).thenReturn(BookingHelper.createBookingList(howMany));
    }

    private void stubServiceToReturnExistingBooking() {
        when(bookingService.findById(any(Long.class))).thenReturn(BookingHelper.createBookingList(1).get(0));
    }

    private void stubServiceToReturnNotExistingBooking() {
        when(bookingService.findById(any(Long.class))).thenReturn(null);
    }

    private void stubServiceToReturnSaveBooking() {
        final Booking booking = BookingHelper.createBooking();
        when(bookingService.saveBooking(any(Booking.class))).thenReturn(booking);
    }


    private void stubServiceToReturnUpdateBooking() {
        final Booking booking = BookingHelper.createBooking();
        when(bookingService.updateBooking(any(Booking.class))).thenReturn(booking);
    }
}