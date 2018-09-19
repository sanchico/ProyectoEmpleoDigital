package com.proyecto.angularjs.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import com.proyecto.angularjs.models.Booking;
import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.repositories.BookingRepository;
import com.proyecto.angularjs.repositories.UserRepository;
import com.proyecto.angularjs.utils.BookingHelper;
import com.proyecto.angularjs.utils.UserHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    private BookingService bookingService;

    @Before
    public void setUp() throws Exception {
        bookingService = new BookingService(bookingRepository);
    }

    @Test
    public void shouldListAllBookings() throws Exception {
        stubServiceToReturnExistingBookings(10);
        List<Booking> response = bookingService.findAllBookings();
        assertNotNull(response);
        assertEquals(10, response.size());
        // verify booking was passed to BookingService
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    public void shouldGetBooking() throws Exception {
        stubServiceToReturnExistingBooking();
        Booking response = bookingService.findById(1l);
        assertNotNull(response);
        // verify booking was passed to BookingService
        verify(bookingRepository, times(1)).getOne(any(Long.class));
    }

   

    @Test
    public void shouldCreateBookingExisting() throws Exception {
        final Booking booking = BookingHelper.createBooking();
        stubServiceToReturnSaveBookingExisting();
        Booking response = bookingService.saveBooking(booking);
        assertNotNull(response);
        // verify booking was passed to BookingService
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    public void shouldUpdateBookingExisting() throws Exception {
        final Booking booking = BookingHelper.createBooking();
        stubServiceToReturnSaveBookingExisting();
        Booking response = bookingService.updateBooking(booking);
        assertNotNull(response);
        // verify booking was passed to BookingService
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    public void shouldDeleteBookingExisting() throws Exception {
    	bookingService.deleteBookingById(1l);
        // verify booking was passed to BookingService
        verify(bookingRepository, times(1)).deleteById(any(Long.class));
    }

    private void stubServiceToReturnExistingBookings(int howMany) {
        when(bookingRepository.findAll()).thenReturn(BookingHelper.createBookingList(howMany));
    }

    private void stubServiceToReturnExistingBooking() {
        when(bookingRepository.getOne(any(Long.class))).thenReturn(BookingHelper.createBooking());
    }


    private void stubServiceToReturnSaveBookingExisting() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(BookingHelper.createBooking());
    }
}